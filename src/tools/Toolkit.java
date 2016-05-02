package tools;

import java.util.HashSet;
import java.util.Set;

import draftform.Curve;
import draftform.Draftform;
import draftform.Vec2;
import draftform.Vertex;

public class Toolkit {

	private Draftform draftform;

	public Tool currentTool;

	private Set<Vertex> selectedVerts = new HashSet<>();
	private Set<Curve> selectedCurves = new HashSet<>();

	private float snapRadius;
	private float gridInterval;
	private boolean snapGrid;
	private boolean snapPoint;

	public Toolkit(Draftform draftform) {

		this.draftform = draftform;
	}

	public void setTool(Tool tool) {

		currentTool = tool;
		
		if (currentTool == null)
			return;
		
		currentTool.setToolkit(this);
		currentTool.reset();
	}

	public void start(Vec2 point) {
		if (currentTool == null)
			return;

		Vertex vert = getSnap(point);
		if (vert == null)
			vert = new Vertex(point);
		
		if (!currentTool.usesSelection())
			clearSelection();

		currentTool.setActive(true);
		currentTool.start(vert);
	}

	public void modify(Vec2 point) {
		if (currentTool == null)
			return;

//		Vertex vert = getSnap(point);
//		if (vert == null)
		Vertex vert = new Vertex(point);

		currentTool.modify(vert);
	}

	public void end() {
		if (currentTool == null)
			return;

		currentTool.setActive(false);
		currentTool.end();
	}

	public void resetTool() {
		if (currentTool == null)
			return;

		if (!currentTool.isActive())
			currentTool.reset();
	}

	public void setSnapRadius(float radius) {

		snapRadius = radius;
	}

	public float getSnapRadius() {

		return snapRadius;
	}

	public Set<Vertex> getSelectedVerts() {

		return selectedVerts;
	}

	public void setSelectedVerts(Set<Vertex> selectedVerts) {

		this.selectedVerts = selectedVerts;
	}

	public Set<Curve> getSelectedCurves() {

		return selectedCurves;
	}

	public void setSelectedCurves(Set<Curve> selectedCurves) {

		this.selectedCurves = selectedCurves;
	}
	
	public void removeSelection() {
		
		for (Vertex vert : getSelectedVerts()) {
            
            for (Curve curve : vert.getCurves()){
                curve.removeVertex(vert);
                if (curve.getStart() == null)
                    getDraftform().getCurves().remove(curve);
            }
            
            getDraftform().getVerts().remove(vert);
        }
        
        getSelectedVerts().clear();
	}

	public void clearSelection() {

		selectedCurves.clear();
		selectedVerts.clear();
	}

	public Draftform getDraftform() {

		return draftform;
	}

	public void setSnapToPoints(boolean snapPoint) {

		this.snapPoint = snapPoint;
	}

	public boolean doesSnapToPoints() {

		return snapPoint;
	}
	
	public void setSnapToGrid(boolean snapGrid) {

		this.snapGrid = snapGrid;
	}

	public boolean doesSnapToGrid() {

		return snapGrid;
	}

	public Vertex getSnap(Vec2 point) {

		if (doesSnapToPoints()) {
			
			for (Vertex vert : getDraftform().getVerts()) {

				if (point.distance(vert) < getSnapRadius())
					return vert;
			}

			for (Curve curve : getDraftform().getCurves()) {

				if (point.distance(curve.getStart()) < getSnapRadius())
					return curve.getStart();

				if (point.distance(curve.getEnd()) < getSnapRadius())
					return curve.getEnd();

//				for (Vertex vert : curve.getControlPoints()) {
//
//					if (point.distance(vert) < getSnapRadius())
//						return vert;
//				}
			}
		}
		
		if (doesSnapToGrid()) {
			
			Math.IEEEremainder(point.getX(), gridInterval);
		}

		return null;
	}
}
