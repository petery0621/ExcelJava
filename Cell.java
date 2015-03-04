//ExcelJava 
//Made by Sean Liu
//Ap Comp Sci. 
//2/16/2015
package Excel;

abstract class Cell {
	// This is the parent class for 
	// EmptyCell, ValueCell, TextCell, DateCell
	
	public String value = "";
	
	public Cell(){
		// Empty no-argument constructor
	}

	public Cell(String newValue)
	{
		value = newValue;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String newValue)
	{
		this.value = newValue;
	}
	
	// Not in use
	public String fullCellText()
	{
		String empty = "";
		for (int i = 1; i <= 12; i++){
			empty = empty + " ";
		}
		return empty;
	}

	public String abbreviatedCellText()
	{
		String empty = "";
		for (int i = 1; i <= 12; i++){
			empty = empty + " ";
		}
		return empty;
	}
	
	// To truncate the longer strings and "centered" them
	// Called when displaying the whole
	// To do: for negative real numbers, you may want to align 
	// them according to the left first digits
	public String formatInput(String newInput){ 
		// To gaurantee there will not be errors
		// when calling "newInput.charAt(0)" at Line 69
		
		if (newInput.isEmpty()){
			System.out.println("formatInput: newInput is empty");
			return "";
		}
		
		Spreadsheet sheet = new Spreadsheet();
		String out = "";
		int limit = (sheet.COL_WIDTH - newInput.length())/2;
		
		if(newInput.length()>=2 && 
		   newInput.charAt(0)=='-' &&
		   Character.isDigit(newInput.charAt(1)))
			limit --; 
		
		for(int i = 0; i < limit; i++){
			out = out + " ";
		}
		
		if (newInput.length() > 12){
			out = newInput.substring(0, 11) + ">";
		}else{
			out = out + newInput;
		}
		
		int numSpaces = sheet.COL_WIDTH - out.length();
		for(int j = 0; j < numSpaces; j++){
			out = out + " ";
		}
		return out;
	}
	
	public String specFormat(){
		// This function is on purpose left blank, 
		// in order for subclasses to override it
		return "Warning: msg not overriden"; 
	}
		
		
	
}
