package genetic.wordguesser.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import genetic.wordguesser.Main;

import org.junit.*;

import static org.junit.Assert.*;

public class WordGuessTest {
	private String result;
	private PrintStream testOut;
	
	@Before
	public void setUp() {
		result = "";
		OutputStream nullOutputStream = new OutputStream () {
			@Override
			public void write(int b) throws IOException {}
		};
		testOut = new PrintStream (nullOutputStream) {
			@Override
			public void println(String string) {result = result + string + "\n";}
		};
	}
	
	@Test
	public void mainTest(){
		Main.setOutput(testOut);
		String[] args = {"Test", "50", "1000"};
		Main.main(args);
		assertEquals("After 1000 iterations the result was:\n"
				+ "\tExpected: Test\n"
				+ "\tGot: Test\n"
				+ "\tHit rate: 100.0%\n", result);
	}
}
