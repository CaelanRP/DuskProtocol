package duskProtocol;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DuskMain {
	public static final int winX = 800;
	public static final int winY = 600;
	private int scrollSpeed = 4;
	
	private int maxFPS = 60;

	public void startGame(){

		//Create wandow
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();

		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0, winX, 0, winY, 1, -1); //set matrix to size of window
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    
	    
	    Level level = new Level();
	    level.generateLevel(winX, winY);
	    
		while (!Display.isCloseRequested()){
			//runtime loop
			
			//Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT |GL11.GL_DEPTH_BUFFER_BIT);
			
			
			level.displayCells();
			
			
			//rendering goes here



			//TODO add framerate cap or some such stuff
			
			
			//SCROLLING
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)){
				level.scroll(scrollSpeed, 0);
			}if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)){
				level.scroll(-scrollSpeed, 0);
			}if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)){
				level.scroll(0, -scrollSpeed);
			}if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)){
				level.scroll(0, scrollSpeed);
			}
			
			Display.update(); //duh
			Display.sync(maxFPS);
			


		}
		Display.destroy();
	}
	
	public static void main(String[] args){
		DuskMain duskMain = new DuskMain();
		duskMain.startGame();
	}

}
