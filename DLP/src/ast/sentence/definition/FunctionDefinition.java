package ast.sentence.definition;


import java.util.List;

import ast.sentence.Sentence;
import ast.type.TypeFunction;

public class FunctionDefinition extends AbstractDefinition {

	private TypeFunction type;
	private String name;
	@SuppressWarnings("unused")
	private List<Sentence> sentences;
	

	public FunctionDefinition(int line, int column, TypeFunction type, String name, List<Sentence> sentences) {
		super(line, column);
		this.type = type;
		this.name = name;
		this.sentences = sentences;
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder ();
		sb.append(type.getReturnType()).append(" ");
		sb.append(name).append(" (");
		for (VariableDefinition vd: type.getParam())
			sb.append(vd).append(", ");
		sb.delete(sb.length()-2, sb.length()-1);
		sb.append("){");
		/*for (Sentence sentence: sentences)
			sb.append("\n\t").append(sentence).append(";");*/
		sb.append("\n}");
		return sb.toString();
	}

	
	
}
