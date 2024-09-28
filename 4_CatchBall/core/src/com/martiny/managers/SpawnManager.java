package com.martiny.managers;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Pool;
import com.martiny.objects.Ball;

public class SpawnManager {
	
	static float delayTime = 0.8f; 
	static float delayCounter=0.0f; 
	    
	static float width,height; 
	    
	static Texture ballTexture; 
	    
	private static final float BALL_RESIZE_FACTOR = 2500f;
	       
	static IntArray removeIndices = new IntArray(); 
	    
	static Random random = new Random(); 
	    
	public static void initialize(float width,float height,Texture ballTexture) {
		SpawnManager.width=width;
		SpawnManager.height=height;
		SpawnManager.ballTexture=ballTexture;
		delayCounter=0.0f;
		ballPool.clear(); 
	}
	    
	private final static Pool<Ball> ballPool = new Pool<Ball>() {
		@Override
		protected Ball newObject() {
			Ball ball = new Ball();
			            
			ball.ballSprite = new Sprite(ballTexture);
			return ball;
		}
	};
	
	    
	public static Ball resetBall(Ball ball) {
		    	
		ball.ballSprite.setSize(ball.ballSprite.getTexture().getWidth()*(width/BALL_RESIZE_FACTOR),ball.ballSprite.getTexture().getHeight()*(width/BALL_RESIZE_FACTOR));
		ball.position.set(random.nextInt((int) (width - ball.ballSprite.getWidth())), height-ball.ballSprite.getHeight());
		ball.velocity.set(0, 0);
		ball.isAlive=true;
		
		Vector2 center = new Vector2();
		
		center.x = ball.position.x + (ball.ballSprite.getWidth()/2);
		center.y = ball.position.y + (ball.ballSprite.getHeight()/2);
		
		ball.ballCircle = new Circle(center, (ball.ballSprite.getHeight()/2));
		
		return ball;
	}
	    
	public static void cleanup(Array<Ball> balls) {
		removeIndices.clear(); 
		for(int i=balls.size-1;i>=0;i--) {
			if(!balls.get(i).isAlive) {
				removeIndices.add(i); 
			}
		}
		        
		for (int i =0 ;i< removeIndices.size;i++) {
			Ball ball= balls.removeIndex(i);
			ballPool.free(ball);
		}
		                
	}
	    
	public static void run(Array<Ball> balls) {
		        
		if(delayCounter>=delayTime) {
			Ball ball= ballPool.obtain(); 
			resetBall(ball); 
			balls.add(ball); 
			delayCounter=0.0f;
		}
		else {
			delayCounter+=Gdx.graphics.getDeltaTime(); 
			}
		}
	}
