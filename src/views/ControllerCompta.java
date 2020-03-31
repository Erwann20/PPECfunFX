/*
 * 
 */
package views;

import java.util.logging.Level;
import java.util.logging.Logger;

import emt.MainApp;
import emt.model.Arrivee;
import emt.model.Complexe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerCompta.
 */
public class ControllerCompta implements ControleurVue{
   
   /** The main app. */
   private MainApp mainApp;
   
   /** The complexe. */
   private Complexe complexe;
   
   /** The t V arrivee. */
   @FXML
   private TableView<Arrivee> tVArrivee;
   
   /** The num arrivee. */
   @FXML
   private TableColumn<Arrivee, Integer> numArrivee;
   
   /** The sport. */
   @FXML
   private TableColumn<Arrivee, String> sport;
   
   /** The heure arrivee. */
   @FXML
   private TableColumn<Arrivee, String> heureArrivee;
   
   /** The date sortie. */
   @FXML
   private TableColumn<Arrivee, String> dateSortie;
   
   /** The montant. */
   @FXML
   private TableColumn<Arrivee, String> montant;
   
   /** The txt montant tot. */
   @FXML
   private Label txtMontantTot;
   
   private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
   
   /**
    * Initialize.
    */
   @FXML
   public void initialize() {
      
      this.numArrivee.setCellValueFactory(cellData -> cellData.getValue().getNumProperty().asObject());
      this.sport.setCellValueFactory(cellData -> cellData.getValue().getSportProperty());
      this.heureArrivee.setCellValueFactory(cellData -> cellData.getValue().getHeureDepartProperty());
      //this.sortie.setCellValueFactory(cellData -> cellData.getValue().getSortieProperty());  
      this.dateSortie.setCellValueFactory(cellData -> cellData.getValue().getDateSortieProperty());
      this.montant.setCellValueFactory(cellData -> cellData.getValue().getMontantProperty());
      
    
   }
   
   /**
    * Button retour.
    */
   @FXML
   public void buttonRetour() {
      mainApp.afficherVue("/views/ViewPrincipal.fxml");
   }
   
   @FXML
   public void buttonDetail() {
      Arrivee arrivee = this.tVArrivee.getSelectionModel().selectedItemProperty().get();
      
      if (arrivee != null) {
         mainApp.afficherVue("/views/ViewDetailArrivee.fxml", arrivee);
         
      } else {
         this.LOG.log(Level.SEVERE,"Aucune arrivée à été selectionner");
         
         Alert alert = new Alert(AlertType.WARNING);
         alert.setTitle("La selection est vide");
         alert.setHeaderText("Pas de selection");
         alert.setContentText("Veuillez selectionner une arrivée");
         
         alert.showAndWait();
      }
      
   }
   
   
   
   
   /**
    * Sets the main app.
    *
    * @param mainApp the new main app
    * @throws Exception the exception
    */
   @Override
   public void setMainApp(MainApp mainApp) throws Exception {
      this.mainApp = mainApp;
      this.complexe = mainApp.getComplexe();
      
      this.tVArrivee.setItems(mainApp.getComplexe().getLesArriveesObervableList());
      this.txtMontantTot.setText(String.valueOf(mainApp.getComplexe().calculMontantTotal()));
   }

   /**
    * Gets the nommethode.
    *
    * @return the nommethode
    */
   @Override
   public String getNOMMETHODE() {
      // TODO Auto-generated method stub
      return "Compta";
   }

}
