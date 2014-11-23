package genetic.framework.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import genetic.framework.core.*;
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
					public void executeMutation(int index) {
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
		population = Population.beginSimulation(population, 50, 20);
		assertTrue(population.fittestIndividual().fitness(problem.solve()) > 50);
	}
}
