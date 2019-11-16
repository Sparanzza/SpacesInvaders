package ui;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;
import java.awt.*;

import static com.sparanzza.constants.Constants.BOARD_HEIGHT;

public class GamePanel extends JPanel {
	private ImageIcon backgroundImage;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}
	
	private void initializeVariables() {
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
	}
	
	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, BOARD_HEIGHT));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		
	}
}
