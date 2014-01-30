package ast.sentence;

import java.util.List;

import ast.expression.Expression;

/**
 * Represents the print operation.
 * 
 * @author Sergio
 * 
 */
public class Write extends AbstractSentence {

	public List<Expression> expressions;

	/**
	 * Constructor
	 * 
	 * @param line
	 *            number of line where is located in the script.
	 * @param column
	 *            number of column where is located in the line.
	 * @param expressions
	 *            list of expresion to print.
	 */
	public Write(int line, int column, List<Expression> expressions) {
		super(line, column);
		this.expressions = expressions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Read {\n\t");
		for (Expression expression : expressions)
			sb.append(expression).append("\n");
		return sb.append("}").toString();

	}

}
