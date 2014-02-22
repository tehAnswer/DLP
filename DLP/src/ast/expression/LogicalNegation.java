package ast.expression;

public class LogicalNegation extends AbstractUnaryOperator {

	public LogicalNegation(int line, int column, Expression expression) {
		super(line, column, expression);
		this.operator = "!";
	}
	
	@Override
	public String toString() {
		return "!(" + expression + ")";
	}

}
