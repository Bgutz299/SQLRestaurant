package SQL;

import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
    	SQLDatabaseConnection data = new SQLDatabaseConnection();
    	Menu menu = new Menu();
    	Scanner input = new Scanner(System.in);
    	String name = "";
    	String size = "";
    	float price = 0f;
    	int id = 0;
    	while(true) {
    		try {
	    		menu.MenuPrint();
	    		input = new Scanner(System.in);
	    		int option = input.nextInt();
				switch(option) {
					case 1:
						System.out.println("Enter the name of the Item (Type All to view all): ");
						input = new Scanner(System.in);
						name = input.nextLine();
						if(name.equals("All") || name.equals("all")) {
							data.Select(name, id);
						}else {
							while(true) {
								try {
									System.out.println("Enter the ID of the Item (If unknown, type 0): ");
									id = input.nextInt();
									data.Select(name, id);
									break;
								}catch(Exception e) {
									input = new Scanner(System.in);
									continue;
								}
							}
						}
						continue;
					case 2:
						while(true) {
							try {
								System.out.println("Enter the name of the item: ");
								input = new Scanner(System.in);
								name = input.nextLine();
								break;
							}catch (Exception e){
								continue;
							}
						}
						while(true) {
							try {
								System.out.println("Enter the price of the item:  ");
								input = new Scanner(System.in);
								price = input.nextFloat();
								break;
							}catch(Exception e) {
								continue;
							}
						}
						while(true) {
							System.out.println("Enter the size of the item (Small, Large, N/A): ");
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
						while(true) {
							try {
								System.out.println("Enter the name of the Item: ");
								input = new Scanner(System.in);
								name = input.nextLine();
								break;
							}catch (Exception e){
								continue;
							}
						}
						while(true) {
							try {
								System.out.println("What ID? Type 0 to list all entries matching name.");
								input = new Scanner(System.in);
								id = input.nextInt();
								break;
							}catch(Exception e) {
								continue;
							}
						}
						data.Delete(name, id, input);
						continue;
					case 4:
						System.out.println("Goodbye!");
						input.close();
						break;
					default:
						continue;
				}
    		}catch(Exception e) {
    			continue;
    		}
			break;
    	}
    }
}
