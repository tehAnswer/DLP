

%{
// * Declaraciones de código Java
// * Se sitúan al comienzo del archivo generado
// * El package lo añade yacc si utilizamos la opción -Jpackage
import lexic.Lexic;
import java.io.Reader;
import ast.*;
import ast.expression.*;
import ast.sentence.*;
import ast.sentence.definition.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({ "unchecked", "unused" })
%}

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
%token MENOR
%token MAYOR


%nonassoc LOWER
%right '='
%left Y O '!'
%left MAYORIGUAL MENORIGUAL IGUALDAD DISTINTO MAYOR MENOR
%left '+' '-'
%left '*' '%' '/'
%right MENOSUNARIO
%left '[' ']'
%left '.'
%nonassoc '(' ')'
%nonassoc ELSE


%%

//program: variableDefinitionList VOID MAIN '(' ')' '{' sentenceList '}' { this.ast = new Program (lexico.getLine(), lexico.getColumn(), (ArrayList<Sentence>)$7, (ArrayList<VariableDefinition>)$1); };

program: definitionList VOID MAIN '(' ')' '{' variableDefinitionList sentenceList '}' { this.ast = new Program (lexico.getLine(), lexico.getColumn(), (List<Sentence>)$8, (List<Definition>)$1,(List<VariableDefinition>)$7 ); };


definitionList: /* optional */ { $$ = new ArrayList<Definition>(); }
				| definitionList definition { 
											$$ = $1;
											((ArrayList<Definition> )$$).addAll((ArrayList<Definition>)$2);
											}
				;
											
definition: variableDefinition
			| functionDefinition
			;

				
functionDefinition: type ID '(' optionalParamList ')' '{' variableDefinitionList sentenceList '}' { 
												TypeFunction type = new TypeFunction ((Type)$1, (ArrayList<VariableDefinition>) $4);
												List<Sentence> body = new ArrayList<Sentence> ();
												body.addAll((List<Sentence>)$7);
												body.addAll((List<Sentence>)$8);
												Definition def = new FunctionDefinition (lexico.getLine(), lexico.getColumn(), type, (String) $2, body);
												List<Definition> xx = new ArrayList<Definition> ();
												xx.add(def);
												$$ = xx;
 };

optionalParamList: /*Optional*/ { $$ = new ArrayList<VariableDefinition> (); }
			| paramList
			;
			
paramList: type ID	{ 
						$$ = new ArrayList<VariableDefinition>();
						Variable v = new Variable (lexico.getLine(), lexico.getColumn(), $2.toString());
						((ArrayList<Definition>)$$).add(new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)$1));
					}
			| paramList ',' type ID {
										$$ = $1;
										Variable v = new Variable (lexico.getLine(), lexico.getColumn(), $4.toString());
										VariableDefinition vd = new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)$3);
										if (!((ArrayList<VariableDefinition>)$$).contains (vd))
											((ArrayList<Definition>) $$).add(vd);
										else 
											throw new RuntimeException ("One param is duplicate");
											
									}		
			;


variableDefinition:	type identVariableDefinitionList ';' { 
													ArrayList<VariableDefinition> xx = new ArrayList<VariableDefinition>();
													for (Variable v: (ArrayList<Variable>)$2) 
													 	xx.add(new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)$1)); 
													$$ = xx; 
													 }
 					;
 					
 					
 					


identVariableDefinitionList: 	ID	{ 
								List<Variable> xx = new ArrayList<Variable>(); 
								xx.add(new Variable (lexico.getLine(), lexico.getColumn(), (String) $1)); 
								$$ = xx; 
								}
						| identVariableDefinitionList ',' ID { 
														$$ = $1; 
														Variable v = new Variable (lexico.getLine(), lexico.getColumn(), (String)$3);
														if (!((ArrayList<Variable>)$$).contains(v))
															((ArrayList<Variable>)$$).add(v); 
														else 
															throw new RuntimeException ("One variable is duplicate");
														}
					;
					
					
					
					
					


variableDefinitionList : /* Optional */ { $$ = new ArrayList<VariableDefinition> ();}
					| variableDefinitionList variableDefinition { 
																$$ = $1;  
																for (VariableDefinition vd: (ArrayList<VariableDefinition>)$2) 
																	if(!((ArrayList<VariableDefinition>)$$).contains(vd))
																		((ArrayList<VariableDefinition>)$$).add(vd); 
																	else
																		throw new RuntimeException("One field struct is duplicate");
																}
					;

					
sentenceList:	/*Optional*/ {$$ = new ArrayList<Sentence> (); }
					| sentenceList sentence		{ 
												$$ = $1;
												((ArrayList<Sentence>)$$).add((Sentence)$2); 
												 }
					;
					
expressionList:	expression { 
							$$ = new ArrayList<Expression>();
							((ArrayList<Expression>)$$).add((Expression)$1); 
							}
				| expressionList ',' expression	{
					 							$$ = $1;
												((ArrayList<Expression>)$$).add((Expression)$3); 
												}
					
					;
					
optionalExpressionList: /* Optional */ { $$ = new ArrayList<Expression> (); }
						| expressionList
					;

sentence:	write
			| read
			| assign
			| invocation
			| return
			| while
			| if
				;
			
