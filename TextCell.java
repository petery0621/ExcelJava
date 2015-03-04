package Excel;

public class TextCell extends Cell
{

//	private String value = "";
	
	public TextCell(String newValue) {
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
	
	public String specValue(){
		String temp = this.value;
		char c = temp.charAt(0);// this needs to be improved; temp might be ""
		String rst = temp.substring(1,temp.length()-1); 
		return rst; 
	}
	
	
}
