package tools;

import draftform.Vertex;

public abstract class Tool {

	protected Toolkit toolkit;
	
	private boolean active;

	public abstract void start(Vertex point);

	public abstract void modify(Vertex point);

	public abstract void end();

	public abstract void reset();
	
	public abstract boolean usesSelection();

	public void setToolkit(Toolkit toolkit) {

		this.toolkit = toolkit;
	}
	
	public boolean isActive() {
		
		return active;
	}
	
	protected void setActive(boolean active) {
		
		this.active = active;
	}
}
