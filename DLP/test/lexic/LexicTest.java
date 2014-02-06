package lexic;

import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexic.Lexic;

import org.junit.Before;
import org.junit.Test;

import sintactico.Parser;

public class LexicTest {
	
	private FileReader fileReader;
	private Lexic lexic;
	@SuppressWarnings("unused")
	private Parser parser;
	private List<Integer> tokens;
	
	
	@Before
	public void setUp() {
		tokens = new ArrayList<Integer>();
	}

	@Test
	public void testBasicComments() throws IOException {
		addTokensFromFileNamed("only_comments.txt");
		assertTrue(tokens.isEmpty());
		
	}
	
	@Test
	public void testBasicReservedWords () throws IOException {
		addTokensFromFileNamed("reservedWords.txt");
		assertTrue(tokens.get(0) == Parser.IF);
		assertTrue(tokens.get(1) == Parser.ELSE);
		assertTrue(tokens.get(2) == Parser.WHILE);
		assertTrue(tokens.get(3) == Parser.INT);
		assertTrue(tokens.get(4) == Parser.DOUBLE);
		assertTrue(tokens.get(5) == Parser.CHAR);
		assertTrue(tokens.get(6) == Parser.MAIN);
		assertTrue(tokens.get(7) == Parser.READ);
		assertTrue(tokens.get(8) == Parser.WRITE);
		assertTrue(tokens.get(9) == Parser.STRUCT);
	}
	
	@Test
	public void testRealNumbers () throws IOException {
		addTokensFromFileNamed("realNumbers.txt");
		for (int i = 0; i <= 9; i++)
			assertTrue(tokens.get(i) == Parser.CTE_REAL);
	}
	
	
	private void addTokensFromFileNamed (String nameOfFile) throws IOException {
		fileReader = new FileReader(nameOfFile);
		lexic = new Lexic(fileReader);
		parser = new Parser(lexic);
		
		int token;
		while ((token=lexic.yylex())!=0)
			tokens.add(token);
	}
	

}
