package ast;

import ast.type.Type;

public class VariableDefinition extends AbstractNodeAST {

	private String name;
	private Type type;

	public VariableDefinition(int line, int column, String name, Type type) {
		super(line, column);
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return type + " " + name;
	}
	
	
	
	

}
