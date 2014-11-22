package wordguesser;

import geneticframework.core.Individual;
import geneticframework.core.IndividualFactory;

public class WordFactory implements IndividualFactory {
	private int wordLength;
	@Override
	public Individual createIndividual() {
		Individual individual = new WordIndividual();
		individual.setNumberOfGenes(wordLength);
		return individual;
	}
}