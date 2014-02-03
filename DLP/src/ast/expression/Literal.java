package ast.expression;

/**
 * Represent an integer value.
 * 
 * @author Sergio
 * 
 */
public class Literal extends AbstractExpression {

	public int value;

	/**
	 * Constructor
	 * 
	 * @param line
	 *            number of line where is located in the script.
	 * @param column
	 *            number of column where is located in the line.
	 * @param value
	 *            value of the integer.
	 */
	public Literal(int line, int column, int value) {
		super(line, column);
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

}
