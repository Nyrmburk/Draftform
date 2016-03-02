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

	// TODO add a hitbox size for vertices
	// TODO add a snapping feature

	public Toolkit(Draftform draftform) {

		this.draftform = draftform;
	}

	public void setTool(Tool tool) {

		currentTool = tool;
		currentTool.setToolkit(this);
		currentTool.reset();
	}

	public void start(Vec2 point) {
		if (currentTool != null)
			currentTool.start(point);
	}

	public void modify(Vec2 point) {
		if (currentTool != null)
			currentTool.modify(point);
	}

	public void end() {
		if (currentTool != null)
			currentTool.end();
	}

	public void resetTool() {
		if (currentTool != null)
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

	public void clearSelection() {

		selectedCurves.clear();
		selectedVerts.clear();
	}

	public Draftform getDraftform() {

		return draftform;
	}
}
