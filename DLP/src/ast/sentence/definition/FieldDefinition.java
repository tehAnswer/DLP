package ast.sentence.definition;

import ast.expression.Variable;
import ast.type.Type;

public class FieldDefinition extends AbstractDefinition {

	private Variable variable;
	private Type type;

	public FieldDefinition(int line, int column, Type type, Variable variable) {
		super(line, column);
		this.variable = variable;
		this.type = type;
	}

	@Override
	public String toString() {
		return type + " " + variable;
	}

	public Variable getVariable() {
		return variable;
	}

	public Type getType() {
		return type;
	}
	
	
	
	

}
