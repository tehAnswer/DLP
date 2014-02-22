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
import ast.sentence.definition.*;
import ast.type.*;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({ "unchecked", "unused" })
//#line 32 "Parser.java"




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
public final static short MENOR=279;
public final static short MAYOR=280;
public final static short LOWER=281;
public final static short MENOSUNARIO=282;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    4,    4,    6,    8,    8,    9,    9,
    5,   10,   10,    2,    2,    3,    3,   12,   12,   14,
   14,   11,   11,   11,   11,   11,   11,   11,   15,   16,
   17,   18,   19,   20,   21,   21,   21,   21,   21,    7,
    7,    7,    7,    7,    7,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,
};
final static short yylen[] = {                            2,
    9,    0,    2,    1,    1,    9,    0,    1,    2,    4,
    3,    1,    3,    0,    2,    0,    2,    1,    3,    0,
    1,    1,    1,    1,    1,    1,    1,    1,    3,    3,
    4,    5,    3,    7,    5,    7,    9,   11,    7,    1,
    1,    1,    4,    1,    4,    3,    3,    3,    3,    3,
    4,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    1,    1,    1,    3,    3,    4,    4,    2,    1,
};
final static short yydefred[] = {                         2,
    0,    0,   42,   41,   40,    0,    0,    3,    4,    5,
    0,   14,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   11,   44,   45,   15,    0,    0,    0,    0,    0,
   43,   13,   12,   14,    9,    0,    0,    0,   14,    0,
    0,    0,   10,   61,   69,   63,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    1,   17,    0,   22,   23,
   24,   25,   26,   27,   28,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    6,    0,    0,    0,    0,   33,
    0,   30,   29,    0,    0,   64,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   65,    0,    0,    0,    0,    0,   67,   31,   51,
   32,   16,    0,   66,   16,    0,    0,    0,    0,   36,
   34,    0,   16,   37,    0,   38,
};
final static short yydgoto[] = {                          1,
    2,   17,   41,    8,   25,   10,   26,   29,   30,   16,
   57,   96,   58,   97,   59,   60,   61,   62,   63,   64,
   65,
};
final static short yysindex[] = {                         0,
    0, -145,    0,    0,    0, -112, -256,    0,    0,    0,
  -90,    0,  -10,   -6, -243,   -8,  -46,   -2,   40,  -52,
 -219,    0,    0,    0,    0,  -89,  -68,  -75,   19,   17,
    0,    0,    0,    0,    0,  -58,   40,   40,    0,  -72,
  192,   40,    0,    0,    0,    0,   32,   55,  620,  620,
  620,   57,  620,  620,  645,    0,    0,   67,    0,    0,
    0,    0,    0,    0,    0,  212,  620,  620,   59,   84,
   15,  231,   34,  620,  252,  -34,  -38,  104,  620,  620,
  620,  620,  620,  620,  620,  620,  620,  620,  620,  620,
  620,  620,  620, -181,    0,   56,   66,  122,  620,    0,
  620,    0,    0,  140,  620,    0,  411,  411,  411,  411,
  252,  252,  411,  411,  147,  348,  348,  -34,  -34,  -34,
  165,    0,   52,  370,   77,  231,    1,    0,    0,    0,
    0,    0, -138,    0,    0,  283,  543,  439, -136,    0,
    0,  459,    0,    0,  480,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  -59,    0,    0,    0,
    0,    0,    0,   37,    0,    0,    0,    0,   97,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  101,
    0,    0,    0,    0,    0,    0,    0,  489,    0,    0,
    0,  489,    0,    0,    0,    0,  330,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  103,    0,  -37,    0,
    0,  -26,    0,    0,   42,  -16,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  107,    0,    0,  103,    0,
    0,    0,    0,    0,    0,    0,  632,  665,  735,  754,
   61,  593,  789,  808,    0,  557,  579,    5,   26,   47,
    0,    0,  391,    0,    0,  -24,    0,    0,    0,    0,
    0,    0,  510,    0,    0,    0,    0,    0,  534,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   98,   91,    0,  137,    0,   21,    0,    0,    0,
  -62,  -13,  867,   53,    0,    0,    0,    0,    0,    0,
    0,
};
final static int YYTABLESIZE=1088;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         62,
   15,   15,  105,   62,   62,   62,   62,   62,   62,   62,
   12,   94,   13,   20,   18,   15,   19,   18,   15,   19,
   60,   62,   11,   62,   60,   60,   60,   60,   60,   18,
   60,   44,   18,   19,   19,   21,   71,   73,   27,   28,
   31,   47,   60,   32,   60,   47,   47,   47,   47,   47,
   22,   47,   15,   62,   34,   62,   93,   40,  101,   36,
   37,  133,   50,   47,   39,   47,   50,   50,   50,   50,
   50,   67,   50,  102,  140,   77,   60,  101,   24,  144,
   12,  122,   68,   49,   50,   68,   50,   49,   49,   49,
   49,   49,  103,   49,   68,   12,   74,   47,   99,  101,
   68,   58,   68,   91,   58,   49,  123,   49,   90,   88,
  131,   89,   94,   92,    3,    4,    5,  134,   50,   58,
   91,   58,    6,  135,    7,   90,   88,   87,   89,   94,
   92,   38,   66,  137,   68,  142,   42,    7,    9,   49,
   91,    8,  100,   20,  106,   90,   88,   21,   89,   94,
   92,  125,    0,   58,    0,    0,    0,   93,   91,    0,
    0,    0,  124,   90,   88,    0,   89,   94,   92,    0,
    0,    0,   14,   33,   93,    0,   91,    0,    0,    0,
  127,   90,   88,   91,   89,   94,   92,   35,   90,   88,
   43,   89,   94,   92,   93,    0,    0,    0,    0,    0,
    0,   91,    0,   44,    0,  129,   90,   88,    0,   89,
   94,   92,   93,    3,    4,    5,    0,    0,    0,    0,
    0,    6,  136,   23,   53,  138,   62,   62,   62,   62,
   93,   55,    0,  145,    0,    0,   54,   93,    0,   62,
   62,   62,   62,    0,   53,    0,    0,   60,   60,   60,
   60,   55,    0,    0,    0,   93,   54,  130,    0,    0,
   60,   60,   60,   60,    0,    0,    0,   91,   47,   47,
   47,   47,   90,   88,    0,   89,   94,   92,    0,    0,
    0,   47,   47,   47,   47,    0,    0,    0,   91,   50,
   50,   50,   50,   90,   88,    0,   89,   94,   92,    3,
    4,    5,   50,   50,   50,   50,    0,    6,    0,   23,
   49,   49,   49,   49,    0,   53,   56,    0,   68,   68,
    0,   93,   55,   49,   49,   49,   49,   54,    0,    0,
   79,   80,   81,   82,    0,    0,   95,   58,   58,    0,
    0,    0,   93,   83,   84,   85,   86,   79,   80,   81,
   82,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   83,   84,   85,   86,    0,    0,   62,   79,   80,   81,
   82,   62,   62,    0,   62,   62,   62,    0,    0,    0,
   83,   84,   85,   86,   91,   79,   80,   81,   82,   90,
   62,    0,    0,   94,   92,    0,    0,    0,   83,   84,
   85,   86,   53,   79,   80,   81,   82,  139,    0,   55,
   79,   80,   81,   82,   54,    0,   83,   84,   85,   86,
   62,    0,    0,   83,   84,   85,   86,   66,   79,   80,
   81,   82,   66,   66,    0,   66,   66,   66,   93,    0,
    0,   83,   84,   85,   86,    0,    0,   91,   44,   45,
   46,   66,   90,   88,   47,   89,   94,   92,    0,    0,
    0,    0,   48,    0,   49,   50,   51,   52,   44,   45,
   46,   53,    0,    0,   47,    0,    0,    0,   55,    0,
    0,   66,   48,   54,   49,   50,   51,   52,    0,    0,
    0,   53,  132,    0,   79,   80,   81,   82,   55,    0,
    0,   93,    0,   54,    0,    0,    0,   83,   84,   85,
   86,    0,   53,    0,    0,   79,   80,   81,   82,   55,
    0,   16,    0,    0,   54,    0,    0,    0,   16,    0,
   85,   86,    0,   16,    0,    0,    0,    0,    0,   44,
   45,   46,   35,    0,    0,   47,    0,    0,    0,   35,
    0,    0,    0,   48,   35,   49,   50,   51,   52,    0,
    0,    0,    0,  141,    0,    0,   39,    0,    0,    0,
    0,    0,    0,   39,    0,   53,    0,    0,   39,    0,
    0,  143,   55,    0,    0,    0,    0,   54,    0,    0,
    0,    0,    0,   62,   62,   62,   62,   46,    0,   46,
   46,   46,    0,    0,  146,    0,   62,   62,   62,   62,
    0,    0,    0,   16,    0,   46,    0,   46,    0,   48,
    0,   48,   48,   48,    0,    0,   44,   45,   46,    0,
    0,    0,   47,   59,   35,    0,   59,   48,    0,   48,
   48,    0,   49,   50,   51,   52,    0,    0,    0,   46,
    0,   59,   53,   59,   66,   66,   66,   66,   39,   55,
    0,    0,    0,    0,   54,    0,    0,   66,   66,   66,
   66,   48,   56,    0,    0,   56,    0,   53,    0,    0,
    0,    0,    0,    0,   55,   59,    0,    0,    0,   54,
   56,    0,   56,    0,    0,   44,   45,   46,    0,    0,
    0,   47,    0,    0,    0,   57,    0,    0,   57,   48,
    0,   49,   50,   51,   52,   44,   45,   46,    0,    0,
    0,   47,    0,   57,   56,   57,    0,    0,    0,   48,
    0,   49,   50,   51,   52,    0,   44,   45,   46,    0,
    0,    0,   47,    0,    0,   16,   16,   16,    0,    0,
   48,   16,   49,   50,   51,   52,    0,   57,    0,   16,
    0,   16,   16,   16,   16,    0,   35,   35,   35,    0,
    0,    0,   35,    0,    0,   52,    0,    0,   52,    0,
   35,    0,   35,   35,   35,   35,    0,    0,    0,    0,
   39,   39,   39,   52,   53,   52,   39,   53,    0,   44,
   45,   46,    0,    0,   39,   47,   39,   39,   39,   39,
    0,    0,   53,   48,   53,   49,   50,   51,   52,    0,
   46,   46,   46,   46,    0,    0,    0,   52,    0,   54,
    0,    0,   54,   46,   46,   46,   46,    0,    0,    0,
    0,    0,   48,   48,   48,   48,   53,   54,   55,   54,
    0,   55,    0,    0,    0,   48,   48,   48,   48,    0,
    0,    0,    0,    0,    0,    0,   55,    0,   55,   59,
   59,    0,    0,    0,    0,    0,   44,   45,   46,    0,
    0,   54,   69,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,   56,   56,   56,    0,
   55,   44,   45,   46,    3,    4,    5,   69,   56,   56,
   56,   56,    6,    0,   23,   70,   72,   72,    0,   75,
   76,   78,    0,    0,    0,    0,    0,    0,   57,   57,
   57,   57,    0,   72,   98,    0,    0,    0,    0,    0,
  104,   57,   57,   57,   57,  107,  108,  109,  110,  111,
  112,  113,  114,  115,  116,  117,  118,  119,  120,  121,
    0,    0,    0,    0,    0,   72,    0,  126,    0,    0,
    0,  128,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   52,   52,
   52,   52,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   52,   52,   52,   52,    0,    0,   53,   53,   53,
   53,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   53,   53,   53,   53,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   54,   54,   54,   54,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   54,   54,   54,   54,    0,
    0,   55,   55,   55,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   55,   55,   55,   55,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   41,   41,   42,   43,   44,   45,   46,   47,
  123,   46,  269,  257,   41,   91,   41,   44,   91,   44,
   37,   59,    2,   61,   41,   42,   43,   44,   45,   40,
   47,   91,   59,   40,   59,   44,   50,   51,   41,   19,
   93,   37,   59,  263,   61,   41,   42,   43,   44,   45,
   59,   47,   91,   91,  123,   93,   91,   37,   44,   41,
   44,  124,   37,   59,  123,   61,   41,   42,   43,   44,
   45,   40,   47,   59,  137,   55,   93,   44,  125,  142,
   44,  263,   41,   37,   59,   44,   61,   41,   42,   43,
   44,   45,   59,   47,   40,   59,   40,   93,   40,   44,
   59,   41,   61,   37,   44,   59,   41,   61,   42,   43,
   59,   45,   46,   47,  260,  261,  262,   41,   93,   59,
   37,   61,  268,  123,  270,   42,   43,   61,   45,   46,
   47,   34,   42,  272,   93,  272,   39,   41,    2,   93,
   37,   41,   59,   41,   41,   42,   43,   41,   45,   46,
   47,   99,   -1,   93,   -1,   -1,   -1,   91,   37,   -1,
   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,   -1,
   -1,   -1,  263,  263,   91,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   37,   45,   46,   47,  263,   42,   43,
  263,   45,   46,   47,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   37,   -1,  263,   -1,   59,   42,   43,   -1,   45,
   46,   47,   91,  260,  261,  262,   -1,   -1,   -1,   -1,
   -1,  268,  132,  270,   33,  135,  264,  265,  266,  267,
   91,   40,   -1,  143,   -1,   -1,   45,   91,   -1,  277,
  278,  279,  280,   -1,   33,   -1,   -1,  264,  265,  266,
  267,   40,   -1,   -1,   -1,   91,   45,   93,   -1,   -1,
  277,  278,  279,  280,   -1,   -1,   -1,   37,  264,  265,
  266,  267,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  277,  278,  279,  280,   -1,   -1,   -1,   37,  264,
  265,  266,  267,   42,   43,   -1,   45,   46,   47,  260,
  261,  262,  277,  278,  279,  280,   -1,  268,   -1,  270,
  264,  265,  266,  267,   -1,   33,  125,   -1,  277,  278,
   -1,   91,   40,  277,  278,  279,  280,   45,   -1,   -1,
  264,  265,  266,  267,   -1,   -1,  125,  277,  278,   -1,
   -1,   -1,   91,  277,  278,  279,  280,  264,  265,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  277,  278,  279,  280,   -1,   -1,   37,  264,  265,  266,
  267,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
  277,  278,  279,  280,   37,  264,  265,  266,  267,   42,
   61,   -1,   -1,   46,   47,   -1,   -1,   -1,  277,  278,
  279,  280,   33,  264,  265,  266,  267,  125,   -1,   40,
  264,  265,  266,  267,   45,   -1,  277,  278,  279,  280,
   91,   -1,   -1,  277,  278,  279,  280,   37,  264,  265,
  266,  267,   42,   43,   -1,   45,   46,   47,   91,   -1,
   -1,  277,  278,  279,  280,   -1,   -1,   37,  257,  258,
  259,   61,   42,   43,  263,   45,   46,   47,   -1,   -1,
   -1,   -1,  271,   -1,  273,  274,  275,  276,  257,  258,
  259,   33,   -1,   -1,  263,   -1,   -1,   -1,   40,   -1,
   -1,   91,  271,   45,  273,  274,  275,  276,   -1,   -1,
   -1,   33,  123,   -1,  264,  265,  266,  267,   40,   -1,
   -1,   91,   -1,   45,   -1,   -1,   -1,  277,  278,  279,
  280,   -1,   33,   -1,   -1,  264,  265,  266,  267,   40,
   -1,   33,   -1,   -1,   45,   -1,   -1,   -1,   40,   -1,
  279,  280,   -1,   45,   -1,   -1,   -1,   -1,   -1,  257,
  258,  259,   33,   -1,   -1,  263,   -1,   -1,   -1,   40,
   -1,   -1,   -1,  271,   45,  273,  274,  275,  276,   -1,
   -1,   -1,   -1,  125,   -1,   -1,   33,   -1,   -1,   -1,
   -1,   -1,   -1,   40,   -1,   33,   -1,   -1,   45,   -1,
   -1,  123,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,
   -1,   -1,   -1,  264,  265,  266,  267,   41,   -1,   43,
   44,   45,   -1,   -1,  125,   -1,  277,  278,  279,  280,
   -1,   -1,   -1,  125,   -1,   59,   -1,   61,   -1,   41,
   -1,   43,   44,   45,   -1,   -1,  257,  258,  259,   -1,
   -1,   -1,  263,   41,  125,   -1,   44,   59,   -1,   61,
  271,   -1,  273,  274,  275,  276,   -1,   -1,   -1,   93,
   -1,   59,   33,   61,  264,  265,  266,  267,  125,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,  277,  278,  279,
  280,   93,   41,   -1,   -1,   44,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   93,   -1,   -1,   -1,   45,
   59,   -1,   61,   -1,   -1,  257,  258,  259,   -1,   -1,
   -1,  263,   -1,   -1,   -1,   41,   -1,   -1,   44,  271,
   -1,  273,  274,  275,  276,  257,  258,  259,   -1,   -1,
   -1,  263,   -1,   59,   93,   61,   -1,   -1,   -1,  271,
   -1,  273,  274,  275,  276,   -1,  257,  258,  259,   -1,
   -1,   -1,  263,   -1,   -1,  257,  258,  259,   -1,   -1,
  271,  263,  273,  274,  275,  276,   -1,   93,   -1,  271,
   -1,  273,  274,  275,  276,   -1,  257,  258,  259,   -1,
   -1,   -1,  263,   -1,   -1,   41,   -1,   -1,   44,   -1,
  271,   -1,  273,  274,  275,  276,   -1,   -1,   -1,   -1,
  257,  258,  259,   59,   41,   61,  263,   44,   -1,  257,
  258,  259,   -1,   -1,  271,  263,  273,  274,  275,  276,
   -1,   -1,   59,  271,   61,  273,  274,  275,  276,   -1,
  264,  265,  266,  267,   -1,   -1,   -1,   93,   -1,   41,
   -1,   -1,   44,  277,  278,  279,  280,   -1,   -1,   -1,
   -1,   -1,  264,  265,  266,  267,   93,   59,   41,   61,
   -1,   44,   -1,   -1,   -1,  277,  278,  279,  280,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   -1,   61,  277,
  278,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,
   -1,   93,  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  264,  265,  266,  267,   -1,
   93,  257,  258,  259,  260,  261,  262,  263,  277,  278,
  279,  280,  268,   -1,  270,   49,   50,   51,   -1,   53,
   54,   55,   -1,   -1,   -1,   -1,   -1,   -1,  264,  265,
  266,  267,   -1,   67,   68,   -1,   -1,   -1,   -1,   -1,
   74,  277,  278,  279,  280,   79,   80,   81,   82,   83,
   84,   85,   86,   87,   88,   89,   90,   91,   92,   93,
   -1,   -1,   -1,   -1,   -1,   99,   -1,  101,   -1,   -1,
   -1,  105,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  264,  265,
  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  277,  278,  279,  280,   -1,   -1,  264,  265,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  277,  278,  279,  280,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  264,  265,  266,  267,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  277,  278,  279,  280,   -1,
   -1,  264,  265,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  277,  278,  279,  280,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=282;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
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
"Y","O","MENOR","MAYOR","LOWER","MENOSUNARIO",
};
final static String yyrule[] = {
"$accept : program",
"program : definitionList VOID MAIN '(' ')' '{' variableDefinitionList sentenceList '}'",
"definitionList :",
"definitionList : definitionList definition",
"definition : variableDefinition",
"definition : functionDefinition",
"functionDefinition : type ID '(' optionalParamList ')' '{' variableDefinitionList sentenceList '}'",
"optionalParamList :",
"optionalParamList : paramList",
"paramList : type ID",
"paramList : paramList ',' type ID",
"variableDefinition : type identVariableDefinitionList ';'",
"identVariableDefinitionList : ID",
"identVariableDefinitionList : identVariableDefinitionList ',' ID",
"variableDefinitionList :",
"variableDefinitionList : variableDefinitionList variableDefinition",
"sentenceList :",
"sentenceList : sentenceList sentence",
"expressionList : expression",
"expressionList : expressionList ',' expression",
"optionalExpressionList :",
"optionalExpressionList : expressionList",
"sentence : write",
"sentence : read",
"sentence : assign",
"sentence : invocation",
"sentence : return",
"sentence : while",
"sentence : if",
"write : WRITE expressionList ';'",
"read : READ expressionList ';'",
"assign : expression '=' expression ';'",
"invocation : ID '(' optionalExpressionList ')' ';'",
"return : RETURN expression ';'",
"while : WHILE '(' expression ')' '{' sentenceList '}'",
"if : IF '(' expression ')' sentence",
"if : IF '(' expression ')' sentence ELSE sentence",
"if : IF '(' expression ')' '{' sentenceList '}' ELSE sentence",
"if : IF '(' expression ')' '{' sentenceList '}' ELSE '{' sentenceList '}'",
"if : IF '(' expression ')' '{' sentenceList '}'",
"type : INT",
"type : DOUBLE",
"type : CHAR",
"type : type '[' CTE_ENTERA ']'",
"type : VOID",
"type : STRUCT '{' variableDefinitionList '}'",
"expression : expression '+' expression",
"expression : expression '*' expression",
"expression : expression '-' expression",
"expression : expression '/' expression",
"expression : expression '%' expression",
"expression : expression '[' expression ']'",
"expression : expression MAYORIGUAL expression",
"expression : expression MENORIGUAL expression",
"expression : expression MENOR expression",
"expression : expression MAYOR expression",
"expression : expression DISTINTO expression",
"expression : expression IGUALDAD expression",
"expression : expression Y expression",
"expression : expression O expression",
"expression : '-' expression",
"expression : CTE_ENTERA",
"expression : ID",
"expression : CTE_REAL",
"expression : '(' expression ')'",
"expression : expression '.' ID",
"expression : ID '(' optionalExpressionList ')'",
"expression : '(' type ')' expression",
"expression : '!' expression",
"expression : CTE_CARACTER",
};

