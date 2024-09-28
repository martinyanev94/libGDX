package com.martiny.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.martiny.objects.Basket;
import com.martiny.objects.Ball;

public class GameManager { 
	public static Basket basket;
	static Texture basketTexture; 
	public static com.badlogic.gdx.graphics.g2d.Sprite backgroundSprite; 
	public static Texture backgroundTexture; 
	
	public static Sprite backButtonSprite;
	public static Texture backButtonTexture;
	
	public static Sound groundSound;
	public static Sound basketSound;
	
	public static Music backgroundMusic;
	
	static Ball ball;
	    
	static Texture ballTexture; 
	
	private static final float BALL_RESIZE_FACTOR = 2500f;    
	private static float BASKET_RESIZE_FACTOR = 3000f;
	private static float BACK_BTN_RESIZE_FACTOR = 1500f;
	
	public static Array<Ball> balls = new Array<Ball>();
	
	public static int score;
	public static int highScore;
	static Preferences prefs;
	
	    
	    
	public static void initialize(float width,float height) {
		
		basket = new Basket();
		basketTexture = new Texture(Gdx.files.internal("data/basket.png"));
		basket.basketSprite = new Sprite(basketTexture);
		basket.basketSprite.setSize(basket.basketSprite.getWidth()*(width/BASKET_RESIZE_FACTOR), basket.basketSprite.getHeight()*(width/BASKET_RESIZE_FACTOR));        
		basket.setPosition(0, 0);
		
		basket.basketRectangle.setSize(basket.basketSprite.getWidth(), basket.basketSprite.getHeight());

		backgroundTexture = new Texture(Gdx.files.internal("data/background.jpg"));
		backgroundSprite= new Sprite(backgroundTexture);
		        
		backgroundSprite.setSize(width, height);
		        
		ballTexture = new Texture(Gdx.files.internal("data/ball.png"));
		
		SpawnManager.initialize(width, height, ballTexture);
		score = 0;
		TextManager.initialize(width, height);
		
		prefs = Gdx.app.getPreferences("My Preferences");
		highScore = prefs.getInteger("highscore");
		
		backButtonTexture = new Texture(Gdx.files.internal("data/backbutton.png"));
		  
		backButtonSprite= new Sprite(backButtonTexture);
		backButtonSprite.setSize(backButtonSprite.getWidth()*(width/BACK_BTN_RESIZE_FACTOR), backButtonSprite.getHeight()*(width/BACK_BTN_RESIZE_FACTOR));
		
		backButtonSprite.setPosition(width/2- backButtonSprite.getWidth()/2, height*0.935f);
		
		Gdx.input.setCatchBackKey(true);
		
		groundSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/groundHit.wav"));
		basketSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/basketHit.wav"));
		
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/backmusic.mp3")); 
		backgroundMusic.setLooping(true); 
		backgroundMusic.play(); 
	
	}
	    
	public static void renderGame(SpriteBatch batch) {
		backgroundSprite.draw(batch);
		basket.render(batch);
		
		SpawnManager.run(balls);
		for(Ball ball : balls) {
			
			if(ball.isAlive) {
				ball.update();
				ball.render(batch);
			}
		}
		
		SpawnManager.cleanup(balls);   
		TextManager.displayMessage(batch);
		backButtonSprite.draw(batch);
		
		prefs.putInteger("highscore", score);
		prefs.flush();
	}    
	    
	public  static void dispose() {
		backgroundTexture.dispose();
		basketTexture.dispose();   
		ballTexture.dispose();
		backButtonTexture.dispose();
		groundSound.dispose();
		basketSound.dispose();
		backgroundMusic.dispose();
		balls.clear();
		     
	}
}

