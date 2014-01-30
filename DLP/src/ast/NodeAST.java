package ast;

/**
 * Represent a node of the abstract syntax tree. Every node stores the line and
 * column where they are located in the file.
 * 
 * @author Sergio
 * 
 */
public interface NodeAST {

	/**
	 * Getter method of the number of line where is located in the file. The
	 * first line corresponds with the number one, the second with the number
	 * two and so on.
	 * 
	 * @return the number of line where the token is located.
	 */
	public int getLine();

	/**
	 * Getter method of the column number. The first token corresponds with the
	 * number one, the second with number two and so on.
	 * 
	 * @return the number of column where the token is located in the line.
	 */
	public int getColumn();

}
