package tools;

import java.util.LinkedList;
import java.util.List;

import draftform.Bezier;
import draftform.ReactiveVertex;
import draftform.Vec2;
import draftform.Vertex;

public class PenTool extends Tool {
	
	EndpointVertex startVert;
	Bezier currentCurve;
	
	Bezier previousCurve;
	BezierVertex previousModifyVert;
	BezierVertex nextModifyVert;
	
	boolean modified = false;
	
	@Override
	public void start(Vertex point) {

		if (point instanceof EndpointVertex) {
			startVert = (EndpointVertex) point;
			startVert.setCoupled(true);
		} else {
			
			startVert = new EndpointVertex(point);
			startVert.replaceVertex(point);
		}
		
		if (nextModifyVert != null)
			previousCurve.addVertex(nextModifyVert);
		
		currentCurve = new Bezier();
		currentCurve.addVertex(startVert);
		toolkit.getDraftform().getCurves().add(currentCurve);
		
		if (previousCurve != null)
			previousCurve.addVertex(startVert);
	}

	@Override
	public void modify(Vertex point) {
		
		if (!modified && point.distance(startVert) > toolkit.getSnapRadius()) {
			
			nextModifyVert = startVert.getHalf();
			if (nextModifyVert == null) {
				nextModifyVert = new BezierVertex(point, startVert);
				startVert.addBezierVertex(nextModifyVert);
			}
			modified = true;
			
			if (previousCurve != null) {
				previousModifyVert = new BezierVertex(new Vertex(startVert), startVert);
				previousCurve.removeVertex(startVert);
				previousCurve.addVertex(previousModifyVert);
				previousCurve.addVertex(startVert);
				startVert.addBezierVertex(previousModifyVert);
				BezierVertex.pair(previousModifyVert, nextModifyVert);
			}
		}
		
		if (modified) {
			
			nextModifyVert.setPosition(point);
		}
	}

	@Override
	public void end() {
		
		previousCurve = currentCurve;
		previousModifyVert = null;
		startVert.setCoupled(false);
		startVert = null;
		currentCurve = null;
		modified = false;
	}

	@Override
	public void reset() {
		
		previousModifyVert = null;
		startVert = null;
		currentCurve = null;
		modified = false;
		previousCurve = null;
		nextModifyVert = null;
		startVert = null;
	}
	
	public boolean usesSelection() {
		
		return false;
	}
	
	private class EndpointVertex extends ReactiveVertex {

		List<BezierPair> pairs = new LinkedList<>();
		
		private boolean coupled = true;
		private boolean independent = false;
		
		public EndpointVertex(Vec2 vec) {
			super(vec);
		}

		@Override
		public void onMove(Vec2 lastPosition) {
			
			Vec2 delta = this.subtract(lastPosition);
			
			for (BezierPair pairs : pairs)
				pairs.translate(delta);
		}
		
		public void addBezierVertex(BezierVertex vert) {
			
			for (BezierPair pair : pairs) {
				
				if (pair.isHalf()) {
					pair.setOtherHalf(vert);
					return;
				}
			}
			
			pairs.add(new BezierPair(vert));
		}
		
		public BezierVertex getHalf() {
			
			for (BezierPair pair : pairs) {
				
				if (pair.isHalf()) 
					return pair.pair1;
			}
			return null;
		}
		
		public boolean isCoupled() {
			
			return coupled;
		}
		
		public void setCoupled(boolean coupled) {
			
			this.coupled = coupled;
		}
		
		public boolean isIndependent() {
			
			return independent;
		}
		
		public void setIndependent(boolean independent) {
			
			this.independent = independent;
		}
		
		private class BezierPair {
			
			BezierVertex pair1;
			BezierVertex pair2;
			
			public BezierPair (BezierVertex pair1, BezierVertex pair2) {
				
				this.pair1 = pair1;
				this.pair2 = pair2;
			}
			
			public BezierPair (BezierVertex pair) {
				
				this(pair, null);
			}
			
			public boolean isHalf() {
				
				return pair2 == null;
			}
			
			public void setOtherHalf(BezierVertex pair) {
				
				pair2 = pair;
			}
			
			public void translate(Vec2 vec) {
				
				pair1.disable = true;
				pair1.translate(vec);
				pair1.disable = false;
				
				if (!isHalf()) {
					pair2.disable = true;
					pair2.translate(vec);
					pair2.disable = false;
				}
			}
		}
	}
	
	private static class BezierVertex extends ReactiveVertex {

		private EndpointVertex parent;
		private BezierVertex pair;
		private boolean disable = false;
		
		public BezierVertex(Vec2 vec, EndpointVertex parent) {
			super(vec);
			this.parent = parent;
		}
		
		private void setPair(BezierVertex pair) {
			
			this.pair = pair;
		}
		
		public static void pair(BezierVertex pair1, BezierVertex pair2) {
			
			pair1.setPair(pair2);
			pair2.setPair(pair1);
		}

		@Override
		public void onMove(Vec2 lastPosition) {
			
			if (disable || pair == null || parent.isIndependent())
				return;
			
			Vec2 delta = parent.subtract(this);
			
			if (!parent.isCoupled()) {
				
				float length = delta.distance();
				if (length == 0)
					return;
				delta = delta.divide(length);
				delta = delta.multiply(pair.distance(parent));
			}
			pairMove(parent.add(delta));
		}
		
		protected void pairMove(Vec2 position) {
			
			pair.disable = true;
			pair.setPosition(position);
			pair.disable = false;
		}
	}
}
