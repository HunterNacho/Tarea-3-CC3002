package genetic.wordguesser;

import java.util.ArrayList;
import genetic.framework.core.Problem;

public class WordGuesserProblem implements Problem {
	
	/**
	 * Word that the algorithm has to guess. 
	 */
	private String wordToFind;
	
	/**
	 * Public constructor.
	 * @param guessTarget The solution of the problem as an string.
	 * Gets stored in wordToFind.
	 */
	public WordGuesserProblem(String guessTarget) {
		this.wordToFind = guessTarget;
	}
	@Override
	public ArrayList<Integer> solve() {
		ArrayList<Integer> solution = new ArrayList<Integer>();
		for (int i = 0; i < this.wordToFind.length(); i++){
			char c = this.wordToFind.charAt(i);
			solution.add((int)c);
		}
		return solution;
	}

}
