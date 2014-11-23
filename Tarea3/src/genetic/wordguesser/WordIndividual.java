package genetic.wordguesser;

import java.util.ArrayList;
import java.util.List;
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
	
	@Override
	public List<Integer> generateGenes() {
		ArrayList<Integer> genes = new ArrayList<Integer>();
		for (int i = 0; i < this.numberOfGenes; i++) {
			genes.add(getRandomGene());
		}
		return genes;
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
	@Override
	public void executeMutation(int index) {
		genes.set(index, getRandomGene());
	}
	
	/**
	 * Generates a random gene.
	 * To do this, a random index corresponding
	 * to a position on the alphabet is generated
	 * @return
	 */
	public int getRandomGene(){
		int index = random.nextInt(alphabet.length());
		return (int) (alphabet.charAt(index));
	}

}