package com.sparanzza.constants;

public class Constants {
	
	public static final String TTILE = "Space Invaders";
	public static final int BOARD_WIDTH = 900;
	public static final int BOARD_HEIGHT = 750;
	
	//Images
	
	public static final String UFO_IMAGE_URL = "resources/images/ufo.png";
	public static final String LASER_IMAGE_URL = "resources/images/laser.png";
	public static final String BOMB_IMAGE_URL = "resources/images/bomb.png";
	public static final String BACKGROUND_IMAGE_URL = "resources/images/background.jpg";
	public static final String SPACESHIP_IMAGE_URL = "resources/images/spaceship.png";
	
	//Speed
	public static final int GAME_SPEED = 10;
	
	//Sizes images
	public static final int SPACESHIP_WIDTH = 28;
	public static final int SPACESHIP_HEIGHT = 34;
	public static final int ENEMYSHIP_WIDTH = 32;
	public static final int ENEMYSHIP_HEIGHT = 24;
	public static final int ENEMYSHIP_ROW = 4;
	public static final int ENEMYSHIP_COLUMNS = 8;
	public static final int ENEMYSHIP_INIT_X = 280;
	public static final int ENEMYSHIP_INIT_Y = 100;
	public static final int BOMB_HEIGHT = 6;
	
	
	// speed of the laser
	public static final int LASER_VERTICAL_TRANSLATION = 4;
	
	//padding border 50px
	public static final int BORDER_PADDING = 50;
	
	public static final int GO_DOWN = 30;
	public static final double BOMB_DROPPING_PROBABOLITY = 0.001;
	public static final String GAME_OVER = "Game Over";
	public static final String WIN = "You Win!";
	
	private Constants() {
	}
}

