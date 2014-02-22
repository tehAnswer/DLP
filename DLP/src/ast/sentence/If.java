package ast.sentence;

import java.util.List;

import ast.expression.Expression;

public class If extends ConditionalSentence {

	private List<Sentence> ifBody;
	private List<Sentence> elseBody;
	

	public If(int line, int column, Expression condition, List<Sentence> ifBody, List<Sentence> elseBody) {
		super(line, column, condition);
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder ("if (").append(condition).append(")");
		sb.append(ifBody.size() == 1 ? "": "{");
		for (Sentence sentence : ifBody)
			sb.append("\n\t").append(sentence).append(";");
		sb.append(ifBody.size() == 1 ? "": "}");
		sb.append(elseBody.size() == 0 ? "": "\nelse");
		sb.append(elseBody.size() == 1 ? "": " {");
		for (Sentence sentence : elseBody)
			sb.append("\n\t").append(sentence).append(";");
		return sb.append(elseBody.size() == 1 ? "": "}").toString();
	}
	
	

}