//#line 279 "./sinj.y"

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

//#line 596 "Parser.java"
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
//#line 63 "./sinj.y"
{ this.ast = new Program (lexico.getLine(), lexico.getColumn(), (List<Sentence>)val_peek(1), (List<Definition>)val_peek(8),(List<VariableDefinition>)val_peek(2) ); }
break;
case 2:
//#line 66 "./sinj.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 3:
//#line 67 "./sinj.y"
{ 
											yyval = val_peek(1);
											((ArrayList<Definition> )yyval).addAll((ArrayList<Definition>)val_peek(0));
											}
break;
case 6:
//#line 78 "./sinj.y"
{ 
												TypeFunction type = new TypeFunction ((Type)val_peek(8), (ArrayList<VariableDefinition>) val_peek(5));
												List<Sentence> body = new ArrayList<Sentence> ();
												body.addAll((List<Sentence>)val_peek(2));
												body.addAll((List<Sentence>)val_peek(1));
												Definition def = new FunctionDefinition (lexico.getLine(), lexico.getColumn(), type, (String) val_peek(7), body);
												List<Definition> xx = new ArrayList<Definition> ();
												xx.add(def);
												yyval = xx;
 }
break;
case 7:
//#line 89 "./sinj.y"
{ yyval = new ArrayList<VariableDefinition> (); }
break;
case 9:
//#line 93 "./sinj.y"
{ 
						yyval = new ArrayList<VariableDefinition>();
						Variable v = new Variable (lexico.getLine(), lexico.getColumn(), val_peek(0).toString());
						((ArrayList<Definition>)yyval).add(new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)val_peek(1)));
					}
