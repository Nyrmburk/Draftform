package tools;

import draftform.Bezier;
import draftform.Vec2;
import draftform.Vertex;

public class BezierTool extends Tool {

	Bezier curve;
	Vec2 currentHandle;
	
	@Override
	public void start(Vec2 point) {
		
		toolkit.clearSelection();
		
		Vertex vert = new Vertex(point);
		currentHandle = vert;
		
		if (curve == null) {
			curve = new Bezier();
			toolkit.getDraftform().addCurve(curve);
		}
		
		curve.addVertex(vert);
	}

	@Override
	public void modify(Vec2 point) {
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
}
