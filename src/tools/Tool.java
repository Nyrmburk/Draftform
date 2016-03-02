package tools;

import draftform.Vertex;

public abstract class Tool {

	protected Toolkit toolkit;

	public abstract void start(Vertex point);

	public abstract void modify(Vertex point);

	public abstract void end();

	public abstract void reset();

	public void setToolkit(Toolkit toolkit) {

		this.toolkit = toolkit;
	}
}
