//Part1
package com.myanev.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.myanev.CheeseGame;

public class CheeseLauncher
{
    public static void main (String[] args)
    {
        CheeseGame myProgram = new CheeseGame();
        LwjglApplication launcher = new LwjglApplication( myProgram );
    }
}
//Part1