/*
 * 
 */
package emt.model;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import emt.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * The Class Complexe.
 */
public class Complexe {
	
	/** The numero actuel. */
	private static int numeroActuel = 0;
	
	/** The nom complexe. */
	private String nomComplexe;
	
	/** The nb total places fit. */
	private int nbTotalPlacesFit;
	
	/** The nb total places muscu. */
	private int nbTotalPlacesMuscu;
	
	/** The nb places occupees fit. */
	private int nbPlacesOccupeesFit;
	
	/** The nb places occupees muscu. */
	private int nbPlacesOccupeesMuscu;

	/** The les arrivees. */
	private List<Arrivee> lesArrivees = new ArrayList<Arrivee>();
	
	/** The les users. */
	private ArrayList<User> lesUsers = new ArrayList<User>();
	
	private static final  Logger LOG = Logger.getLogger(MainApp.class.getPackage().getName());
	
	/** The les arrivees obervable list. */
	private ObservableList<Arrivee> lesArriveesObervableList = FXCollections.observableArrayList();
	
	/**
	 * Instantiates a new complexe.
	 *
	 * @param nbTotalPlacesMuscu the nb total places muscu
	 * @param nbTotalPlacesFit the nb total places fit
	 * @param nomComplexe the nom complexe
	 * @throws SQLException the SQL exception
	 */
	public Complexe(final int nbTotalPlacesMuscu, final int nbTotalPlacesFit,
		final String nomComplexe) throws SQLException {
	  
		this.nbTotalPlacesFit = nbTotalPlacesFit;
		this.nbTotalPlacesMuscu = nbTotalPlacesMuscu;
		this.nomComplexe = nomComplexe;
		this.nbPlacesOccupeesFit = 0;
		this.nbPlacesOccupeesMuscu = 0;
		
		LOG.log(Level.INFO,"Le complexe à été ajouter");
		
	}
	
	
	/**
	 * Entree usager.
	 *
	 * @param uneArrivee the une arrivee
	 * @return
	 */
	public boolean entreeUsager(final Arrivee uneArrivee) {
		boolean ok;
		char choix;

		ok = false;
		choix = uneArrivee.getChoixSport();
		if (choix == 'F') {
			if (this.etatFit() != 1) {
				Complexe.setNumeroActuel();
				uneArrivee.setNumeroArrivee(Complexe.getNumeroActuel());
				lesArrivees.add(uneArrivee);
				this.lesArriveesObervableList.add(uneArrivee);
				this.nouvelUsagerFitness();
				ok = true;
				
				LOG.log(Level.INFO,"Un usager rentre dans l'espace fitness");
			}
		} else {
			if (this.etatMuscu() != 1.0) {
				Complexe.setNumeroActuel();
				uneArrivee.setNumeroArrivee(Complexe.getNumeroActuel());
				lesArrivees.add(uneArrivee);
				this.lesArriveesObervableList.add(uneArrivee);
				this.nouvelUsagerMusculation();
				ok = true;
				
				LOG.log(Level.INFO,"Un usager rentre dans l'espace musculation");
			}
		}
		return ok;
	}

	/**
	 * Sortie usager.
	 *
	 * @param entree the entree
	 * @return the arrivee
	 * @throws Exception retourne le depart de l'usager de la sale de sport
	 */
	public Arrivee sortieUsager(final int entree) throws Exception {
		Arrivee leDepart = recherche(entree);
		
		
			if (leDepart.getChoixSport() == 'F') {
			   
				this.oterUsagerFitness();
			} else {
			  
				this.oterUsagerMusculation();
			}
		
			LOG.log(Level.INFO,"Un usager sort");
		return leDepart;
	}

 /**
  * Couleur muscu.
  *
  * @return the string
  */
 /*
  * retourne la couleur de la salle de sport
  */
	public String couleurMuscu() {
		ChoixCouleur choixCouleur = new ChoixCouleur(this.etatMuscu());
		return choixCouleur.getCouleur().toString();
	}

	/**
	 * Etat fit.
	 *
	 * @return the double
	 */
	/*retourne le nombre de place occupé*/
	public double etatFit() {
		return (this.getNbPlacesOccupeesFit()) * 1.0 / this.nbTotalPlacesFit;
	}

