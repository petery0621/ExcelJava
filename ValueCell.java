package Excel;

public class ValueCell extends Cell
{

//	private String value = "";
	
	public ValueCell(){
	}
	
	public ValueCell(String newValue) {
//		super(newValue);
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
//		value = formatInput(value);
		return value;
	}

	public void setValue(String newValue)
	{
		this.value = newValue;
	}
	
	public String specFormat(){
		return this.value;
	}

	
}
