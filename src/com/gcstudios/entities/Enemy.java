package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.gcstudios.main.Game;
import com.gcstudios.world.AStar;
import com.gcstudios.world.Vector2i;
import com.gcstudios.world.World;



public class Enemy extends Entity{
	
	public static boolean ghostMode = false;
	public int ghostFrames = 0;
	
	//tempo do ghost
	public int ghostMaxFrames = 200;
	
	public static int frames = 0, maxframes = 20, index = 0, maxindex = 1;

	protected BufferedImage[] sprites5;
	
	public static int dir=0;

	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		this.x = x;
		this.y = y;
		sprites5 = new BufferedImage[2];
		
		sprites5[0] = Game.spritesheet.getSprite(48, 64, 16, 16);
		sprites5[1] = Game.spritesheet.getSprite(48, 80, 16, 16);
		
	}

	public void tick(){
		depth = 0;
		
		if (!isCollidingWithPlayer()) {
			
				IA();

		} else if(isCollidingWithPlayer() && ghostMode == false){
			
			Game.player.life--;
			
		}
		
		if(ghostMode == true) {
			ghostFrames++;
			if(ghostFrames == ghostMaxFrames) {
				
				ghostFrames = 0;
				ghostMode = false;
				
			}
			
		}
		
		/*
		//ghost mode
			ghostFrames++;
			if(ghostFrames == nextTime) {
				
				ghostFrames =0;
				
				if(ghostMode == false) {
					
					ghostMode = true;
					
				} else {
					
					ghostMode = false;
					
				}
			}
			*/
			//andando
			frames++;

			if (frames == maxframes) {

				frames = 0;
				index++;

				if (index > maxindex) {

					index = 0;
				}

			}
		
	}
	
	public void IA() {
		
		if(path == null || path.size() == 0) {
			Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
			Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
			path = AStar.findPath(Game.world, start, end);
		}
	
		if(new Random().nextInt(100) < 80)
			followPath(path);
		
		if(x % 16 == 0 && y % 16 == 0) {
			if(new Random().nextInt(100) < 10) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
				path = AStar.findPath(Game.world, start, end);
			}
		}
		
		
	}
	

	public boolean isCollidingWithPlayer() {

		Rectangle enemyCurrent = new Rectangle(this.getX(), this.getY(), width, height);

		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), World.TILE_SIZE, World.TILE_SIZE);

		return enemyCurrent.intersects(player);
	}
	

	public void destroySelf() {

		//Game.enemies.remove(this);
		Game.entities.remove(this);
		return;

	}
	
	public void render(Graphics g) {
		
	
	}
}