	/*
	 * retourne l'etat de la salle de sport dans l'instanté
	 * affiche : la date, l'heure, le nombre de place disponible et occupé pour la musculation et le fitness
	 * ainsi que la couleur (vert, orange, rouge)
	 */
	
	
	/**
	 * Txt entete.
	 *
	 * @return the string
	 */
	public String txtEntete() {
      final String MSGDATE = "date : ";
      final String MSGHEURE = "heure : ";
      
      String leDoc;

      Date laDate = Calendar.getInstance().getTime();
      SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");
      leDoc = MSGDATE + leJour.format(laDate) + "\t";
      SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");
      leDoc += MSGHEURE + lHeure.format(laDate) + "\n";
      
      return leDoc;

	}
	
	/**
	 * Txt muscu.
	 *
	 * @return the string
	 */
	@Deprecated
	public String txtMuscu() {
	   final String MSGDISPMUSCU = "Places disponibles M : ";
	   final String MSGOCCMUSCU = "Places occupées M : ";
	   final String MSGTXMUSCU = "Taux occ. M : ";
	   final String MSGCOULMUSCU = "Couleur M : ";
	   
	   String leDoc = "";
	   DecimalFormat df2 = new DecimalFormat("##0.00%");
	   leDoc = MSGDISPMUSCU + this.getNbPlacesRestantesMuscu() + "\t";
      leDoc += MSGOCCMUSCU + this.nbPlacesOccupeesMuscu + "\t";
      leDoc += MSGTXMUSCU + df2.format(this.etatMuscu()) + "\t";
      leDoc += MSGCOULMUSCU + "\n";
	   
      return leDoc;
	   
	}
	
	/**
	 * Txt fitness.
	 *
	 * @return the string
	 */
	@Deprecated
	public String txtFitness() {
	      
	      final String MSGDISPFIT = "Places disponibles F : ";
	      final String MSGOCCFIT = "Places occupées F : ";
	      final String MSGTXFIT = "Taux occ. F : ";
	      final String MSGCOULFIT = "Couleur F : ";
	      

	      String leDoc=""; 
	      DecimalFormat df2 = new DecimalFormat("##0.00%");

	      leDoc += MSGDISPFIT + this.getNbPlacesRestantesFit() + "\t";
	      leDoc += MSGOCCFIT + this.nbPlacesOccupeesFit + "\t";
	      leDoc += MSGTXFIT + df2.format(this.etatFit()) + "\t";
	      leDoc += MSGCOULFIT + "\n\n";
	      return leDoc;
	   }
	
	/**
	 * Muscu libre.
	 *
	 * @return the string
	 */
	public String MuscuLibre() {
		return String.valueOf(this.getNbPlacesRestantesMuscu());
	}
	
	/**
	 * Fit libre.
	 *
	 * @return the string
	 */
	public String FitLibre() {
		return String.valueOf(this.getNbPlacesRestantesFit());
	}
	
	/**
	 * Muscu occupée.
	 *
	 * @return the string
	 */
	public String MuscuOccupée() {
		return String.valueOf(this.nbPlacesOccupeesMuscu);
	}
	
	/**
	 * Fit occupée.
	 *
	 * @return the string
	 */
	public String FitOccupée() {
		return String.valueOf(this.nbPlacesOccupeesFit);
	}
	
	/**
	 * Muscu taux.
	 *
	 * @return the string
	 */
	public String MuscuTaux() {
		String leDoc; 
	    DecimalFormat df2 = new DecimalFormat("##0.00%");
	    
	    leDoc = df2.format(this.etatMuscu());
		
	    return leDoc; 
	}
	
	/**
	 * Fit taux.
	 *
	 * @return the string
	 */
	public String FitTaux() {
		String leDoc; 
	    DecimalFormat df2 = new DecimalFormat("##0.00%");
	    
	    leDoc = df2.format(this.etatFit());
		
	    return leDoc;
	}
	

	
	/**
	 * Txt bas.
	 *
	 * @return the string
	 */
	public String txtBas() {
	   final String MSGBAS = "M : en musculation   F : en fitness";
	   
	   return MSGBAS;
	}
	
	/**
	 * Gets the nb places restantes fit.
	 *
	 * @return the nb places restantes fit
	 */
	public int getNbPlacesRestantesFit() {
		return this.nbTotalPlacesFit - (this.nbPlacesOccupeesFit);
	}

