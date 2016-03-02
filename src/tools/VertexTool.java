package tools;

import draftform.Vertex;

public class VertexTool extends Tool {

	public Vertex currentVertex;

	public void start(Vertex point) {
		currentVertex = new Vertex(point);
		toolkit.getDraftform().getVerts().add(currentVertex);
		toolkit.clearSelection();
	}

	public void modify(Vertex point) {
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
