package ast.expression;

/**
 * Represent an arithmetic operation. Every arithmetic operation can be modeled
 * with two expressions in a recursive way.
 * 
 * @author Sergio
 * 
 */
public class Arithmetic extends AbstractExpression {

	private Expression leftSide;
	private Expression rightSide;
	private String operator;

	/**
	 * Constructor
	 * 
	 * @param line
	 *            number of line where is located in the script.
	 * @param column
	 *            number of line where is located in the line.
	 * @param leftSide
	 *            left side of the arithmetic expression. For example, in the
	 *            expression a+b, the leftSide expression is a.
	 * @param operator
	 *            operator of the arithmetic expression. For example, in the
	 *            expression a+b, the operator is +.
	 * @param rightSide
	 *            right side of the arithmetic expression. For example, in the
	 *            expression a+b the rightSide expression is b.
	 */
	public Arithmetic(int line, int column, Expression leftSide,
			String operator, Expression rightSide) {
		super(line, column);
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		this.operator = operator;
	}

	@Override
	public String toString() {
		return leftSide + " " + operator + " " + rightSide;
	}

}