	/**
	 * Gets the nb places occupees fit.
	 *
	 * @return the nb places occupees fit
	 */
	public int getNbPlacesOccupeesFit() {
		return this.nbPlacesOccupeesFit;
	}

	/**
	 * Nouvel usager fitness.
	 */
	public void nouvelUsagerFitness() {
		nbPlacesOccupeesFit++;
	}

	/**
	 * Oter usager fitness.
	 *
	 * @throws Exception the exception
	 */
	public void oterUsagerFitness() throws Exception {
		
		if (this.nbPlacesOccupeesFit > 0) {
			nbPlacesOccupeesFit--;
		} 
		
	}

	/**
	 * Gets the nb places restantes muscu.
	 *
	 * @return the nb places restantes muscu
	 */
	public int getNbPlacesRestantesMuscu() {
		return this.nbTotalPlacesMuscu - (this.nbPlacesOccupeesMuscu);
	}

	/**
	 * Gets the nb places occupees muscu.
	 *
	 * @return the nb places occupees muscu
	 */
	public int getNbPlacesOccupeesMuscu() {
		return this.nbPlacesOccupeesMuscu;
	}

	/**
	 * Nouvel usager musculation.
	 */
	public void nouvelUsagerMusculation() {
		nbPlacesOccupeesMuscu++;
	}

	/**
	 * Oter usager musculation.
	 *
	 * @throws Exception the exception
	 */
	public void oterUsagerMusculation() throws Exception{
		
		if (this.nbPlacesOccupeesMuscu > 0) {
			nbPlacesOccupeesMuscu--;
		} 
		

	}

	/**
	 * Couleur fit.
	 *
	 * @return the string
	 */
	public String couleurFit() {
		ChoixCouleur choixCouleur = new ChoixCouleur(this.etatFit());
		return choixCouleur.getCouleur().toString();
	}

	/**
	 * Etat muscu.
	 *
	 * @return the double
	 */
	public double etatMuscu() {
		return (this.getNbPlacesOccupeesMuscu()) * 1.0
				/ this.nbTotalPlacesMuscu;
	}

	/**
	 * Recherche.
	 *
	 * @param num the num
	 * @return the arrivee
	 * @throws Exception the exception
	 */
	public Arrivee recherche(int num) throws Exception{
		int i = 0;
		Arrivee courant = lesArrivees.get(i);
		
		while (courant.getNumeroArrivee() != num) {
			courant = lesArrivees.get(++i);
		}
		
		
		return courant;
	}
	
	/**
	 * Gets the numero actuel.
	 *
	 * @return the numero actuel
	 */
	public static int getNumeroActuel() {
		return numeroActuel;
	}

	/**
	 * Sets the numero actuel.
	 */
	public static void setNumeroActuel() {
		Complexe.numeroActuel = Complexe.getNumeroActuel() + 1;
	}
	
	/**
	 * Gets the nom complexe.
	 *
	 * @return the nom complexe
	 */
	public String getNomComplexe() {
		return nomComplexe;
	}


   /**
    * Gets the les arrivees.
    *
    * @return the les arrivees
    */
   public List<Arrivee> getLesArrivees() {
      return lesArrivees;
   }
	
   /**
    * Calcul montant total.
    *
    * @return the double
    * @throws Exception the exception
    */
   public double calculMontantTotal() throws Exception {
      double montantTo = 0;
      for (Arrivee arrivee: this.lesArrivees) {
         montantTo +=arrivee.getMontant();
      }
      
      return montantTo;
   }


   /**
    * Gets the les arrivees obervable list.
    *
    * @return the les arrivees obervable list
    */
   public ObservableList<Arrivee> getLesArriveesObervableList() {
      return lesArriveesObervableList;
   }


   /**
    * Gets the les users.
    *
    * @return the les users
    */
   public ArrayList<User> getLesUsers() {
      return lesUsers;
   }
   
   /**
    * Trouver le user.
    *
    * @param identifiant the identifiant
    * @param motDePasse the mot de passe
    * @return the user
    */
   public User trouverLeUser(String identifiant, String motDePasse) {
      User userValide = null;
      
      for(User user: this.getLesUsers()) {
         
         if (user.getUsername().equals(identifiant) && user.verifMdp(motDePasse)==true) {
            userValide = user;
         }
      }
      
      return userValide;
   }
    
   
	
}

