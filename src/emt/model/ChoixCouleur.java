/*
 * 
 */
package emt.model;


/**
 * The Class ChoixCouleur.
 */
public class ChoixCouleur {
	
	/** The couleur. */
	private Couleur couleur;

	
	/**
	 * en fonction de l'occupation de la salle le voyant change de couleur
	 * si 70% de la salle occupé le voyant est rouge 
	 * sinon le voyant est vert ou orange .
	 *
	 * @param etat the etat
	 */
	public ChoixCouleur(final double etat) {
		
		if (etat<0.7) {
			couleur = Couleur.vert; 
			
		} else if (etat>0.7 && etat<1) {
			couleur = Couleur.orange;
			
		} else {
			couleur = Couleur.rouge;
		}
	}
	
	/**
	 * retourne la couleur.
	 *
	 * @return the couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}
}
