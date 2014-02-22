package ast.sentence;

import java.util.List;

import ast.expression.Expression;

public class Invocation extends SentenceWithListOfExpressions {

	private String functionName;

	public Invocation(int line, int column, String nameFunction, List<Expression> expressions) {
		super(line, column, expressions);
		this.functionName = nameFunction;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(functionName).append("(");
		for (Expression exp: expressions)
			sb.append(exp).append(", ");
		return sb.delete(sb.length()-2, sb.length()-1).append(")").toString();
	}

	

}
