package geneticframework.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import geneticframework.core.*;

import org.junit.*;

import static org.junit.Assert.*;

public class GeneticTest {
	private Problem problem;
	private IndividualFactory factory;
	private Population population;
	
	@Before
	public void setUp(){
		problem = new Problem(){
			@Override
			public ArrayList<Integer> solve() {
				ArrayList<Integer> solution = new ArrayList<Integer>(Arrays.asList(
						1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0,	1, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						1, 1, 1, 1));
				return solution;
			}
		};
		factory = new IndividualFactory(){
			public Individual createIndividual(){
				return new Individual(){
					@Override
					public List<Integer> generateGenes() {
						List<Integer> answer = new ArrayList<Integer>();
						for(int i = 0; i < this.numberOfGenes; i++)
							answer.add(Math.random()>=0.5?1:0);
						return answer;
					}
					@Override
					public void mutate() {
						for(int index = 0; index < this.numberOfGenes; index++)
							if(Math.random() < this.mutationRate()) 
								this.putGeneAt(Math.random()>=0.5?1:0, index);
						
					}
				};
			}
		};
		population = new Population(factory, problem);
		population.setElitism(1);
	}
	/**
	 * Tests that an application of the genetic algorithm
	 * actually approaches the solution in a reasonable amount.
	 */
	@Test
	public void geneticAlgorithmTest(){
		population.generatePopulation(50);
		for(int loop = 0; loop < 20; loop++) {
			population = population.evolve();
		}
		assertTrue(population.fittestIndividual().fitness(problem.solve()) > 55);
	}
}