break;
case 10:
//#line 98 "./sinj.y"
{
										yyval = val_peek(3);
										Variable v = new Variable (lexico.getLine(), lexico.getColumn(), val_peek(0).toString());
										VariableDefinition vd = new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)val_peek(1));
										if (!((ArrayList<VariableDefinition>)yyval).contains (vd))
											((ArrayList<Definition>) yyval).add(vd);
										else 
											throw new RuntimeException ("One param is duplicate");
											
									}
break;
case 11:
//#line 111 "./sinj.y"
{ 
													ArrayList<VariableDefinition> xx = new ArrayList<VariableDefinition>();
													for (Variable v: (ArrayList<Variable>)val_peek(1)) 
													 	xx.add(new VariableDefinition(lexico.getLine(), lexico.getColumn(), v, (Type)val_peek(2))); 
													yyval = xx; 
													 }
break;
case 12:
//#line 123 "./sinj.y"
{ 
								List<Variable> xx = new ArrayList<Variable>(); 
								xx.add(new Variable (lexico.getLine(), lexico.getColumn(), (String) val_peek(0))); 
								yyval = xx; 
								}
break;
case 13:
//#line 128 "./sinj.y"
{ 
														yyval = val_peek(2); 
														Variable v = new Variable (lexico.getLine(), lexico.getColumn(), (String)val_peek(0));
														if (!((ArrayList<Variable>)yyval).contains(v))
															((ArrayList<Variable>)yyval).add(v); 
														else 
															throw new RuntimeException ("One variable is duplicate");
														}
