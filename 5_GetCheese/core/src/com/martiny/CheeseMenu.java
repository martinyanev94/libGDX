package com.martiny;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class CheeseMenu extends BaseScreen
{
	public CheeseMenu(Game g)
	{
		super(g);
	}
	    
	public void create() 
	{        
		BaseActor background = new BaseActor();
		background.setTexture( new Texture(Gdx.files.internal("assets/tiles-menu.jpg")) );
		uiStage.addActor( background );
		
		BaseActor titleText = new BaseActor();
		titleText.setTexture( new Texture(Gdx.files.internal("assets/cheese-please.png")) );
		titleText.setPosition( 20, 100 );
		uiStage.addActor( titleText );
		
		BitmapFont font = new BitmapFont();
		String text = " Press S to start, M for main menu ";
		LabelStyle style = new LabelStyle( font, Color.YELLOW );
		Label instructions = new Label( text, style );
		instructions.setFontScale(2);
		instructions.setPosition(100, 50); 
		
		instructions.addAction( 
		Actions.forever( 
		Actions.sequence(
		Actions.color( new Color(1, 1, 0, 1), 0.5f ),
		Actions.delay( 0.5f ),
		Actions.color( new Color(0.5f, 0.5f, 0, 1), 0.5f )
		)
		)
		);
		uiStage.addActor( instructions );
		}
		
		public void update(float dt) 
		{   
		              
		              }
		                  
		                  
		public boolean keyDown(int keycode)
		     {
		         if (keycode == Keys.S) 
		            game.setScreen(new CheeseLevel(game));
		         return false;
	         
	}

		@Override
		public boolean scrolled(float amountX, float amountY) {
			// TODO Auto-generated method stub
			return false;
		}
}