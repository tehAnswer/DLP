

%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexic.Lexic;
import java.io.Reader;
import ast.*;
import ast.expression.*;
import ast.sentence.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;
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

%left "["
%left "+" "-"
%left "*" "%"
%left "/"
%left MENOSUNARIO

%%
// * Gramática y acciones Yacc



programa: listaDeclaraciones VOID MAIN "(" ")" "{" listaSentencias "}" { this.ast = new Program (lexico.getLine(), lexico.getColumn(), (ArrayList<Sentence>)$7, (ArrayList<VariableDefinition>)$1); };

declaracion:	tipo listaIndentificadores ";"; { ArrayList<VariableDefinition> xx = new ArrayList<VariableDefinition>(); for (Variable v: (ArrayList<Variable>)$2) xx.add(new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)$1)); $$ = xx; }
 
listaIndentificadores: 	ID	{ List<Variable> xx = new ArrayList<Variable>(); xx.add(new Variable (lexico.getLine(), lexico.getColumn(), (String) $1)); $$ = xx; }
						| listaIndentificadores "," ID { $$ = $1; ((ArrayList<Variable>)$$).add(new Variable (lexico.getLine(), lexico.getColumn(), (String) getYylval())); }
						;

listaDeclaraciones : /* Optional */ { $$ = new ArrayList<VariableDefinition> ();}
					| listaDeclaraciones declaracion { $$ = $1;  for (VariableDefinition vd: (ArrayList<VariableDefinition>)$2) ((ArrayList<VariableDefinition>)$$).add(vd); }
					;
					
listaSentencias:	/*Optional*/ {$$ = new ArrayList<Sentence> (); }
					| listaSentencias sentencia		{ $$ = $1; ((ArrayList<Sentence>)$$).add((Sentence)$2); };
					
listaExpresiones:	expresion { $$ = new ArrayList<Expression>(); ((ArrayList<Expression>)$$).add((Expression)$1); }
					| listaExpresiones ", " expresion	{ $$ = $1; ((ArrayList<Expression>)$$).add((Expression)$3); }
					
					;


sentencia:	escritura
			| lectura
			| asignacion
			;
			
escritura:	WRITE listaExpresiones ";"	{ $$ = new Write(lexico.getLine(), lexico.getColumn(), (List<Expression>)$3); };
lectura:	READ listaExpresiones ";"	{ $$ = new Read(lexico.getLine(), lexico.getColumn(), (List<Expression>)$3); };
asignacion: expresion "=" expresion ";"	{ $$ = new Assign(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3); };
tipo:	INT	{ $$ = new TypeInteger(); }
		| DOUBLE	{ $$ = new TypeDouble(); }
		| CHAR	{ $$ = new TypeChar(); }
		| tipo "[" CTE_ENTERA "]"	{ $$ = new TypeArray((Type)$1, Integer.parseInt(String.valueOf(($3)))); }

expresion: expresion '+' expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, (String)$2 , (Expression)$3); }
		| expresion '*' expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expresion "-" expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expresion "/" expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expresion "%" expresion	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expresion "[" expresion "]"	{ $$=  new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$2); }
		| "-" expresion	%prec MENOSUNARIO { $$= new UnaryNegation (lexico.getLine(), lexico.getColumn(), (Expression)$2); }
		| CTE_ENTERA	{ $$= new Literal (lexico.getLine(), lexico.getColumn(), (Integer) getYylval()); }
		| listaIndentificadores	{ /*$$= new Variable (lexico.getLine(), lexico.getColumn(), (String)getYylval());*/ }
		| "(" expresion ")" { $$= $2;}
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
