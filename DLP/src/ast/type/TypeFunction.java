package ast.type;

import java.util.Collections;
import java.util.List;

import ast.sentence.definition.VariableDefinition;

public class TypeFunction implements Type {
	
	private Type returnType;
	private List<VariableDefinition> param;
	
	public TypeFunction(Type returnType, List<VariableDefinition> param) {
		this.returnType = returnType;
		this.param = param;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder (returnType.toString()).append("(");
		for (VariableDefinition vd: param)
			sb.append(vd).append(", ");
		sb.delete(sb.length()-2, sb.length()-1);
		return sb.append(")").toString();
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<VariableDefinition> getParam() {
		return Collections.unmodifiableList(param);
	}
	
	

}
