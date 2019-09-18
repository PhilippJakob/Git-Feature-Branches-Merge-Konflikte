/*
 * Hr. Jakob 21.03.2017 angelegt
 * Hr. Naasner 14.10.2018 angepasst
 */
package terminPaket;

import java.sql.DriverManager;

import java.sql.*;

public  class Datenbankverbindung 
{
   private static Connection connection;

   public boolean verbinden()
   {
      String lHostrechner="dbserver";
      String lDatenbank="dbpr_termin";
      String lBenutzer ="dblkuser";
      String lPasswort ="lkbenutzer";
//      
  
  //erstellet Verbindung ind legt sie in der Instanzvariablen connection ab.
    try
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance(); // neue Instanz des Treibers holen
        String connectionCommand = "jdbc:mysql://"+lHostrechner+":3306/"+lDatenbank+"?user="+lBenutzer+"&password="+lPasswort;
          connection = DriverManager.getConnection(connectionCommand);
          
          return true;
//
      }
    catch (Exception ex)
    {       //Fehlerbehandlung
        System.out.println("Fehler");
        return false;
        
      }
   }


   public static Connection getConnection()
   {
    return connection;
   }

   public void setConnection(Connection pConnection)
   {
    connection = pConnection;
   }

}

