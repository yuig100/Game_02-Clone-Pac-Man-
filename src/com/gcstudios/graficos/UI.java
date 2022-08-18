package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Ma�as: " + Game.frutas_atual + "/" + Game.frutas_Contagem, 10, 20);
	}
	
}
