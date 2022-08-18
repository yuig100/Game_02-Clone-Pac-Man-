package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	protected BufferedImage[] sprite_left;
	protected BufferedImage[] sprite_right;
	protected BufferedImage[] sprite_up;
	protected BufferedImage[] sprite_down;
	
	private boolean moved = false;
	
	private int frames = 0, maxframes = 5, index = 0, maxindex = 1;

	int dir=1;
	
	public int life = 1;
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		
		this.x = x;
		this.y = y;
		
		sprite_left = new BufferedImage[2];
		sprite_right = new BufferedImage[2];
		sprite_up = new BufferedImage[2];
		sprite_down = new BufferedImage[2];
		
		
			sprite_left[0] = Game.spritesheet.getSprite(0,80,16,16);
			sprite_left[1] = Game.spritesheet.getSprite(16,80,16,16);
			
			sprite_right[0] = Game.spritesheet.getSprite(0,64,16,16);
			sprite_right[1] = Game.spritesheet.getSprite(16,64,16,16);
			
			sprite_up[0] = Game.spritesheet.getSprite(0,112,16,16);
			sprite_up[1] = Game.spritesheet.getSprite(0,96,16,16);
			
			sprite_down[0] = Game.spritesheet.getSprite(16,96,16,16);
			sprite_down[1] = Game.spritesheet.getSprite(16,112,16,16);
		
	}
	
	public void tick(){
		depth = 1;
		moved = false;
		
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			dir = 1;
			moved = true;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			dir = -1;
			moved = true;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			dir = 2;
			moved = true;
			
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			dir = -2;
			moved = true;
		}
		
		if (moved) {

			frames++;
			if (frames == maxframes) {

				frames = 0;
				index++;

				if (index > maxindex) {

					index = 0;
				}

			}

		}

		verificaPegaFruta();
		verificaPegaCereja();
		
		if(Game.frutas_atual == Game.frutas_Contagem) {
			
			World.restartGame(null);
			return;
			
		}
		
		if (life <= 0) {
			
			life = 1;
			World.restartGame(null);
			return;
			
		}

	}
	
	public void verificaPegaFruta() {
		
		for(int i=0;i < Game.entities.size();i++) {
			
			Entity current = Game.entities.get(i);
			
			if(current instanceof Fruta) {
				
				if(Entity.isColidding(this, current)) {
					
					Game.frutas_atual++;
					Game.entities.remove(i);
					return;
					
				}
				
			}
			
		}
		
	}
	
	public void verificaPegaCereja() {
		
		for(int i=0;i < Game.entities.size();i++) {
			
			Entity current = Game.entities.get(i);
			
			if(current instanceof Cereja) {
				
				if(Entity.isColidding(this, current)) {
					
					Game.entities.remove(i);
					Enemy.ghostMode = true;
					return;
					
				}
				
			}
			
		}
		
	}
	
	public void render(Graphics g) {
				
		if(dir == 1) {
			
			g.drawImage(sprite_right[index],this.getX() ,this.getY(), width, height, null);
			
		} else if(dir == -1){
			
			g.drawImage(sprite_left[index],this.getX() ,this.getY(), width, height, null);
			
		}else if(dir == 2) {
			
			g.drawImage(sprite_up[index],this.getX() ,this.getY(), width, height, null);
			
		} else if(dir == -2) {
			
			g.drawImage(sprite_down[index],this.getX() ,this.getY(),width, height, null);
			
		}
		
	}
	
}
