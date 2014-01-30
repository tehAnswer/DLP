package ast;

import java.util.List;

public class Read implements Sentence {
	
	
	private List<Expression> exp;
	public Read (int linea, int colum, List<Expression> exp) {
		
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
