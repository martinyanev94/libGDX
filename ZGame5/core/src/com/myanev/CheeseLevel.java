//Part5
package com.myanev;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;

public class CheeseLevel extends BaseScreen
{
    private AnimatedActor mousey;
	/* Part7 */   private BaseActor cheese;
	/* Part8 */   private BaseActor floor;
	/* Part9 */   private BaseActor winText;   
    private boolean win;

	/* Part9 */ private float timeElapsed;
    private Label timeLabel;

    // game world dimensions
    final int mapWidth = 800;
    final int mapHeight = 800;
   
    public CheeseLevel(Game g)
    {
        super(g);
    }

    public void create() 
    {        
		/* Part9 */  timeElapsed = 0;
//Part8
        floor = new BaseActor();
        floor.setTexture( new Texture(Gdx.files.internal("assets/tiles-800-800.jpg")) );
        floor.setPosition( 0, 0 );
        mainStage.addActor( floor );
//Part8
//Part7
        cheese = new BaseActor();
        cheese.setTexture( new Texture(Gdx.files.internal("assets/cheese.png")) );
        cheese.setPosition( 400, 300 );
        cheese.setOrigin( cheese.getWidth()/2, cheese.getHeight()/2 );
        mainStage.addActor( cheese );
//Part7
        mousey = new AnimatedActor();

        TextureRegion[] frames = new TextureRegion[4];
        for (int n = 0; n < 4; n++)
        {   
            String fileName = "assets/mouse" + n + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
            tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames[n] = new TextureRegion( tex );
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);

        Animation anim = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP_PINGPONG);

        mousey.setAnimation( anim );
        mousey.setOrigin( mousey.getWidth()/2, mousey.getHeight()/2 );
        mousey.setPosition( 20, 20 );
        mainStage.addActor(mousey);
      //Part9
        winText = new BaseActor();
        winText.setTexture( new Texture(Gdx.files.internal("assets/you-win.png")) );
        winText.setPosition( 170, 60 );
        winText.setVisible( false );
        uiStage.addActor( winText );

        BitmapFont font = new BitmapFont();
        String text = "Time: 0";
        LabelStyle style = new LabelStyle( font, Color.NAVY );
        timeLabel = new Label( text, style );
        timeLabel.setFontScale(2);
        timeLabel.setPosition(500,440); // sets bottom left (baseline) corner?

        uiStage.addActor( timeLabel );
      //Part9

        win = false;
    }

    public void update(float dt) 
    {   
        // process input
    	//Part6
        mousey.velocityX = 0;
        mousey.velocityY = 0;

        if (Gdx.input.isKeyPressed(Keys.LEFT)) 
            mousey.velocityX -= 100;
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            mousey.velocityX += 100;;
        if (Gdx.input.isKeyPressed(Keys.UP)) 
            mousey.velocityY += 100;
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
            mousey.velocityY -= 100;
    	

        // bound mousey to the rectangle defined by mapWidth, mapHeight
        mousey.setX( MathUtils.clamp( mousey.getX(), 0,  mapWidth - mousey.getWidth() ));
        mousey.setY( MathUtils.clamp( mousey.getY(), 0,  mapHeight - mousey.getHeight() ));
    	//Part6
        // check win condition: mousey must be overlapping cheese
      //Part7.5 
        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();

        if ( !win && cheeseRectangle.contains( mouseyRectangle ) )
        {
            win = true;
            
            Action spinShrinkFadeOut = Actions.parallel(
                Actions.alpha(1),         // set transparency value
                Actions.rotateBy(360, 1), // rotation amount, duration
                Actions.scaleTo(0,0, 2),  // x amount, y amount, duration
                Actions.fadeOut(1)        // duration of fade in
            );
            cheese.addAction( spinShrinkFadeOut );
               
            Action fadeInColorCycleForever = Actions.sequence( 
                Actions.alpha(0),   // set transparency value
                Actions.show(),     // set visible to true
                Actions.fadeIn(2),  // duration of fade out
                Actions.forever(    
                    Actions.sequence(
                        // color shade to approach, duration
                        Actions.color( new Color(1,0,0,1), 1 ),
                        Actions.color( new Color(0,0,1,1), 1 )
                    )
                )
            );
			/* Part9 */ winText.addAction( fadeInColorCycleForever );
        }
        //Part7.5
//Part9
        if (!win)
        {
            timeElapsed += dt;
            timeLabel.setText( "Time: " + (int)timeElapsed );
        }
//Part9
    	//Part8
        // camera adjustment
        Camera cam = mainStage.getCamera();

        // center camera on player
        cam.position.set( mousey.getX() + mousey.getOriginX(), mousey.getY() + mousey.getOriginY(), 0 );

        // bound camera to layout
        cam.position.x = MathUtils.clamp(cam.position.x, viewWidth/2,  mapWidth - viewWidth/2);
        cam.position.y = MathUtils.clamp(cam.position.y, viewHeight/2, mapHeight - viewHeight/2);
        cam.update();
        //Part8
    }
    
    // InputProcessor methods for handling discrete input
    public boolean keyDown(int keycode)
    {
    	//Part10
        if (keycode == Keys.M) 
            game.setScreen( new CheeseMenu(game) );
                
        if (keycode == Keys.P)    
            togglePaused(); 
       
    	//Part10 
        return false;
    }

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
//Part5