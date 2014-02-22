package ast.sentence;

import ast.expression.Expression;

public abstract class ConditionalSentence extends AbstractSentence {

	protected Expression condition;

	public ConditionalSentence(int line, int column, Expression condition) {
		super(line, column);
		this.condition = condition;
	}

}
