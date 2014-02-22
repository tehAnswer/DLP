package ast.expression;

import java.util.List;

public class FunctionInvocation extends AbstractExpression {

	private List<Expression> param;
	private String functionName;
	
	public FunctionInvocation(int line, int column, String functionName, List<Expression> param) {
		super(line, column);
		this.param = param;
		this.functionName = functionName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(functionName).append("(");
		for (Expression exp: param)
			sb.append(exp).append(", ");
		return sb.delete(sb.length()-2, sb.length()-1).append(")").toString();
	}
	
	

}
