package geneticframework.core;

import java.util.ArrayList;

/**
 * Interface for problem objects.
 * These represent an specific problem to be solved
 * by a genetic algorithm.
 * @author Ignacio Cuevas
 *
 */
public interface Problem {
	/**
	 * Computes the solution of the problem
	 * as a genotype, which is represented
	 * as an ArrayList of integers.
	 * @return Genotype that corresponds to the
	 * solution of the problem.
	 */
	public ArrayList<Integer> solve();
}
