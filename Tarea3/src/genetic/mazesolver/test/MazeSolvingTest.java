package genetic.mazesolver.test;

import genetic.framework.core.Individual;
import genetic.framework.core.Population;
import genetic.mazesolver.Main;
import genetic.mazesolver.MazeProblem;
import genetic.mazesolver.PathFactory;
import genetic.mazesolver.PathIndividual;

import org.junit.*;

import static org.junit.Assert.*;

public class MazeSolvingTest {
	private Integer[][] maze = {
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
			};
	private int startX, startY, endX, endY;
	private MazeProblem problem;
	private PathFactory factory;
	private Population population;
	
	@Before
	public void setUp() {
		startX = startY = 4;
		endX = endY = 0;
		problem = new MazeProblem();
		problem.setExit(endX, endY);
		factory = new PathFactory(maze, startX, startY);
		population = new Population(factory, problem);
		population.setElitism(2);
	}
	
	@Test
	public void solvingTest() {
		int currentX = startX;
		int currentY = startY;
		population = Population.beginSimulation(population, 50, 1000);
		Individual answer = population.fittestIndividual();
		for (int i = 0; i < answer.numberOfGenes(); i++){
			int move = answer.geneAt(i);
			currentX = PathIndividual.DIRECTIONS[move].transformX(currentX);
			currentY = PathIndividual.DIRECTIONS[move].transformY(currentY);
			maze[currentX][currentY] = 3;
		}
		maze[startX][startY] = 2;
		Integer expected = 3;
		assertEquals(expected, maze[endX][endY]);
	}
	
	@Test
	public void mainTest() {
		Integer[][] mainMaze = Main.generateResult();
		Integer expected = 3;
		assertEquals(expected, mainMaze[0][0]);
	}
}
