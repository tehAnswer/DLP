

%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexic.Lexic;
import java.io.Reader;
import ast.*;
%}

// * Declaraciones Yacc
%token CTE_ENTERA
%token CTE_CARACTER
%token CTE_REAL
%token CHAR
%token DOUBLE
%token INT
%token ID
%token DISTINTO
%token IGUALDAD
%token MAYORIGUAL
%token MENORIGUAL
%token STRUCT
%token MAIN
%token VOID
%token IF
%token ELSE
%token RETURN
%token READ
%token WRITE
%token WHILE
%token Y
%token O

%left "+"
%left "*"

%%
// * Gramática y acciones Yacc

//programa: listaDeclaraciones VOID MAIN () { listaSentencias } { this.ast = new Program ($1, $7); }
//expresion: expresion '+' expresion { $$= new LiteralEntero (1,1,(Expression)$1,"+",(Expression)$3); }
//         | expresion '*' expresion { $$= new LiteralEntero (1,1,(Expression)$1,"*",(Expression)$3); }
//expresion: CTE_ENTERA	{ $$= new LiteralEntero (1,1, getYylval()); }


programa: expresion
expresion: expresion '+' expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), $1, (String)$2 , $3); }
		| expresion '*' expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), $1, (String)$2 , $3); }
		| expresion "-" expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), $1, (String)$2 , $3); }
		| expresion "/" expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), $1, (String)$2 , $3); }
		| expresion "%" expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), $1, (String)$2 , $3); }
		| expresion "[" expresion "]"	{ $$=  new AccesoArray(lexico.getLine(), lexico.getColumn(), $1, $2); }
		| "-" expresion	{ $$= new UnaryNegation (lexico.getLine(), lexico.getColumn(), $S2); }
		| CTE_ENTERA	{ $$= new Literal (lexico.getLine(), lexico.getColumn(), (Integer), getYylval()); }
         ;
%%

// * Código Java
// * Se crea una clase "Parser", lo que aquí ubiquemos será:
//	- Atributos, si son variables
//	- Métodos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador léxico
private Lexic lexico;
public NodeAST ast;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
	    System.err.println ("Error Léxico en línea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
    System.err.println ("Error Sintáctico en línea " + lexico.getLine()+
		" y columna "+lexico.getColumn()+":\n\t"+error);
}

// * El yylval no es un atributo público
public Object getYylval() {
    	return yylval;
}
public void setYylval(Object yylval) {
        this.yylval = yylval;
}

// * Constructor del Sintáctico
public Parser(Lexic lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}
