package ast.type;

import java.util.List;

import ast.sentence.definition.FieldDefinition;

public class TypeStruct implements Type {
	
	List<FieldDefinition> fieldsStruct;

	public TypeStruct(List<FieldDefinition> fieldsStruct) {
		this.fieldsStruct = fieldsStruct;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder ("struct {");
		for (FieldDefinition field : fieldsStruct) 
			sb.append("\n\t").append(field).append(";");
		return sb.append("\n}").toString();
		
		}
	
	
	
	

}
