package draftform;

public class Arc extends Curve {

	private Vertex start;
	private Vertex end;
	private Vertex center;
	
	private static Vec2 ellipse(Vec2 pt1, Vec2 pt2) {
		
		Vec2 q = pt1.multiply(pt1);
		Vec2 p = pt2.multiply(pt2);
		
		float ratio = -q.getY() / p.getY();
		Vec2 r = p.multiply(ratio);
		
		float width = (float) Math.sqrt((q.getX() + r.getX()) / (1 + ratio));
		
		ratio = -q.getX() / p.getX();
		Vec2 s = p.multiply(ratio);
		
		float height = (float) Math.sqrt((q.getY() + s.getY()) / (1 + ratio));
		
		return new Vec2(width, height);
	}

	@Override
	public Vertex getStart() {
		return start;
	}

	@Override
	public Vertex getEnd() {
		return end;
	}

	@Override
	public Vertex[] getControlPoints() {

		Vertex[] control;

		if (center == null) {

			control = new Vertex[0];
		} else {

			control = new Vertex[] { center };
		}

		return control;
	}

	@Override
	public void addVertex(Vertex v) {

		v.addCurve(this);
		
		if (start == null)
			start = v;

		if (end == null)
			end = v;

		if (start == end) {
			end = v;
		} else {
			center = v;
		}
	}

	@Override
	public void removeVertex(Vertex v) {

		if (start == v)
			start = center;
		if (end == v)
			end = center;
	}
	
	@Override
	public void replaceVertex(Vertex oldVert, Vertex newVert) {
		
		newVert.addCurve(this);
		
		if (start == oldVert)
			start = newVert;

		if (end == oldVert)
			end = newVert;

		if (center == oldVert) {
			center = newVert;
		}
	}

	@Override
	public float getLength() {
		return 0;
	}

	@Override
	public int recommendedSubdivisions() {
		return 20;
	}

	@Override
	public Vec2 getPoint(float t) {

		if (center != null) {

			Vec2 relativeStart = start.subtract(center);
			Vec2 relativeEnd = end.subtract(center);
			
			Vec2 size = ellipse(relativeStart, relativeEnd);
			
			float start = (float) Math.atan2(relativeStart.getY() / size.getY(), relativeStart.getX() / size.getX());
			float end = (float) Math.atan2(relativeEnd.getY() / size.getY(), relativeEnd.getX() / size.getX());
			float eval;
			float range = (end - start);
			if (range > Math.PI) {
				range = (float) Math.PI * 2 - range;				
				eval = start - range * t;
			} else if (range < -Math.PI) {
				range = (float) Math.PI * 2 + range;				
				eval = start + range * t;
			} else {
				eval = start + range * t;
			}

			Vec2 point = new Vec2((float) (size.getX() * Math.cos(eval)), (float) (size.getY() * Math.sin(eval)));
			
			if (Float.compare(point.getX(), Float.NaN) != 0 && Float.compare(point.getY(), Float.NaN) != 0)
				return point.add(center);
		}
		
		return (start.multiply(1 - t).add(end.multiply(t)));
	}
}
