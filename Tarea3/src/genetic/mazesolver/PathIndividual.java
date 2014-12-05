package genetic.mazesolver;

import java.util.List;
import java.util.Random;
import genetic.framework.core.Individual;

/**
 * Individuals representing paths on a maze.
 * @author Ignacio Cuevas
 *
 */
public class PathIndividual extends Individual {
	/**
	 * Interface designed for converting numbers into
	 * coordinates transformations.
	 * @author Ignacio Cuevas
	 *
	 */
	public static interface Move {
		/**
		 * Transforms the row coordinate according to the
		 * direction represented by the move.
		 * @param currentX Current row.
		 * @return New row coordinate.
		 */
		public int transformX(int currentX);
		/**
		 * Transforms the column coordinate according to the
		 * direction represented by the move.
		 * @param currentY Current column.
		 * @return New column coordinate.
		 */
		public int transformY(int currentY);
	}
	/**
	 * An array moves, each of which represents a possible
	 * direction of movement.
	 */
	public static final Move[] DIRECTIONS =
		{
		new Move() {
			public int transformX(int currentX){return currentX;}
			public int transformY(int currentY){return currentY + 1;} 
		},
		new Move() {
			public int transformX(int currentX){return currentX - 1;}
			public int transformY(int currentY){return currentY;}
		},
		new Move() {
			public int transformX(int currentX){return currentX;}
			public int transformY(int currentY){return currentY - 1;}
		},
		new Move() {
			public int transformX(int currentX){return currentX + 1;}
			public int transformY(int currentY){return currentY;}
		}
		};
	/**
	 * Maze of the problem to be solved.
	 */
	private Integer[][] maze;
	/**
	 * Start row.
	 */
	private int startX;
	/**
	 * Start column.
	 */
	private int startY;
	
	/**
	 * Constructor for the class.
	 * Sets the attributes related to the problem
	 * that has to be solved.
	 * <br>
	 * Calls the super-constructor specifying that
	 * the number of genes must be the longest possible
	 * move without loops inside the maze. That is, the
	 * number of genes is width*height of the maze.
	 * @param maze Matrix representing the maze.
	 * Contains free spots and walls.
	 * @param startX Start row.
	 * @param startY Start column.
	 */
	public PathIndividual(Integer[][] maze, int startX, int startY) {
		super(maze.length * maze[0].length);
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
	}
	
	/**
	 * Generates a random direction represented as a number.
	 * <br>
	 * While the move may not be valid, this is not checked
	 * here but afterwards.
	 */
	public Integer generateRandomGene() {
		int gene = new Random().nextInt(4);
		return gene;
	}
	
	/**
	 * Computes the fitness of an individual given the desired solution.
	 * The higher the number, the more fit the individual.
	 * <br>
	 * The fitness here is calculated according to how close to the exit
	 * the path generated leads in the maze.
	 * If at any point a move is invalid, the calculation stops and
	 * the value returned corresponds to the best score the valid part 
	 * of the path got.
	 * <br>
	 * If a valid part of the path actually gets to the exit, the fitness
	 * is increased based on how short the path is, this way the algorithm
	 * attempts to not only get to exit, but also get there as quick as 
	 * possible.
	 * <br>
	 * After the fitness has been calculated, the number of steps that
	 * is required to get to the best part of the path is recorded in
	 * the number of genes attribute to explicit the fact that only
	 * up to that point the path is relevant.
	 * @param coordinates Exit's coordinates.
	 */
	@Override
	public int fitness(List<Integer> coordinates) {
		int maxDistance = maze.length + maze[0].length;
		int fitness = 0;
		int currentX = startX;
		int currentY = startY;
		int solutionSteps = 0;
		int exitX = coordinates.get(0);
		int exitY = coordinates.get(1);
		int counter = 0;
		for (int move : this.genes){
			int newFitness = maxDistance - Math.abs(currentX - exitX)
					- Math.abs(currentY - exitY);
			if (newFitness > fitness) {
				solutionSteps = counter;
				fitness = newFitness;
			}
			if (currentX == exitX && currentY == exitY) {
				fitness += maze.length * maze[0].length - solutionSteps;
				break;
			}
			currentX = DIRECTIONS[move].transformX(currentX);
			currentY = DIRECTIONS[move].transformY(currentY);
			if (!isPositionValid(currentX, currentY)
					|| maze[currentX][currentY] == 1) {break;}
			counter++;
		}
		this.numberOfGenes = solutionSteps;
		return fitness;
	}
	
	/**
	 * Checks if a given position is valid, that is, if it's
	 * inside the maze's boundaries.
	 * @param x Row coordinates to be checked.
	 * @param y Column coordinate to be checked.
	 * @return True if the position is valid. False otherwise.
	 */
	private boolean isPositionValid(int x, int y) {
		return x >= 0 && x < maze.length && y >=0 && y < maze[0].length;
	}
}