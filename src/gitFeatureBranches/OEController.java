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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
    private Stage 		bühneOrganisationseinheitenhinzufuegen = new Stage();	
    private AnchorPane 	grundPane;
    private AnchorPane 	grundPane2;
   private int hinzugefügteOrganisationseinheiten;
    
    public void initialize()
    {

 	 btZuweisen.setOnAction(new EventHandler<ActionEvent>() {
 		@Override
 	    public void handle(ActionEvent event)
 	    { 
 		   hinzufügenOrganisationseinheit(tfName.getText(), dbVerbindung.holenConnection());
 	    }
    } );
    }
    //Holt sich höchste ID und Fügt Person mit höchsterID+1 hinzu.
    void hinzufügenOrganisationseinheit(String pOrganisationseinheit, Connection connection)
    {
 	  Statement lBefehl;
 	  String ID = new String();
 	  String[] tokens;
 	  
 	  ID = Organisationseinheit.getLetzteOrganisationseinheit();
 	  tokens = ID.split(" ");
 	  ID = tokens[tokens.length-1];
 	  ID = Integer.toString(Integer.parseInt(ID)+hinzugefügteOrganisationseinheiten);
 	  hinzugefügteOrganisationseinheiten++;
 	  try
 	  {
 		 lBefehl= connection.createStatement();
 		 lBefehl.executeUpdate("INSERT INTO Organisationseinheit(OEID,OENAME) VALUES('"+ID+"','"+pOrganisationseinheit+"',NULL);");
 	  }
 	  catch (SQLException e)
 	  {
 		 // TODO Automatisch generierter Erfassungsblock
 		e.printStackTrace();
 	  }
    }
}

