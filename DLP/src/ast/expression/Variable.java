package ast.expression;

/**
 * Reference to one variable. Every variable have a name.
 * 
 * @author Sergio
 * 
 */
public class Variable extends AbstractExpression {

	private String name;

	/**
	 * Constructor
	 * 
	 * @param line
	 *            number of line where is located in the script.
	 * @param column
	 *            number of column where is located in the line.
	 * @param name
	 *            name of the variable
	 */
	public Variable(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Variable {\n\tname: " + name + "\n}";
	}

}
