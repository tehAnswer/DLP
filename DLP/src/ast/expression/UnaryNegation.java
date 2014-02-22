package ast.expression;

/**
 * Represent the unary operation of negation.
 * 
 * @author Sergio
 * 
 */
public class UnaryNegation extends AbstractUnaryOperator {

	

	/**
	 * Constructor
	 * 
	 * @param line
	 *            number of line where is located in the script.
	 * @param column
	 *            number of column where is located in the line.
	 * @param expression
	 *            expression which is negated.
	 */
	public UnaryNegation(int line, int column, Expression expression) {
		super(line, column, expression);
		this.operator = "-";
	}

	@Override
	public String toString() {
		return "-(" + expression + ")";
	}

}
