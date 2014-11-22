package wordguesser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import geneticframework.core.Individual;

public class WordIndividual extends Individual {
	private final Random random = new Random();
	private final String alphabet = 
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	@Override
	public List<Integer> generateGenes() {
		ArrayList<Integer> genes = new ArrayList<Integer>();
		for (int i = 0; i < this.numberOfGenes; i++) {
			genes.add(getRandomGene());
		}
		return genes;
	}
	
	public String genesToString(){
		StringBuilder sb = new StringBuilder();
		for (int gene : this.genes) {
			sb.append((char) gene);
		}
		return sb.toString();
	}
	@Override
	public void mutate() {
		int randomIndex = random.nextInt(this.numberOfGenes);
		genes.set(randomIndex, getRandomGene());
	}
	
	public int getRandomGene(){
		int index = random.nextInt(alphabet.length());
		return (int) (alphabet.charAt(index));
	}

}