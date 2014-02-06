// ************  Código a incluir ********************

package lexico;
import sintactico.Parser;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * El analizador sintáctico
private Parser parser;
public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]*
Any = .
FinLinea = \n|\r|\n\r
Space = {FinLinea}[ \t\f]
Char = [a-zA-ZáéíóúÁÉÚÍÓñÑ]
Ident = {Char}({Char}|{ConstanteEntera})*
ComentarioLinea = "//" .* \n
Comentario = "/*" ~ "*/"

%%
// ************  Acciones ********************

// * Constante Entera
{ Space }| {ComentarioLinea}| {Comentario} { }  
{ConstanteEntera}	{ parser.setYylval(new Integer(yytext()));
         			  return Parser.CTE_ENTERA;  }
"read" 		{parser.setYylval(yytext()); return Parser.READ;}
"write"		{parser.setYylval(yytext()); return Parser.WRITE;}
"while"		{parser.setYylval(yytext()); return Parser.WHILE;}

"==" 		{parser.setYylval(yytext()); return Parser.IGUALDAD;}
"!=" 		{parser.setYylval(yytext()); return Parser.DISTINTO;}

"+"|"-" 		{parser.setYylval(yytext()); return yycharat(0);}

{ Ident }	{parser.setYylval(yytext()); return Parser.ID;}
     			  
{ Any }  {System.out.println("Error lexico en la linea: " + this.getLinea()+ ", columna: " + this.getColumna() + "\nCaracter: '" + yycharat(0) + "'."); }
