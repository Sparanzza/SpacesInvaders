package model;

import com.sparanzza.constants.Constants;
import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

public class Bomb extends Sprite {
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.BOMB);
		setImage(imageIcon.getImage());
	}
	
	@Override
	public void move() {
		this.y++;
		if (y >= Constants.BOARD_HEIGHT - Constants.BOMB_HEIGHT) {
			setDead(true);
		}
	}
}
