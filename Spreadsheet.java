//ExcelJava 
//Made by Sean Liu
//Ap Comp Sci. 2/16/2015
package Excel;

public class Spreadsheet {

	//	Var to be implemented in CP2
	final int NUM_COLS = 7;
	final int NUM_ROWS = 10;
	final int COL_WIDTH = 12;
	protected Cell[][] cells;

	//	Var already implemented in CP1
	private String[][] table = new String [11][8];
	private char yaxis = 'A';
	private int xaxis = 1;

	// for testing purpose
	public static void main(String[] args){
		Spreadsheet  sheet = new Spreadsheet(); 
		
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
	
	}

	//	Spreadsheet constructor
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

	//	Method processes the command from command
	public void processCommand(String input)
	{
		if (input.contains("=")){ // set operation
			String[] temp = input.split("=", 2);
			temp[0] = temp[0].trim(); 
			temp[1] = temp[1].trim(); 
			assignValueToCell(temp[0], temp[1]);			

		}else if(input.contains(" ")){// operations like clear
			String[] temp = input.split(" ", 2);// split into two by " "
			String operator = temp[0]; 
			if (operator == "clear"){// clear a cell
			
			}else if(operator == "save"){// save a txt file
				
				
			}else if(operator == "load"){// load a txt file
				
				
			}
			
		}else if (input.equalsIgnoreCase("print")){
			String[][] table = getGridText();
			String decoration = decoration();
			for(int i = 0; i < table.length; i++){
				for(int j = 0; j < table[i].length; j++){
					System.out.print(table[i][j]);
				}
				System.out.println(decoration);
			}
		}else{// display a cell
			SpreadsheetLocation loc = new SpreadsheetLocation(input);
			System.out.print(input + " = ");
			System.out.print(cells[loc.getRow()][loc.getCol()].getValue());
			System.out.print("\n");
		}
	}

	//	Method returns column / row/ column width values
	public int getCols(){return NUM_COLS;}	
	public int getRows(){return NUM_ROWS;}
	public int getColWidth(){return COL_WIDTH;}
	
	//	Assign value to the cell
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
				double realValue = Double.parseDouble(value);
				cells[r][c] = new  ValueCell(Double.toString(realValue));
				 
			}catch(NumberFormatException e){
				System.out.println("Format of Real Number Not Correct. ");
			}
		}

	}

	//	Method prints the String Grid
	public String[][] getGridText()
	{		

		//		Sets the x and y axis labels
		//		First box
		table[0][0] = cells[0][0].abbreviatedCellText() + "|";

		//		First Row
		for(int i = 1; i < table[0].length; i++){
			table[0][i] = "     "+yaxis +"      |";
			yaxis = (char) (yaxis + 1);
		}

		//		First Column
		for(int i = 1; i < table.length; i++){
			table[i][0] = "     " + xaxis + "      |";
			xaxis = xaxis + 1;
		}

		//		Last Row
		xaxis = xaxis - 1;
		table[10][0] = "     " + xaxis + "     |";

		//		Sets each cell in the grid to a value
		for(int i = 1; i < table.length; i++){
			for(int j = 1; j < table[i].length; j++){
				table[i][j] = cells[i-1][j-1].abbreviatedCellText() + "|";
			}	
		}
		return table;
	}

	//	Method the decoration around the outer edges of the cells
	public String decoration(){
		String decoration = "";
		for(int i = 0; i <= 7; i++){
			decoration += "------------+";
		}
		return "\n" +decoration;
	}
}
