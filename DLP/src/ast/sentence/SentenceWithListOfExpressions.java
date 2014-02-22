package ast.sentence;

import java.util.List;

import ast.expression.Expression;

public class SentenceWithListOfExpressions extends AbstractSentence {
	
	protected List<Expression> expressions;

	public SentenceWithListOfExpressions(int line, int column, List<Expression> expressions) {
		super(line, column);
		this.expressions = expressions;
	}

	
}
