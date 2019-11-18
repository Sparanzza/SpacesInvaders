package ui;

import com.sparanzza.callbacks.GameEventListener;
import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;
import model.Bomb;
import model.EnemyShip;
import model.Laser;
import model.SpaceShip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.sparanzza.constants.Constants.*;

public class GamePanel extends JPanel {
	private ImageIcon backgroundImage;
	private Timer timer;
	private boolean inGame = true;
	private SpaceShip spaceShip;
	private Laser laser;
	
	//Emenies list
	private int direction = 1;
	private List<EnemyShip> enemyShipList;
	private int enemyShipPadding = 50;
	
	private List<Bomb> bombsList;
	private Random generator;
	private String message;
	
	private int deaths;
	
	public GamePanel() {
		initializeVariables();
		initializeLayout();
		initializeGame();
	}
	
	private void initializeGame() {
		for (int i = 0; i < Constants.ENEMYSHIP_ROW; i++) {
			for (int j = 0; j < Constants.ENEMYSHIP_COLUMNS; j++) {
				EnemyShip enemyShip = new EnemyShip(Constants.ENEMYSHIP_INIT_X + enemyShipPadding * j,
						Constants.ENEMYSHIP_INIT_Y + enemyShipPadding * i);
				this.enemyShipList.add(enemyShip);
			}
		}
	}
	
	private void initializeVariables() {
		this.enemyShipList = new ArrayList<>();
		this.bombsList = new ArrayList<>();
		this.spaceShip = new SpaceShip();
		this.laser = new Laser();
		this.backgroundImage = ImageFactory.createImage(Image.BACKGROUND);
		this.timer = new Timer(GAME_SPEED, new GameLoop(this));
		this.timer.start();
		this.generator = new Random();
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
			drawAliens(g);
			drawBombs(g);
		} else {
			if (timer.isRunning())
				timer.stop();
			drawGameOver(g);
		}
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawGameOver(Graphics g) {
		g.drawImage(backgroundImage.getImage(), 0, 0, null);
		Font font = new Font("Helvetica", Font.BOLD, 50);
		FontMetrics fontMetrics = this.getFontMetrics(font);
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(message, (BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2, BOARD_HEIGHT / 2 - 100);
	}
	
	private void drawBombs(Graphics g) {
		for (Bomb b : this.bombsList) {
			if (!b.isDead()) {
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}
	
	private void drawAliens(Graphics g) {
		for (EnemyShip es : this.enemyShipList) {
			if (es.isVisible()) {
				g.drawImage(es.getImage(), es.getX(), es.getY(), this);
			}
		}
	}
	
	public void doOneLoop() {
		update();
		repaint();
	}
	
	private void update() {
		if (spaceShip.isDead()) {
			inGame = false;
			message = Constants.GAME_OVER;
		}
		
		if (deaths == this.enemyShipList.size()) {
			inGame = false;
			message = Constants.WIN;
		}
		
		this.spaceShip.move();
		
		if (!laser.isDead()) {
			
			int shotX = laser.getX();
			int shotY = laser.getY();
			
			for (EnemyShip alien : this.enemyShipList) {
				if (!alien.isVisible()) continue;
				int alienX = alien.getX();
				int alienY = alien.getY();
				
				// collision detection algorithm
				if (shotX >= (alienX) && shotX <= (alienX + ENEMYSHIP_WIDTH)
						&& shotY >= (alienY) && shotY <= (alienY + ENEMYSHIP_HEIGHT)) {
					alien.setVisible(false);
					laser.setDead(true);
					deaths++;
				}
			}
			
			this.laser.move();
		}
		
		for (EnemyShip es : this.enemyShipList) {
			// on the right
			if ((es.getX() >= BOARD_WIDTH - 2 * BORDER_PADDING) && direction == 1 || ((es.getX() < BORDER_PADDING) && direction == -1)) {
				direction *= -1;
				
				Iterator<EnemyShip> ufoIterator = enemyShipList.iterator();
				while (ufoIterator.hasNext()) {
					EnemyShip ufo = ufoIterator.next();
					ufo.setY(ufo.getY() + GO_DOWN);
				}
			}
			
			if (es.isVisible()) {
				es.move(direction);
			}
			// generate bombs by ufo
			if (es.isVisible() && generator.nextDouble() < Constants.BOMB_DROPPING_PROBABOLITY) {
				Bomb bomb = new Bomb(es.getX(), es.getY());
				this.bombsList.add(bomb);
			}
		}
		for (Bomb b : bombsList) {
			
			int bombX = b.getX();
			int bombY = b.getY();
			int spaceShipX = spaceShip.getX();
			int spaceShipY = spaceShip.getY();
			
			if (!b.isDead() && !spaceShip.isDead()) {
				if (bombX >= (spaceShipX) && bombX <= (spaceShipX + SPACESHIP_WIDTH)
						&& bombY >= (spaceShipY) && bombY <= (spaceShipY + SPACESHIP_HEIGHT)) {
					spaceShip.setDead(true);
					b.setDead(true);
				}
			}
			
			
			if (!b.isDead()) {
				b.move();
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		this.spaceShip.keyPressed(e);
		// if user hits SPACE the a new laser beam is generated
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			int laserX = this.spaceShip.getX();
			int laserY = this.spaceShip.getY();
			if (inGame && laser.isDead()) {
				laser = new Laser(laserX, laserY);
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		this.spaceShip.keyReleased(e);
	}
}
