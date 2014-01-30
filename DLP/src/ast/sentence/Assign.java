package ast.sentence;

import ast.expression.Expression;

/**
 * Represents an assignment.
 * 
 * @author Sergio
 * 
 */
public class Assign extends AbstractSentence {

	public Expression leftSide;
	public Expression rightSide;

	/**
	 * Constructor
	 * 
	 * @param line
	 *            number of line where is located in the script.
	 * @param column
	 *            number of column where is located in the line.
	 * @param leftSide
	 *            one variable.
	 * @param rightSide
	 *            one expression.
	 */
	public Assign(int line, int column, Expression leftSide,
			Expression rightSide) {
		super(line, column);
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	@Override
	public String toString() {
		return "Assign {\n\tleftSide: " + leftSide + "\n\trightSide="
				+ rightSide + "\n}";
	}

}
