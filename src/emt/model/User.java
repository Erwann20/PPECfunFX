/*
 * 
 */
package emt.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import emt.MainApp;
import emt.database.BaseHsqldb;
import emt.security.BCrypt;


/**
 * The Class User.
 */
public class User {
   
   /** The username. */
   private String username;
   
   /** The mdp. */
   private String mdp;
   
   /** The connecter. */
   private boolean connecter;
   
   private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
   /**
    * Instantiates a new user.
    *
    * @param username the username
    * @param mdp the mdp
    */
   public User(String username, String mdp) {
          
      BaseHsqldb Bdd = new BaseHsqldb();
      Bdd.testBDD();
      this.username = username;
      this.mdp = mdp;
      this.mdp = BCrypt.hashpw(mdp, BCrypt.gensalt(12));
      
      Bdd.insertUser(username, mdp, 0);
      try {
		Bdd.selectAllUser();
	} catch (SQLException e) {
		e.printStackTrace();
	}
      LOG.log(Level.INFO,"Un utilisateur à été créer");
   }
   
   /**
    * Verif mdp.
    *
    * @param unMdp the un mdp
    * @return true, if successful
    */
   public boolean verifMdp(String unMdp) {
      boolean mdpValid = false ;
      
      if (BCrypt.checkpw(unMdp, mdp)) {
         mdpValid = true;
      } else {
         this.LOG.log(Level.SEVERE,"Le mot de passe n'est pas identique à celui qui est crypter");
      }
      
      return  mdpValid;
   }
   
   /**
    * Gets the username.
    *
    * @return the username
    */
   public String getUsername() {
      return username;
   }
   
   /**
    * Gets the mdp.
    *
    * @return the mdp
    */
   public String getMdp() {
      return mdp;
   }
   
   /**
    * Se connecter.
    */
   public void seConnecter() {
     this.setConnecter(true);
   }
   
   /**
    * Se deconnecter.
    */
   public void seDeconnecter() {
      this.setConnecter(false);
   }
   
   /**
    * Sets the connecter.
    *
    * @param connecter the new connecter
    */
   public void setConnecter( boolean connecter) {
      this.connecter = connecter;
   }
   
   /**
    * Gets the connecter.
    *
    * @return the connecter
    */
   public boolean getConnecter() {
      return this.connecter;
   }
   
   

}
