package film;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
public class Databaze {

	    private final String url =null;
	    private final String user = null;
	    private final String password = null;
	    private ArrayList<FilmNew> films;
	    
	    public void saveFilmsToDatabaze() throws SQLException {
	        try (Connection connection = DriverManager.getConnection(url, user, password)) {
	            for (FilmNew film : films) {
	                String insertQuery = "INSERT INTO filmy (Nazev, Reziser, RokVydani, Her/Animatori, BodoveHodnoceni, SlovniHodnoceni, VekovaOmezeni) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement statement = connection.prepareStatement(insertQuery);
	                statement.setString(1, film.getNazev());
	                statement.setString(2, film.getReziser());
	                statement.setInt(3, film.getRokVydani());
	                statement.setArray(4, film.getHerci() != null ? connection.createArrayOf("text", film.getHerci().toArray()) : null);
	                statement.setArray(4, film.getAnimatori() != null ? connection.createArrayOf("text", film.getAnimatori().toArray()) : null);
	                statement.setArray(5, connection.createArrayOf("integer", film.getBodoveHodnoceni().toArray()));
	                statement.setArray(6, connection.createArrayOf("text", film.getSlovniHodnoceni().toArray()));
	                statement.setInt(7, film.getVekovaOmezeni());
	                statement.executeUpdate();
	            }
	        }
	    }
*/

public class Databaze {
	        private static final String DATABASE_URL = "jdbc:sqlite:db/user.db";
	        private static final String DATABASE_USERNAME = "username";
	        private static final String DATABASE_PASSWORD = "password";
	        private static final String TABLE_NAME = "films";

	        private static Connection connection;
	        private static boolean databaseInitialized = false;

	        public static void initializeDatabase() throws SQLException {
	            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
	            Statement statement = connection.createStatement();

	            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
	                    "id INT AUTO_INCREMENT PRIMARY KEY," +
	                    "nazev VARCHAR(255) NOT NULL," +
	                    "reziser VARCHAR(255) NOT NULL," +
	                    "rok_vydani INT NOT NULL," +
	                    "herci VARCHAR(255)," +
	                    "animatori VARCHAR(255)," +
	                    "bodove_hodnoceni VARCHAR(255)," +
	                    "slovni_hodnoceni VARCHAR(255)," +
	                    "vekova_omezeni INT" +
	                    ")";

	            statement.executeUpdate(createTableQuery);

	            databaseInitialized = true;
	        }
	        
	        public static void saveAllFilms(ArrayList<FilmNew> films) throws SQLException {
	            if (!databaseInitialized) {
	                initializeDatabase();
	            }

	            String insertQuery = "INSERT INTO " + TABLE_NAME + " (nazev, reziser, rok_vydani, herci, animatori, bodove_hodnoceni, slovni_hodnoceni, vekova_omezeni) " +
	                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	            for (FilmNew film : films) {
	                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

	                preparedStatement.setString(1, film.getNazev());
	                preparedStatement.setString(2, film.getReziser());
	                preparedStatement.setInt(3, film.getRokVydani());
	                preparedStatement.setString(4, String.join(",", film.getHerci()));
	                preparedStatement.setString(5, String.join(",", film.getAnimatori()));
	                preparedStatement.setString(6, String.join(",", film.getBodoveHodnoceni().stream().map(Object::toString).collect(Collectors.toList())));
	                preparedStatement.setString(7, String.join(",", film.getSlovniHodnoceni()));
	                preparedStatement.setInt(8, film.getVekovaOmezeni());

	                preparedStatement.executeUpdate();
	            }
	        }
	        
	        public static FilmNew getFilm(String nazev) throws SQLException {
	            if (!databaseInitialized) {
	                initializeDatabase();
	            }

	            String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE nazev=?";
	            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	            preparedStatement.setString(1, nazev);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            FilmNew film = null;
	            if (resultSet.next()) {
	                String reziser = resultSet.getString("reziser");
	                int rokVydani = resultSet.getInt("rok_vydani");
	                int vekovaOmezeni = resultSet.getInt("vekova_omezeni");
	                ArrayList<String> herci = new ArrayList<>(Arrays.asList(resultSet.getString("herci").split(",")));
	                ArrayList<String> animatori = new ArrayList<>(Arrays.asList(resultSet.getString("animatori").split(",")));
	                ArrayList<String> bodoveHodnoceniStr = new ArrayList<>(Arrays.asList(resultSet.getString("bodove_hodnoceni").split(",")));
	                ArrayList<Integer> bodoveHodnoceni = new ArrayList<>();
	                for (String str : bodoveHodnoceniStr) {
	                bodoveHodnoceni.add(Integer.parseInt(str));
	                }
	                ArrayList<String> slovniHodnoceni = new ArrayList<>(Arrays.asList(resultSet.getString("slovni_hodnoceni").split(",")));

	                if (animatori.size() > 0) {
	                    film = new FilmNew(nazev, reziser, rokVydani, animatori, bodoveHodnoceni, slovniHodnoceni, vekovaOmezeni);
	                } else {
	                    film = new FilmNew(nazev, reziser, rokVydani, herci, bodoveHodnoceni, slovniHodnoceni);
	                }
	            }

	            return film;
	        }
	        
	        public static ArrayList<FilmNew> getAllFilms() throws SQLException {
	            if (!databaseInitialized) {
	                initializeDatabase();
	            }

	            String selectQuery = "SELECT nazev FROM " + TABLE_NAME;
	            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            ArrayList<FilmNew> films = new ArrayList<>();
	            while (resultSet.next()) {
	                String nazev = resultSet.getString("nazev");
	                FilmNew film = getFilm(nazev);
	                films.add(film);
	            }

	            return films;
	        }
	        
	        public static void closeDatabaze() throws SQLException {
	            if (connection != null) {
	                connection.close();
	                connection = null;
	            }
	        }


}