package emt.model;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.IBarcode;

import emt.database.BaseHsqldb;
import emt.exception.MargeHorraireImpossible;
import emt.exception.PasDHeureDepart;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//ARHANTEC

public class Arrivee {
	private static final int idComplexe = 0;
	private static int idArrivee = 0;
	private static int numeroSortie = 0;
	private int numeroArrivee;
	private char choixSport;
	private long horaireArrivee;
	private String dateSortie;
	private Calendar hDep;
	private Complexe complexe;

	private double montant;
 
	BaseHsqldb bdd = new BaseHsqldb();
	
	
	/**
	 * Constructor
	 * @param complexe
	 * @param choixSport
	 * @throws SQLException 
	 */
	public Arrivee(final Complexe complexe, final char choixSport) {
		
		
		this.horaireArrivee = Calendar.getInstance().getTimeInMillis();
		this.choixSport = choixSport;
		this.complexe = complexe;
		this.hDep = null;
		this.dateSortie = null;
		this.idArrivee++;
	}
		
		

	/**
	 * Permet de retourner un billet avec le n° du billet d'entrée, le complexe, la date et l'heure
	 * @return
	 * @throws Exception 
	 */
	
	public String afficheBillet() throws Exception {
		//Déclaration des variables

		final String MSGNOM = "Complexe ";
		final String MSGNUM = "Billet d'entrée n° : ";
		final String MSGDATE = "Date : ";
		final String MSGHEURE = "Heure : ";
		
		String leBillet;//Création du billet


		
		leBillet = MSGNOM + this.getComplexe().getNomComplexe() + "\t";
		leBillet += MSGNUM + this.numeroArrivee + "\n";

		Calendar leCal = Calendar.getInstance();
		leCal.setTimeInMillis(this.horaireArrivee);

		
		Date laDate = leCal.getTime();//récupère la date du jour
		
		SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");//date du jour
		leBillet += MSGDATE + leJour.format(laDate) + "\n";
		
		SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");//heure à l'instanté
		leBillet += MSGHEURE + lHeure.format(laDate) + "\n";
	
		if (idArrivee == 1) {
				bdd.testBDD();
				bdd.creationData();
				insertComplexe();
				bdd.selectAll();
			
			
				// TODO Auto-generated catch block
			}
			
			bdd.insertArrivee(String.valueOf(choixSport), numeroArrivee, leJour.format(laDate)+ " " + lHeure.format(laDate),idComplexe);
			bdd.selectAllArrivee();
		
			// TODO Auto-generated catch block	
		
		creationCodeBarre();

		return leBillet;
	}
	
	public void insertComplexe() throws SQLException {
			
		String nomComplexe = this.getComplexe().getNomComplexe();
		int placeRM = this.getComplexe().getNbPlacesRestantesMuscu();
		int PlaceRF = this.getComplexe().getNbPlacesRestantesFit();
		int PlaceOM = this.getComplexe().getNbPlacesOccupeesMuscu();
		int PlaceOF = this.getComplexe().getNbPlacesOccupeesFit();
	
		bdd.insertComplexe(nomComplexe,placeRM,PlaceRF,PlaceOM,PlaceOF);


		
	}
		
	
/**
 * 
 * @throws Exception
 */
public void creationCodeBarre() throws Exception {
		EAN13 barcode = new EAN13();
		String number = "";
		
		if (this.numeroArrivee < 10) {
		number = "0" + String.valueOf(this.numeroArrivee);
		}else {
			number =  String.valueOf(this.numeroArrivee);	
		}
		
		Calendar leCal = Calendar.getInstance();
		leCal.setTimeInMillis(this.horaireArrivee);
		
		Date laDate = leCal.getTime();//récupère la date du jour 
		SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");//date du jour
		

		//Permet de récuperer la date du jour et de supprimer les / 
		//de la date pour ne laissez que les chiffres
		String today = leJour.format(laDate);
		//remplace les '/' de la date par ''
		today = today.replace("/", "");
		//isole l'année pour retirer les deux premier chiffre (2019 -> 19)
		String year = today.substring(6);
		//retire l'année de la date (01012019 -> 0101)
		today = today.substring(0,today.length()-4);
		//réassemble le tout 
		today += year;
		number += today ;
		SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");//heure à l'instanté
		String heure = lHeure.format(laDate);
		heure = heure.replace(":", "");
		number += heure;
		
		 String data = " ";
		 data = String.valueOf(number); // convertie le chiffre en String
		/*
		   EAN 13 Valid data char set:
		        0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
		
		   EAN 13 Valid data length: 12 digits only, excluding the last checksum digit
		*/
		//barcode.setData("012345678901");
		barcode.setData(data);
		
		// EAN 13 Unit of Measure, pixel, cm, or inch
		barcode.setUom(IBarcode.UOM_PIXEL);
		// EAN 13 bar module width (X) in pixel
		barcode.setX(3f);
		// EAN 13 bar module height (Y) in pixel
		barcode.setY(75f);
		// barcode image margins
		barcode.setLeftMargin(0f);
		barcode.setRightMargin(0f);
		barcode.setTopMargin(0f);
		barcode.setBottomMargin(0f);
		// barcode image resolution in dpi
		barcode.setResolution(72);
		// disply barcode encoding data below the barcode
		barcode.setShowText(true);
		// barcode encoding data font style
		barcode.setTextFont(new Font("Arial", 0, 12));
		// space between barcode and barcode encoding data
		barcode.setTextMargin(6);
		
		//  barcode displaying angle
		barcode.setRotate(IBarcode.ROTATE_0);
		
		//Lien à changer pour generer l'image du code barre
		barcode.drawBarcode("codeBarre/ean" + numeroArrivee + ".png");
		
	}

