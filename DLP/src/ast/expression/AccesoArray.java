package ast.expression;

public class AccesoArray extends AbstractExpression {

	private Expression nameArray;
	private Expression index;

	public AccesoArray(int line, int column, Expression nameArray,
			Expression index) {
		super(line, column);
		this.nameArray = nameArray;
		this.index = index;
	}

	@Override
	public String toString() {
		return nameArray + " [" + index + "]";
	}
	
	
}
