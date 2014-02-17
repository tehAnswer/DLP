package ast;

import ast.expression.Variable;
import ast.type.Type;

public class VariableDefinition extends AbstractNodeAST {

	private Variable variable;
	private Type type;

	public VariableDefinition(int line, int column, Variable variable, Type type) {
		super(line, column);
		this.variable = variable;
		this.type = type;
	}

	@Override
	public String toString() {
		return type + " " + variable;
	}
	
	
	
	

}
