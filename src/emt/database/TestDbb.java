/*
 * 
 */
package emt.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;


// TODO: Auto-generated Javadoc
/**
 * The Class TestDbb.
 */
public class TestDbb {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception{
		File index = new File("jdbc:hsqldb:file:CFUN//BBD");

		deleteDir(index);
		
		/*
		BaseHsqldb bdd = new BaseHsqldb();
		Calendar leCal = Calendar.getInstance();
		//Date date = (java.sql.Date) leCal.getTime();
	
		
		bdd.testBDD();
		bdd.creationData();
		bdd.insertComplexe("ComplexeCfun", 100, 100, 50, 50);
		bdd.selectAll();
		
		char mot = 'm';
		int numeroArrivee = 1;
		long horaireArrivee = 456456;
		boolean sortie = false;
		
		bdd.insertArrivee(String.valueOf(mot), numeroArrivee, horaireArrivee, sortie, 0);
		bdd.selectAllArrivee();
		//bdd.selectAllArrivee();*/
}
	
	/**
	 * Delete dir.
	 *
	 * @param file the file
	 */
	static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            if (! Files.isSymbolicLink(f.toPath())) {
	                deleteDir(f);
	            }
	        }
	    }
	    file.delete();
	}
	
	
}
