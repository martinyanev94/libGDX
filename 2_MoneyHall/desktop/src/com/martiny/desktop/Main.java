package com.martiny.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.martiny.moneyhall.Monty;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = "MoneyHall";
		cfg.width = 800;
		cfg.height = 600;
		
		new LwjglApplication(new Monty(), cfg);
		
		
	}
}

