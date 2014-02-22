package ast.sentence;

import java.util.List;

import ast.expression.Expression;

/**
 * Represents the print operation.
 * 
 * @author Sergio
 * 
 */
public class Write extends SentenceWithListOfExpressions {


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
		super(line, column, expressions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("write ");
		for (Expression expression : expressions)
			sb.append(expression).append(", ");
		return sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1).toString();

	}

}
