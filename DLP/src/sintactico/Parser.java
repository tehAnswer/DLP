
package sintactico;

import lexico.Lexico;

/**
 * Clase Analizador Sintáctico (Parser).<br/>
 * Diseño de Lenguajes de Programación.<br/>
 * Escuela de Ingeniería Informática.<br/>
 * Universidad de Oviedo.<br/>
 * @author Francisco Ortin
 */

public class Parser {

    // * Tokens
    public final static int CTE_ENTERA = 257;
    public final static int ID = 258;
    public final static int MAIN = 259;
    public final static int READ = 260;
    public final static int WRITE = 261;
    public final static int WHILE = 262;
    public final static int IF = 263;
    public final static int ELSE = 264;
    public final static int INT = 265;
    public final static int DOUBLE = 266;
    public final static int CHAR = 267;    
    public final static int IGUALDAD = 268;    
    public final static int DISTINTO = 269;    
    public final static int MAYORIGUAL = 270;    
    public final static int MENORIGUAL = 271;    
    public final static int CTE_REAL = 272;    
    public final static int CTE_CARACTER = 273;    
    public final static int CTE_STRING = 274;  
    public final static int O = 275;
    public final static int Y = 276;
    public final static int VOID = 277;
    public final static int STRUCT = 278;
    public final static int RETURN = 279;

    // * Lexema del token devuelto (valor semántico)
    Object yylval;

    // * El yylval no es un atributo público
    public Object getYylval() {
    	return yylval;
    }
    public void setYylval(Object yylval) {
        this.yylval = yylval;
    }

    /**
    * Referencia al analizador léxico
    */
    private Lexico lexico;
    
    // * Constructor del Sintáctico
    public Parser(Lexico lexico) {
        // * El sintático conoce al léxico
        this.lexico = lexico;
        // * El léxico conoce al sintáctico (para el yylval)
        lexico.setParser(this);
    }
    
}