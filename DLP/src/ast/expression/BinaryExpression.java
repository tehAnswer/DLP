package ast.expression;

public abstract class BinaryExpression extends AbstractExpression {
	
	protected Expression leftSide;
	protected Expression rightSide;
	protected String operator;


	public BinaryExpression(int line, int column, Expression leftSide,
			Expression rightSide, String operator) {
		super(line, column);
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		this.operator = operator;
	}
	
	
	
	

}
