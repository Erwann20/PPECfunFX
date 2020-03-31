/*
 * 
 */
package emt.tests;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import emt.model.Arrivee;
import emt.model.Complexe;
import emt.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class ComplexeTest.
 */
public class ComplexeTest {
	
	/** The complexe general. */
	Complexe complexeGeneral;
	
	/** The arrivee. */
	Arrivee arrivee;
	
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
		 complexeGeneral = new Complexe(5,5,"test");
		 arrivee = new Arrivee(complexeGeneral,'F');
	}
	
	/**
	 * Test entree usager.
	 */
	@Test
	public void testEntreeUsager() {
		
		assertTrue(complexeGeneral.entreeUsager(arrivee));
		
		arrivee = new Arrivee(complexeGeneral,'M');
		
		assertTrue(complexeGeneral.entreeUsager(arrivee));
		
	}
	
	/**
	 * Test sortie usager.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSortieUsager() throws Exception {
		arrivee = new Arrivee(complexeGeneral,'M');
		complexeGeneral.entreeUsager(arrivee);
		complexeGeneral.sortieUsager(1);
		
		arrivee = new Arrivee(complexeGeneral,'F');
		complexeGeneral.entreeUsager(arrivee);
		complexeGeneral.sortieUsager(2);
		
		
	}
	
	/**
	 * Test nom complexe.
	 */
	@Test
	public void testNomComplexe() {
		assertEquals("test", this.complexeGeneral.getNomComplexe());
	}
	
	/**
	 * Test verifi user.
	 */
	@Test 
	public void testVerifiUser() {
	   User user = new User("test", "test");
	  this.complexeGeneral.getLesUsers().add(user);
	  
	  User usertester = this.complexeGeneral.trouverLeUser("yazueyhj", user.getMdp());
	  assertEquals(usertester,null);
	  assertEquals(usertester,usertester);
	}
	
}
