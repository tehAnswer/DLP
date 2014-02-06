
import sintactico.Parser;
import java.io.FileReader;
import java.io.IOException;

import lexic.Lexic;


public class Main {

	public static void main(String args[]) throws IOException {
	    if (args.length<1) {
	        System.err.println("Necesito el archivo de entrada.");
	        return;
	    }
	        
		FileReader fr=null;
		try {
			fr=new FileReader(args[0]);
		} catch(IOException io) {
			System.err.println("El archivo "+args[0]+" no se ha podido abrir.");
			return;
		}
		
		Lexic lexico = new Lexic(fr);
		Parser parser = new Parser(lexico);
		int token;
		while ((token=lexico.yylex())!=0) {
		    System.out.println("Linea: "+lexico.getLine()+
		            ", columna: "+lexico.getColumn()+
		            ", token: "+token+
		            ", valor semántico: "+parser.getYylval()+".");
		}
		    
	}

}