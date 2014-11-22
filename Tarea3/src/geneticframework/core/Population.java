package geneticframework.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Framework class for the population.
 * A collection of individuals that can evolve.
 * @author Ignacio Cuevas
 *
 */
public class Population {
	/**
	 * Members of the population.
	 */
	private ArrayList<Individual> individuals;
	
	/**
	 * Tournament size.
	 * Default value is 5, but can be
	 * modified using setTournamentSize().
	 */
	private int tournamentSize = 5;
	
	/**
	 * Object representing the current problem
	 */
	private Problem problem;
	
	/**
	 * Solution for the current problem.
	 * Expressed as an ArrayList of integers, since
	 * it's a collection of genes.
	 * Computed when the Population object is created.
	 */
	private ArrayList<Integer> solution;
	/**
	 * Individual factory.
	 * Responsible for the creation of the population
	 * members.
	 */
	private IndividualFactory factory;
	
	/**
	 * Elitism offset.
	 * Used to decide how many of the best
	 * individuals of a certain generation pass directly to the next.
	 * Defaults to 0, but can be modified using setElitism();
	 */
	private int elitismOffset = 0;

	/**
	 * Public constructor.
	 * Sets the Individual factory.
	 * @param individualFactory An instance from a class
	 * that implements IndividualFactory.
	 * Used to create the population's individuals.
	 */
	public Population(IndividualFactory individualFactory, Problem problem) {
		this.factory= individualFactory;
		this.problem = problem;
		this.solve();
		this.individuals = new ArrayList<Individual>();
	}
	
	/**
	 * Elitism offset setter.
	 * @param newOffset Desired elitismOffset.
	 */
	public void setElitism(int newOffset) {
		this.elitismOffset = newOffset;
	}
	
	/**
	 * Gets the number of individuals based on the size
	 * of the population attribute.
	 * @return Number of individuals in the population.
	 */
	public int numberOfIndividuals(){
		return this.individuals.size();
	}
	
	
	/**
	 * Sets the solution of the population.
	 * Delegates the responsibility of computing it
	 * to the Problem object.
	 */
	public void solve(){
		this.solution = problem.solve();
	}
	
	/**
	 * Tournament size setter.
	 * Used to modify the default value if required.
	 * @param newSize Desired size for the tournament.
	 */
	public void setTournamentSize(int newSize) {
		this.tournamentSize = newSize;
	}
	
	/**
	 * Sorts the population according to the fitness of its individuals.
	 * Fittest individuals are placed at the top.
	 * This is used to get the fittest individuals in an easier way.
	 */
	public void sortByFitness(){
		Collections.sort(this.individuals, new Comparator<Individual>(){
			@Override
			public int compare(Individual anIndividual, Individual anotherIndividual) {
				return anotherIndividual.fitness(solution) - anIndividual.fitness(solution);
			}
		});
	}
	
	/**
	 * Gets the n fittest individuals of the population.
	 * @param n Number of individuals requested.
	 * @return An ArrayList containing the n fittest individuals
	 * of the population.
	 */
	public ArrayList<Individual> getFittest(int n){
		ArrayList<Individual> fittest = new ArrayList<Individual>();
		this.sortByFitness();
		for (int i = 0; i < n; i++){
			fittest.add(this.individuals.get(i));
		}
		return fittest;
	}
	
	
	/**
	 * Generates a new set of individuals for the population.
	 * Each of the individuals has their genes set.
	 * @param size Size of the population to be generated.
	 */
	public void generatePopulation(int size) {
		individuals = new ArrayList<Individual>();
		for (int i = 0; i < size; i++) {
			individuals.add(factory.createIndividual());
		}
	}
	/**
	 * Creates a new population that has evolved from the current one
	 * and should be more fit to solve the problem.
	 * @return A new population generated according to the
	 * defined rules.
	 */
	public Population evolve(){
		//Propagate old generation's properties.
		Population newPopulation = new Population(this.factory, this.problem);
		newPopulation.setTournamentSize(this.tournamentSize);
		newPopulation.setElitism(this.elitismOffset);
		newPopulation.individuals.addAll(this.getFittest(this.elitismOffset));
		//Loop over the population size and
		//create new individuals with crossover.
		for (int i = elitismOffset; i < this.numberOfIndividuals(); i++) {
			Individual newIndividual = factory.createIndividual(); 
			newIndividual.childOf(this.tournamentSelection(), this.tournamentSelection());
			newPopulation.individuals.add(newIndividual);
		}
		
		//Mutate population
		for(int index = elitismOffset; index < this.numberOfIndividuals(); index++)
			newPopulation.individuals.get(index).mutate();
		return newPopulation;
	}

	/**
	 * Picks some random individuals from the population, and takes the fittest. 
	 * @return The fittest individual from the subset of the population.
	 */
	private Individual tournamentSelection() {
		Population newPopulation = new Population(this.factory, this.problem);
		for(int i = 0; i < this.tournamentSize; i++) {
			int randomIndex = (int)(Math.random()*this.numberOfIndividuals());
			newPopulation.individuals.add(this.individuals.get(randomIndex));
		}
		return newPopulation.fittestIndividual();
	}
	/**
	 * Gets the fittest individual of the population.
	 * @return The fittest individual of this population.
	 */
	public Individual fittestIndividual() {
		this.sortByFitness();
		return this.individuals.get(1);
	}
}
