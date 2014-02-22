package ast.sentence.definition;

import ast.AbstractNodeAST;

public abstract class AbstractDefinition extends AbstractNodeAST implements Definition {

	public AbstractDefinition(int line, int column) {
		super(line, column);
	}

}
