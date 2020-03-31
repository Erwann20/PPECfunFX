/*
 * 
 */
package views;

import java.util.logging.Level;
import java.util.logging.Logger;

import emt.MainApp;
import emt.model.Complexe;
import emt.model.Session;
import emt.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlleurAuth.
 */
public class ControlleurAuth implements ControleurVue{
   
   /** The main app. */
   private MainApp mainApp;
   
   /** The complexe. */
   private Complexe complexe;
   
   /** The session. */
   private Session session;
   
   /** The user. */
   private User user;
   
   /** The utilisateur. */
   @FXML
   private TextField utilisateur;
   
   /** The mot de passe. */
   @FXML
   private PasswordField motDePasse;   
   
   
   private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
   
   /**
    * Initialize.
    */
   @FXML
   private void initialize() {
     
      
   }

   
   
   /**
    * Button connexion avec verif 
    * de l'id et du mot de passe.
    */
   @FXML
   public void buttonCo() {
      
      user = this.complexe.trouverLeUser(utilisateur.getText(), motDePasse.getText());
      
   
      if (user != null) {
         session.createSession(user);
         mainApp.afficherVue("/views/ViewPrincipal.fxml");
      } else {
         
         LOG.log(Level.SEVERE,"erreur de connexion | valeur essayer: (" + utilisateur.getText() +", "+
         motDePasse.getText()+")");
         
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Authentification impossible");
         alert.setHeaderText("Authentification impossible");
         alert.setContentText("Veuillez rentrer un mot de passe valide");

         alert.showAndWait();
      }
      
        
   }
   
   /**
    * initialise le mainApp ainsi que les instance d'objet de complexe et d'un user.
    *
    * @param mainApp the new main app
    * @throws Exception the exception
    */
   @Override
   public void setMainApp(MainApp mainApp) throws Exception {
      this.mainApp = mainApp;     
      this.complexe = this.mainApp.getComplexe();
      this.session = mainApp.getSession();
      
   }

   /**
    * retourne le nom.
    *
    * @return the nommethode
    */
   @Override
   public String getNOMMETHODE() {
      
      return "Connexion";
   }

   /**
    * Gets the complexe.
    *
    * @return the complexe
    */
   public Complexe getComplexe() {
      return complexe;
   }

}
