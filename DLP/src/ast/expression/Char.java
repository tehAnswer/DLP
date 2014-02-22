package ast.expression;

public class Char extends AbstractExpression {

	private String character;

	public Char(int line, int column, String character) {
		super(line, column);
		this.character = character;
	}

	@Override
	public String toString() {
		return String.valueOf(character);
	}

	
}
