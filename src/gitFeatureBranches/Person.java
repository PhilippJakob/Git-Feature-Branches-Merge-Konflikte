package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Person
{
   

   String Name;
   int	  ID;

   public Person(String name, int iD)
   {
	  super();
	  Name = name;
	  ID = iD;
   }

   public Person()
   {
	  // TODO Automatisch generierter Konstruktorstub
   }

   // Liest DB aus und füllt AL
   public static ArrayList<Person> auslesenDB(Connection pConnection)
   {
	  Person lPerson;
	  ArrayList<Person> lPersonenAL = new ArrayList<Person>();
	  Statement lBefehl;
	  ResultSet lErgebnis;

	  try
	  {
		 lBefehl = pConnection.createStatement();
		 lErgebnis = lBefehl.executeQuery("SELECT Name,IDPerson FROM person p;");
		 lErgebnis.first();

		 while (!lErgebnis.isAfterLast())
		 {
			lPerson = new Person(lErgebnis.getString(1), lErgebnis.getInt(2));
			lPersonenAL.add(lPerson);
			lErgebnis.next();
		 }
	  }
	  catch (Exception ex)
	  {
		 System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	  }
	  return lPersonenAL;
   }
   @SuppressWarnings("resource")
   public  ArrayList<Termin>sortierenTermin(Connection pConnection, int pOEID, int pGruppenID, int pPersonID)
   {
	  	 // -1 = leer
	  	 int lGruppenID=0;
	  	 int lPersonID=0;
	  	 int lOEID=0;
	  	 Termin lTermin;
	     ArrayList<Termin> TerminAL = new ArrayList<Termin>();
	     Statement lBefehl;
	     ResultSet lErgebnis;
	     lOEID = pOEID;
	     lPersonID = pPersonID;
	     lGruppenID = pGruppenID;
	    try {
	    	 lBefehl = pConnection.createStatement();
	  	     lErgebnis = lBefehl.executeQuery("SELECT * FROM dbpr_termin.termin WHERE IDPerson = -1 ;");
	  	     lErgebnis.first(); 
	  	//Person und OE leer
	    if(lGruppenID!=0&&lPersonID==0&&lOEID==0)
	    {    
	     lBefehl = pConnection.createStatement();
  	     lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where IDGruppe="+lGruppenID+";");
  	     lErgebnis.first(); 	     
	    }
	    //OE und Gruppe leer
	    else if(lGruppenID==0&&lPersonID!=0&&lOEID==0)
	    {
	       lBefehl = pConnection.createStatement();
	  	   lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where IDPerson="+lPersonID+";");
	  	   lErgebnis.first(); 	
	    }
	    //Gruppen und Personen leer
	    else if(lGruppenID==0&&lPersonID==0&&lOEID!=0)
	    {
	       lBefehl = pConnection.createStatement();
	  	   lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where OEID="+lOEID+";");
	  	   lErgebnis.first(); 	
	    }
	    //OE leer 
	    else if(lGruppenID!=0&&lPersonID!=0&&lOEID==0)
	    {
	       lBefehl = pConnection.createStatement();
	  	   lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where IDGruppe="+lGruppenID+"&& IDPerson="+lPersonID+";");
	  	   lErgebnis.first(); 	     
	    }
	    //Gruppe leer
	    else if(lGruppenID==0&&lPersonID!=0&&lOEID!=0)
	    {
	       lBefehl = pConnection.createStatement();
	  	   lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where OEID="+lOEID+"&& IDPerson="+lPersonID+";");
	  	   lErgebnis.first(); 	     
	    }
	    //nichts leer
	    else if(lGruppenID!=0&&lPersonID!=0&&lOEID!=0)
	    {
	       lBefehl = pConnection.createStatement();
	  	   lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where OEID="+lOEID+"&& IDPerson="+lPersonID+"&& IDGruppe="+lGruppenID+";");
	  	   lErgebnis.first(); 	     
	    }
	  
	    while(!lErgebnis.isAfterLast())   
	        {
        	lTermin = new Termin(lErgebnis.getInt(1),lErgebnis.getDate(3).toLocalDate(),lErgebnis.getDate(4).toLocalDate(),lErgebnis.getTime(5).toLocalTime(),lErgebnis.getTime(6).toLocalTime(),lErgebnis.getInt(7),lErgebnis.getString(8),lErgebnis.getInt(9),lErgebnis.getString(10),lErgebnis.getString(11));
	    	TerminAL.add(lTermin);
	        lErgebnis.next();
	          
	        }
	        } catch (Exception ex)
	             {
	               System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	             }
	     if(!(TerminAL.size()==0))
	     {
	    	 return TerminAL ;
	     }
	     else
	     {
	 
	     }
	     return TerminAL;
	     
}
   // Wandelt AL<Personen> in AL<String> um
   public static ArrayList<String> getPersonen()
   {
	  ArrayList<String> lPersonen = new ArrayList<String>();
	  ArrayList<Person> lPersonenAL = new ArrayList<Person>();
	  lPersonenAL = FensterController.getPersonenAL();
	  for (int i = 0; i < lPersonenAL.size(); i++)
	  {
		 String lName = new String();
		 lName = lPersonenAL.get(i).getName();
		 String lPersonenID = "";
		 lPersonenID = Integer.toString(lPersonenAL.get(i).getID());
		 lName = lName.concat(" " + lPersonenID);
		 lPersonen.add(lName);
	  }
	  return (lPersonen);
   }

   // Holt höchste ID
   public static String getLetztePerson()
   {
	  String lPerson = new String();
	  ArrayList<String> lPersonenAL = new ArrayList<String>();
	  lPersonenAL = getPersonen();
	  lPerson = lPersonenAL.get(lPersonenAL.size() - 1);
	  return (lPerson);
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
