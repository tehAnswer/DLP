package ast;

import java.util.List;

public class Program implements NodeAST {
	
	public List<Sentence> sentences;

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
	
	public Program (int numLinea, int numColumk, List<Sentence> sentences) {
		
	}

}
