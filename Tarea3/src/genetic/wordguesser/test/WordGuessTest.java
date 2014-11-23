package genetic.wordguesser.test;

import genetic.framework.core.Individual;
import genetic.framework.core.Population;
import genetic.wordguesser.WordFactory;
import genetic.wordguesser.WordGuesserProblem;
import org.junit.*;
import static org.junit.Assert.*;

public class WordGuessTest {
	@Test
	public void MainTest(){
		String guessWord = "Test";
		int populationSize = 50;
		int loopCount = 1000;
		WordGuesserProblem problem = new WordGuesserProblem(guessWord);
		WordFactory factory = new WordFactory();
		factory.setWordLength(guessWord.length());
		Population population = new Population(factory, problem);
		population.setElitism(2);
		population = Population.beginSimulation
				(population, populationSize, loopCount);
		Individual result = population.fittestIndividual();
		assertEquals(guessWord, result.genesAsString());
	}
}
