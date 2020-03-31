/*
 * 
 */
package views;

import java.io.File;

import com.a.a.a.g.b.a;

import emt.MainApp;
import emt.model.Arrivee;
import emt.model.Complexe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Affine;


// TODO: Auto-generated Javadoc
/**
 * The Class ControlleurEntree.
 */
public class ControlleurEntree implements ControleurVue {
   
	
   /** The main app. */
   private MainApp mainApp;
   
   /** The complexe. */
   private Complexe complexe;   
   
   private static int compteur = 0; 
   /** The txt. */
   @FXML
   private Label txt;
   
   @FXML
   private ImageView codeBaree;

   /**
    * Initialize.
    */
   @FXML
   public void initialize() {
	
   }

   /**
    * Cette méthode fait le lien avec le MainApp, cela va permettre de récupérer 
    * tous les controleurs initier dans le MainApps.
    *
    * @param mainApp the new main app
    * @throws Exception the exception
    */
  
   
   public void afficheCodeBarre() {
	   	compteur++;
	   	System.err.print(compteur);
	      File file = new File("codeBarre/ean" +  compteur + ".png");
	      Image image = new Image(file.toURI().toString());
	      this.codeBaree.setImage(image);
   }
   
   
   @Override
   public void setMainApp(MainApp mainApp) throws Exception {
      this.mainApp = mainApp;
      this.complexe = mainApp.getComplexe();
    
      this.txt.setText(this.txtLastBillet(complexe));
      afficheCodeBarre();
     
      
   }
   
   /**
    * Retour vers l'interface d'accueil pour l'utilisateur.
    */
   @FXML
   public void buttonRetour() {
      
      mainApp.afficherVue("/views/ViewM_ou_F.fxml");
      
   }
   
   /**
    * Permet de récupérer le dernier billet à afficher.
    *
    * @param complexe the complexe
    * @return the string
    * @throws Exception the exception
    */
   public String txtLastBillet(Complexe complexe) throws Exception {
	 
      final int NbArriveeMax = complexe.getLesArrivees().size()-1;
      
      return complexe.getLesArrivees().get(NbArriveeMax).afficheBillet();
      
     
   }
   
   /**
    * titre de l'interface.
    *
    * @return the nommethode
    */
   @Override
   public String getNOMMETHODE() {
      // TODO Auto-generated method stub
      return "Choix du sport validé";
   }

}
