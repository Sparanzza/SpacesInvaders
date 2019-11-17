package ui;

import com.sparanzza.callbacks.GameEventListener;
import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;
import model.Laser;
import model.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static com.sparanzza.constants.Constants.BOARD_HEIGHT;
import static com.sparanzza.constants.Constants.GAME_SPEED;

public class GamePanel extends JPanel {
	private ImageIcon backgroundImage;
	private Timer timer;
	private boolean inGame = true;
	private SpaceShip spaceShip;
	private Laser laser;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
	}
	
	private void initializeVariables() {
		this.spaceShip = new SpaceShip();
		this.laser = new Laser();
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.timer = new Timer(GAME_SPEED, new GameLoop(this));
		this.timer.start();
	}
	
	private void initializeLayout() {
		addKeyListener(new GameEventListener(this));
		setFocusable(true);
		setPreferredSize(new Dimension(Constants.BOARD_WIDTH, BOARD_HEIGHT));
	}
	
	private void drawPlayer(Graphics g) {
		g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
	}
	
	private void drawLaser(Graphics g) {
		// if the laser if dead, not show
		if (!laser.isDead()) {
			g.drawImage(laser.getImage(), laser.getX(), laser.getY(), this);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		doDrawing(g);
	}
	
	private void doDrawing(Graphics g) {
		if (inGame) {
			drawPlayer(g);
			drawLaser(g);
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
		this.spaceShip.move();
		this.laser.move();
	}
	
	public void keyPressed(KeyEvent e) {
		this.spaceShip.keyPressed(e);
		// if user hits SPACE the a new laser beam is generated
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			int laserX = this.spaceShip.getX();
			int laserY = this.spaceShip.getY();
			if (inGame && laser.isDead()) {
				System.out.println("LASER BEAM!");
				laser = new Laser(laserX, laserY);
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		this.spaceShip.keyReleased(e);
	}
}
