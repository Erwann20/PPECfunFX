/*
 * 
 */
package views;

import emt.MainApp;
import emt.model.Arrivee;
import emt.model.Complexe;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlleurM_ou_F.
 */
public class ControlleurM_ou_F implements ControleurVue {
	
	/** The main app. */
	private MainApp mainApp;
	
	/** The arrivee. */
	private Arrivee arrivee;
	
	/** The complexe. */
	private Complexe complexe;
	
	
	/** The bouton fit. */
	@FXML
	private Pane boutonFit;
	
	/** The bouton musc. */
	@FXML
	private Pane boutonMusc;

	
	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {
		
	}
	
	/**
	 * Bouton choix musculation
	 * Si l'utilisateur clique sur le bouton, on verifie premierement si il reste 
	 * toujours de la place (en cas contraire on affiche un message d'erreur), si oui, 
	 * On créer un objet arrivee et on le fait entrer dans le complexe avec la méthode entréeUsager
	 * et finalement on le redirige vers la vue qui lui affihera son billet.
	 */
	@FXML
	public void entreeMuscu() {
	   
		if (this.complexe.getNbPlacesRestantesMuscu() > 0) {
		   arrivee = new Arrivee(this.complexe,'M');
	      this.complexe.entreeUsager(arrivee);
	      mainApp.afficherVue("/views/ViewEntree.fxml");
		} else {
		   Alert alert = new Alert(AlertType.INFORMATION);
		   alert.setTitle("Place non disponible");
         alert.setHeaderText("Place non disponible");
         alert.setContentText("Il n'y a plus de disponible !");

         alert.showAndWait();
		}
		
	}
	
	/**
	 * Bouton choix Fitness
	 * Si l'utilisateur clique sur le bouton, on verifie premierement si il reste 
	 * toujours de la place (en cas contraire on affiche un message d'erreur), si oui, 
	 * On créer un objet arrivee et on le fait entrer dans le complexe avec la méthode entréeUsager
	 * et finalement on le redirige vers la vue qui lui affihera son billet.
	 */
	@FXML
	public void entreeFitn() {
	   
	   if (this.complexe.getNbPlacesRestantesFit() > 0 ) {
   	   arrivee = new Arrivee(this.complexe,'F');
   		this.complexe.entreeUsager(arrivee);
   		mainApp.afficherVue("/views/ViewEntree.fxml");
	   }
   	else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Place non disponible");
            alert.setHeaderText("Place non disponible");
            alert.setContentText("Il n'y a plus de disponible !");

            alert.showAndWait();
         }
	}
	
	/**
	 * Cela va permettre d'afficher le code couleur en fonction des places disponible pour
	 * l'utilisateur.
	 */
	public void setCouleur() {
		if(complexe.couleurMuscu().contentEquals("vert")) {
			this.boutonMusc.setStyle("-fx-background-color: #006400;");
		}
		else if(complexe.couleurMuscu().contentEquals("orange")) {
			this.boutonMusc.setStyle("-fx-background-color: #FF8C00;");
		}
		else {
			this.boutonMusc.setStyle("-fx-background-color: #8B0000;");
		}
		
		if(complexe.couleurFit().contentEquals("vert")) {
			this.boutonFit.setStyle("-fx-background-color: #006400;");
		}
		else if(complexe.couleurFit().contentEquals("orange")) {
			this.boutonFit.setStyle("-fx-background-color: #FF8C00;");
		}
		else {
			this.boutonFit.setStyle("-fx-background-color: #8B0000;");
		}
	}
	
	/**
	 * retourne un objet arrivee.
	 *
	 * @return the arrivee
	 */
	public Arrivee getArrivee() {
		return arrivee;
	}


	/**
	 * Récupére les objets instancié dans le MainApp et d'initilizé la couleur.
	 *
	 * @param mainApp the new main app
	 */
	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		this.complexe = mainApp.getComplexe();
		setCouleur();
	}
	
	/**
	 * initialise le titre de la fenetre.
	 *
	 * @return the nommethode
	 */
	@Override
	public String getNOMMETHODE() {
		// TODO Auto-generated method stub
		return " Choix du sport";
	}

}
