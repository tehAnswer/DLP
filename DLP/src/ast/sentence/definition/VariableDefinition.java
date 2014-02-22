package ast.sentence.definition;

import ast.expression.Variable;
import ast.sentence.AbstractSentence;
import ast.type.Type;

public class VariableDefinition extends AbstractSentence implements Definition {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((variable == null) ? 0 : variable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VariableDefinition other = (VariableDefinition) obj;
		if (variable == null) {
			if (other.variable != null)
				return false;
		} else if (!variable.equals(other.variable))
			return false;
		return true;
	}

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

	public Variable getVariable() {
		return variable;
	}

	public Type getType() {
		return type;
	}
	
	
	
	

}
