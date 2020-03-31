/*
 * 
 */
package emt.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import emt.model.ChoixCouleur;
import emt.model.Couleur;

// TODO: Auto-generated Javadoc
/**
 * The Class ChoixCouleurTest.
 */
public class ChoixCouleurTest {

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test controleur.
	 */
	@Test
	public void testControleur() {
		
		double etat = 0.2;
		ChoixCouleur choixCoul =  new ChoixCouleur(etat);
		assertEquals(Couleur.vert,choixCoul.getCouleur());
		
		etat = 	0.8;
		choixCoul =  new ChoixCouleur(etat);
		assertEquals(Couleur.orange,choixCoul.getCouleur());
		
		etat = 	1;
		choixCoul =  new ChoixCouleur(etat);
		assertEquals(Couleur.rouge,choixCoul.getCouleur());
		
		
		
	}

}
