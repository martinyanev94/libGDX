package com.martiny.gameobjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Door
{
    public Sprite openSprite;
    public Sprite closeSprite;
    public boolean isOpen;
    public boolean isGoat;
    public Vector2 position;
    public float height;
    public float width;
    
    public Door() {
        this.isOpen = false;
        this.isGoat = false;
        this.position = new Vector2();
    }
    
    public void render(final SpriteBatch batch) {
        if (this.isOpen) {
            this.openSprite.draw((Batch)batch);
        }
        else {
            this.closeSprite.draw((Batch)batch);
        }
    }
}