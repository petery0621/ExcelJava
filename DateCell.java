package Excel;

import java.text.SimpleDateFormat;

public class DateCell extends Cell
{

//	private String value = "";
	public DateCell(){
		
	}
	
	public DateCell(String newValue) {
		super(newValue);
		this.value = newValue;
		
	}

	public String fullCellText()
	{
		return value;
	}
	
	public String abbreviatedCellText()
	{
		value = formatInput(value);
		return value;
	}
	
	public String getValue()
	{
		value = formatInput(value);
		return value;
	}

	public void setValue(String newValue)
	{
		this.value = newValue;
	}
	
	public String specFormat(){
		String rst = new String(); 
		String[] temp = this.value.split("/"); 
		if (temp.length != 3){
			System.out.println("Date Format Not Correct.");
			return rst; 
		}
		// format month, day
		for(int i=0; i<temp.length;i++){
			temp[i]=temp[i].trim();
			if (temp[i].length() == 1){
				temp[i] = "0" + temp[i]; 
			}
			rst += temp[i] ;
			if(i!=temp.length-1) rst += "/";
		}
		return rst; 
	}
	
	public static void main(String[] args){

	}
	
}
