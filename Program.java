//ExcelJava 
//Made by Sean Liu
//Ap Comp Sci. 
//2/16/2015
package Excel;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("Welcome to TextExcel!\n");
      
        System.out.println("Enter a command: ");
//      Calls Spreadsheet and Scanner
        Spreadsheet sheet = new Spreadsheet();
		Scanner userInput = new Scanner(System.in);
		String input = userInput.nextLine();
		//What if user input is not "print"? 
		
//		Command loop takes command from user until "exit".
		while(!input.equalsIgnoreCase("exit")){
			
//			Process the command in spreadsheet
			sheet.processCommand(input);
			
			System.out.println();
			System.out.println("Enter a command: ");
			//What if user input not "exit"?
			input = userInput.nextLine();
		}
		System.out.println("Farewell!");
		userInput.close();
	}
}
