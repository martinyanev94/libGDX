package com.martiny.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.martiny.managers.GameManager;

public class Ball {
	
	public Sprite ballSprite; 
	    
	public Vector2 position = new Vector2(); 
	public Vector2 velocity = new Vector2(); 
	    
	public final Vector2 gravity = new Vector2(0,-0.4f); 
	
	public Circle ballCircle;
	    
	public boolean isAlive; 
	
	public void update() {
		
		velocity.add(gravity);
		position.add(velocity);
		ballSprite.setPosition(position.x, position.y); 
		ballCircle.setPosition(position.x + (ballSprite.getWidth()/2), (position.y + ballSprite.getHeight()/2));
		checkCollisions();
		  
		       
	}
	    
	public void render(SpriteBatch batch) {
		ballSprite.draw(batch);
			}
	public boolean checkCollisionsWithGround() {
		 
		if(position.y<=0.0) {
			GameManager.groundSound.play();
			
			isAlive=false;
			return true;
		}
		return false;
	}
	  
	public boolean checkCollisionsWithBasket() {
		
		if(Intersector.overlaps(ballCircle, GameManager.basket.basketRectangle)) {
			GameManager.basketSound.play();
			GameManager.score++;
			if(GameManager.score>GameManager.highScore) {
				GameManager.highScore = GameManager.score;
			}
			
			isAlive=false;
			return true;
		}
		return false;
	}
	
	public void checkCollisions() {
		checkCollisionsWithGround();
		checkCollisionsWithBasket();
	}
		
}
