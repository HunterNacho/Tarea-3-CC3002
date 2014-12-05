package genetic.wordguesser;

import java.io.PrintStream;

import genetic.framework.core.Individual;
import genetic.framework.core.Population;

/**
 * Main class for the word-searching genetic algorithm.
 * It currently only works with sequences of letters without any spaces
 * or numbers in between.
 * @author Ignacio Cuevas
 *
 */
public class Main {
	/**
	 * Output for the simulation.
	 * May be changed for testing purposes.
	 */
	private static PrintStream output = System.out;
	
	/**
	 * Output setter. Used for testing purposes.
	 * @param newOut Desired output.
	 */
	public static void setOutput(PrintStream newOut) {
		output = newOut;
	}
	/**
	 * Executes the genetic algorithm that attempts to guess a word.
	 * @param args Console arguments.
	 * If present, they are used for the word to guess, population size
	 * and number of iterations (in that order). Otherwise, these are set
	 * to the respective default values of "example", 50 and 20.
	 */
	public static void main(String[] args) {
		String guessWord = args.length > 0 ? args[0] : "example";
		int populationSize = args.length > 1 ? Integer.parseInt(args[1]) : 50;
		int loopCount = args.length > 2 ? Integer.parseInt(args[2]) : 20;
		WordGuesserProblem problem = new WordGuesserProblem(guessWord);
		WordFactory factory = new WordFactory();
		factory.setWordLength(guessWord.length());
		Population population = new Population(factory, problem);
		population.setElitism(2);
		population = Population.beginSimulation
				(population, populationSize, loopCount);
		Individual result = population.fittestIndividual();
		output.println("After "+loopCount+" iterations the result was:");
		output.println("\tExpected: " + guessWord);
		output.println("\tGot: " + result.genesAsString());
		output.println("\tHit rate: " + (100.0 * result.fitness(problem.solve())) / guessWord.length() + "%");
	}

}
