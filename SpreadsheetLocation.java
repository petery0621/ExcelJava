package Excel;

public class SpreadsheetLocation {
	// parse a string to an array of int coordinates
	
	protected int row = 0;
	protected int col = 0;
	protected int XAXIS = 1;
	protected char YAXIS = 'A';
	
	public SpreadsheetLocation(int newRow, int newCol)
	{
		row = newRow;
		col = newCol;
	}
	
	public SpreadsheetLocation(String location)
	{
		// input: 1 letter & up to 2 digits
		if (location.length() >= 2 && location.length() <= 3){
			
			char letter = location.charAt(0);
			String str = location.substring(1);//"1" or "10" or whatever two digits
			int number = Integer.parseInt(str); // str must be able to be parsed
			
			row = number - XAXIS; 
			col = letter - YAXIS; 
			
		}else{
			System.out.println("Location format not corrrect");
		}
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
}
