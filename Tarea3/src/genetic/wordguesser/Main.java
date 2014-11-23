package genetic.wordguesser;

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
		System.out.println("After "+loopCount+" iterations the result was:");
		System.out.println("\tExpected: " + guessWord);
		System.out.println("\tGot: " + result.genesAsString());
		System.out.println("\tHit rate: " + (100.0 * result.fitness(problem.solve())) / guessWord.length() + " %");
	}

}
