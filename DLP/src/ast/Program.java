package ast;

import java.util.List;

import ast.sentence.Sentence;
import ast.sentence.definition.Definition;
import ast.sentence.definition.VariableDefinition;

/** Represents the root element of the abstract syntax tree. One program consist of many sentences.
 * @author Sergio
 *
 */
public class Program extends AbstractNodeAST {
	
	public List<Sentence> sentences;
	public List<Definition> definitions;
	public List<VariableDefinition> localVariables;

	public Program (int line, int column, List<Sentence> sentences, List<Definition> definitions, List<VariableDefinition> localVariables ) {
		super(line, column);
		this.localVariables = localVariables;
		this.sentences = sentences;
		this.definitions = definitions;
		
	}

}
