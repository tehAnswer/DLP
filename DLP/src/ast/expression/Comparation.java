package ast.expression;

public class Comparation extends BinaryExpression {

	public Comparation(int line, int column, Expression leftSide,
			String operator, Expression rightSide) {
		super(line, column, leftSide, rightSide, operator);
	}
	
	@Override
	public String toString() {
		return leftSide + " " + operator + " " + rightSide;
	}

}
