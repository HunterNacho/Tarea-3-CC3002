package genetic.mazesolver;

import genetic.framework.core.Individual;
import genetic.framework.core.IndividualFactory;
import genetic.framework.core.Population;
import genetic.mazesolver.graphicdisplay.MazeDisplay;
import javax.swing.JFrame;

/**
 * Main class for the maze-solver.
 * Defines the problem, runs the simulation and
 * then shows the result on-screen.
 * @author Ignacio Cuevas
 *
 */
public class Main {
	
	/**
	 * Runs the simulation.
	 * @return The final state of the maze.
	 */
	public static Integer[][] generateResult() {
		Integer[][] maze =
			{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			 {0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
			 {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
		int startX = 15;
		int startY = 15;
		int endX = 0;
		int endY = 0;
		int currentX = startX;
		int currentY = startY;
		MazeProblem problem = new MazeProblem();
		problem.setExit(endX, endY);
		IndividualFactory factory = new PathFactory(maze, startX, startY);
		Population population = new Population(factory, problem);
		population.setElitism(2);
		population = Population.beginSimulation(population, 50, 1000);
		Individual answer = population.fittestIndividual();
		for (int i = 0; i < answer.numberOfGenes(); i++){
			int move = answer.geneAt(i);
			currentX = PathIndividual.DIRECTIONS[move].transformX(currentX);
			currentY = PathIndividual.DIRECTIONS[move].transformY(currentY);
			maze[currentX][currentY] = 3;
		}
		maze[startX][startY] = 2;
		return maze;
	}
	
	/**
	 * Main function.
	 * Runs the simulation and displays the result.
	 * @param args Console arguments. Ignored.
	 */
	public static void main(String[] args) {
		Integer[][] maze = generateResult();
		JFrame frame = new MazeDisplay(maze);
		frame.setVisible(true);
	}
}
