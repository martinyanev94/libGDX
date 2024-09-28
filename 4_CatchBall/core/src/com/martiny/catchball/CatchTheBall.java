package com.martiny.catchball;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.martiny.managers.GameManager;
import com.martiny.managers.InputManager;

public class CatchTheBall implements Screen {
	SpriteBatch batch; 
	OrthographicCamera camera;
	
	public static MainGame game;
	    
	
	    
	public CatchTheBall (MainGame game) {
		
		CatchTheBall.game = game;
				
				
				
		float height= Gdx.graphics.getHeight();
		float width = Gdx.graphics.getWidth();
				
		camera = new OrthographicCamera(width,height);
				
		camera.setToOrtho(false);
				
		batch = new SpriteBatch();
				
		GameManager.initialize(width, height);
		
		Gdx.input.setInputProcessor(new InputManager(camera));
			    
			    
	}
	
	@Override
	public void dispose() {
				
		batch.dispose();
		GameManager.backgroundMusic.stop();
		GameManager.dispose();
	}
	
	@Override
	public void render (float delta) {
				
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
				
		batch.setProjectionMatrix(camera.combined);
				
				
		batch.begin();	
		GameManager.renderGame(batch);		
		batch.end();
		
				
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
		
	}

	}
