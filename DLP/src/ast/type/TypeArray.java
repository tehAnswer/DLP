package ast.type;

public class TypeArray implements Type {

	private Type type;
	private Integer size;

	@SuppressWarnings("unused")
	private TypeArray() {
	}

	public TypeArray(Type type, Integer size) {
		super();
		this.type = type;
		this.size = size;
	}

	@Override
	public String toString() {
		return type + "[" + size + "]";
	}

}
