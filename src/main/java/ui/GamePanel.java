package ui;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;
import model.SpaceShip;

import javax.swing.*;
import java.awt.*;

import static com.sparanzza.constants.Constants.BOARD_HEIGHT;
import static com.sparanzza.constants.Constants.GAME_SPEED;

public class GamePanel extends JPanel {
	private ImageIcon backgroundImage;
	private Timer timer;
	private boolean inGame = true;
	private SpaceShip spaceShip;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}
	
	private void initializeVariables() {
		this.spaceShip = new SpaceShip();
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.timer = new Timer(GAME_SPEED, new GameLoop(this));
		this.timer.start();
	}
	
	private void initializeLayout() {
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, BOARD_HEIGHT));
	}
	
	private void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		doDrawing(g);
		System.out.println("REPAINT");
		
	}
	
	private void doDrawing(Graphics g) {
		if (inGame) {
			drawPlayer(g);
		} else {
			if (timer.isRunning())
				timer.stop();
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void doOneLoop() {
		update();
		repaint();
	}
	
	private void update() {
		System.out.println("UPDATE");
	}
}
