package ast.expression;

/** Class created by refactoring. Represents the unary operators.
 * @author Sergio
 *
 */
public abstract class AbstractUnaryOperator extends AbstractExpression {
	
	protected Expression expression;
	protected String operator;

	public AbstractUnaryOperator(int line, int column, Expression expression) {
		super(line, column);
		this.expression = expression;
	}

}
