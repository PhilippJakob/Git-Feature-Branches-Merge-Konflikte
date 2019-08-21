package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Person
{
   String Name;
   int ID;
   public Person(String name, int iD)
   {
	  super();
	  Name = name;
	  ID = iD;
   }

   public static ArrayList<Person> auslesenDB(Connection pConnection)
	    {
	      Person lPerson;
	      ArrayList<Person> lPersonenAL = new ArrayList<Person>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT Name,IDPerson FROM person p;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lPerson = new Person(lErgebnis.getString(1),lErgebnis.getInt(2));
	           lPersonenAL.add(lPerson);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lPersonenAL ;
	 }
   public static ArrayList<String> getPersonen()
   {
	  ArrayList<String> lPersonen = new ArrayList<String>();
	  ArrayList<Person> lPersonenAL = new ArrayList<Person>();
	  lPersonenAL = FensterController.getPersonenAL();
	  for(int i = 0; i<lPersonenAL.size();i++)
	  {
		 String lName = new String();
		 lName = lPersonenAL.get(i).getName();
		 String lPersonenID = "";
		 lPersonenID = Integer.toString(lPersonenAL.get(i).getID());
		 lName = lName.concat(" " +lPersonenID);
		 lPersonen.add(lName);
	  }
	  return(lPersonen);
   }
   public static String getLetztePerson()
   {
	  String lPerson = new String();
	  ArrayList<String> lPersonenAL = new ArrayList<String>();
	  lPersonenAL = getPersonen();
	  lPerson = lPersonenAL.get(lPersonenAL.size()-1);
	  return(lPerson);
   }
   public String getName()
   {
      return Name;
   }
   public void setName(String name)
   {
      Name = name;
   }
   public int getID()
   {
      return ID;
   }
   public void setID(int iD)
   {
      ID = iD;
   }
}
