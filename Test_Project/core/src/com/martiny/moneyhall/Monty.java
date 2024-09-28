package com.martiny.moneyhall;
// Part1

import com.martiny.managers.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.ApplicationListener;
import com.martiny.managers.GameManager;

public class Monty implements ApplicationListener
{

	
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private float w;
    private float h;
    
    public void create() {
        this.w = (float)Gdx.graphics.getWidth();
        this.h = (float)Gdx.graphics.getHeight();
        (this.camera = new OrthographicCamera(this.w, this.h)).setToOrtho(false);
        this.batch = new SpriteBatch();
        GameManager.initialize(this.w, this.h);
    }
    
    public void dispose() {
        this.batch.dispose();
        GameManager.dispose();
    }
    
    public void render() {
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16384);
        this.batch.setProjectionMatrix(this.camera.combined);
        InputManager.handleInput(this.camera);
        this.batch.begin();
        GameManager.renderGame(this.batch);
        this.batch.end();
    }
    
    public void resize(final int width, final int height) {
    }
    
    public void pause() {
    }
    
    public void resume() {
    }
}
//Part1