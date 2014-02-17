package ast.expression;

import ast.AbstractNodeAST;

/**
 * Class created by code refactoring. Implements the Expression interface.
 * 
 * @author Sergio
 *
 */
public class AbstractExpression extends AbstractNodeAST implements Expression  {

	public AbstractExpression(int line, int column) {
		super(line, column);
	}


}
