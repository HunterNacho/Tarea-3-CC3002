package genetic.mazesolver;

import genetic.framework.core.Individual;
import genetic.framework.core.IndividualFactory;
 /**
  * PathIndividual factory.
  * Creates path individuals to match the problem's
  * conditions.
  * @author Ignacio Cuevas
  *
  */
public class PathFactory implements IndividualFactory {
	/**
	 * Maze of the problem to be solved
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
	 * Sets the attributes for the factory.
	 * @param maze Maze of the problem to be solved.
	 * @param startX Start row.
	 * @param startY Start column.
	 */
	public PathFactory(Integer[][] maze, int startX, int startY){
		this.maze = maze;
		this.startX = startX;
		this.startY = startY;
	}
	
	/**
	 * Main factory method.
	 * Generates a new PathIndividual and
	 * gives it the problem's conditions
	 * other than the exit.
	 */
	@Override
	public Individual createIndividual() {
		return new PathIndividual(maze, startX, startY);
	}

}
