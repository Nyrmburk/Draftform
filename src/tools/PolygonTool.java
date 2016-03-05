package tools;

import draftform.Bezier;
import draftform.Vec2;
import draftform.Vertex;

public class PolygonTool extends Tool {

	Vec2 center;
	Vertex[] verts;

	int sides;

	public PolygonTool(int sides) {

		this.sides = sides;
	}

	@Override
	public void start(Vertex point) {
		center = point;

		Vec2[] points = polygon(center, 1, sides);

		verts = new Vertex[sides];

		for (int i = 0; i < sides; i++)
			verts[i] = new Vertex(points[i]);

		for (int i = 0; i < sides; i++) {

			Bezier curve = new Bezier();

			curve.addVertex(verts[i]);
			curve.addVertex(verts[(i + 1) % sides]);

			toolkit.getDraftform().getCurves().add(curve);
		}
	}

	private static Vec2[] polygon(Vec2 center, float radius, int sides) {

		if (sides < 3)
			sides = 3;
		
		Vec2[] points = new Vec2[sides];

		double angle;
		for (int i = 0; i < sides; i++) {

			angle = i * 2 * Math.PI / sides;

			float x = (float) (center.getX() + radius * Math.cos(angle));
			float y = (float) (center.getY() + radius * Math.sin(angle));

			points[i] = new Vec2(x, y);
		}

		return points;
	}

	@Override
	public void modify(Vertex point) {

		Vec2[] points = polygon(center, center.distance(point), sides);

		for (int i = 0; i < sides; i++)
			verts[i].setPosition(points[i]);
	}

	@Override
	public void end() {
	}

	@Override
	public void reset() {
	}
}
