package ast.expression;

import ast.type.Type;

public class Cast extends AbstractExpression {
	
	private Type type;
	private Expression expression;
	

	public Cast(int line, int column, Type type, Expression expression) {
		super(line, column);
		this.type = type;
		this.expression = expression;
	}


	@Override
	public String toString() {
		return "(" + type + ")" + expression;
	}
	
	

}
