//ExcelJava 
//Made by Sean Liu
//Ap Comp Sci. 2/16/2015
package Excel;

public class Spreadsheet {

	// Var to be implemented in CP2
	final int NUM_COLS = 7;
	final int NUM_ROWS = 10;
	final int COL_WIDTH = 12;
	protected Cell[][] cells;

	// Var already implemented in CP1
	private String[][] table = new String [11][8];
	private char yaxis = 'A';
	private int xaxis = 1;

	// for testing purpose
	public static void main(String[] args){
		Spreadsheet sheet = new Spreadsheet(); 
	
		// for testing purpose
		sheet.processCommand("A1 = 6");
		sheet.processCommand("B1 = 6");
		sheet.processCommand("B2 = 6");
		sheet.processCommand("B3 = -6.2");
		sheet.processCommand("B4 = 6/6/1993");		
		sheet.processCommand("A2 = \"this is a long string\"");
		sheet.processCommand("print");		
		sheet.processCommand("C1");		
		sheet.processCommand("B2");		
		sheet.processCommand("B3");		
		sheet.processCommand("B4");		
		sheet.processCommand("A2");		
		sheet.processCommand("C2");
		sheet.processCommand("clear B1");
		sheet.processCommand("B1");
		sheet.processCommand("print");
		sheet.processCommand("clear");
		sheet.processCommand("print");
	}

	// Spreadsheet constructor
	public Spreadsheet()
	{
		cells = new Cell[NUM_ROWS][NUM_COLS];

		for (int row = 0; row < NUM_ROWS; row++)
		{
			for (int col = 0; col < NUM_COLS; col++)
			{
				cells[row][col] = new EmptyCell(" ");
			}
		}
	}

	// Method processes the command from command
	public void processCommand(String input)
	{	
		// Note: all the operations(including following operations)
		// can be divided into two groups: 
		// one w/ "=", the other one w/o "=" , so fisrt check "="
		if (input.contains("=")){ // set operation
			String[] temp = input.split("=", 2);
			// The result strings may contain heading/tailing blanks,
			// so trim them. 
			temp[0] = temp[0].trim(); 
			temp[1] = temp[1].trim(); 
			assignValueToCell(temp[0], temp[1]);			

		}else if(input.contains(" ")){// operations like "clear A1"
			String[] temp = input.split(" ", 2);
			String operator = temp[0].trim();
			String operand = temp[1].trim(); 
			if (operator.equalsIgnoreCase("clear")){// clear a cell
				SpreadsheetLocation loc = new SpreadsheetLocation(operand);
				cells[loc.getRow()][loc.getCol()] = new EmptyCell(); 
			}else if(operator.equalsIgnoreCase("save")){// save a txt file
				// To do ..
				
			}else if(operator.equalsIgnoreCase("load")){// load a txt file
				// To do ..
				
			}
			
		}else if (input.equalsIgnoreCase("print")){
			// Nothing changed in this "if" part
			String[][] table = getGridText();
			String decoration = decoration();
			for(int i = 0; i < table.length; i++){
				for(int j = 0; j < table[i].length; j++){
					System.out.print(table[i][j]);
				}
				System.out.println(decoration);
			}
		}else if (input.equalsIgnoreCase("clear")){
			// Make all cells "EmptyCell"
			// Can't write "cells = new EmptyCell[ROW][COL]", 
			// or cells will become "EmptyCell[Row][]" 
			for(int i=0;i<cells.length;i++){
				for(int j=0;j<cells[0].length;j++){
					cells[i][j] = new EmptyCell(); 
				}
			}
		}else{// display a cell
			SpreadsheetLocation loc = new SpreadsheetLocation(input);
			System.out.print(input + " = ");
			// The field of value of each cell stores the original input 
			// in format of String
			System.out.print(cells[loc.getRow()][loc.getCol()].getValue());
			System.out.print("\n");
		}
	}

	// Method returns column / row/ column width values
	public int getCols(){return NUM_COLS;}	
	public int getRows(){return NUM_ROWS;}
	public int getColWidth(){return COL_WIDTH;}
	
	// Assign value to the cell
	private void assignValueToCell(String cell, String value)
	{
		SpreadsheetLocation loc = new SpreadsheetLocation(cell); 	
		int r = loc.getRow(); 
		int c = loc.getCol();
		
		// text and date
		if(value.contains("\"")){
			// what if a blank string? 
			cells[r][c] = new TextCell(value);

		}else if (value.contains("/")){
			// what if a different date format?
			cells[r][c] = new DateCell(value);
			
		}else{// real value  
			try {
				// Why to use "try - catch" block? 
				// Input, value, may not be in corrent format,
				// e.g. "13213.aaaa1", which can not be ruled out 
				// by the above conditions. If this is the case, 
				// The catch block is executed. 
				double realValue = Double.parseDouble(value);
				cells[r][c] = new  ValueCell(Double.toString(realValue));
				 
			}catch(NumberFormatException e){
				System.out.println("Format of Real Number Not Correct. ");
			}
		}

	}

	// Method prints the String Grid
	public String[][] getGridText()
	{	
		// Sets the x and y axis labels
		// Without this resetAxis(), the indices of the spreadsheet
		// will increase every time the user inputs a "print" command
		resetAxis(); 
		
		Cell c = new EmptyCell(); 
		// First box 
		table[0][0] = c.abbreviatedCellText() + "|";

		// First Row
		for(int i = 1; i < table[0].length; i++){
			table[0][i] = "     "+yaxis +"      |";
			yaxis = (char) (yaxis + 1);
		}

		// First Column
		for(int i = 1; i < table.length; i++){
			table[i][0] = "     " + xaxis + "      |";
			xaxis = xaxis + 1;
		}

		// Last Row
		xaxis = xaxis - 1;
		table[10][0] = "     " + xaxis + "     |";

		// Sets each cell in the grid to a value
		for(int i = 1; i < table.length; i++){
			for(int j = 1; j < table[i].length; j++){
				String str = cells[i-1][j-1].specFormat(); 
				table[i][j] =  cells[i-1][j-1].formatInput(str) + "|";
			}	
		}
		return table;
	}

	// Method the decoration around the outer edges of the cells
	public String decoration(){
		String decoration = "";
		for(int i = 0; i <= 7; i++){
			decoration += "------------+";
		}
		return "\n" +decoration;
	}
	
	private void resetAxis(){
		yaxis = 'A'; 
		xaxis = 1;
	}
}
