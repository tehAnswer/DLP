package ast;

public class Aritmetica implements Expression {
	
	private Expression izq, der;
	private String op;
	
	public Aritmetica (int linea, int column, Expression izq, String op, Expression der) {
		
	}

	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumn() {
		// TODO Auto-generated method stub
		return 0;
	}

}
