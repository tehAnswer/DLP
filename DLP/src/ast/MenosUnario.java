package ast;

public class MenosUnario implements Expression {
	
	public String operador;
	
	public MenosUnario (int linea, int colum, Expression exp) {
		this.operador = "-";
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
