/*
 * 
 */
package emt.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import emt.model.Arrivee;
import emt.model.Complexe;

// TODO: Auto-generated Javadoc
/**
 * The Class ArriveeTest.
 */
public class ArriveeTest {
	
	/** The complexe. */
	private Complexe complexe;
	
	/** The arrivee. */
	private Arrivee arrivee;
	
	/** The delta. */
	final float DELTA = 0.0001f;
	
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
		complexe = new Complexe(4, 4, "test");
		arrivee = new Arrivee(complexe, 'f');
	}

	/**
	 * Test get numero arrivee.
	 */
	@Test
	public void testGetNumeroArrivee() {
		
		assertEquals(0,arrivee.getNumeroArrivee());
		
	}

	/**
	 * Test get choix sport.
	 */
	@Test
	public void testGetChoixSport() {
		assertEquals('f',arrivee.getChoixSport());
		
	}

	
	


	/**
	 * Test get complexe.
	 */
	@Test
	public void testGetComplexe() {
		assertEquals(complexe,arrivee.getComplexe());
	}

	/**
	 * Test set numero arrivee.
	 */
	@Test
	public void testSetNumeroArrivee() {
		assertEquals(0,arrivee.getNumeroArrivee());
		
		arrivee.setNumeroArrivee(2);
		assertEquals(2,arrivee.getNumeroArrivee());
	}
	


}
