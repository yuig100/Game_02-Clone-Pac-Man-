package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Ghost3 extends Enemy{

	
	public BufferedImage[] sprites3;

	
	public Ghost3(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprites3 = new BufferedImage[10];
		
		for(int i =0;i < 10;i++) {

			sprites3[i] = Game.spritesheet.getSprite(16 * i, 32, 16, 16);
			
		}
	}
	
	public void render(Graphics g) {
		
		if(ghostMode == false) {
			
			g.drawImage(sprites3[Enemy.index],this.getX() ,this.getY(), width, height, null);
			
		}else {
			
			g.drawImage(sprites5[Enemy.index],this.getX() ,this.getY(), width, height, null);
			
		}
	
	}

}
