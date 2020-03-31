/*
 * 
 */
package views;


import emt.MainApp;
import emt.model.Complexe;
import emt.model.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Class ControlleurPrincipal.
 */
public class ControlleurPrincipal implements ControleurVue{
	
	/** The mainapp. */
	private MainApp mainapp;
	
	/** The un complexe. */
	private Complexe unComplexe;
	
	/** The session. */
	private Session session;
	
	/** The txt nom commerce. */
	@FXML
	private Label txtNomCommerce;
	
	/** The txt entete. */
	@FXML
	private Label txtEntete;
	
	/** The txt muscu. */
	@FXML
	private Label txtMuscu;
	
	/** The txt fitness. */
	@FXML
	private Label txtFitness;
	
	/** The txt bas. */
	@FXML
	private Label txtBas;
	
	/** The cercle muscu. */
	@FXML
	private Circle cercleMuscu;
	
	/** The cercle fit. */
	@FXML
	private Circle cercleFit;
	
	/** The M libre. */
	//Ajouts Matisse
	@FXML
	private Label MLibre;
	
	/** The F libre. */
	@FXML
	private Label FLibre;
	
	/** The M oc. */
	@FXML
	private Label MOc;
	
	/** The F oc. */
	@FXML
	private Label FOc;
	
	/** The tm. */
	@FXML
	private Label TM;
	
	/** The tf. */
	@FXML
	private Label TF;
	
	/** The Rec M. */
	@FXML
	private Rectangle RecM;
	
	/** The Rec F. */
	@FXML
	private Rectangle RecF;
	
	/** The grid pane. */
	@FXML
	private GridPane gridPane;
	
	

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
	  
	}

	/**
	 * Open compta.
	 */
	@FXML
	public void openCompta() {
	   this.mainapp.afficherVue("/views/ViewCompta.fxml");
	}
	
	/**
	 * Deconnexion session.
	 *
	 * @throws Exception the exception
	 */
	@FXML
	public void deconnexionSession() throws Exception {
	   this.session.destroySession();
	   this.mainapp.afficherVue("/views/ViewAuth.fxml");
	}
	
	/**
	 * retourne un objet complexe.
	 *
	 * @return the un complexe
	 */
	public Complexe getUnComplexe() {
		return unComplexe;
	}

	/**
	 * Récupére les objets initialise dans le mainApp.
	 *
	 * @param mainApp the new main app
	 */
	@Override
	public void setMainApp(MainApp mainApp) {
		this.mainapp = mainApp;
		this.unComplexe = this.mainapp.getComplexe();
		this.session = mainApp.getSession();
		setLeText(this.unComplexe);
		setCouleur();
	}
	
	/**
	 * initialise l'affichage permettant de donner tous les détail
	 * du complexe.
	 *
	 * @param complexe the new le text
	 */
	public void setLeText(Complexe complexe) {
	   this.txtNomCommerce.setText(complexe.getNomComplexe());
		this.txtEntete.setText(complexe.txtEntete());
		this.MLibre.setText(complexe.MuscuLibre());
		this.FLibre.setText(complexe.FitLibre());
		this.MOc.setText(complexe.MuscuOccupée());
		this.FOc.setText(complexe.FitOccupée());
		this.TM.setText(complexe.MuscuTaux());
		this.TF.setText(complexe.FitTaux());
		this.txtBas.setText(complexe.txtBas());
	}
	
	/**
	 * va permettre de initialiser les couleurs.
	 */
	public void setCouleur() {
		if(unComplexe.couleurMuscu().contentEquals("vert")) {
			this.RecM.setFill(Color.GREEN);
		}
		else if(unComplexe.couleurMuscu().contentEquals("orange")) {
			this.RecM.setFill(Color.ORANGE);
		}
		else {
			this.RecM.setFill(Color.DARKRED);
		}
		
		if(unComplexe.couleurFit().contentEquals("vert")) {
			this.RecF.setFill(Color.GREEN);
		}
		else if(unComplexe.couleurFit().contentEquals("orange")) {
			this.RecF.setFill(Color.ORANGE);
		}
		else {
			this.RecF.setFill(Color.DARKRED);
		}
	}
	
	/**
	 * Matisse c'est à toi de jouer mdr.
	 */
	public void setCirclePosition() {
		this.cercleMuscu.setLayoutX(this.txtMuscu.getWidth() +  630);
		this.cercleMuscu.setLayoutY(this.txtMuscu.getLayoutY() + 12);
		
		this.cercleFit.setLayoutX(this.txtFitness.getWidth() + 590);
		this.cercleFit.setLayoutY(this.txtFitness.getLayoutY() + 22);
	}

	/**
	 * Gets the nommethode.
	 *
	 * @return the nommethode
	 */
	@Override
	public String getNOMMETHODE() {
		// TODO Auto-generated method stub
		return "Détail complexe";
	}
	
	
	
}
