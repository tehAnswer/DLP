//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package syntax;



//#line 4 "./sinj.y"
/* * Declaraciones de código Java*/
/* * Se sitúan al comienzo del archivo generado*/
/* * El package lo añade yacc si utilizamos la opción -Jpackage*/
import lexic.Lexic;
import java.io.Reader;
import ast.*;
import ast.expression.*;
import ast.sentence.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({ "unchecked", "unused" })
//#line 31 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_CARACTER=258;
public final static short CTE_REAL=259;
public final static short CHAR=260;
public final static short DOUBLE=261;
public final static short INT=262;
public final static short ID=263;
public final static short DISTINTO=264;
public final static short IGUALDAD=265;
public final static short MAYORIGUAL=266;
public final static short MENORIGUAL=267;
public final static short STRUCT=268;
public final static short MAIN=269;
public final static short VOID=270;
public final static short IF=271;
public final static short ELSE=272;
public final static short RETURN=273;
public final static short READ=274;
public final static short WRITE=275;
public final static short WHILE=276;
public final static short Y=277;
public final static short O=278;
public final static short MENOSUNARIO=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    1,    1,    1,    1,    2,    2,    6,    6,
    5,    5,    5,    8,    9,   10,    4,    4,    4,    4,
    7,    7,    7,    7,    7,    7,    7,    7,    7,    7,
};
final static short yylen[] = {                            2,
    8,    3,    1,    3,    0,    2,    0,    2,    1,    3,
    1,    1,    1,    3,    3,    4,    1,    1,    1,    4,
    3,    3,    3,    3,    3,    4,    2,    1,    1,    3,
};
final static short yydefred[] = {                         0,
    3,    0,    0,   19,   18,   17,    0,    0,    6,    0,
    0,    4,    0,    0,    0,    0,    2,    0,   20,    7,
    0,   28,   29,    0,    0,    0,    0,    1,    8,    0,
   11,   12,   13,    0,    0,    0,   27,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    0,   14,   30,    0,
    0,    0,    0,    0,   24,    0,    0,   26,   16,
};
final static short yydgoto[] = {                          2,
    3,   21,    9,   10,   29,   34,   35,   31,   32,   33,
};
final static short yysindex[] = {                      -260,
    0,    0,  -42,    0,    0,    0, -263, -255,    0,  -90,
  -29,    0, -245,  -24,  -22,  -63,    0,  -92,    0,    0,
  -40,    0,    0,  -36,  -36,  -36,  -36,    0,    0,   10,
    0,    0,    0,  -37,   51,  -21,    0,   31,  -36,  -36,
  -36,  -36,  -36,  -36,  -36,    0,  -36,    0,    0,   -1,
   12,   12,  -10,  -10,    0,   44,   51,    0,    0,
};
final static short yyrindex[] = {                       -31,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -20,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -11,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   64,   69,  -27,  -16,    0,    0,    6,    0,    0,
};
final static short yygindex[] = {                         0,
   30,    0,    0,    0,    0,   26,  105,    0,    0,    0,
};
final static int YYTABLESIZE=242;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         27,
   13,    8,    1,   27,   26,   11,   47,   12,   26,   22,
   15,   16,    5,   22,   22,   22,   22,   22,   18,    8,
   25,   46,   47,    5,   25,   25,   25,   25,   25,   19,
   20,   22,    9,   22,   17,   43,   44,   48,    5,   14,
   42,   40,   25,   41,   25,   44,   43,    9,   43,   10,
   36,   42,   40,   42,   41,    0,   44,    0,   44,    0,
    0,    0,    0,   22,   10,   22,    0,   43,    0,    0,
   45,   49,   42,   40,   25,   41,   25,   44,    0,    0,
   43,    0,    0,    0,   28,   42,   40,   43,   41,   39,
   44,   58,   42,   40,    0,   41,    0,   44,    0,    0,
   39,    0,   59,    0,   21,    0,   21,   21,   21,   23,
    0,   23,   23,   23,    0,    0,    0,    0,    0,    0,
    0,   39,   21,    0,   21,   30,    0,   23,    0,   23,
   37,   38,    0,    0,   39,    0,    0,    0,    0,    0,
    0,   39,    0,   50,   51,   52,   53,   54,   55,   56,
    0,   57,    0,    0,   21,    0,   21,    0,    0,   23,
    0,   23,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   22,    4,    5,    6,
   22,    0,   23,    0,    0,    0,   23,    7,    5,    5,
    5,    0,    0,   24,   25,    4,    5,    6,    5,    5,
    5,    5,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   91,   44,  263,   40,   45,  269,   44,  263,   45,   37,
   40,  257,   44,   41,   42,   43,   44,   45,   41,   44,
   37,   59,   44,   44,   41,   42,   43,   44,   45,   93,
  123,   59,   44,   61,   59,   37,   47,   59,   59,   10,
   42,   43,   59,   45,   61,   47,   37,   59,   37,   44,
   25,   42,   43,   42,   45,   -1,   47,   -1,   47,   -1,
   -1,   -1,   -1,   91,   59,   93,   -1,   37,   -1,   -1,
   61,   41,   42,   43,   91,   45,   93,   47,   -1,   -1,
   37,   -1,   -1,   -1,  125,   42,   43,   37,   45,   91,
   47,   93,   42,   43,   -1,   45,   -1,   47,   -1,   -1,
   91,   -1,   59,   -1,   41,   -1,   43,   44,   45,   41,
   -1,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   59,   -1,   61,   21,   -1,   59,   -1,   61,
   26,   27,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   91,   -1,   39,   40,   41,   42,   43,   44,   45,
   -1,   47,   -1,   -1,   91,   -1,   93,   -1,   -1,   91,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  260,  261,  262,
  257,   -1,  263,   -1,   -1,   -1,  263,  270,  260,  261,
  262,   -1,   -1,  274,  275,  260,  261,  262,  270,  260,
  261,  262,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'",null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","CTE_CARACTER",
