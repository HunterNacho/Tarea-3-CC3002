package geneticframework.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Population {
	/**
	 * Members of the population.
	 */
	private ArrayList<Individual> population;
	
	/**
	 * Solution for the current problem.
	 * Computed when a new population is processed.
	 */
	private ArrayList<Integer> solution;
	/**
	 * Individual factory.
	 * Responsible for the creation of the population
	 * members.
	 */
	private IndividualFactory factory;
	
	/**
	 * Elitism offset getter.
	 * Used to decide how many of the best
	 * individuals of a certain generation pass directly to the next.
	 * Defaults to 0, but may be overridden by child classes to modify it.
	 * @return Elitism offset.
	 */
	public int elitismOffset() {
		return 0;
	}
	
	/**
	 * Constructor for the class.
	 * Can only be called by child classes.
	 * Sets the Individual factory.
	 * @param individualFactory An instance from a class
	 * that implements IndividualFactory.
	 * Used to create the population's individuals.
	 */
	protected Population(IndividualFactory individualFactory) {
		this.factory = individualFactory;
	}
	
	/**
	 * Used in place of the constructor to get new instances according to
	 * the specific implementation.
	 * @return A new instance of the class.
	 */
	public abstract Population newInstance();
	
	/**
	 * Computes the solution for the current problem.
	 * @return
	 */
	public abstract ArrayList<Integer> solve();
	
	/**
	 * Tournament size getter.
	 * Defaults to 5, but may be overridden by
	 * child classes to modify it.
	 * @return Default value for tournament size.
	 */
	public int tournamentSize() {
		return 5;
	}
	
	/**
	 * Sorts the population according to the fitness of its individuals.
	 * Fittest individuals are placed at the top.
	 * This is used to get the fittest individuals in an easier way.
	 */
	public void sortByFitness(){
		Collections.sort(this.population, new Comparator<Individual>(){
			@Override
			public int compare(Individual anIndividual, Individual anotherIndividual) {
				return anotherIndividual.fitness(solution) - anIndividual.fitness(solution);
			}
		});
	}
	
	/**
	 * Gets the n fittest individuals of the population.
	 * @param n Number of individuals requested.
	 * @return
	 */
	public ArrayList<Individual> getFittest(int n){
		ArrayList<Individual> fittest = new ArrayList<Individual>();
		this.sortByFitness();
		for (int i = 0; i < n; i++){
			fittest.add(this.population.get(i));
		}
		return fittest;
	}
	
	
	/**
	 * Generates a new set of individuals for the population.
	 * Each of the individuals has their genes set.
	 * @param size Size of the population to be generated.
	 */
	public void generatePopulation(int size) {
		population = new ArrayList<Individual>();
		for (int i = 0; i < size; i++) {
			population.add(factory.createIndividual());
		}
		this.solve();
	}

	public Population evolve(){
		Population newPopulation = this.newInstance();
		
		return newPopulation;
	}
}
