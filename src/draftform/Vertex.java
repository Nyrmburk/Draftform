package draftform;

import java.util.ArrayList;
import java.util.List;

public class Vertex extends Vec2 {
	
	private List<Curve> curves = new ArrayList<>();
	
	public Vertex(Vec2 vec) {
		
		super(vec);
	}

	public void addCurve(Curve curve) {
		
		curves.add(curve);
	}
	
	public void removeCurve(Curve curve) {
		
		curves.remove(curve);
	}

	public List<Curve> getCurves() {
		
		return curves;
	}
	
	public void replaceVertex(Vertex toReplace) {
		
		this.setPosition(toReplace);
		
		for (Curve curve : toReplace.getCurves())
			curve.replaceVertex(toReplace, this);
	}
}
