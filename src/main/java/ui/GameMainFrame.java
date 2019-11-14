package ui;

import javax.swing.*;

import static com.sparanzza.constants.Constants.TTILE;

public class GameMainFrame extends JFrame {
	
	public GameMainFrame() {
		initializeLayout();
	}
	
	private void initializeLayout() {
		add(new GamePanel());
		setTitle(TTILE);
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
}
