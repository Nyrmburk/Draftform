package draftform;

public abstract class ReactiveVertex extends Vertex {

	public ReactiveVertex(Vec2 vec) {
		super(vec);
	}

	public abstract void onMove(Vec2 lastPosition);
	
	@Override
	public void setPosition(Vec2 vec) {
		
		Vec2 previous = new Vec2(this);
		super.setPosition(vec);
		onMove(previous);
	}
	
	@Override
	public void translate(Vec2 vec) {
		
		Vec2 previous = new Vec2(this);
		super.translate(vec);
		onMove(previous);
	}
}
