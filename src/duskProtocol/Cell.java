package duskProtocol;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Cell {
	private int gridX;
	private int gridY;
	private float[] color;
	

	
	
	public Cell(int x, int y){
		this.gridX = x;
		this.gridY = y;
		color = new float[4];
		color[0] = 1f;
		color[1] = 1f;
		color[2] = 1f;
		color[3] = 0.5f;
	}
	
	
	//THIS IS WHAT ACTUALLY DRAWS THE CELL ONTO THE SCREEN BRO
	public void displayCell(int x, int y, int quadSize, int cornerSize){
		
		GL11.glColor4f(color[0], color[1], color[2], 0.5f);
		
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
	
	public int getX(){
		return gridX;
	}
	
	public int getY(){
		return gridY;
	}
	
	public void setColor(float color0, float color1, float color2, float color3){
		color[0] = color0;
		color[1] = color1;
		color[2] = color2;
		color[3] = color3;
	}
}
