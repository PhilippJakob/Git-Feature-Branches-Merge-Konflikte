package gitFeatureBranches;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.control.gauge.linear.AbstractLinearGauge;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
   
   private Stage 		bühne;
   private AnchorPane 	grundPane;

   @Override
   public void start(Stage pPrimaryStage) throws IOException
   {

       this.bühne = pPrimaryStage;
       FXMLLoader lLoader = new FXMLLoader();

       lLoader.setLocation(Main.class.getResource("Main.fxml"));
       grundPane = lLoader.load();
       Scene lScene = new Scene(grundPane);
       bühne.setScene(lScene);
       bühne.show();
   }
	
	public static void main(String[] args) {
	 
		launch(args);
		
		
	}
}