write:	WRITE expressionList ';'	{$$ = new Write(lexico.getLine(), lexico.getColumn(), (List<Expression>)$2); };
read:	READ expressionList ';'	{ $$ = new Read(lexico.getLine(), lexico.getColumn(), (List<Expression>)$2); };
assign: expression '=' expression ';'	{ $$ = new Assign(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3); };
invocation: ID '(' optionalExpressionList ')' ';'{ $$ = new Invocation (lexico.getLine(), lexico.getColumn(), (String) $1, (List<Expression>)$3); }
return: RETURN expression ';' { $$ = new Return (lexico.getLine(), lexico.getColumn(), (Expression) $2); }
while:  WHILE '(' expression ')' '{' sentenceList '}' { $$ = new While (lexico.getLine(), lexico.getColumn(), (Expression) $3, (List<Sentence>) $6); }


if: IF '(' expression ')' sentence %prec LOWER { 
									List<Sentence> ifBody = new ArrayList<Sentence> ();
									ifBody.add((Sentence) $5);
									$$ = new If(lexico.getLine(), lexico.getColumn(), (Expression) $3, ifBody, new ArrayList<Sentence>());
									}
	| IF '(' expression ')' sentence ELSE sentence 	{
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.add((Sentence) $5);
													elseBody.add((Sentence)$7);
													$$ = new If(lexico.getLine(), lexico.getColumn(), (Expression)$3, ifBody, elseBody);	
													}
	| IF '(' expression ')' '{' sentenceList '}' ELSE sentence {
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.addAll((List<Sentence>) $5);
													elseBody.add((Sentence)$7);
													$$ = new If(lexico.getLine(), lexico.getColumn(), (Expression)$3, ifBody, elseBody);
													 }
	
	| IF '(' expression ')' '{' sentenceList '}' ELSE '{' sentenceList '}' {
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.addAll((List<Sentence>) $5);
													elseBody.addAll((List<Sentence>)$7);
													$$ = new If(lexico.getLine(), lexico.getColumn(), (Expression)$3, ifBody, elseBody);
													 }
	
	| IF '(' expression ')' '{' sentenceList '}' %prec LOWER {
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.addAll((List<Sentence>) $5);
													$$ = new If(lexico.getLine(), lexico.getColumn(), (Expression)$3, ifBody, elseBody);
	}
												
	;
	


type:	INT	{ $$ = new TypeInteger(); }
		| DOUBLE	{ $$ = new TypeDouble(); }
		| CHAR	{ $$ = new TypeChar(); }
		| type '[' CTE_ENTERA ']'	{ $$ = createArray((Type)$1, Integer.parseInt(String.valueOf(($3))));/*$$ = new TypeArray((Type)$1, Integer.parseInt(String.valueOf(($3))));*/ }
		| VOID { $$ = new TypeVoid (); }
		| STRUCT '{' variableDefinitionList '}' { 
							List <VariableDefinition> variables = (ArrayList<VariableDefinition>) $3;
							List <FieldDefinition> fieldsStruct = new ArrayList<FieldDefinition> ();
							for (VariableDefinition vd : variables) {
								FieldDefinition field = new FieldDefinition (vd.getLine(), vd.getColumn(), vd.getType(), vd.getVariable());
								fieldsStruct.add(field);
							}
							
							$$ = new TypeStruct (fieldsStruct);
							}
				;

		

expression: expression '+' expression	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) $1, (String)$2 , (Expression)$3); }
		| expression '*' expression	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression '-' expression	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression '/' expression	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression '%' expression	{ $$=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression '[' expression ']'	{ $$=  new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expression)$1, (Expression)$3); }
		| expression MAYORIGUAL expression { $$ = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression MENORIGUAL expression { $$ = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression MENOR expression { $$ = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression MAYOR expression { $$ = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression DISTINTO expression { $$ = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression IGUALDAD expression { $$ = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression Y expression { $$ = new Logic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| expression O expression { $$ = new Logic(lexico.getLine(), lexico.getColumn(), (Expression)$1, (String)$2 , (Expression)$3); }
		| '-' expression	%prec MENOSUNARIO { $$= new UnaryNegation (lexico.getLine(), lexico.getColumn(), (Expression)$2); }
		| CTE_ENTERA	{ $$= new Literal (lexico.getLine(), lexico.getColumn(), (Integer) $1); }
		| ID { $$ = new Variable (lexico.getLine(), lexico.getColumn(), $1.toString()); }
		| CTE_REAL { $$= new RealNumber (lexico.getLine(), lexico.getColumn(), (Double) $1); }
		| '(' expression ')' { $$= $2;}
		| expression '.' ID { $$ = new FieldAccess (lexico.getLine(), lexico.getColumn(), (Expression) $1, new Variable (lexico.getLine(), lexico.getColumn(), $3.toString()));}
		| ID '(' optionalExpressionList ')' { $$ = new FunctionInvocation (lexico.getLine(), lexico.getColumn(), (String) $1, (List<Expression>)$3); }
		| '(' type ')' expression { $$ = new Cast (lexico.getLine(), lexico.getColumn(), (Type) $2, (Expression) $4); }
		| '!' expression { $$ = new LogicalNegation (lexico.getLine(), lexico.getColumn(), (Expression)$2); }
		| CTE_CARACTER { $$ = new Char (lexico.getLine(), lexico.getColumn(), $1.toString()); }
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

public TypeArray createArray (Type type, Integer size) {
	if (type instanceof TypeArray) {
			TypeArray original = (TypeArray) type;
			Type newArray = new TypeArray (original.getType(), size);
			original.setType(newArray);
			return original;
		}
		
	else
		return new TypeArray (type, size);
		
}