	/**
	 * Cette fonction retourner le ticket de sortie de la salle 
   	 * en affichant le montant total dû par l'utilisateur
	 * @return
	 */
	public String afficheTicket() throws Exception{
		//Déclaration des variables
		final String MSGNOM = "Complexe ";
		final String MSGNUM = "Ticket de sortie n° : ";
		final String MSGDATE = "Date : ";
		final String MSGHEURE = "Heure : ";
		final String MSGCOUT = "Montant : ";

		String leTicket;

		
		leTicket = MSGNOM + this.getComplexe().getNomComplexe() + "\t";
		leTicket += MSGNUM + ++Arrivee.numeroSortie + "\n";

		this.hDep = Calendar.getInstance();
		
		//on simule ici une sortie 32 mn plus tard
        hDep.add(Calendar.MINUTE, +30);
        //hDep.add(Calendar.MINUTE, +8);
		
		Date laDate = hDep.getTime();
		
		SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");
		this.dateSortie = leJour.format(laDate);
		leTicket += MSGDATE + leJour.format(laDate) + "\n";
		SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");
		leTicket += MSGHEURE + lHeure.format(laDate) + "\n";
		leTicket += MSGCOUT + this.calculMontant() + " €\n";
		
		bdd.updateSortie(numeroArrivee, getDateSortie());
		bdd.selectAllArrivee();
		
		return leTicket;
	}
	
	
	
	/**
	 * permet de retourner le montant en fonction du temps d'occupation de la salle
	 * @return
	 */
	public double calculMontant() {
		double cout = 0;

		if (hDep != null) {
			// on passe des ms en mn
			long dep = hDep.getTimeInMillis() / (1000 * 60);
			long arr = this.horaireArrivee / (1000 * 60);
			long duree =  dep - arr;
			

			if (duree <= 30 && duree > 15) {
				cout = 0.5;
			} else if (duree<15){
				cout=0;
			} else if (duree < 60) {
				cout = 1;
			} else if (duree > 60) {
				// cout fixe d'une heure
				cout = 1;
				duree -= 60;
				// + tous les 1/4 h commencés
				long nbquarts, reste;
				nbquarts = duree / 15;
				reste = duree % 15;
				
				if (reste != 0)
					nbquarts++;
				cout += nbquarts * 0.5;
			} else {
				throw new MargeHorraireImpossible();
			}
		} else {
		   throw new PasDHeureDepart();
		} 
		this.montant = cout;
		return cout;
	} 
	
	public double getMontant() {
	   return this.montant;
	}
		
	/**
	 * getComplexe
	 * @return
	 */
	public Complexe getComplexe() {
		return this.complexe;
	}

	/**
	 * setNumeroArrivee
	 * @param numero
	 */
	public void setNumeroArrivee(int numero) {
		numeroArrivee = numero;
	}
	
	/**
	 * Get NumeroArrivee
	 * @return
	 */
	public int getNumeroArrivee() {
		return numeroArrivee;
	}

	/**
	 * Get choix du sport
	 * @return
	 */
	public char getChoixSport() {
		return choixSport;
	}

   public static int getIdArrivee() {
      return idArrivee;
   }
   
   public String getDateSortie() {
      return dateSortie;
   }


   public IntegerProperty getNumProperty() {
      return new SimpleIntegerProperty(this.numeroArrivee);
   }
   
   public StringProperty getSportProperty() {
      return new SimpleStringProperty(String.valueOf(this.choixSport));
   }
   
  
   public StringProperty getHeureDepartProperty() {
      SimpleDateFormat lHeure = new SimpleDateFormat("HH:mm");
      return new SimpleStringProperty(String.valueOf(lHeure.format(this.horaireArrivee)));
   }
   
   public StringProperty getDateSortieProperty() {
      return new SimpleStringProperty(this.dateSortie);
   }
   
   public Calendar gethDep() {
      return hDep;
   }

      
   
   public String getHoraireArrivee() {
      SimpleDateFormat leJour = new SimpleDateFormat("dd/MM/yyyy");
      return leJour.format(this.horaireArrivee);
   }



   public StringProperty getMontantProperty()  {
      if (this.dateSortie == null) {
         return new SimpleStringProperty("");
      } else {
         return new SimpleStringProperty(String.valueOf(this.getMontant()));
      }
   }
   
   
   
   
   
   
   
   
	
	
}
