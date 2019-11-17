package ui;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;
import java.awt.*;

import static com.sparanzza.constants.Constants.BOARD_HEIGHT;
import static com.sparanzza.constants.Constants.GAME_SPEED;

public class GamePanel extends JPanel {
	private ImageIcon backgroundImage;
	private Timer timer;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}
	
	private void initializeVariables() {
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.timer = new Timer(GAME_SPEED, new GameLoop(this));
		this.timer.start();
	}
	
	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, BOARD_HEIGHT));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		System.out.println("REPAINT");
		
	}
	
	public void doOneLoop() {
		update();
		repaint();
	}
	
	private void update() {
		System.out.println("UPDATE");
	}
}