"CTE_REAL","CHAR","DOUBLE","INT","ID","DISTINTO","IGUALDAD","MAYORIGUAL",
"MENORIGUAL","STRUCT","MAIN","VOID","IF","ELSE","RETURN","READ","WRITE","WHILE",
"Y","O","MENOSUNARIO",
};
final static String yyrule[] = {
"$accept : program",
"program : variableDefinitionList VOID MAIN '(' ')' '{' sentenceList '}'",
"variableDefinition : type variableDefinitionList ';'",
"variableDefinitionList : ID",
"variableDefinitionList : variableDefinitionList ',' ID",
"variableDefinitionList :",
"variableDefinitionList : variableDefinitionList variableDefinition",
"sentenceList :",
"sentenceList : sentenceList sentence",
"expressionList : expression",
"expressionList : expressionList ',' expression",
"sentence : write",
"sentence : read",
"sentence : assign",
"write : WRITE expressionList ';'",
"read : READ expressionList ';'",
"assign : expression '=' expression ';'",
"type : INT",
"type : DOUBLE",
"type : CHAR",
"type : type '[' CTE_ENTERA ']'",
"expression : expression '+' expression",
"expression : expression '*' expression",
"expression : expression '-' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '[' expression ']'",
"expression : '-' expression",
"expression : CTE_ENTERA",
"expression : ID",
"expression : '(' expression ')'",
};

//#line 96 "./sinj.y"

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
//#line 332 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 50 "./sinj.y"
{ this.ast = new Program (lexico.getLine(), lexico.getColumn(), (ArrayList<Sentence>)val_peek(1), (ArrayList<VariableDefinition>)val_peek(7)); }
break;
case 2:
//#line 52 "./sinj.y"
{ ArrayList<VariableDefinition> xx = new ArrayList<VariableDefinition>(); for (Variable v: (ArrayList<Variable>)val_peek(1)) xx.add(new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)val_peek(2))); yyval = xx; }
break;
case 3:
//#line 54 "./sinj.y"
{ List<Variable> xx = new ArrayList<Variable>(); xx.add(new Variable (lexico.getLine(), lexico.getColumn(), (String) val_peek(0))); yyval = xx; }
break;
case 4:
//#line 55 "./sinj.y"
{ yyval = val_peek(2); ((ArrayList<Variable>)yyval).add(new Variable (lexico.getLine(), lexico.getColumn(), (String)val_peek(0))); }
break;
case 5:
//#line 58 "./sinj.y"
{ yyval = new ArrayList<VariableDefinition> ();}
break;
case 6:
//#line 59 "./sinj.y"
{ yyval = val_peek(1);  for (VariableDefinition vd: (ArrayList<VariableDefinition>)val_peek(0)) ((ArrayList<VariableDefinition>)yyval).add(vd); }
break;
case 7:
//#line 62 "./sinj.y"
{yyval = new ArrayList<Sentence> (); }
break;
case 8:
//#line 63 "./sinj.y"
{ yyval = val_peek(1); ((ArrayList<Sentence>)yyval).add((Sentence)val_peek(0)); }
break;
case 9:
//#line 65 "./sinj.y"
{ yyval = new ArrayList<Expression>(); ((ArrayList<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 10:
//#line 66 "./sinj.y"
{ yyval = val_peek(2); ((ArrayList<Expression>)yyval).add((Expression)val_peek(0)); }
break;
case 14:
//#line 76 "./sinj.y"
{ yyval = new Write(lexico.getLine(), lexico.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 15:
//#line 77 "./sinj.y"
{ yyval = new Read(lexico.getLine(), lexico.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 16:
//#line 78 "./sinj.y"
{ yyval = new Assign(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 17:
//#line 79 "./sinj.y"
{ yyval = new TypeInteger(); }
break;
case 18:
//#line 80 "./sinj.y"
{ yyval = new TypeDouble(); }
break;
case 19:
//#line 81 "./sinj.y"
{ yyval = new TypeChar(); }
break;
case 20:
//#line 82 "./sinj.y"
{ yyval = new TypeArray((Type)val_peek(3), Integer.parseInt(String.valueOf((val_peek(1))))); }
break;
case 21:
//#line 84 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 22:
//#line 85 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 23:
//#line 86 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 24:
//#line 87 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 25:
//#line 88 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 26:
//#line 89 "./sinj.y"
{ yyval=  new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 27:
//#line 90 "./sinj.y"
{ yyval= new UnaryNegation (lexico.getLine(), lexico.getColumn(), (Expression)val_peek(0)); }
break;
case 28:
//#line 91 "./sinj.y"
{ yyval= new Literal (lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0)); }
break;
case 29:
//#line 92 "./sinj.y"
{ yyval = new Variable (lexico.getLine(), lexico.getColumn(), val_peek(0).toString()); }
break;
case 30:
//#line 93 "./sinj.y"
{ yyval= val_peek(1);}
break;
//#line 589 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
