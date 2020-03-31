/*
 * 
 */
package views;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import emt.MainApp;

import emt.database.BaseHsqldb;

import emt.model.Session;


import javafx.application.Platform;
import javafx.fxml.FXML;


/**
 * The Class ControllerRootLayout.
 *
 * @author erwan
 */
public class ControllerRootLayout  {
	
	/** The bdd. */
	BaseHsqldb bdd = new BaseHsqldb();
	
	/** The mainapp. */
	private MainApp mainapp;
	
	/** The session. */
	private Session session;
	private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
	
	/**
	 * Bouton menu qui permet à l'utilisateur d'entrée.
	 */
	@FXML
	public void openViewPrincipal() {
		mainapp.afficherVue("/views/ViewM_ou_F.fxml");
	}
	
	/**
	 * Bouton menu qui permet à l'utilisateur de sortir.
	 */
	@FXML
   public void openViewSortie() {
      mainapp.afficherVue("/views/ViewSortie.fxml");
   }
	
	/**
	 * Bouton menu qui permet de se connecter.
	 */
	@FXML
   private void openAuth() {
	   if (this.session.dernierUserConnecter()!= null) {
         this.mainapp.afficherVue("/views/ViewPrincipal.fxml");
      } else {
         mainapp.afficherVue("/views/ViewAuth.fxml");
      }
	   
     
   }
	
	
	   /**
	    * Fermeture del'application.
	    */
	@FXML
	private void handleQuitter() {
		try {
			bdd.testBDD();
			bdd.DeleteAll();
			bdd.deconnection();
			LOG.log(Level.INFO,"Base correctement suprimée");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	   Platform.exit();
	 
	}
	
	/**
	 * Sets the mainapp.
	 *
	 * @param mainapp the new mainapp
	 */
	public void setMainapp(MainApp mainapp) {
		this.mainapp = mainapp;
		this.session = mainapp.getSession();
		
	}
	
	

}
