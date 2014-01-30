package ast;

import java.util.List;

public class Write implements Sentence {

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
	
	public List<Expression> expresiones;
	
	public Write (int linea, int column, List<Expression> expresiones) {
		this.expresiones= expresiones;
	}

}
