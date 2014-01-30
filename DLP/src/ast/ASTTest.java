package ast;

import introspector.view.IntrospectorTree;
import introspector.model.IntrospectorModel;

import java.util.*;

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
		sentencia2 = new Assign(2, 3, new Variable(2, 1, "a"), new Aritmetica(
				2, 11, new Aritmetica(2, 8, new MenosUnario(2, 6, new Variable(
						2, 7, "b")), "+", new LiteralEntero(2, 9, 3)), "*",
				new Aritmetica(2, 13, new Variable(2, 12, "c"), "/",
						new LiteralEntero(2, 14, 2))));

		// * Tercera Línea
		expresiones = new ArrayList<Expression>();
		expresiones.add(new Variable(3, 7, "a"));
		expresiones.add(new Aritmetica(3, 10, new Variable(3, 9, "c"), "*",
				new LiteralEntero(3, 11, 2)));
		sentencia3 = new Write(3, 1, expresiones);
		// * Construimos y devolvemos el árbol
		List<Sentence> sentencias = new ArrayList<Sentence>();
		sentencias.add(sentencia1);
		sentencias.add(sentencia2);
		sentencias.add(sentencia3);
		return new Program(1, 1, sentencias);
	}

	public static void main(String[] args) {
		IntrospectorModel modelo = new IntrospectorModel("Program",
				crearArbol());
		new IntrospectorTree("Introspector", modelo);
	}
}
