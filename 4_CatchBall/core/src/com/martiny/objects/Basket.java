package com.martiny.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Basket {
	    
	public Sprite basketSprite; 
	public Rectangle basketRectangle = new Rectangle();
	
	public void render(SpriteBatch batch) {
		basketSprite.draw(batch);
	}
	
	public void setPosition(float x,float y) {
		basketSprite.setPosition(x, y);
		basketRectangle.setPosition(x,y);
		
	}
	public void handleTouch(float x, float y) {
		if(x-(basketSprite.getWidth()/2)>0.0) {
			setPosition(x-(basketSprite.getWidth()/2),0);
		}
	}
	}
