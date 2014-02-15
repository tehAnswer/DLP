package ast;

import introspector.view.IntrospectorTree;
import introspector.model.IntrospectorModel;

import java.util.*;

import ast.expression.Arithmetic;
import ast.expression.Expression;
import ast.expression.Literal;
import ast.expression.UnaryNegation;
import ast.expression.Variable;
import ast.sentence.Assign;
import ast.sentence.Read;
import ast.sentence.Sentence;
import ast.sentence.Write;

public class ASTTest {

	/**
	 * Prueba de creación de un AST. El programa de entrada es: read a,b; a =
	 * (-b+3)*c/2; write a,c*2;
	 */
	private static NodeAST crearArbol() {
		Sentence sentencia1, sentencia2, sentencia3;
		// * Primera Línea
		List<Expression> expresiones = new ArrayList<Expression>();
		expresiones.add(new Variable(1, 6, "a"));
		expresiones.add(new Variable(1, 8, "b"));
		sentencia1 = new Read(1, 1, expresiones);
		// * Segunda Línea
		sentencia2 = new Assign(2, 3, new Variable(2, 1, "a"), new Arithmetic(
				2, 11, new Arithmetic(2, 8, new UnaryNegation(2, 6, new Variable(
						2, 7, "b")), "+", new Literal(2, 9, 3)), "*",
				new Arithmetic(2, 13, new Variable(2, 12, "c"), "/",
						new Literal(2, 14, 2))));

		// * Tercera Línea
		expresiones = new ArrayList<Expression>();
		expresiones.add(new Variable(3, 7, "a"));
		expresiones.add(new Arithmetic(3, 10, new Variable(3, 9, "c"), "*",
				new Literal(3, 11, 2)));
		sentencia3 = new Write(3, 1, expresiones);
		// * Construimos y devolvemos el árbol
		List<Sentence> sentencias = new ArrayList<Sentence>();
		sentencias.add(sentencia1);
		sentencias.add(sentencia2);
		sentencias.add(sentencia3);
		return new Program(1, 1, sentencias, null);
	}

	public static void main(String[] args) {
		IntrospectorModel modelo = new IntrospectorModel("Program",
				crearArbol());
		new IntrospectorTree("Introspector", modelo);
	}
}
