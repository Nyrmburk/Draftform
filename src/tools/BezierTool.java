package tools;

import draftform.Bezier;
import draftform.Vec2;
import draftform.Vertex;

public class BezierTool extends Tool {

	Bezier curve;
	Vec2 currentHandle;
	
	@Override
	public void start(Vertex point) {
		
		currentHandle = point;
		
		if (curve == null) {
			curve = new Bezier();
			toolkit.getDraftform().getCurves().add(curve);
		}
		
		curve.addVertex(point);
	}

	@Override
	public void modify(Vertex point) {
		if (currentHandle != null)
			currentHandle.setPosition(point);
	}

	@Override
	public void end() {
		currentHandle = null;
	}

	@Override
	public void reset() {
		curve = null;
		currentHandle = null;
	}
	
	public boolean usesSelection() {
		
		return false;
	}
}
