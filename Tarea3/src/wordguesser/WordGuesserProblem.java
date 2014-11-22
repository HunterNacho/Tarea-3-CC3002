package wordguesser;

import java.util.ArrayList;
import geneticframework.core.Problem;

public class WordGuesserProblem implements Problem {
	private String wordToFind;
	
	public WordGuesserProblem(String guessTarget) {
		this.wordToFind = guessTarget;
	}
	@Override
	public ArrayList<Integer> solve() {
		ArrayList<Integer> solution = new ArrayList<Integer>();
		for (int i = 0; i < this.wordToFind.length(); i++){
			char c = this.wordToFind.charAt(i);
			solution.add(Character.getNumericValue(c));
		}
		return solution;
	}

}
