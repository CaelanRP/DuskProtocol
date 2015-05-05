package duskProtocol;

import org.newdawn.slick.Color;

import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.opengl.GL11;



public class Cell {
	private int gridX;
	private int gridY;
	private float[] color;
	private boolean filled;

	private Texture texture;


	public Cell(int x, int y, boolean filled){
		this.gridX = x;
		this.gridY = y;
		this.filled = filled;

		color = new float[4];
		color[0] = 0.5f;
		color[1] = 0.5f;
		color[2] = 0.5f;
		color[3] = 0.6f;
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("img/white.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//THIS IS WHAT ACTUALLY DRAWS THE CELL ONTO THE SCREEN BRO
	public void displayCell(int x, int y, int quadSize, int cornerSize){
		texture.bind();
		if (filled){

			Color.white.bind();
			GL11.glColor4f(color[0], color[1], color[2], color[3]);

			GL11.glBegin(GL11.GL_POLYGON);
			//TOP
			GL11.glVertex2f(x + cornerSize, y + quadSize);
			GL11.glVertex2f(x + quadSize - cornerSize, y + quadSize);

			//RIGHT SIDE
			GL11.glVertex2f(x + quadSize, y + quadSize - cornerSize);
			GL11.glVertex2f(x + quadSize, y + cornerSize);

			//BOTTOM
			GL11.glVertex2f(x + quadSize- cornerSize, y);
			GL11.glVertex2f(x + cornerSize, y);

			//LEFT
			GL11.glVertex2f(x, y + cornerSize);
			GL11.glVertex2f(x, y + quadSize - cornerSize);
			GL11.glEnd();
		}
	}

	public void displayLinkUp(int x, int y, int quadSize, int cornerSize, int spacing, int linkSize){
		GL11.glColor4f(color[0], color[1], color[2], color[3]);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x,y);
		GL11.glVertex2f(x,y + spacing);
		GL11.glVertex2f(x + linkSize , y + spacing);
		GL11.glVertex2f(x + linkSize, y );
		GL11.glEnd();
		
		
	}
	
	public void displayLinkRight(int x, int y, int quadSize, int cornerSize, int spacing, int linkSize){
		GL11.glColor4f(color[0], color[1], color[2], color[3]);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x,y);
		GL11.glVertex2f(x + spacing ,y);
		GL11.glVertex2f(x + spacing , y + linkSize);
		GL11.glVertex2f(x , y + linkSize);
		GL11.glEnd();
		
	}

	public int getX(){
		return gridX;
	}

	public int getY(){
		return gridY;
	}

	public boolean isFilled(){
		return filled;
	}

	public void setColor(float color0, float color1, float color2, float color3){
		color[0] = color0;
		color[1] = color1;
		color[2] = color2;
		color[3] = color3;
	}
}