break;
case 14:
//#line 144 "./sinj.y"
{ yyval = new ArrayList<VariableDefinition> ();}
break;
case 15:
//#line 145 "./sinj.y"
{ 
																yyval = val_peek(1);  
																for (VariableDefinition vd: (ArrayList<VariableDefinition>)val_peek(0)) 
																	if(!((ArrayList<VariableDefinition>)yyval).contains(vd))
																		((ArrayList<VariableDefinition>)yyval).add(vd); 
																	else
																		throw new RuntimeException("One field struct is duplicate");
																}
break;
case 16:
//#line 156 "./sinj.y"
{yyval = new ArrayList<Sentence> (); }
break;
case 17:
//#line 157 "./sinj.y"
{ 
												yyval = val_peek(1);
												((ArrayList<Sentence>)yyval).add((Sentence)val_peek(0)); 
												 }
break;
case 18:
//#line 163 "./sinj.y"
{ 
							yyval = new ArrayList<Expression>();
							((ArrayList<Expression>)yyval).add((Expression)val_peek(0)); 
							}
break;
case 19:
//#line 167 "./sinj.y"
{
					 							yyval = val_peek(2);
												((ArrayList<Expression>)yyval).add((Expression)val_peek(0)); 
												}
break;
case 20:
//#line 174 "./sinj.y"
{ yyval = new ArrayList<Expression> (); }
break;
case 29:
//#line 187 "./sinj.y"
{yyval = new Write(lexico.getLine(), lexico.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 30:
//#line 188 "./sinj.y"
{ yyval = new Read(lexico.getLine(), lexico.getColumn(), (List<Expression>)val_peek(1)); }
break;
case 31:
//#line 189 "./sinj.y"
{ yyval = new Assign(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 32:
//#line 190 "./sinj.y"
{ yyval = new Invocation (lexico.getLine(), lexico.getColumn(), (String) val_peek(4), (List<Expression>)val_peek(2)); }
break;
case 33:
//#line 191 "./sinj.y"
{ yyval = new Return (lexico.getLine(), lexico.getColumn(), (Expression) val_peek(1)); }
break;
case 34:
//#line 192 "./sinj.y"
{ yyval = new While (lexico.getLine(), lexico.getColumn(), (Expression) val_peek(4), (List<Sentence>) val_peek(1)); }
break;
case 35:
//#line 195 "./sinj.y"
{ 
									List<Sentence> ifBody = new ArrayList<Sentence> ();
									ifBody.add((Sentence) val_peek(0));
									yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), ifBody, new ArrayList<Sentence>());
									}
break;
case 36:
//#line 200 "./sinj.y"
{
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.add((Sentence) val_peek(2));
													elseBody.add((Sentence)val_peek(0));
													yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(4), ifBody, elseBody);	
													}
break;
case 37:
//#line 207 "./sinj.y"
{
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.addAll((List<Sentence>) val_peek(4));
													elseBody.add((Sentence)val_peek(2));
													yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(6), ifBody, elseBody);
													 }
