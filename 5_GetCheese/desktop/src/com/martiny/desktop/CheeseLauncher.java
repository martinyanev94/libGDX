package com.martiny.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.martiny.CheeseGame;

public class CheeseLauncher {
	public static void main (String[] arg) {
		
		CheeseGame  config = new CheeseGame();
		LwjglApplication launcher = new LwjglApplication( config );
	}
}
