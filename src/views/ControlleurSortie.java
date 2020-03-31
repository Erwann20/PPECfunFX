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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
// TODO: Auto-generated Javadoc

/**
 * The Class ControlleurSortie.
 *
 * @author s
 * zdzdzdzd
 */
public class ControlleurSortie implements ControleurVue{

   /** The main app. */
   private MainApp mainApp;
   
   /** The complexe. */
   private Complexe complexe;
   
   /** The txt. */
   @FXML
   private Label txt;
   
   /** The num. */
   @FXML
   private TextField num;

   private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
   
   /**
    * Bouton de validation de sortie
    * verifie si l'utilisateur via le numéro n'est pas déja sortie .
    *
    * @throws Exception the exception
    */
   @FXML
   public void ButtonOk() throws Exception {
      
      
         this.actionButton();
     
      
      
   }
   
   
   public void actionButton() throws Exception {
         int num = 0;
         
         if (this.verifNum()) {
            num = Integer.valueOf(this.num.getText());
         } else {
            
            this.LOG.log(Level.SEVERE,"La saisie n'est pas un numéro");
            
            Alert alert = new Alert(AlertType.INFORMATION);
            
            alert.setTitle("Numéro invalide");
            alert.setHeaderText("Ce n'est pas un numéro");
            alert.setContentText("Veuillez rentrer un numéro valide");
      
            alert.showAndWait();
         }
         
         
         
         if (this.estPresent(num)==true) {
            
            this.dejasortie(num);
                   
         } else {
            this.LOG.log(Level.SEVERE,"Le numéro saisie n'a pas été inscrit dans la liste des arriver");
            Alert alert = new Alert(AlertType.INFORMATION);
            
            alert.setTitle("Numéro invalide");
            alert.setHeaderText("Ce numéro n'a pas été attribuer");
            alert.setContentText("Veuillez rentrer un numéro valide");
      
            alert.showAndWait();
            
         }
      
      
   }
   
   public boolean verifNum() {
      boolean verif = false;
      int num;
      
      try {
         num = Integer.parseInt(this.num.getText());
         
         verif = true;
         
        
      } catch (NumberFormatException e) {
         
      }
       return verif;
   }
   
   public void dejasortie(int num) throws Exception {
      
      if (this.complexe.getLesArrivees().get(num-1).getDateSortie() == null) {
         
         this.txt.setText(this.complexe.sortieUsager(num).afficheTicket());
      } else {
         Alert alert = new Alert(AlertType.INFORMATION);
         
         alert.setTitle("Numéro déja saisie");
         alert.setHeaderText("Numéro déja saisie");
         alert.setContentText("Numéro à déja été saisie");

         alert.showAndWait();
      }
   }
   
   /**
    * verifie si l'objet arrivee est présent dans la liste.
    *
    * @param num the num
    * @return true, if successful
    */
   public boolean estPresent(int num) {
      boolean present = false;
      
      for(Arrivee arrivee: this.complexe.getLesArrivees()) {
         if (num == arrivee.getNumeroArrivee() && num > 0) {
            present = true;
         }
         
      }
      return present;
   }
   
   /**
    * Récupére les objets initialiser dans le MainApp.
    *
    * @param mainApp the new main app
    * @throws Exception the exception
    */
   @Override
   public void setMainApp(MainApp mainApp) throws Exception {
      this.mainApp = mainApp;
      this.complexe = mainApp.getComplexe();
     
   }

/**
 * Initialie le titre de la fenetre.
 *
 * @return the nommethode
 */
   @Override
   public String getNOMMETHODE() {
      // TODO Auto-generated method stub
      return "Sortie";
   }

}
