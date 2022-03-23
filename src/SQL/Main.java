package SQL;

import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
    	SQLDatabaseConnection data = new SQLDatabaseConnection();
    	Menu menu = new Menu();
    	Scanner input = new Scanner(System.in);
    	String name = "";
    	int id = 0;
    	while(true) {
    		menu.MenuPrint();
    		input = new Scanner(System.in);
    		int option = input.nextInt();
			switch(option) {
				case 1:
					data.Select();
					continue;
				case 2:
					System.out.println("What name? ");
					input = new Scanner(System.in);
					name = input.next();
					System.out.println("What Price? ");
					input = new Scanner(System.in);
					float price = input.nextFloat();
					System.out.println("What Size (Small, Large, N/A)?  ");
					String size = input.next();
			    	data.Insert(name, price, size);
			    	continue;
				case 3:
					System.out.println("What name? ");
					input = new Scanner(System.in);
					name = input.next();
					System.out.println("What ID? ");
					input = new Scanner(System.in);
					id = input.nextInt();
					data.Delete(name, id);
					continue;
				case 4:
					System.out.println("Goodbye!");
					input.close();
					break;
		        
			}
			break;
    	}
    }
}