break;
case 38:
//#line 215 "./sinj.y"
{
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.addAll((List<Sentence>) val_peek(6));
													elseBody.addAll((List<Sentence>)val_peek(4));
													yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(8), ifBody, elseBody);
													 }
break;
case 39:
//#line 223 "./sinj.y"
{
													List<Sentence> ifBody = new ArrayList<Sentence> ();
													List<Sentence> elseBody = new ArrayList<Sentence> ();
													ifBody.addAll((List<Sentence>) val_peek(2));
													yyval = new If(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(4), ifBody, elseBody);
	}
break;
case 40:
//#line 234 "./sinj.y"
{ yyval = new TypeInteger(); }
break;
case 41:
//#line 235 "./sinj.y"
{ yyval = new TypeDouble(); }
break;
case 42:
//#line 236 "./sinj.y"
{ yyval = new TypeChar(); }
break;
case 43:
//#line 237 "./sinj.y"
{ yyval = createArray((Type)val_peek(3), Integer.parseInt(String.valueOf((val_peek(1)))));/*$$ = new TypeArray((Type)$1, Integer.parseInt(String.valueOf(($3))));*/ }
break;
case 44:
//#line 238 "./sinj.y"
{ yyval = new TypeVoid (); }
break;
case 45:
//#line 239 "./sinj.y"
{ 
							List <VariableDefinition> variables = (ArrayList<VariableDefinition>) val_peek(1);
							List <FieldDefinition> fieldsStruct = new ArrayList<FieldDefinition> ();
							for (VariableDefinition vd : variables) {
								FieldDefinition field = new FieldDefinition (vd.getLine(), vd.getColumn(), vd.getType(), vd.getVariable());
								fieldsStruct.add(field);
							}
							
							yyval = new TypeStruct (fieldsStruct);
							}
