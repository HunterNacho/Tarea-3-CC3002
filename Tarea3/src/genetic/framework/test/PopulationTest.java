package genetic.framework.test;

import java.util.ArrayList;
import java.util.Arrays;
import genetic.framework.core.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PopulationTest {
	private IndividualFactory factory;
	private Problem problem;
	private Population population;
	
	@Before
	public void setUp() {
		factory = new IndividualFactory() {
			@Override
			public Individual createIndividual() {
				return new Individual(5) {
					@Override
					public Integer generateRandomGene() {
						return 0;
					}
				};
			}
		};
		problem = new Problem() {
			@Override
			public ArrayList<Integer> solve() {
				return new ArrayList<Integer> (
						Arrays.asList(0, 0, 0, 0, 0));
			}
		};
		population = new Population(factory, problem);
	}
	@Test
	public void test() {
		assertTrue(true);
	}
}
