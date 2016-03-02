package tools;

import draftform.Vec2;

public abstract class Tool {

	protected Toolkit toolkit;

	public abstract void start(Vec2 point);

	public abstract void modify(Vec2 point);

	public abstract void end();

	public abstract void reset();

	public void setToolkit(Toolkit toolkit) {

		this.toolkit = toolkit;
	}
}
