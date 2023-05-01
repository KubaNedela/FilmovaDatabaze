package film;

import java.util.ArrayList;

public abstract class Abstrakt {
	
	protected ArrayList<FilmNew> films;

    public Abstrakt(ArrayList<FilmNew> films) {
        this.films = films;
    }
	
    public abstract void deleteFilm();
    
}