
package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Organisationseinheit
{
   int ID;
   String Name;
   public Organisationseinheit( String name, int iD)
   {
	  super();
	  ID = iD;
	  Name = name;
   }

   public static ArrayList<Organisationseinheit> auslesenDB(Connection pConnection)
   {
     Organisationseinheit lOrganisationseinheit;
     ArrayList<Organisationseinheit> lOrganisationsAL = new ArrayList<Organisationseinheit>();
     Statement lBefehl;
     ResultSet lErgebnis;

     try {
     lBefehl 	= pConnection.createStatement();
     lErgebnis = lBefehl.executeQuery("SELECT OEID,OENAME FROM organisationseinheit o;");
     lErgebnis.first(); 

     while(!lErgebnis.isAfterLast())   
        {
    	lOrganisationseinheit = new Organisationseinheit(lErgebnis.getString(2),lErgebnis.getInt(1));
    	lOrganisationsAL.add(lOrganisationseinheit);
          lErgebnis.next();
        }
        } catch (Exception ex)
             {
               System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
             }
     return lOrganisationsAL ;
}
  

   public static ArrayList<String> getOrganisationseinheiten()
   {
	  ArrayList<String> lOrganisationseinheit = new ArrayList<String>();
	  ArrayList<Organisationseinheit> lOrganisationseinheitenAL = new ArrayList<Organisationseinheit>();
	  for(int i = 0; i<lOrganisationseinheitenAL.size();i++)
	  {
		 String lOEName = new String();
		 lOEName = lOrganisationseinheitenAL.get(i).getName();
		 String lOEID = "";
		 lOEID = Integer.toString(lOrganisationseinheitenAL.get(i).getID());
		 lOEName = lOEName.concat(" " +lOEID);
		 lOrganisationseinheit.add(lOEName);
	  }
	  return(lOrganisationseinheit);
   }

   public int getID()
   {
      return ID;
   }

   public void setID(int iD)
   {
      ID = iD;
   }

   public String getName()
   {
      return Name;
   }

   public void setName(String name)
   {
      Name = name;
   }
   public Organisationseinheit(int iD, String name)
   {
	  super();
	  ID = iD;
	  Name = name;
   }

   public static String getLetzteOrganisationseinheit()
   {
	  // TODO Automatisch generierter Methodenstub
	  return null;
   }

 
   
   
}