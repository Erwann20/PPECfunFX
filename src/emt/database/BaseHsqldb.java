/*
 * 
 */
package emt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import emt.MainApp;

public class BaseHsqldb {

	private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DB_URL = "jdbc:hsqldb:file:CFUN;hsqldb.lock_file=false;hsqldb.sqllog=1;shutdown=true;syntax_ora=true";
	private static final String USER = "sa";
	private static final String PASS = "";

	
	public void testBDD() {
		try {
			Class.forName(JDBC_DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void creationData() throws SQLException {
		// CFUN est le nom de la base de donnée
		Connection connexion = DriverManager.getConnection(DB_URL, USER, PASS);

		// Création des tables
		Statement statement = connexion.createStatement();
		DeleteAll();

		// Script de création des tables de la BDD
		String createSql = "CREATE TABLE COMPLEXE (" + "id_Complexe INT NOT NULL  IDENTITY ,"
				+ "nomComplexe VARCHAR(20)," + "nbTotalPlaceMuscu INT," + "nbTotalFitness INT,"
				+ "placeOccupeeMuscu INT," + "placeOccupeeFitness INT," + "PRIMARY  KEY (id_complexe));"

				+ "CREATE TABLE ARRIVEE (" + "id_Arrivee INT NOT NULL IDENTITY," + "choixSport VARCHAR(1),"
				+ "numeroArrivee INT," + "horaireArrivee VARCHAR(40)," + "horaireSortie VARCHAR(40), "
				+ "id_Complexe INT," + "PRIMARY KEY(id_Arrivee)," + "FOREIGN KEY (id_Complexe)"
				+ "	REFERENCES COMPLEXE(id_Complexe));"

				+ "CREATE TABLE USER(" + "id_User INT NOT NULL IDENTITY," + "identifiant VARCHAR(40),"
				+ "password VARCHAR(40)," + "id_Complexe INT," + "PRIMARY KEY(id_User)," + "FOREIGN KEY (id_Complexe)"
				+ "	REFERENCES COMPLEXE(id_Complexe));";
		// Exécution de la requete
		statement.execute(createSql);

	}

	/**
	 * 
	 * @param choixSport
	 * @param numeroArrivee
	 * @param horaireArrivee
	 * @param sortie
	 * @param id_complexe
	 * @throws SQLException
	 */
	public void insertArrivee(String choixSport, int numeroArrivee, String horaireArrivee, int id_complexe)
			throws SQLException {
		Connection connexion = DriverManager.getConnection(DB_URL, USER, PASS);

		// Création des tables
		String sql = "INSERT INTO ARRIVEE (choixSport,numeroArrivee,horaireArrivee,horaireSortie,id_Complexe) VALUES (?,?,?,?,?)";
		PreparedStatement insertion = connexion.prepareStatement(sql);
		insertion.setString(1, choixSport);
		insertion.setInt(2, numeroArrivee);
		insertion.setString(3, horaireArrivee);
		insertion.setString(4, ""); // la sortie est laissé vide pour le moment
		insertion.setInt(5, id_complexe);
		insertion.execute();
	}

	/**
	 * Permet de rajouter un nouvel utilisateur
	 * 
	 * @param identifiant
	 * @param pass
	 * @param id_complexe
	 */
	public void insertUser(String identifiant, String pass, int id_complexe) {
		try {
			Connection connexion = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "INSERT INTO USER (identifiant,password,id_Complexe) VALUES (?,?,?)";
			PreparedStatement insert = connexion.prepareStatement(sql);
			insert.setString(1, identifiant);
			insert.setString(2, pass);
			insert.setInt(3, id_complexe);
			insert.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Ajoute le complexe dans la base de donnée
	 * 
	 * @param nomComplexe
	 * @param tPM
	 * @param tPF
	 * @param pOM
	 * @param POF
	 * @throws SQLException
	 */
	public void insertComplexe(String nomComplexe, int tPM, int tPF, int pOM, int POF) throws SQLException {
		Connection connexion = DriverManager.getConnection(DB_URL, USER, PASS);

		// Création du complexe
		String sql = "INSERT INTO COMPLEXE (nomComplexe,nbTotalPlaceMuscu,nbTotalFitness,placeOccupeeMuscu,placeOccupeeFitness) VALUES (?,?,?,?,?)";
		PreparedStatement insertion = connexion.prepareStatement(sql);
		insertion.setString(1, nomComplexe);
		insertion.setInt(2, tPM);
		insertion.setInt(3, tPF);
		insertion.setInt(4, pOM);
		insertion.setInt(5, POF);
		insertion.execute();

	}

	/**
	 * A utiliser pour les LOG
	 * 
	 * @throws SQLException
	 */
	public void selectAll() throws SQLException {

		Connection connexion = DriverManager.getConnection(DB_URL, "sa", "");

		Statement state = connexion.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT * FROM Complexe");
		// On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();

		System.out.println("\n**********************************");
		// On affiche le nom des colonnes
		for (int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************");

		while (result.next()) {
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + result.getObject(i).toString() + "\t |");

			System.out.println("\n---------------------------------");
		}
	}

	/**
	 * A utiliser pour les LOG
	 * 
	 * @throws SQLException
	 */
	public void selectAllUser() throws SQLException {

		Connection connexion = DriverManager.getConnection(DB_URL, "sa", "");

		Statement state = connexion.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT * FROM USER");
		// On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();

		System.out.println("\n**********************************");
		// On affiche le nom des colonnes
		for (int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************");

		while (result.next()) {
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + result.getObject(i).toString() + "\t |");

			System.out.println("\n---------------------------------");
		}
	}

	/**
	 * A utiliser pour les LOG
	 * 
	 * @throws SQLException
	 */
	public void selectAllArrivee() throws SQLException {

		Connection connexion = DriverManager.getConnection(DB_URL, "sa", "");
		Statement state = connexion.createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT * FROM ARRIVEE");
		// On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();

		System.out.println("\n**********************************");
		// On affiche le nom des colonnes
		for (int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************");

		while (result.next()) {
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + result.getObject(i).toString() + "\t |");

			System.out.println("\n---------------------------------");
		}

	}

	/**
	 * A chaque arret de l'application la base de donnée est remis à zero
	 * 
	 * @throws SQLException
	 */
	public void DeleteAll() throws SQLException {
		Connection connexion = DriverManager.getConnection(DB_URL, "sa", "");
		Statement statement = connexion.createStatement();

		String dropTables = "DROP TABLE ARRIVEE IF EXISTS;" + "DROP TABLE USER IF EXISTS;"
				+ "DROP TABLE COMPLEXE IF EXISTS;";
		String deleteRows = "DELETE FROM ARRIVEE;" + "DELETE FROM USER;" + "DELETE FROM COMPLEXE;";

		// Test si la table Complexe existe, si elle existe les tables sont vidées
		ResultSet rs = connexion.getMetaData().getTables(null, null, "COMPLEXE", null);
		if (rs.next()) {
			statement.executeUpdate(deleteRows);
			statement.executeUpdate(dropTables);

		}
	}

	/**
	 * déconnecte la base de donnée du projet
	 */
	public void deconnection() {
		Connection connexion;
		try {
			connexion = DriverManager.getConnection(DB_URL, "sa", "");
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Met à jour la situation de l'utilisateur faux = sortie
	 */
	public void updateSortie(int numeroArrivee, String Sortie) {
		try {
			Connection connexion = DriverManager.getConnection(DB_URL, USER, PASS);

			String sqlUpdate = "UPDATE ARRIVEE SET HORAIRESORTIE = ? WHERE numeroArrivee = ?;";
			PreparedStatement insertion = connexion.prepareStatement(sqlUpdate);
			insertion.setString(1, Sortie);
			insertion.setInt(2, numeroArrivee);
			insertion.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void selectUser(int numero) {
		Connection connexion;
		try {
			connexion = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT * FROM USER";
			Statement state = connexion.createStatement();
			// L'objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery(sql);
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
