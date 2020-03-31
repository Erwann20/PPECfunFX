package views;

import emt.MainApp;
import emt.model.Arrivee;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerDetail {
   private MainApp mainApp;
   private Arrivee arrivee;
   private Stage dialogStage;
   @FXML
   private TextField num;
   @FXML
   private TextField sport;
   @FXML
   private TextField heureArr;
   @FXML
   private TextField montant;
   @FXML
   private TextField dateSortie;
   
   @FXML
   private void initialize() {
     
      
   }
   
   
   public void setMainApp(MainApp mainApp) {
      this.mainApp = mainApp;
      
   }
   
   public void setArrivee(Arrivee arrivee) {
      this.arrivee = arrivee;
      this.setArrivee();
   }
   
   public void setArrivee() {
      
      this.num.setText(String.valueOf(arrivee.getNumeroArrivee()));
      this.sport.setText(String.valueOf(arrivee.getChoixSport()));
      this.heureArr.setText(arrivee.getHeureDepartProperty().get());
      this.montant.setText(String.valueOf(arrivee.getMontant()));
      this.dateSortie.setText(arrivee.getDateSortie());
      
   }
   
   @FXML
   public void buttonRetour() {
      this.dialogStage.close();
   }


   public void setDialog(Stage dialogStage) {
       this.dialogStage = dialogStage;
   }  
   
   
   
}
