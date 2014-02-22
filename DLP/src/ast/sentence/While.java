package ast.sentence;

import java.util.List;

import ast.expression.Expression;

public class While extends ConditionalSentence {

	private List<Sentence> sentences;

	public While(int line, int column, Expression condition, List<Sentence> sentences) {
		super(line, column, condition);
		this.sentences = sentences;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder ("while (").append(condition).append(") {");

		for (Sentence sentence : sentences)
			sb.append("\n\t").append(sentence).append(";");
		
		return sb.append("\n}").toString();
	}

	
	
}
