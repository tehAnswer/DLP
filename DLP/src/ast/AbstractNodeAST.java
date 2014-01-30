package ast;

/**
 * Class created by code refactoring. Implements the NodeAST interface.
 * 
 * @author Sergio
 * 
 */
public abstract class AbstractNodeAST implements NodeAST {

	private int line;
	private int column;

	/**
	 * Constructor of every node in the abstract syntax tree.
	 * 
	 * @param line
	 *            number of line where the token is located in the script.
	 * @param column
	 *            number of column where the token is located in the line.
	 */
	public AbstractNodeAST(int line, int column) {
		this.line = line;
		this.column = column;
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public int getColumn() {
		return column;
	}

}
