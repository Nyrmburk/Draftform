package draftform;

import java.util.ArrayList;

public class Bezier extends Curve {

	private Vertex start;
	private Vertex end;
	private ArrayList<Vertex> points = new ArrayList<>();

	private Vec2 bezier(float t, ArrayList<? extends Vec2> points) {

		Vec2 curve = new Vec2();
		int n = points.size() - 1;
		for (int i = 0; i < points.size(); i++) {
			float scalar = (float) (binCoefficient(n, i) * Math.pow((1 - t), (n - i)) * Math.pow(t, i));
			curve = curve.add(points.get(i).multiply(scalar));
		}
		return curve;
	}

	private double binCoefficient(float n, float k) {

		double c;
		double numerator = n;
		double denominator = k;

		if (k == 0) {
			c = 1;
			return c;
		} else if (n == k) {
			c = 1;
			return c;
		}

		for (int i = 1; i < k; i++) {
			numerator *= n - i;
			denominator *= k - i;
		}

		c = numerator / denominator;

		return c;
	}

	public void addVertex(Vertex v) {

		if (start == null)
			start = v;
		points.add(v);
		v.addCurve(this);
		end = v;
	}

	public void removeVertex(Vertex v) {

		points.remove(v);

		if (points.isEmpty()) {
			start = null;
			end = null;
		} else {

			if (end == v)
				end = points.get(points.size() - 1);
			if (start == v)
				start = points.get(0);
		}
	}

	public int getOrder() {

		return points.size() - 1;
	}

	@Override
	public Vertex getStart() {

		return start;
	}

	@Override
	public Vertex getEnd() {

		return end;
	}

	public Vertex[] getControlPoints() {

		int size = points.size() - 2;
		if (size <= 0)
			return new Vertex[0];

		Vertex[] array = new Vertex[size];

		for (int i = 1; i < points.size() - 1; i++) {

			array[i - 1] = points.get(i);
		}

		return array;
	}

	@Override
	public float getLength() {

		Vec2[] points = linearize(this.points.size() * 2);

		float length = 0;
		for (int i = 1; i < points.length; i++)
			length += points[i - 1].distance(points[i]);

		return length;
	}

	@Override
	public Vec2 getPoint(float t) {

		return bezier(t, points);
	}

	@Override
	public int recommendedSubdivisions() {

		int divisions = points.size() * 5;
		if (points.size() <= 2)
			divisions = 1;
		return divisions;
	}
}
