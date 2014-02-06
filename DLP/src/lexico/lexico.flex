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
Space = \n\t\r
Char = [a-zA-ZáéíóúÁÉÚÍÓñÑ]
Ident = {Char}({Char}|{ConstanteEntera})*

%%
// ************  Acciones ********************

// * Constante Entera
{ConstanteEntera}	{ parser.setYylval(new Integer(yytext()));
         			  return Parser.CTE_ENTERA;  }
"read" 		{parser.setYylval(yytext()); return Parser.READ;}
"write"		{parser.setYyval(yytext()); return Parser.WRITE;}
"while"		{parser.setYyval(yytext()); return Parser.WHILE;}
{ Ident }	{parser.setYylval(yytext()); return Parser.ID;}
{ Space } { }       			  
{ Any }  {System.out.println("Error lexico en la linea: " + this.getLinea()+ ", columna: " + this.getColumna() + "\nCaracter: '" + yycharat(0) + "'."); }
