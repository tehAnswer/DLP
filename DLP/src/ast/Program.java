package ast;

import java.util.List;

import ast.sentence.Sentence;

/** Represents the root element of the abstract syntax tree. One program consist of many sentences.
 * @author Sergio
 *
 */
public class Program extends AbstractNodeAST {
	
	public List<Sentence> sentences;

	public Program (int line, int column, List<Sentence> sentences) {
		super(line, column);
		this.sentences = sentences;
		
	}

}
