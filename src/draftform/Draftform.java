package draftform;

import java.util.ArrayList;
import java.util.List;

public class Draftform {

	private ArrayList<Curve> curves = new ArrayList<>();
	private ArrayList<Vertex> verts = new ArrayList<>();
	
	public void addCurve(Curve curve) {
		
		curves.add(curve);
	}
	
	public void addVert(Vertex vert) {
		
		verts.add(vert);
	}
	
	public List<Curve> getCurves() {
		
		return curves;
	}
	
	public List<Vertex> getVerts() {
		
		return verts;
	}
}
