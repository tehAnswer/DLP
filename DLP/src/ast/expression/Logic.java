package ast.expression;

public class Logic extends BinaryExpression {

	public Logic(int line, int column, Expression leftSide,
			String operator, Expression rightSide) {
		super(line, column, leftSide, rightSide, operator);
	}
	
	@Override
	public String toString() {
		return leftSide + " " + operator + " " + rightSide;
	}

}
