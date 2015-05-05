package duskProtocol;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DuskMain {
	public static final int winX = 1280;
	public static final int winY = 800;
	private int scrollSpeed = 4;
	
	private int maxFPS = 60;

	public void startGame(){

		//Create wandow
		try {
			Display.setDisplayMode(new DisplayMode(winX, winY));
			Display.create();
			Display.setVSyncEnabled(true);

		} catch (LWJGLException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		// init OpenGL
		 GL11.glEnable(GL11.GL_TEXTURE_2D);               
         
	        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);          
	         
	            // enable alpha blending
	            GL11.glEnable(GL11.GL_BLEND);
	            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	         
	            GL11.glViewport(0,0,winX, winY);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	 
	        GL11.glMatrixMode(GL11.GL_PROJECTION);
	        GL11.glLoadIdentity();
	        GL11.glOrtho(0, winX, winY, 0, 1, -1);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    
	    
	    Level level = new Level(winX, winY);
	    File f = new File("levels/level.txt");
	    
	    try {
			level.generateLevel(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		while (!Display.isCloseRequested()){
			//runtime loop
			
			//Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT |GL11.GL_DEPTH_BUFFER_BIT);
			
			
			level.render();
			
			
			//rendering goes here



			//TODO add framerate cap or some such stuff
			
			
			//SCROLLING
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A)){
				level.scroll(scrollSpeed, 0);
			}if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D)){
				level.scroll(-scrollSpeed, 0);
			}if (Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W)){
				level.scroll(0, scrollSpeed);
			}if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S)){
				level.scroll(0, -scrollSpeed);
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
