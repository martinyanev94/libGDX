package com.martiny.managers;

import com.martiny.gameobjects.Door;
import com.martiny.managers.TextManager;

import java.lang.System.Logger.Level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

public class GameManager {
	
	static Array<Door> doors; // array of the 3 doors
	static Texture doorTexture; // texture image for the door
	static Texture carTexture; // texture image for the car
	static Texture goatTexture; // texture image for the goat
	static Vector3 temp = new Vector3(); // temp vector to store input coordinates
	static IntArray goatIndices;
	
	private static final float DOOR_RESIZE_FACTOR = 2500f;
	private static final float DOOR_VERT_POSITION_FACTOR = 3f;
	private static final float DOOR1_HORIZ_POSITION_FACTOR = 7.77f;
	private static final float DOOR2_HORIZ_POSITION_FACTOR = 2.57f;
	private static final float DOOR3_HORIZ_POSITION_FACTOR = 1.52f;
	static float width,height;
	
	static Sprite restartSprite;
	static Texture restartTexture;
	
	static Texture backtexture;
	
	static Sprite backSprite;
	
	static final float RESTART_RESIZE_FACTOR = 5500f;
	
	public static enum Level {
		START,
		CONFIRM,
		END
	}
	static Level level;

	

	
	
	static boolean hasWon=false;
	public static void initialize(float width,float height) {
		    
		GameManager.width = width;
		GameManager.height= height;
		doorTexture = new Texture(Gdx.files.internal("data/door_close.png"));
		carTexture = new Texture(Gdx.files.internal("data/door_open_car.png"));
		goatTexture = new Texture(Gdx.files.internal("data/door_open_goat.png"));
		initDoors();
		
		
		
		goatIndices = new IntArray();
		level = Level.START;
		
	   TextManager.initialize(width,height);
		
		restartTexture = new Texture(Gdx.files.internal("data/restart.png"));
		restartSprite = new Sprite(restartTexture);
		restartSprite.setSize(restartSprite.getWidth()*width/RESTART_RESIZE_FACTOR, restartSprite.getHeight()*width/RESTART_RESIZE_FACTOR);
		restartSprite.setPosition(0 ,0);
		
		backtexture = new Texture(Gdx.files.internal("data/background.jpg"));
		backSprite = new Sprite(backtexture);
		backSprite.setSize(width, height);
		backSprite.setPosition(0,0f);
		
		
	}
	
	public static void renderGame(SpriteBatch batch) {
		
		backSprite.draw(batch);
		
		for(Door door : doors)
			door.render(batch);
		    TextManager.displayMessage(batch);
		    
		    restartSprite.draw(batch);
		
	}
	
	public static void dispose() {
		// dispose of the door texture to ensure no memory leaks
		doorTexture.dispose();
		carTexture.dispose();
		goatTexture.dispose();
		restartTexture.dispose();
		backtexture.dispose();
	}
	

public static void initDoors() {
	doors = new Array<Door>();
	
	// instantiate new doors and add it to the array
	for(int i=0;i<3;i++) {
		doors.add(new Door());
	}
	
	// set the doors' display position
	doors.get(0).position.set(width/DOOR1_HORIZ_POSITION_FACTOR,height/DOOR_VERT_POSITION_FACTOR);
	doors.get(1).position.set(width/DOOR2_HORIZ_POSITION_FACTOR,height/DOOR_VERT_POSITION_FACTOR);
	doors.get(2).position.set(width/DOOR3_HORIZ_POSITION_FACTOR,height/DOOR_VERT_POSITION_FACTOR);
	
	for(Door door : doors) {
		// instantiate sprite for the closed door with the texture of it
		door.closeSprite = new Sprite(doorTexture);
		
		door.openSprite = new Sprite();
		door.width = door.closeSprite.getWidth()*(width/DOOR_RESIZE_FACTOR);
		door.height = door.closeSprite.getHeight()*(width/DOOR_RESIZE_FACTOR);
		door.closeSprite.setSize(door.width, door.height);
		door.closeSprite.setPosition(door.position.x,door.position.y);
		            
		//set the dimensions for the open door
		door.openSprite.setSize(door.width, door.height);
		door.openSprite.setPosition(door.position.x, door.position.y);
	}
	
	doors.get(0).openSprite.setRegion(goatTexture);
	doors.get(0).isGoat = true;
	doors.get(1).openSprite.setRegion(carTexture);
	doors.get(1).isGoat = false;
	doors.get(2).openSprite.setRegion(goatTexture);
	doors.get(2).isGoat = true;
	
}

public static IntArray getGoatindices(int selectedDoorIndex) {
	goatIndices.clear(); // remove all previous values from the array
	
	for(int i=0;i<doors.size;i++) {
		// exclude selected door
		if(i!=selectedDoorIndex && doors.get(i).isGoat) {
			goatIndices.add(i);
		}
	}
	
	return goatIndices;
}

public static void restartGame() {
	// shuffle the positions of the doors inside the doors array
	doors.shuffle();
	        
	// reset the door positions
	doors.get(0).position.set(width/DOOR1_HORIZ_POSITION_FACTOR,height/DOOR_VERT_POSITION_FACTOR);
	doors.get(1).position.set(width/DOOR2_HORIZ_POSITION_FACTOR,height/DOOR_VERT_POSITION_FACTOR);
	doors.get(2).position.set(width/DOOR3_HORIZ_POSITION_FACTOR,height/DOOR_VERT_POSITION_FACTOR);
	
	for(int i=0;i<GameManager.doors.size;i++) {
		GameManager.doors.get(i).isOpen=false;
		// reset the sprite positions
		GameManager.doors.get(i).closeSprite.setPosition(GameManager.doors.get(i).position.x, GameManager.doors.get(i).position.y);
		GameManager.doors.get(i).openSprite.setPosition(GameManager.doors.get(i).position.x, GameManager.doors.get(i).position.y);
		
	}
	GameManager.hasWon=false;
	// reset the level
	GameManager.level = GameManager.level.START;
}

		}
	




