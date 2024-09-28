package com.martiny.catchball;

import com.badlogic.gdx.Game;
import com.martiny.catchball.*;

public class MainGame extends Game {
	
	 
@Override
public void create() {
	setScreen(new MenuScreen(this)); 
}
	

}
