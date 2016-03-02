package draftform;

import java.util.ArrayList;

public class Vertex extends Vec2 {
	
	private ArrayList<Curve> curves = new ArrayList<>();
	
	public Vertex(Vec2 vec) {
		
		super(vec);
	}

	public void addCurve(Curve curve) {
		
		curves.add(curve);
	}
	
	public void removeCurve(Curve curve) {
		
		curves.remove(curve);
	}

	public Curve[] getCurves() {
		
		Curve[] array = new Curve[curves.size()];
		curves.toArray(array);
		
		return array;
	}
}
