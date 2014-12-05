package genetic.mazesolver.test;

import java.util.ArrayList;
import java.util.Arrays;
import genetic.mazesolver.MazeProblem;
import org.junit.*;
import static org.junit.Assert.*;

public class MazeProblemTest {
	private MazeProblem problem;
	
	@Before
	public void setUp() {
		problem = new MazeProblem();
	}
	
	@Test
	public void solveTest() {
		ArrayList<Integer> expected = 
				new ArrayList<Integer>(Arrays.asList(0, 0));
		ArrayList<Integer> actual = problem.solve();
		assertEquals(expected, actual);
	}
	
	@Test
	public void setExitTest() {
		ArrayList<Integer> expected = 
				new ArrayList<Integer>(Arrays.asList(3, 3));
		problem.setExit(3, 3);
		assertEquals(expected, problem.solve());
	}
}
