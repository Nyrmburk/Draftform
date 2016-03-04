package tools;

import draftform.Arc;
import draftform.Vec2;
import draftform.Vertex;

public class ArcTool extends Tool {

	Arc curve;
	Vec2 currentHandle;
	
	@Override
	public void start(Vertex point) {
		
		toolkit.clearSelection();
		currentHandle = point;
		
		if (curve == null) {
			curve = new Arc();
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
}
