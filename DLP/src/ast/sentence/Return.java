package ast.sentence;

import ast.expression.Expression;

public class Return extends AbstractSentence {

	private Expression returnValue;

	public Return(int line, int column, Expression returnValue) {
		super(line, column);
		this.returnValue = returnValue;
	}

	@Override
	public String toString() {
		return "return " + returnValue;
	}
	
	

}
