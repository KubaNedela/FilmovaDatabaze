package film;

import java.util.ArrayList;
import java.util.Scanner;

public class deleteFilm extends Abstrakt {

    public deleteFilm(ArrayList<FilmNew> films) {
        super(films);
    }
    
	@Override
    public void deleteFilm() {
        Scanner input = new Scanner(System.in);
        System.out.println("Zadej název filmu, který chceš vymazat: ");
        String nazev = input.nextLine();

        boolean bylSmazan = false;
        for (FilmNew film : films) {
            if (film.getNazev().equals(nazev)) {
                films.remove(film);
                bylSmazan = true;
                break;
            }
        }
        if (bylSmazan) {
            System.out.println("Film s názvem " + nazev + " byl úspěšně smazán.");
        } else {
            System.out.println("Film s názvem " + nazev + " nebyl nalezen v databázi.");
        }
    }
}
