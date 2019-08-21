package terminPaket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class Termin
{
   private static final Datenbankverbindung dbVerbindung = new Datenbankverbindung();
   
   public ArrayList <Termin> auslesenTermine(Connection pConnection, Integer pIdPerson)
   {
	  int lIdPerson = pIdPerson;
	  ArrayList<Termin> = new ArrayList<Termin>();
	  Statement lBefehl;
	  ResultSet lErgebnis;


	     try
	     {
		    lBefehl = pConnection.createStatement();
		    lErgebnis=lBefehl.executeQuery("SELECT * FROM dbpr_termin.termin WHERE IDPerson = " + lIdPerson);
		    lErgebnis.first();
		    lIdPerson=lErgebnis.getInt(1);
		
	      }

	     catch (SQLException e)
	     {
		   
		     e.printStackTrace();
	    
	     }

	     return Termin;
   }
}
