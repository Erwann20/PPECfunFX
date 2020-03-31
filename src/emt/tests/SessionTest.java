/*
 * 
 */
package emt.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import emt.model.Session;
import emt.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class SessionTest.
 */
public class SessionTest {
   
   /** The session. */
   Session session;
   
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
       this.session = new Session();
      
   }
   
   /**
    * Test create session.
    */
   @Test
   public void testCreateSession() {
      User user = new User("test", "test");
      
      this.session.createSession(user);
   }

   /**
    * Test dernier user.
    */
   @Test
   public void testDernierUser() {
      
      assertEquals(this.session.dernierUserConnecter(),null);
      
      User user = new User("test", "test");
      
      this.session.createSession(user);
      assertEquals(this.session.dernierUserConnecter(),user);
      
   }

}
