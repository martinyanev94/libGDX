//Part1
package com.myanev;

import com.badlogic.gdx.Game;
public class CheeseGame extends Game
{
    public void create() 
    {  
        CheeseLevel cm = new CheeseLevel(this); //Change to CheeseLevel to avoid MenuScreen CheeseMenu for Menu
        setScreen( cm );
    }
}
//Part1