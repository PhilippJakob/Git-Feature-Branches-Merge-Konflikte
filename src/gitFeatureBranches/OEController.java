package gitFeatureBranches;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
public class OEController 
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();
   private static ArrayList<Organisationseinheit> organisationseinheitAL = new ArrayList<Organisationseinheit>();
    @FXML
    private ChoiceBox<?> cbUeber;

    @FXML
    private TextField tfName;

    @FXML
    private ChoiceBox<?> cbStelle;

    @FXML
    private Button btZuweisen;
    private Stage 		bühnePersonenhinzufügen = new Stage();	
    private AnchorPane 	grundPane;
    private AnchorPane 	grundPane2;
    
    public void initialize()
    {
       
 	  if (dbVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
 	  {
 		return;
 	  }
 	  setOrganisationseinheitAL(Organisationseinheit.auslesenDB(dbVerbindung.holenConnection()));
      cbUeber.getItems().addAll(Organisationseinheit.getOrganisationseinheiten());
 	 btZuweisen.setOnAction(new EventHandler<ActionEvent>() {
 		@Override
 	    public void handle(ActionEvent event)
 	    { 
 		  FXMLLoader lLoader = new FXMLLoader();
	       try
	   {
   	  	   lLoader.setLocation(getClass().getResource("AnzeigeOEView.fxml"));
   	  	  
		        
	   }
	   catch (IOException e)
	   {
		  // TODO Automatisch generierter Erfassungsblock
		  e.printStackTrace();
	   }
 	    }
    } );
    }
    
    public void erstellenOE(Organisationseinheit pOrganisationseinheit, Connection connection)
    {
    Statement lBefehl;
	  String ID = new String();
	  String[] tokens;
	  
	  ID = Organisationseinheit.getOrganisationseinheiten();
	  tokens = ID.split(" ");
	  ID = tokens[tokens.length-1];
	  ID = Integer.toString(Integer.parseInt(ID)+hinzugefügteOrganisationseinheiten);
	  hinzugefügteOrganisationseinheiten++;
	  try
	  {
		 lBefehl= connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO organisationseinheit(OEID,OENAME) VALUES('"+ID+"','"+pOrganisationseinheit+"',NULL);");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
 }

   
    public static ArrayList<Organisationseinheit> getOrganisationseinheitAL()
	  {
	     return organisationseinheitAL;
	  }
	  public static void setOrganisationseinheitAL(ArrayList<Organisationseinheit> organisationseinheitAL)
	  {
	     OEController.organisationseinheitAL = organisationseinheitAL;
	  } 
  
}



	  
    	
    	 



