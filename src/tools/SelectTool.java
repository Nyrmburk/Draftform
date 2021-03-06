package tools;

import java.util.Set;

import draftform.Curve;
import draftform.Vec2;
import draftform.Vertex;

public class SelectTool extends Tool {

	private boolean single;
	
	private Vec2 start;
	private boolean movedPastSnap = false;

	@Override
	public void start(Vertex point) {
		
		start = point;
		
		for (Vertex vert : toolkit.getDraftform().getVerts()) {

			if (select(point, vert))
				return;
		}
		
		for (Curve curve : toolkit.getDraftform().getCurves()) {

			if (select(point, curve.getStart()))
				return;
			
			if (select(point, curve.getEnd()))
				return;
			
			for (Vertex vert : curve.getControlPoints()) {

				if (select(point, vert))
					return;
			}
		}
		
		if (single)
			toolkit.clearSelection();
	}
	
	private boolean select(Vertex point, Vertex test) {
		
		boolean found = false;
		if (test.distance(point) <= toolkit.getSnapRadius()) {

			if (single) {

				Set<Vertex> singleSelection = toolkit.getSelectedVerts();
				singleSelection.clear();
				singleSelection.add(test);
				toolkit.setSelectedVerts(singleSelection);
				found = true;
			} else {

				Set<Vertex> newSelection = toolkit.getSelectedVerts();
				newSelection.add(test);
				toolkit.setSelectedVerts(newSelection);
				found = true;
			}
		}
		
		return found;
	}

	@Override
	public void modify(Vertex point) {
		
		if (movedPastSnap || start.distance(point) > toolkit.getSnapRadius()) {
			movedPastSnap = true;
			
			for (Vec2 vert : toolkit.getSelectedVerts()) {
				
				vert.translate(point.subtract(start));
			}
			
			start = point;
		}
	}

	@Override
	public void end() {
		movedPastSnap = false;
	}

	@Override
	public void reset() {
		setSingleSelection(true);
		start = null;
		movedPastSnap = false;
	}

	public void setSingleSelection(boolean single) {

		this.single = single;
	}
	
	public boolean usesSelection() {
		
		return true;
	}
}
