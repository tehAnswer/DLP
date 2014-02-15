package ast;

import java.util.List;

import ast.sentence.Sentence;

/** Represents the root element of the abstract syntax tree. One program consist of many sentences.
 * @author Sergio
 *
 */
public class Program extends AbstractNodeAST {
	
	public List<Sentence> sentences;
	public List<VariableDefinition> definitions;

	public Program (int line, int column, List<Sentence> sentences, List<VariableDefinition> definitions) {
		super(line, column);
		this.sentences = sentences;
		this.definitions = definitions;
		
	}

}
