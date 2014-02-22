package ast.expression;

public class RealNumber extends AbstractExpression {

	private double value;

	public RealNumber(int line, int column, double value) {
		super(line, column);
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
