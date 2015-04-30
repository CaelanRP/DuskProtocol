package duskProtocol;

import java.util.ArrayList;

public class Level {
	private int spacing = 3;
	private int quadSize = 40;
	private int cornerSize = 3;
	private int offsetX;
	private int offsetY;
	private static Cell[][] cellList;


	//generates a level from a file (currently autogenerates)
	public void generateLevel(int winX, int winY){


		//TODO make this load from a file rather than this garbage
		int levelSizeX = 10; int levelSizeY = 10;
		cellList = new Cell[levelSizeX][levelSizeY];

		for (int y = 0; y < levelSizeY; y++){
			for (int x = 0; x < levelSizeX; x++){

				//make a new cell
				Cell cell = new Cell(x,y);
				cellList[x][y] = cell;

			}
		}
		calculateOffset(winX, winY);
	}
	
	
	//calculates the initial pixel offset of the level so that it's centered when the level begins
	public void calculateOffset(int winX, int winY){
		offsetX = ( winX - (quadSize*cellList.length) - (spacing*cellList.length) ) / 2;
		offsetY = ( winY - (quadSize*cellList[0].length) - (spacing*cellList[0].length) ) / 2;
	}

	//returns the list of cells and empty space in the level
	public Cell[][] getCellList(){
		return cellList;
	}

	//display all the cells currently in the CellList
	public void displayCells(){
		for (int x = 0; x < cellList.length; x++){
			for (int y = 0; y < cellList[0].length; y++){
				Cell c = cellList[x][y];
				int gridX = c.getX();
				int gridY = c.getY();
				int size = quadSize;

				//calculate pixel position of cell
				int xPos = gridX*(size + spacing) + offsetX;
				int yPos =	gridY*(size + spacing) + offsetY;


				//pass pixel positions to cell so it can display
				c.displayCell(xPos,yPos,quadSize, cornerSize);
			}
		}
	}
	
	public void scroll(int adjustX, int adjustY){
		offsetX += adjustX;
		offsetY += adjustY;
	}
}
