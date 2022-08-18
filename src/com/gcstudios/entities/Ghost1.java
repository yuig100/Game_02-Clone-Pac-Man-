package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Ghost1 extends Enemy{
	
	
	public static BufferedImage[] sprites1;

	public Ghost1(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		sprites1 = new BufferedImage[10];
		
		for(int i =0;i < 10;i++) {
			
			sprites1[i] = Game.spritesheet.getSprite(16 * i, 0, 16, 16);
			
		}

	}
	
	public void render(Graphics g) {
		
		if(ghostMode == false) {
			
			if(Enemy.dir == 1) {
				
				g.drawImage(sprites1[8 + Enemy.index],this.getX() ,this.getY(), width, height, null);
				
			} else if(Enemy.dir == 4) {
				
				g.drawImage(sprites1[6 +Enemy.index],this.getX() ,this.getY(), width, height, null);
				
			}
			
		}else {
			
			g.drawImage(sprites5[Enemy.index],this.getX() ,this.getY(), width, height, null);
			
		}
	
	}
	
}
