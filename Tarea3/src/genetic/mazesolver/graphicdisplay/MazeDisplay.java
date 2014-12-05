package genetic.mazesolver.graphicdisplay;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MazeDisplay extends JFrame {
	private static final Color[] COLORS = 
		{Color.WHITE, Color.BLACK, Color.RED, Color.GRAY};
	private static final long serialVersionUID = 4805953726113182670L;
	private JPanel[][] mazeDisplay;
	public MazeDisplay(Integer[][] maze) {
		super("Genetic maze-solver");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int xLimit = maze.length;
		int yLimit = maze[0].length;
		this.setSize(600, 600*xLimit/yLimit);
		setLayout(new GridLayout(xLimit,yLimit));
		mazeDisplay = new JPanel[xLimit][yLimit];
		for (int i = 0; i < xLimit; i++){
			for (int j = 0; j < yLimit; j++){
				mazeDisplay[i][j] = new JPanel();
				mazeDisplay[i][j].setBackground(COLORS[maze[i][j]]);
				this.add(mazeDisplay[i][j]);
			}
		}
	}
}