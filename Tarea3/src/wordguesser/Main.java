package wordguesser;

public class Main {
	public static void main(String[] args) {
		String guessWord = args.length > 1 ? args[1] : "example";
		WordGuesserProblem problem = new WordGuesserProblem(guessWord);
		
	}

}
