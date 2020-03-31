
package emt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import emt.database.BaseHsqldb;
import emt.formatter.HTMLFormatter;
import emt.model.Arrivee;
import emt.model.Complexe;
import emt.model.Session;
import emt.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.ControleurVue;
import views.ControllerDetail;
import views.ControllerRootLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class MainApp.
 */
public class MainApp extends Application {
	
	/** The titre. */
	private final String TITRE ="PPECUN - ";
	
	/** The primary stage. */ 
	private Stage primaryStage;
	
	/** The principal. */
	private BorderPane principal;
	
	/** The complexe. */
	private Complexe complexe;
	
	/** The un user. */
	private User unUser;
	
	/** The session. */
	private Session session;
	
	/** The bdd. */
	private BaseHsqldb bdd;

	/** The les arrivees. */
	private ObservableList<Arrivee> lesArrivees = FXCollections.observableArrayList();
	
	private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
	
	
	/**
	 * Créer les Objets Complexe et user.
	 *
	 * @throws SQLException the SQL exception
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public MainApp() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, IOException {
	   
	   LOG.setLevel(Level.INFO);
      FileHandler fhInfos = new FileHandler("logs/log.html");
      fhInfos.setFormatter(new HTMLFormatter());
      fhInfos.setLevel(Level.ALL);
      LOG.addHandler(fhInfos);
      
	   
		this.session = new Session();
	   
		this.complexe = new Complexe(2, 2, "PPE CFUN");
		
		
		this.unUser = new User("admin", "admin");
		
		this.complexe.getLesUsers().add(unUser);
	}
	
	/**
	 * Initialisation de l'interface.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(TITRE);
		
		this.initRootLayout();
	
		
	}
	
	/**
	 * Interface principal de l'appli. Cette méthode initialise la localisation de la vue 
	 * et lui injecte son controller
	 */
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/views/RootLayout.fxml"));
			
			this.principal = (BorderPane) loader.load();
			
			Scene scene = new Scene(principal);
			this.primaryStage.setScene(scene);
			
			ControllerRootLayout controleur = loader.getController();
			controleur.setMainapp(this);
			
			this.primaryStage.show();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode que l'on va reutiliser afin d'initier chaque vue.
	 *
	 * @param laVue the la vue
	 */
	public void afficherVue(String laVue) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(laVue));
			
			AnchorPane vueSecondaire = (AnchorPane) loader.load();
			
			principal.setCenter(vueSecondaire);
			
			ControleurVue controleur = loader.getController();
			controleur.setMainApp(this);
			
			this.primaryStage.setTitle(this.TITRE + controleur.getNOMMETHODE());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void afficherVue(String laVue, Arrivee arrivee) {
      try {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource(laVue));
         
         AnchorPane vueSecondaire = (AnchorPane) loader.load();
         
         // Create the dialog Stage.
         Stage dialogStage = new Stage();
         dialogStage.setTitle("Edit Person");
         dialogStage.initModality(Modality.WINDOW_MODAL);
         dialogStage.initOwner(primaryStage);
         Scene scene = new Scene(vueSecondaire);
         dialogStage.setScene(scene);

         ControllerDetail controleur = loader.getController();
         controleur.setMainApp(this);
         controleur.setArrivee(arrivee);
         controleur.setDialog(dialogStage);
         
         dialogStage.show();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
	
	/**
	 * return le complexe.
	 *
	 * @return the complexe
	 */
	public Complexe getComplexe() {
      return complexe;
   }
	
	/**
	 * return le primaryStage (Interface principale).
	 *
	 * @return the primary stage
	 */
   public Stage getPrimaryStage() {
		return primaryStage;
	}
   
   
	
	
	/**
	 * Gets the les arrivees.
	 *
	 * @return the les arrivees
	 */
	public ObservableList<Arrivee> getLesArrivees() {
      return lesArrivees;
   }
	
	

   /**
    * Sets the les arrivees.
    *
    * @param lesArrivees the new les arrivees
    */
   public void setLesArrivees(ObservableList<Arrivee> lesArrivees) {
      this.lesArrivees = lesArrivees;
   }
   
   
   
   /**
    * permet de récupérer le log
    * @return
    */
   public Logger getLog() {
      return LOG;
   }

   /**
    * Gets the session.
    *
    * @return the session
    */
   public Session getSession() {
      return session;
   }

   /**
    * C'est ce qui va permettre de lancer l'appli dans la JVM.
    *
    * @param args the arguments
    */
   public static void main(String[] args) {
		launch(args);
	}
}
