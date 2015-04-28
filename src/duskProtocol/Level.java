package duskProtocol;

import java.util.ArrayList;

public class Level {
	
	private static ArrayList<Cell> cellList;
	

	//generates a level from a file (currently autogenerates)
	public void generateLevel(){

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
	
	//returns the list of cells and empty space in the level
	public ArrayList<Cell> getCellList(){
		return cellList;
	}
	
	public void displayCells(){
		for (Cell c : cellList){
			c.displayCell();
		}
	}
}
