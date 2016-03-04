package tools;

import draftform.Bezier;
import draftform.Vec2;
import draftform.Vertex;

public class PenTool extends Tool {

	Vertex startVert;
	Bezier currentCurve;
	
	Bezier previousCurve;
	Vertex previousModifyVert;
	
	boolean modified = false;
	Vec2 modifyPoint;
	
	@Override
	public void start(Vertex point) {

		startVert = point;
		currentCurve = new Bezier();
		currentCurve.addVertex(point);
		toolkit.getDraftform().getCurves().add(currentCurve);
		
		if (previousCurve != null) {
			
//			previousModifyVert = new Vertex(point);
//			previousCurve.addVertex(previousModifyVert);
			previousCurve.addVertex(point);
		}
	}

	@Override
	public void modify(Vertex point) {
		
		if (modified) {
			
			modifyPoint.setPosition(point);
			
			if (previousModifyVert != null) {
				
				Vec2 delta = startVert.subtract(modifyPoint);
				previousModifyVert.setPosition(startVert.add(delta));
			}
		} else if (point.distance(startVert) > toolkit.getSnapRadius()) {
			
			currentCurve.addVertex(point);
			modifyPoint = point;
			modified = true;
			
			if (previousCurve != null) {
				previousModifyVert = new Vertex(startVert);
				previousCurve.removeVertex(startVert);
				previousCurve.addVertex(previousModifyVert);
				previousCurve.addVertex(startVert);
			}
		}
	}

	@Override
	public void end() {
		
		previousCurve = currentCurve;
		previousModifyVert = null;
		modifyPoint = null;
		startVert = null;
		currentCurve = null;
		modifyPoint = null;
		modified = false;
	}

	@Override
	public void reset() {
		
		end();
		previousCurve = null;
	}
}
