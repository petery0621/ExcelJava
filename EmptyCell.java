package Excel;

public class EmptyCell extends Cell{

	// We can always access the vaiable,value, defined in the parent class
	// private String value = "";
	
	public EmptyCell(){
		this.value = "<empty>"; 
	}
	public EmptyCell(String newValue) {
//		super(newValue);   
//		this.value = newValue;
		this.value = "<empty>"; 
	}

	public String fullCellText()
	{
		String empty = "";
		for (int i = 1; i <= 12; i++){
			empty = empty + " ";
		}
		return empty;
	}
	
	public String specFormat(){

		String empty = ""; 
		for(int i=0;i<12;i++) empty += " "; 
		return empty;
	
	}
}
