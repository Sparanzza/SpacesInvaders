package model;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

public class SpaceShip extends Sprite {
	
	public SpaceShip() {
		initialize();
	}
	
	private void initialize() {
		
		ImageIcon imageIcon = ImageFactory.createImage(Image.SPACESHIP);
		setImage(imageIcon.getImage());
		int start_x = Constants.BOARD_WIDTH / 2 - Constants.SPACESHIP_WIDTH / 2;
		int start_y = Constants.BOARD_HEIGHT - 100;
		
		setX(start_x);
		setY(start_y);
	}
	
	@Override
	public void move() {
	
	}
}
