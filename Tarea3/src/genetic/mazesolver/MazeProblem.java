package genetic.mazesolver;

import java.util.ArrayList;
import genetic.framework.core.Problem;

/**
 * Class that represents a maze problem.
 * @author Ignacio Cuevas
 *
 */
public class MazeProblem implements Problem {
	/**
	 * Exit's position in the rows;
	 */
	private int exitX;
	/**
	 * Exit's position in the columns.
	 */
	private int exitY;
	
	/**
	 * Sets exitX and exitY according to the
	 * coordinates passed as arguments.
	 * @param x Row of the exit.
	 * @param y Column of the exit.
	 */
	public void setExit(int x, int y) {
		this.exitX = x;
		this.exitY = y;
	}
	
	@Override
	public ArrayList<Integer> solve() {
		ArrayList<Integer> answer = new ArrayList<Integer>();
		answer.add(this.exitX);
		answer.add(this.exitY);
		return answer;
	}
}
