package ui;

import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

import static com.sparanzza.constants.Constants.TTILE;

public class GameMainFrame extends JFrame {
	
	public GameMainFrame() {
		initializeLayout();
	}
	
	private void initializeLayout() {
		add(new GamePanel());
		setTitle(TTILE);
		setIconImage(ImageFactory.createImage(Image.SPACESHIP).getImage());
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	}
	
}
