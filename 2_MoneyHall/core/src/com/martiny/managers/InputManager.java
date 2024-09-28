package com.martiny.managers;

import com.martiny.gameobjects.Door;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputManager
{
	public static void handleInput(final OrthographicCamera camera) {
		if (Gdx.input.justTouched()) {
			GameManager.temp.set((float)Gdx.input.getX(), (float)Gdx.input.getY(), 0.0f);
			camera.unproject(GameManager.temp);
			final float touchX = GameManager.temp.x;
			final float touchY = GameManager.temp.y;
			for (int i = 0; i < GameManager.doors.size; ++i) {
				final Door door = (Door)GameManager.doors.get(i);
				
				if(!door.isOpen && handleDoor(door, touchX, touchY, i)) {
					break;
				
				}
			}
			
			handleRestart(touchX, touchY);
		}
	}
	
	public static boolean handleDoor(final Door door, final float touchX, final float touchY, final int doorIndex) {
		if (touchX >= door.position.x && touchX <= door.position.x + door.width && touchY >= door.position.y && touchY <= door.position.y + door.height) {
			switch (GameManager.level) {
			case START: {
				((Door)GameManager.doors.get(GameManager.getGoatindices(doorIndex).random())).isOpen = true;
				GameManager.level = GameManager.Level.CONFIRM;
				break;
			}
			case CONFIRM: {
				door.isOpen = true;
				GameManager.level = GameManager.Level.END;
				if (!door.isGoat) {
					GameManager.hasWon = true;
					break;
				}
				break;
			}
			}
			return true;
		}
		return false;
	}
	public static void handleRestart(final float touchX, final float touchY) {
		if(touchX >= GameManager.restartSprite.getX() && touchX <= GameManager.restartSprite.getX() + GameManager.restartSprite.getWidth() && touchY >= GameManager.restartSprite.getY() && touchY <= GameManager.restartSprite.getY() + GameManager.restartSprite.getHeight()) {
	    GameManager.restartGame();
}
	}
	
				}

