// ************  Imports and packages ********************

package lexic;
import syntax.Parser;

%%
// ************  Options ********************
// % debug 
%byaccj
%class Lexic
%public
%unicode
%line
%column

%{
// ************  Attributes and methods ********************

private Parser parser;

public void setParser(Parser parser) {
	this.parser=parser;
}

// * Getter method of line generated attribute by JFlex.
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// Getter method of column generated attribute by JFlex.
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

// ************  Patterns ********************

CHAR = [a-zA-Z‡çŽƒ’êœò—î]
COMMENT = "/*"~"*/"
DOT = "."
IDENT = {CHAR}({CHAR}|{LITINT})*
LINECOMMENT = "//" .* {LINETERMINATOR}
LITCHAR = \{LITINT}
LITINT = [0-9]+
LINETERMINATOR = \r|\n|\r\n
REAL = {LITINT}{DOT}{LITINT}*(([eE][+-]?)?{LITINT}*)? | {LITINT}[eE]{LITINT}
SPACES = {LINETERMINATOR} | [ \t\f]

ANY = .|\n


%%
// ************  Actions ********************

"char"		{parser.setYylval(yytext()); return Parser.CHAR;}
"double"	{parser.setYylval(yytext()); return Parser.DOUBLE;}
"else"		{parser.setYylval(yytext()); return Parser.ELSE;}      			  
"if"		{parser.setYylval(yytext()); return Parser.IF;}
"int"		{parser.setYylval(yytext()); return Parser.INT;}  
"main"		{parser.setYylval(yytext()); return Parser.MAIN;}      			  
"read" 		{parser.setYylval(yytext()); return Parser.READ;}
"struct"	{parser.setYylval(yytext()); return Parser.STRUCT;}
"while"		{parser.setYylval(yytext()); return Parser.WHILE;}
"write"		{parser.setYylval(yytext()); return Parser.WRITE;}
"void"		{parser.setYylval(yytext()); return Parser.VOID;}
"return"	{parser.setYylval(yytext()); return Parser.RETURN;}

"==" 		{parser.setYylval(yytext()); return Parser.IGUALDAD;}
"!=" 		{parser.setYylval(yytext()); return Parser.DISTINTO;}
"&&"		{parser.setYylval(yytext()); return Parser.Y;}
"||"		{parser.setYylval(yytext()); return Parser.O;}
"<="		{parser.setYylval(yytext()); return Parser.MENORIGUAL;}
">="		{parser.setYylval(yytext()); return Parser.MAYORIGUAL;}


"'" | 
"!" |
"*" |
"+" |
"-" |
"=" |
"," |
";" |
"(" |
"{" |
")" |
"}" |
"[" |
"]" |
"/"			{parser.setYylval(yytext()); return yycharat(0);}

{ IDENT }		{parser.setYylval(yytext()); return Parser.ID;}
{ LITCHAR }		{ parser.setYylval(new Integer(yytext())); return Parser.CTE_CARACTER;  }
{ LITINT }		{ parser.setYylval(new Integer(yytext())); return Parser.CTE_ENTERA;  }
{ REAL }		{ parser.setYylval(new Double(yytext())); return Parser.CTE_REAL;  }   

{ SPACES }|{ LINECOMMENT }|{ COMMENT }		{	} 			  
{ ANY }  {System.out.println("Unexpected char at (line: " + this.getLine()+ ", column: " + this.getColumn() + "): '" + yycharat(0) + "'."); }
