package ast;

public class Assign implements Sentence {

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
	
	public Expression der, izq;
	
	public Assign (int lin, int col, Expression der, Expression izq) {
		this.der = der;
		this.izq = izq;
	}

}
