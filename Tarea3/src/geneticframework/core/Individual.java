package geneticframework.core;

import java.util.List;
/**
 * Framework class for the individuals, i.e. any of the basic entities
 * in the genetic algorithm model.
 * @author Ignacio Cuevas
 *
 */
public abstract class Individual {
	/** Genotype of the individual*/
	protected List<Integer> genes;
	
	/**
	 * Number of genes.
	 * Defaults to 64, but may be set using
	 * setNumberOfGenes().
	 */
	protected int numberOfGenes = 64;
	
	/** 
	 * Constructor for the class.
	 * Can only be called by child classes.
	 * Generates the genes for the individual.
	 */
	protected Individual() {
		genes = this.generateGenes();
	}
	
	/**
	 * Generates a set of genes for the individual.
	 * @return An integer list which contains the genes of the
	 * individual.
	 */
	public abstract List<Integer> generateGenes();
	
	/**
	 * Crossover rate getter.
	 * Defaults to 50%, but may be overridden by
	 * child classes to modify it.
	 * @return Crossover rate value.
	 */
	public double uniformRate() {
		return 0.5;
	}
	
	/**
	 * Mutation rate getter.
	 * Defaults to 0.15%, but may be overridden by
	 * child classes to modify it.
	 * @return Mutation rate value.
	 */
	public double mutationRate() {
		return 0.015;
	}
	
	/**
	 * Number of genes setter.
	 * @param newGeneNumber Desired number of genes.
	 */
	public void setNumberOfGenes(int newGeneNumber) {
		numberOfGenes = newGeneNumber;
	}
	
	/**
	 * Performs the mutation operation which is
	 * driven by the mutation rate.
	 * Used after crossover, when a new individual
	 * is generated.
	 */
	public abstract void mutate();
	
	/**
	 * Performs the crossover operation.
	 * The arguments are the parents, while the receiver
	 * is their child.
	 * The child's genes are set according to the
	 * uniform rate.
	 * @param father Father for the crossover.
	 * @param mother Mother for the crossover.
	 */
	public void childOf(Individual father, Individual mother) {
		for (int index = 0; index < this.numberOfGenes; index++) {
			if (Math.random() < this.uniformRate())
				this.putGeneAt(father.geneAt(index), index);
			else {
				this.putGeneAt(mother.geneAt(index), index);
			}
		}
	}
	
	/**
	 * Sets a new gene in the specified position.
	 * @param gene Gene to be inserted.
	 * @param index Position of the gene.
	 */
	protected void putGeneAt(Integer gene, Integer index) {
		genes.set(index, gene);
	}
	
	/**
	 * Gets the gene located in the position
	 * specified by index.
	 * @param index Position of the gene.
	 * @return Gene located at position index.
	 */
	private Integer geneAt(int index) {
		return genes.get(index);
	}
	
	/**
	 * Compute the fitness of an individual given the
	 * desired solution.
	 * The higher the number, the more fit the individual.
	 * @param solution The target for the genetic algorithm.
	 * @return Fitness of the individual.
	 */
	public int fitness(List<Integer> solution) {
		int score = 0;
		for(int index = 0; index < this.numberOfGenes; index++)
			if(solution.get(index) == this.geneAt(index)) score++;
		return score;
	}
	
	/**
	 * Generates a text version of the genotype.
	 * Useful for debugging and testing purposes.
	 * @return A String representing the genotype.
	 */
	public String genesAsString() {
		StringBuilder sb = new StringBuilder();
		for(Integer i : genes) sb.append(i);
		return sb.toString();
	}
}
