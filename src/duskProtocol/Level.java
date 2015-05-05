package duskProtocol;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	private int spacing = 3;
	private int quadSize = 40;
	private int cornerSize = 5;
	private int linkSize = 6;
	private int offsetX;
	private int offsetY;
	private int winX;
	private int winY;
	private Background bkg;
	private static Cell[][] cellList;


	public Level(int winX, int winY) {
		this.winX = winX;
		this.winY = winY;

		try {
			bkg = new Background("img/storage.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//generates a level from a file (currently autogenerates)
	public void generateLevel(File f) throws FileNotFoundException{
		Scanner s = new Scanner(f);

		//Step 1: find how large the level should be
		int levelSizeX = 0;
		int levelSizeY = 0;

		while (s.hasNext()){
			String line = s.nextLine();
			if (line.length() > levelSizeX)
				levelSizeX = line.length();

			levelSizeY++;
		}
		cellList = new Cell[levelSizeX][levelSizeY];

		//Step 2: Read from the level

		s = new Scanner(f);
		System.out.println(levelSizeY);
		String line = "";
		
		for (int y = 0; y < levelSizeY; y++){
			if(s.hasNext()){
				line = s.nextLine();
				
				for (int x = 0; x < levelSizeX; x++){
					String letter = line.substring(x, x+1);
					Cell cell = null;
					if (letter.toUpperCase().equals("O"))
						cell = new Cell(x,y, false);
					else if (letter.toUpperCase().equals("X"))
						cell =  new Cell(x,y, true);
					cellList[x][y] = cell;
				}
			}
		}
		calculateOffset();
	}


	//calculates the initial pixel offset of the level so that it's centered when the level begins
	public void calculateOffset(){
		offsetX = ( winX - (quadSize*cellList.length) - (spacing*cellList.length) ) / 2;
		offsetY = ( winY - (quadSize*cellList[0].length) - (spacing*cellList[0].length) ) / 2;
	}

	//returns the list of cells and empty space in the level
	public Cell[][] getCellList(){
		return cellList;
	}

	public void render(){
		displayBackground();
		displayCells();
	}

	//display all the cells currently in the CellList
	public void displayCells(){
		for (int x = 0; x < cellList.length; x++){
			for (int y = 0; y < cellList[0].length; y++){
				Cell c = cellList[x][y];

				//calculate pixel position of cell
				int xPos = c.getX()*(quadSize + spacing) + offsetX;
				int yPos =	c.getY()*(quadSize + spacing) + offsetY;


				//pass pixel positions to cell so it can display
				c.displayCell(xPos,yPos,quadSize, cornerSize);
				displayConnections(c);
			}
		}
	}
	//display the connections between cells
	public void displayConnections(Cell cell){

		if(cell.isFilled() && cell.getX() != cellList.length - 1 && cellList[cell.getX()+1][cell.getY()].isFilled()){
			//DRAW A HORIZONTAL CONNECTION
			int xPos = cell.getX()*(quadSize + spacing) + offsetX + quadSize;
			int yPos =	cell.getY()*(quadSize + spacing) + offsetY + (quadSize/2) - (linkSize/2);

			cell.displayLinkRight(xPos, yPos, quadSize, cornerSize, spacing, linkSize);
		}

		if(cell.isFilled() && cell.getY() != cellList[0].length - 1 && cellList[cell.getX()][cell.getY()+1].isFilled()){
			//DRAW A VERTICAL CONNECTION
			int xPos = cell.getX()*(quadSize + spacing) + offsetX + (quadSize/2) - (linkSize/2);
			int yPos =	cell.getY()*(quadSize + spacing) + offsetY + quadSize;

			cell.displayLinkUp(xPos, yPos, quadSize, cornerSize, spacing, linkSize);
		}
	}

	public void displayBackground(){
		bkg.display(winX, winY);
	}




	public void scroll(int adjustX, int adjustY){
		offsetX += adjustX;
		offsetY += adjustY;
	}
}
