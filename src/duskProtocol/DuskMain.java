package duskProtocol;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DuskMain {
	public static final int winX = 800;
	public static final int winY = 600;

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
			Display.update(); //duh


		}
		Display.destroy();
	}
	
	public static void main(String[] args){
		DuskMain duskMain = new DuskMain();
		duskMain.startGame();
	}

}
