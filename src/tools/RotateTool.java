package tools;

import draftform.Vec2;
import draftform.Vertex;

public class RotateTool extends Tool {

	private Vec2 center;
	private float lastAngle = 0;
	
	@Override
	public void start(Vertex point) {
		center = point;
	}

	@Override
	public void modify(Vertex point) {

		Vec2 delta = point.subtract(center);
		float newAngle = (float) Math.atan2(delta.getY(), delta.getX());
		float deltaAngle = newAngle - lastAngle;
		lastAngle = newAngle;
		
		for (Vec2 vec : toolkit.getSelectedVerts())
			vec.rotate(center, deltaAngle);
	}

	@Override
	public void end() {
	}

	@Override
	public void reset() {
	}
	
	public boolean usesSelection() {
		
		return true;
	}
}
