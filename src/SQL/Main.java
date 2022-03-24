package SQL;

import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
    	SQLDatabaseConnection data = new SQLDatabaseConnection();
    	Menu menu = new Menu();
    	Scanner input = new Scanner(System.in);
    	String name = "";
    	String size = "";
    	int id = 0;
    	while(true) {
    		menu.MenuPrint();
    		input = new Scanner(System.in);
    		int option = input.nextInt();
			switch(option) {
				case 1:
					System.out.println("What name?(Type All to view all)");
					input = new Scanner(System.in);
					name = input.nextLine();
					if(name.equals("All") || name.equals("all")) {
						data.Select(name, id);
					}else {
						System.out.println("What ID?(If unknown, type 0)");
						id = input.nextInt();
						data.Select(name, id);
					}
					continue;
				case 2:
					System.out.println("What name? ");
					input = new Scanner(System.in);
					name = input.nextLine();
					System.out.println("What Price? ");
					input = new Scanner(System.in);
					float price = input.nextFloat();
					while(true) {
						System.out.println("What Size (Small, Large, N/A)?  ");
						size = input.next();
						switch(size) {
							case "small":
							case "Small":
								size = "Small";
								break;
							case "Large":
							case "large":
								size = "Large";
								break;
							case "N/A":
							case "n/a":
								size = "N/A";
								break;
							default:
								continue;
						}
						break;
					}
			    	data.Insert(name, price, size);
			    	continue;
				case 3:
					System.out.println("What name? ");
					input = new Scanner(System.in);
					name = input.nextLine();
					System.out.println("What ID? ");
					input = new Scanner(System.in);
					id = input.nextInt();
					data.Delete(name, id, input);
					continue;
				case 4:
					System.out.println("Goodbye!");
					input.close();
					break;
				default:
					continue;
		        
			}
			break;
    	}
    }
}
