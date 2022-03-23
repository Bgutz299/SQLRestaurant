package SQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public void Select() {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                Statement statement = connection.createStatement();) {

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from dbo.Menu_Items";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println(resultSet.getString(4) + " " + resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
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

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertMenuItem = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

        	prepsInsertMenuItem.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertMenuItem.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Delete(String name, int ID) {
    	 String deleteSql = "DELETE FROM dbo.Menu_Items WHERE " + "ID = " + ID + " " + "AND " + "NAME = " + name;

         try (Connection connection = DriverManager.getConnection(connectionUrl);
                 PreparedStatement prepsDeleteMenuItem = connection.prepareStatement(deleteSql, Statement.RETURN_GENERATED_KEYS);) {

         	prepsDeleteMenuItem.execute();
         	System.out.println("Deleted Successfully.");
         }
         // Handle any errors that may have occurred.
         catch (Exception e) {
             e.printStackTrace();
         }
    }

}