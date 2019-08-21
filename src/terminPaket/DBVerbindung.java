package terminPaket;

import java.sql.*;

public class DBVerbindung {
static Connection connection;

public boolean verbinden(String pHostrechner, String pDatenbank, String pBenutzer, String pPasswort){
	try{
	 Class.forName("com.mysql.jdbc.Driver").newInstance(); 
	 String connectionCommand = "jdbc:mysql://"+pHostrechner+":3306/"+pDatenbank+"?user="+pBenutzer+"&password="+pPasswort;
     connection = DriverManager.getConnection(connectionCommand);
     return true;

    }catch (Exception ex)
	  {				
	    System.out.println("Fehler");
	    return false;
      }
}

public static Connection holenConnection()
{
	return connection;
}

public void setzenConnection(Connection pConnection)
{
	connection = pConnection;
}

}
