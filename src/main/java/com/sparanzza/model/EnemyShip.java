package com.sparanzza.model;

import com.sparanzza.images.Image;
import com.sparanzza.images.ImageFactory;

import javax.swing.*;

public class EnemyShip extends Sprite {
	
	private boolean visible = true;
	
	public EnemyShip(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}
	
	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.UFO);
		setImage(imageIcon.getImage());
	}
	
	@Override
	public void move() {
	
	}
	
	public void move(int direction) {
		this.x += direction;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
