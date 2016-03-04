package draftform;

public abstract class Curve {
	
	public abstract Vertex getStart();
	public abstract Vertex getEnd();
	public abstract Vertex[] getControlPoints();
	public abstract void addVertex(Vertex v);
	public abstract void removeVertex(Vertex v);
	public abstract void replaceVertex(Vertex oldVert, Vertex newVert);
	
	public abstract float getLength();
	
	public Vec2[] linearize(int divisions) {

		Vec2[] points = new Vec2[divisions + 1];
		
		points[0] = getStart();
		for (int i = 1; i < divisions; i++) {
			
			points[i] = getPoint(((float) i) / divisions);
		}
		points[points.length-1] = getEnd();
		
		return points;
	}
	
	public abstract int recommendedSubdivisions();
	
	public abstract Vec2 getPoint(float t);
}
