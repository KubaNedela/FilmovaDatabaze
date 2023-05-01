package film;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnection.getDBConnection();      
    	try {
            ArrayList<FilmNew> films = Databaze.getAllFilms();
            System.out.println("Načteno " + films.size() + " filmů.");
    	
            
        Scanner scanner = new Scanner(System.in);
        FilmManager filmManager = new FilmManager();
        Databaze databaze = new Databaze();
        int choice = 11;
        boolean validChoice = false;
        
        deleteFilm delete = new deleteFilm(filmManager.getList());

        do {
            System.out.println("\n1. Přidání nového filmu");
            System.out.println("2. Upravení filmu");
            System.out.println("3. Smazání filmu");
            System.out.println("4. Přidání hodnocení");
            System.out.println("5. Výpis filmů");
            System.out.println("6. Vyhledání filmu");
            System.out.println("7. Výpis herců nebo animátorů, kteří se podíleli na více než jednom filmu");
            System.out.println("8. Výpis všech filmů, které obsahují konkrétního herce nebo animátora");
            System.out.println("9. Uložení filmu do souboru");
            System.out.println("10. Načtení filmu ze souboru");
            System.out.println("0. Konec");
            
            do {
                try {             	
                	System.out.print("\nZadej volbu: \n");
                	choice = scanner.nextInt();
                	scanner.nextLine();
                	validChoice = true;
                } catch (InputMismatchException e) {
                    System.out.println("Chyba: Zadána neplatná hodnota. Musíte zadat celé číslo.");
                    scanner.nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Chyba: Konec vstupu. Musíte zadat platnou volbu.");
                    scanner.nextLine();
                }
            } while (!validChoice);            
            validChoice = false;
            
            switch (choice) {
                case 1:
                    filmManager.addFilm();    
                    break;
                case 2:
                    filmManager.updateFilm();
                    break;
                case 3:
                    delete.deleteFilm();
                    break;
                case 4:
                    filmManager.addRating();
                    break;	
                case 5:
                    filmManager.printFilms();
                    break;
                case 6:
                    filmManager.searchFilm();
                    break;	
                case 7:
                    filmManager.vypisHercuViceNezJedenFilm();
                    break;	
                case 8:
                    filmManager.vypisVsechFilmuDleHerce();
                    break;	
                case 9:
                    filmManager.saveFilmToFile();
                    break;
                case 10:
                    filmManager.loadFilmFromFile();
                    break;
                case 0:
                    System.out.println("Konec programu.");
                    break;
                default:
                    System.out.println("Neplatná volba. Zkus to znovu.");
                    break;
            }

        } while (choice != 0);

        scanner.close();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Databaze.saveAllFilms(films);
                Databaze.closeDatabaze();
                System.out.println("Data uložena do databáze.");
            } catch (SQLException e) {
                System.out.println("Chyba při ukládání dat: " + e.getMessage());
            }
        }));
    } catch (SQLException e) {
        System.out.println("Chyba při připojení k databázi: " + e.getMessage());
    }
    }
}
