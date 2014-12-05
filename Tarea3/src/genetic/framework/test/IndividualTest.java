package genetic.framework.test;

import java.util.ArrayList;
import java.util.Arrays;

import genetic.framework.core.Individual;

import org.junit.*;

import static org.junit.Assert.*;

public class IndividualTest {
	private Individual testSubject;
	private Individual testParent;
	
	@Before
	public void setUp() {
		testSubject = new Individual(10) {
			@Override
			public Integer generateRandomGene() {
				return 0;
			}
		};
		testParent = new Individual(10) {
			@Override
			public Integer generateRandomGene() {
				return 1;
			}
		};
	}
	
	@Test
	public void fitnessTest() {
		ArrayList<Integer> solution = new ArrayList<Integer>(
				Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		assertEquals(10, testSubject.fitness(solution));
	}
	
	@Test
	public void childTest() {
		testSubject.childOf(testParent, testParent);
		assertEquals(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
				testSubject.getGenes());
	}
	
	@Test
	public void asStringTest() {
		assertEquals("0000000000", testSubject.genesAsString());
	}
	
	@Test
	public void numberOfGenesTest() {
		assertEquals(10, testSubject.numberOfGenes());
	}
	
	@Test
	public void mutationTest() {
		testSubject.mutate();
		assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
				testSubject.getGenes());
	}
}
