package genetic.wordguesser;

import genetic.framework.core.Individual;
import genetic.framework.core.IndividualFactory;

/**
 * WordIndividual factory.
 * <p>
 * Generates new individuals of the class
 * WordIndividual based on the word length that
 * must be given to the factory.
 * @author IgnacioCuevas
 *
 */
public class WordFactory implements IndividualFactory {
	/**
	 * Length of the word that is the problem's solution.
	 * <p>
	 * Must be given to the factory on its constructor, otherwise
	 * there's no way to know the length of the words to be generated.
	 */
	private int wordLength;
	
	/**
	 * wordLength setter.
	 * @param n Desired value for wordLength.
	 */
	public void setWordLength(int n){
		wordLength = n;
	}
	/**
	 * Main factory method.
	 * Generates a new Individual.
	 * @return A new instance of WordIndividual
	 * with a number of genes determined by wordLength.
	 */
	@Override
	public Individual createIndividual() {
		return new WordIndividual(wordLength);
	}
}