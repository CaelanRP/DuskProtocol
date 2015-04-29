package duskProtocol;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Cell {
	private int gridX;
	private int gridY;
	

	
	
	public Cell(int x, int y){
		this.gridX = x;
		this.gridY = y;
	}
	
	
	
	public void displayCell(int x, int y, int quadSize){
		
		GL11.glColor3f(0.5f, 0.5f, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + quadSize, y);
		GL11.glVertex2f(x + quadSize, y + quadSize);
		GL11.glVertex2f(x, y + quadSize);
		GL11.glEnd();
	}
	
	public int getX(){
		return gridX;
	}
	
	public int getY(){
		return gridY;
	}
}
