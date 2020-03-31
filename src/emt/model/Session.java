package emt.model;

import java.util.ArrayList;


/**
 * The Class Session.
 */
public class Session {
   
   /** The user. */
   private User user;
   
   /** The user connecter. */
   private ArrayList<User> userConnecter = new ArrayList<User>();
   
   /**
    * Instantiates a new session.
    */
   public Session() {

   }
   
   /**
    * Destroy session.
    */
   public void destroySession()  {
      this.userConnecter.remove(this.getnumUserConnecter());
   }

   
   /**
    * Creates the session.
    *
    * @param user the user
    */
   public void createSession(User user){
      user.seConnecter();
      userConnecter.add(user);
   }
   
   /**
    * Dernier user connecter.
    *
    * @return the user
    */
   public User dernierUserConnecter() {
      User userCo = null;
      int i = this.userConnecter.size()-1;
      
      for (User user: this.userConnecter) {
       
            if (this.userConnecter.get(i).getConnecter() == true) {
               userCo = this.userConnecter.get(i);
               i=-1;
            }
                 
      }
      
      return userCo;
   }
   
   /**
    * Gets the num user connecter.
    *
    * @return the num user connecter
    */
   public int getnumUserConnecter()  {
         
      return this.userConnecter.size()-1;  
   }
 
 }
