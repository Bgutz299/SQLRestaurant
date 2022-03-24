package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class SQLDatabaseConnection {
    private String connectionUrl = "jdbc:sqlserver://mysqlserverbrendan.database.windows.net:1433;"
            + "database=mySampleDatabase;"
            + "user=azureuser;"
            + "password=sqldatabase11!;"
            + "encrypt=true;"
            + "trustServerCertificate=false;"
            + "loginTimeout=30;";
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public boolean Select(String name, int ID) {
        ResultSet resultSet = null;
        //Statement parameters are due to ResultSet objects incrementing when calling next() on ResultSet objects.
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from dbo.Menu_Items WHERE " + "Name = " + "'" + name + "' " + "AND " + "ID = " + ID + ";";
            if(name.equals("All") || name.equals("all")) {
            	selectSql = "SELECT * from dbo.Menu_Items;";
            }else if(ID == 0) {
            	selectSql = "SELECT * FROM dbo.Menu_Items WHERE Name = " + "'" + name + "'" + ";";
            }
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            if(resultSet.next()) {
            	resultSet.beforeFirst();	//ResultSet.TYPE_SCROLL_INSENSITIVE allows for this statement to work.
    	        while (resultSet.next()) {
    	            System.out.print(resultSet.getString(4) + " " + resultSet.getString(1) + " ");
    	            System.out.printf("%.2f", Float.valueOf(resultSet.getString(2)).floatValue());
    	            System.out.println(" " + resultSet.getString(3));
    	        }
            }else {
            	System.out.println("No entries found.\n");
            	return false;
            }

	        System.out.println();
	        return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void Insert(String name, float price, String size) {
        String insertSql = "INSERT INTO [dbo].[Menu_Items] (Name, Price, Size, ID) VALUES " 
        		+ "(" + "'" + name + "'" + ", " + price + ", " + "'" + size + "'" + ", " + 0 + ")"
        		+ "UPDATE Counter "
        		+ "SET Number = Number + 1"
        		+ "UPDATE [dbo].[Menu_Items]"
        		+ "SET ID = (SELECT NUMBER FROM [dbo].[Counter])"
        		+ "WHERE ID = (SELECT MIN(ID) FROM [dbo].[Menu_Items])"
        		+ "SELECT * FROM [dbo].[Menu_Items];";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertMenuItem = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

        	prepsInsertMenuItem.execute();
        	System.out.println("Added Successfully.");
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Delete(String name, int ID, Scanner input) {
         try (Connection connection = DriverManager.getConnection(connectionUrl);
                 Statement DeleteMenuItem = connection.createStatement();) {
        	if(Select(name, ID)) {
	        	String deleteSql = "DELETE FROM [dbo].[Menu_Items] WHERE " + "Name = " + "'" + name + "'" + "AND " + "ID = " + ID + ";";
	        	while(true) {
		        	System.out.println("Are you sure you would like to delete this entry? Y/N");
		        	input = new Scanner(System.in);
		        	switch(input.next()) {
		        		case "y":
		        		case "Y":
		                 	DeleteMenuItem.executeUpdate(deleteSql);
		                 	System.out.println("Deleted Successfully.");
		        			break;
		        		case "n":
		        		case "N":
		        			System.out.println("Delete Aborted.");
		        			break;
		        		default:
		        			continue;
		        			
		        	}
		        	break;
	        	}
        	}
        	else {
        		
        	}
         }
         // Handle any errors that may have occurred.
         catch (Exception e) {
             e.printStackTrace();
         }
         
    }

}