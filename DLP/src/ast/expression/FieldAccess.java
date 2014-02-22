package ast.expression;

public class FieldAccess extends AbstractExpression {

	private Expression object;
	private Variable field;

	public FieldAccess(int line, int column, Expression object, Variable field) {
		super(line, column);
		this.object = object;
		this.field = field;
	}
	
	@Override
	public String toString() {
		return object + "." + field;
	}

}
