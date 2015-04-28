package duskProtocol;

import java.util.Random;

import org.lwjgl.opengl.GL11;

public class Cell {
	private int gridX;
	private int gridY;
	
	private int quadSize = 40;
	
	private int spacing = 2;
	public Cell(int x, int y){
		this.gridX = x;
		this.gridY = y;
	}
	
	
	
	public void displayCell(){
		int[] quadCoords = {gridX*(quadSize + spacing), gridY*(quadSize + spacing)};
		
		GL11.glColor3f(0.5f, 0.5f, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(quadCoords[0], quadCoords[1]);
		GL11.glVertex2f(quadCoords[0] + quadSize, quadCoords[1]);
		GL11.glVertex2f(quadCoords[0] + quadSize, quadCoords[1] + quadSize);
		GL11.glVertex2f(quadCoords[0], quadCoords[1] + quadSize);
		GL11.glEnd();
	}
	
	public int size(){
		return quadSize;
	}
	
	public int getX(){
		return gridX;
	}
	
	public int getY(){
		return gridY;
	}
}
