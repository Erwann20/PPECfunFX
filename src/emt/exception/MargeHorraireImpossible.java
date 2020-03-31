/*
 * 
 */
package emt.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class MargeHorraireImpossible.
 */
public class MargeHorraireImpossible extends RuntimeException{

   /** The Constant serialVersionUID. */
   private static final long serialVersionUID = 1L;
   
   /**
    * Instantiates a new marge horraire impossible.
    */
   public MargeHorraireImpossible() {
      super("Cette marge d'horraire n'existe pas");
   }

}
