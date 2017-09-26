

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class contains the main method which starts a match of badminton.
 * @author Andrew Liu
 * @version 5-23-2016
 */
public class Badminton {
	public static void main(String args[]) {
		Match drawing = new Match();
	
		
		drawing.init();
		JFrame window = new JFrame();
		window.setSize(1200, 600);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(drawing);
		window.setVisible(true);
		drawing.startMatch();
	}	
}