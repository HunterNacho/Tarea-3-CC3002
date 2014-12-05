package genetic.wordguesser;

import java.util.Random;

import genetic.framework.core.Individual;

public class WordIndividual extends Individual {
	/**
	 * Random number generator.
	 * Used in a couple of methods.
	 */
	private static final Random random = new Random();
	
	/**
	 * Alphabet that is used by the words.
	 * Stored as an string constant.
	 */
	private static final String alphabet = 
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * Public constructor.
	 * Calls the super-class constructor with its integer argument.
	 * @param wordLength Length of the words to be generated.
	 * Used as the number of genes.
	 * Passed on to the super-class constructor.
	 */
	public WordIndividual(int wordLength){
		super(wordLength);
	}
	
	/**
	 * @return The word (String) that the genotype represents.
	 */
	@Override
	public String genesAsString(){
		StringBuilder sb = new StringBuilder();
		for (int gene : this.genes) {
			sb.append((char) gene);
		}
		return sb.toString();
	}
	
	/**
	 * Generates a random gene.
	 * To do this, a random index corresponding
	 * to a position on the alphabet is generated.
	 */
	@Override
	public Integer generateRandomGene() {
		int index = random.nextInt(alphabet.length());
		return (int) (alphabet.charAt(index));
	}

}