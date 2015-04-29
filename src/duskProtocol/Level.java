package duskProtocol;

import java.util.ArrayList;

public class Level {
	private int spacing = 2;
	private int quadSize = 40;
	private int offset;
	private static ArrayList<Cell> cellList;
	

	//generates a level from a file (currently autogenerates)
	public void generateLevel(int winX, int winY){

		cellList = new ArrayList<Cell>();
		//TODO make this load from a file rather than this garbage
		int levelSizeX = 10; int levelSizeY = 10;
		
		for (int y = 0; y < levelSizeY; y++){
			for (int x = 0; x < levelSizeX; x++){

				//make a new cell
				Cell cell = new Cell(x,y);
				cellList.add(cell);
				
			}
		}
	}
	//calculates the initial pixel offset of the level so that it's centered when the level begins
	public int[] calculateOffset(int winX, int winY){
		int offsetX = ( winX - (quadSize*cellList.size()) - (spacing*cellList.size()) ) / 2;
		int offsetY = ( winY - (quadSize*cellList.size()) - (spacing*cellList.size()) ) / 2;
	}
	
	//returns the list of cells and empty space in the level
	public ArrayList<Cell> getCellList(){
		return cellList;
	}
	
	public void displayCells(){
		for (Cell c : cellList){
			int gridX = c.getX();
			int gridY = c.getY();
			int size = c.quadSize();
			
			//calculate pixel position of cell
			int x = gridX*(size + spacing);
			int y =	gridY*(size + spacing);
			
			//pass pixel positions to cell so it can display
			c.displayCell(x,y);
		}
	}
}
