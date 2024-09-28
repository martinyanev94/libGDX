package com.martiny.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.martiny.managers.GameManager;

public class  Mole {
	public Sprite moleSprite; //sprite to display the mole
	public Vector2 position =new Vector2();// The mole's position
	public float  height,width;  // the mole's dimensions 
		  
	public float scaleFactor=4000f;
		  
	public enum State {GOINGUP,GOINGDOWN,UNDERGROUND,SNED}; 
	public State state=State.GOINGUP;  
	public float currentHeight = 0.0f; 
	public float speed =2f; // speed of the mole as it goes up and down
	
	public float timeUnderGround = 0.0f;
	public float maxTimeUnderGround = 0.8f;
	
	public float stunTime = 0.1f;
	public float stunCounter = 0.0f;
	
	public Sprite stunSprite;
	
	public void update() {
		
		switch(state) {
		
		case SNED: 
			if (stunCounter>=stunTime) {
				state=State.UNDERGROUND;
				stunCounter = 0.0f;
				currentHeight = 0.0f;
				randomizeWaitTime();
			}
			else {
				stunCounter+=Gdx.graphics.getDeltaTime();
			}
			break;
	
		case UNDERGROUND:
			if(timeUnderGround>=maxTimeUnderGround) {
				state=State.GOINGUP;
				timeUnderGround=0.0f;
			}
			else {
				timeUnderGround+=Gdx.graphics.getDeltaTime();
			}
			break;
			// here increase the height till it reaches max, once it reaches, change the state  
		case GOINGUP:
			currentHeight+=speed;
			if(currentHeight>=height) {
				currentHeight=height;
				state=State.GOINGDOWN;
			}
			break;
			
			// here decrease the height till it reaches min(0), once it reaches, change the state  
		case GOINGDOWN:
			currentHeight-=speed;
			if(currentHeight<=0.0) {
				currentHeight=0.0f;
				state=State.UNDERGROUND;
			}
			break;
	
			}
			
		// draw only some portion of the mole image, depending on height
		moleSprite.setRegion(0, 0, (int)(width/scaleFactor), (int)(currentHeight/scaleFactor));
		moleSprite.setSize(moleSprite.getWidth(), currentHeight);
		
	}
	
	public void  render(SpriteBatch batch) {
			  
			  
		moleSprite.draw(batch);
		
		if(state==State.SNED) {
			stunSprite.draw(batch);
		}
	}
		
		public void randomizeWaitTime() {
			maxTimeUnderGround = (float)  Math.random()*2f;
		}
		
	
		
		public boolean handleTouch(float touchX,float touchY) {
			if((touchX>=position.x)&&touchX<=(position.x+width) && (touchY>=position.y)&&touchY<=(position.y+currentHeight) ) {
				
				stunSprite.setPosition(position.x + width -(stunSprite.getWidth()/2), position.y+currentHeight - (stunSprite.getHeight()/2));
				state = State.SNED;
				GameManager.hitSound.play();
			return true;
			}
			return false;
			
			}
		
	}
