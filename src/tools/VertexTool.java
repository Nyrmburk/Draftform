package tools;

import draftform.Vec2;
import draftform.Vertex;

public class VertexTool extends Tool {

	public Vertex currentVertex;

	public void start(Vec2 point) {
		currentVertex = new Vertex(point);
		toolkit.getDraftform().addVert(currentVertex);
		toolkit.clearSelection();
	}

	public void modify(Vec2 point) {
		if (currentVertex != null)
			currentVertex.setPosition(point);
	}

	public void end() {
		currentVertex = null;
	}

	public void reset() {
		end();
	}
}