break;
case 46:
//#line 253 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 47:
//#line 254 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 48:
//#line 255 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 49:
//#line 256 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 50:
//#line 257 "./sinj.y"
{ yyval=  new Arithmetic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 51:
//#line 258 "./sinj.y"
{ yyval=  new AccesoArray(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(3), (Expression)val_peek(1)); }
break;
case 52:
//#line 259 "./sinj.y"
{ yyval = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 53:
//#line 260 "./sinj.y"
{ yyval = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 54:
//#line 261 "./sinj.y"
{ yyval = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 55:
//#line 262 "./sinj.y"
{ yyval = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 56:
//#line 263 "./sinj.y"
{ yyval = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 57:
//#line 264 "./sinj.y"
{ yyval = new Comparation(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 58:
//#line 265 "./sinj.y"
{ yyval = new Logic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 59:
//#line 266 "./sinj.y"
{ yyval = new Logic(lexico.getLine(), lexico.getColumn(), (Expression)val_peek(2), (String)val_peek(1) , (Expression)val_peek(0)); }
break;
case 60:
//#line 267 "./sinj.y"
{ yyval= new UnaryNegation (lexico.getLine(), lexico.getColumn(), (Expression)val_peek(0)); }
break;
case 61:
//#line 268 "./sinj.y"
{ yyval= new Literal (lexico.getLine(), lexico.getColumn(), (Integer) val_peek(0)); }
break;
case 62:
//#line 269 "./sinj.y"
{ yyval = new Variable (lexico.getLine(), lexico.getColumn(), val_peek(0).toString()); }
break;
case 63:
//#line 270 "./sinj.y"
{ yyval= new RealNumber (lexico.getLine(), lexico.getColumn(), (Double) val_peek(0)); }
break;
case 64:
//#line 271 "./sinj.y"
{ yyval= val_peek(1);}
break;
case 65:
//#line 272 "./sinj.y"
{ yyval = new FieldAccess (lexico.getLine(), lexico.getColumn(), (Expression) val_peek(2), new Variable (lexico.getLine(), lexico.getColumn(), val_peek(0).toString()));}
break;
case 66:
//#line 273 "./sinj.y"
{ yyval = new FunctionInvocation (lexico.getLine(), lexico.getColumn(), (String) val_peek(3), (List<Expression>)val_peek(1)); }
break;
case 67:
//#line 274 "./sinj.y"
{ yyval = new Cast (lexico.getLine(), lexico.getColumn(), (Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 68:
//#line 275 "./sinj.y"
{ yyval = new LogicalNegation (lexico.getLine(), lexico.getColumn(), (Expression)val_peek(0)); }
break;
case 69:
//#line 276 "./sinj.y"
{ yyval = new Char (lexico.getLine(), lexico.getColumn(), val_peek(0).toString()); }
break;
//#line 1070 "Parser.java"
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
