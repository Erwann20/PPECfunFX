/*
 * 
 */
package emt.codeBarre;

import java.awt.Font;
import java.util.Random;
import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.IBarcode;



/**
 * The Class CodeBarre.
 */
public class CodeBarre {
	 
 	/** The Constant lenght. */
 	private static final int lenght = 12 ;
	 
 	/** The number. */
 	private long number;
	 
		
	/**
	 * Instantiates a new code barre.
	 *
	 * @param number the number
	 */
	public CodeBarre(long number) {
		super();
		this.number = 0;
	}

/**
 * Generate random.
 *
 * @param length the length
 * @return Permet de génerer un chiffre à 12 digits de façon aléatoire
 */
	public static long generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
	
	
	
	/**
	 * Generate code barre.
	 *
	 * @throws Exception the exception
	 */
	public void generateCodeBarre() throws Exception {
	
	EAN13 barcode = new EAN13();
	
	
	number =  generateRandom(lenght); // appel de la fonction pour generer le nombre à 12 digits	
	
	
	System.out.print(number);
	
	
	 String data = " ";
	 data = String.valueOf(number); // convertie le chiffre en String
	 //data = String.valueOf("210987654321"); // convertie le chiffre en String
	

	/*
	   EAN 13 Valid data char set:
	        0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
	
	   EAN 13 Valid data length: 12 digits only, excluding the last checksum digit
	*/
	//barcode.setData("012345678901");
	barcode.setData(data);
	
	
	// for EAN13 with supplement data (2 or 5 digits)
	/*
	barcode.setSupData("12");
	// supplement bar height vs bar height ratio
	barcode.setSupHeight(0.8f);
	// space between barcode and supplement barcode (in pixel)
	barcode.setSupSpace(15);
	*/
	
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
	barcode.drawBarcode("C:\\Users\\ARHANTEC.SIO\\Desktop\\CodeBarre\\ean13.png");

	}

}
