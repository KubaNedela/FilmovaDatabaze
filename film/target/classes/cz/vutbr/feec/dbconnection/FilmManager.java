package film;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import film.FilmNew.TypFilmu;

public class FilmManager {
    private ArrayList<FilmNew> films;
	private ArrayList<String> animatori;
    
    public List<FilmNew> getFilms() {
        return films;
    }
    
    public FilmManager() {
        this.films = new ArrayList<>();
    }

    public void addFilm() {
    	int typFilmu;
    	int hodnoceni = 0;
    	int rokVydani =0;
        int vekovaOmezeni =0;
        boolean validHodnoceni = false;
        boolean validRokVydani = false;
        boolean validReziser = false;
        boolean validNazev = false;
        boolean validSlHodnoceni = false;
        String nazev = null;
        String reziser = null;
        String slHodnoceni = null;
    	ArrayList<String> herci = new ArrayList<String>();
    	ArrayList<String> animatori = new ArrayList<String>();
    	ArrayList<Integer> bodoveHodnoceni = new ArrayList<Integer>();
    	ArrayList<String> slovniHodnoceni = new ArrayList<String>();
    	
    	Scanner input = new Scanner(System.in);      
           
        System.out.println("Zadej název filmu: ");
        do {
        	try {
        		nazev = input.nextLine();
        		validNazev = true;
        	} catch (NoSuchElementException e) {
        		System.err.println("Chyba: Není další vstup.");
        		e.printStackTrace();
        	} catch (IllegalStateException e) {
        		System.err.println("Chyba: Instance Scanner je uzavřena.");
        		e.printStackTrace();
        	} catch (Exception e) {
        		System.err.println("Chyba: Nastala obecná výjimka.");
        		e.printStackTrace();
        	}
        } while (!validNazev);
        
        while (true) {
            try {
                System.out.println("Zadej typ filmu (1 - hraný film, 2 - animovaný film): ");
                typFilmu = Integer.parseInt(input.nextLine());
                if (typFilmu != 1 && typFilmu != 2) {
                    throw new IllegalArgumentException("Zadané číslo není platné");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Zadaný vstup není platné číslo!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        
        do {
        	try {
        		System.out.println("Zadej rezisera: ");
        		reziser = input.nextLine();
        		validReziser = true;
        	} catch (NoSuchElementException e) {
        		System.err.println("Chyba: nebyl zadan zadny vstup.");
        		input.nextLine();
        	} catch (IllegalStateException e) {
        		System.err.println("Chyba: vstupni scanner byl uzavren.");
        		input.nextLine();
        	}
        }while (!validReziser);
        
        do {
        	try {
        		System.out.println("Zadej rok vydání: ");
        		rokVydani = input.nextInt();
        		validRokVydani = true;
        	} catch (InputMismatchException e) {
        		System.out.println("Neplatný formát. Zadej celé číslo!");
        	} catch (NoSuchElementException e) {
        		System.out.println("Neplatný znak!");
        	}
        } while (!validRokVydani);
        
        
        do {
            try {            	
                if (typFilmu == 1) {
                	System.out.println("Napiš bodové hodnocení filmu. Pro hrané filmy platí, ze rozmezi je od 1 do 5 hvezdicek: ");
                    hodnoceni = input.nextInt();
                } else if (typFilmu == 2) {
                	System.out.println("Napiš bodové hodnocení filmu. Pro animovane filmy platí, ze rozmezi je od 1 do 10 bodu: ");
                    hodnoceni = input.nextInt();
                }
                if (typFilmu == 1 && (hodnoceni < 1 || hodnoceni > 5)) {
                    throw new IllegalArgumentException("Hodnocení musí být v rozmezí 1 až 5 pro hrané filmy.");
                } else if (typFilmu == 2 && (hodnoceni < 1 || hodnoceni > 10)) {
                    throw new IllegalArgumentException("Hodnocení musí být v rozmezí 1 až 10 pro animované filmy.");
                }
                validHodnoceni = true;
            } catch (InputMismatchException e) {
                System.out.println("Hodnocení musí být celé číslo.");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine(); 
            }
            
        bodoveHodnoceni.add(hodnoceni);
        } while (!validHodnoceni);

        System.out.println("Napiš slovní hodnocení filmu: ");
        do {
        	try {
        		Scanner input2 = new Scanner(System.in);
                slHodnoceni = input2.nextLine();
                validSlHodnoceni = true;                
        /*	}	catch (InputMismatchException e) {
                System.out.println("Došlo k chybě při zpracování vstupu: " + e.getMessage());
                input.nextLine(); // přeskočení neplatného vstupu	*/
            } catch (Exception e) {
                System.out.println("Neočekávaná chyba: " + e.getMessage());
            }
        	slovniHodnoceni.add(slHodnoceni);
        }	while(!validSlHodnoceni);
        
        if(typFilmu == 1) {
        	while (true) {
                try {
                    System.out.println("Zadej jméno herce nebo napiš \"konec\" pro ukončení:");
                    String jmeno = input.nextLine();
                    if (jmeno.equals("konec")) {
                        break;
                    }
                    if (!jmeno.matches("[a-zA-Z ]+")) {
                        throw new IllegalArgumentException("Jméno herce musí obsahovat pouze písmena a mezery.");
                    }
                    herci.add(jmeno);
                } catch (InputMismatchException e) {
                    System.out.println("Došlo k chybě při zpracování vstupu: " + e.getMessage());
                    input.nextLine(); // přeskočení neplatného vstupu
                } catch (Exception e) {
                    System.out.println("Neočekávaná chyba: " + e.getMessage());
                }
            }

            if (herci.isEmpty()) {
                System.out.println("Seznam herců je prázdný.");
            } else {
                System.out.println("Seznam herců:");
                for (String jmenoHercu : herci) {
                    System.out.println(jmenoHercu);
                }
            }
        }
        
        if(typFilmu == 2) {
            while (true) {
                System.out.println("Zadej jméno animátora nebo stiskni \"konec\" pro ukončení:");
                String jmeno = input.nextLine();
                if (jmeno.equalsIgnoreCase("konec")) {
                    break;
                }
                animatori.add(jmeno);
            }
            
            System.out.println("Zadali jste následující herce: ");
            for (String jmenoHercu : animatori) {
            	System.out.println(jmenoHercu);
            }          
        }
        
        if(typFilmu ==2) {
        	while (true) {
        	    try {
        	        System.out.println("Napiš od kolika let je tento film přístupný: ");
        	        vekovaOmezeni = input.nextInt(); 
        	        break;
        	    } catch (InputMismatchException e) {
        	        System.out.println("Neplatný vstup. Zadej celé číslo!");
        	        input.next();
        	    } catch (NoSuchElementException e) {
        	        System.out.println("Zadán neplatný znak.");
        	        input.next();
        	    }
        	}
        }
        
        FilmNew novyFilm;
        if (typFilmu == 1 ) {
        novyFilm = new FilmNew(nazev, reziser, rokVydani, herci, bodoveHodnoceni, slovniHodnoceni);
        } else{
        novyFilm = new FilmNew(nazev ,reziser ,rokVydani , animatori, bodoveHodnoceni , slovniHodnoceni, vekovaOmezeni);
        }
         
        films.add(novyFilm);
        System.out.println("Přidán nový film: " + novyFilm.getNazev());
        
    }

    public void updateFilm() {
    	Scanner scanner = new Scanner(System.in);
        
        System.out.println("Zadej název filmu, který chceš upravit.");
    	String title = scanner.nextLine();
    	  
    	if (films.isEmpty()) {
               System.out.println("Databáze filmů je prázdná:");
               scanner.close();
               return;
           }
           
    	for (FilmNew film : films) {
    	   System.out.println(film.toString());
    	   System.out.println("\n");
           if(film.getNazev().compareTo(title) == 0) {                	   
           //int typFilmu = film.getTypFilmu(); // uložení typu filmu do proměnné typFilmu
           // System.out.println("Typ filmu: " + typFilmu);
        	   
           TypFilmu typFilmu = film.getTypFilmu();
        	   
        System.out.println("Vyberte pole, které chcete změnit:");
        System.out.println("1 - Název");
        System.out.println("2 - Režisér");
        System.out.println("3 - Rok vydání");
        System.out.println("4 - Seznam herců/animátorů");
        if (typFilmu == TypFilmu.ANIMOVANY) {
        System.out.println("5 - Doporučený věk diváka");
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Zadejte nový název:");
                String NovyNazev = scanner.nextLine();
                film.setNazev(NovyNazev);
                //System.out.println("Film byl úspěšně upraven.");
                break;
            case 2:
                System.out.println("Zadejte nového režiséra:");
                String NovyReziser = scanner.nextLine();
                film.setReziser(NovyReziser);
                break;
            case 3:
                System.out.println("Zadejte nový rok vydání:");
                int NovyRokVydani = scanner.nextInt();
                scanner.nextLine();
                film.setRokVydani(NovyRokVydani);
                break;
            case 4:
                System.out.println("Zadejte nový seznam herců/animátorů (oddělte čárkou):");
                String newCast = scanner.nextLine();
                String[] newCastArray = newCast.split(",");
                ArrayList<String> herci = new ArrayList<>();
                Collections.addAll(herci, newCastArray);
                film.setHerci(herci);
                break;
            case 5:
            	System.out.println("Zadejte nový vekovy limit:");
                int vekovyLimit = scanner.nextInt();
                scanner.nextLine();
                film.setVekovaOmezeni(vekovyLimit);
                break;
                               
            default:
                System.out.println("Neplatná volba.");
                break;    
    		}
        } else {
            System.out.println("Film s názvem " + title + " nebyl nalezen.");
        	}
          	scanner.close();
    	}
    }

    //Hotovo
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
    
    
    public void searchFilm(){
    	Scanner input = new Scanner(System.in); 
    	// uživatel zadá jméno filmu
    	System.out.println("Zadej jméno filmu, který chceš najít:");
    	String jmeno = input.nextLine();
    	
    	// projít seznam filmů a vypsat ty, na kterých se podílel zadaný herec nebo animátor
    	for (FilmNew film : films) {
    	    if (film.getNazev().contains(jmeno)) {
    	        System.out.println("- " + film.toString());
    	    }
    	}

    }
    
    
    //Hotovo
    public void vypisVsechFilmuDleHerce(){
    	Scanner input = new Scanner(System.in); 
    	 boolean nalezen = false;
    	 
    	 try {
    	 System.out.println("Zadej jméno herce nebo animátora:");
    	 String jmeno = input.nextLine();
    	
    	 for (FilmNew film : films) {
    	        if (film.getHerci().contains(jmeno) || film.getAnimatori().contains(jmeno)) {
    	            System.out.println(film.getNazev());
    	            nalezen = true;
    	        }
    	    }
    	    if (!nalezen) {
    	        throw new Exception("Zadané jméno nebylo nalezeno v žádném filmu!");
    	    }
    	} catch (NoSuchElementException e) {
    	    System.err.println("Chyba: Nebyl zadaný žádný vstup!");
    	    e.printStackTrace();
    	} catch (IllegalStateException e) {
    	    System.err.println("Chyba: Vstupní scanner byl uzavřen nebo jinak neplatný!");
    	    e.printStackTrace();
    	} catch (Exception e) {
    	    System.err.println(e.getMessage());
    	    e.printStackTrace();
    	}
   	 }
   
    //Hotovo
    public void vypisHercuViceNezJedenFilm() {

        Map<String, List<FilmNew>> HerciAnimatori = new HashMap<>();

        boolean existujeAnimator = false;
        boolean existujeHerec = false;

        for (FilmNew film : films) {
            if (film.getAnimatori() != null && !film.getAnimatori().isEmpty()) {
                existujeAnimator = true;

                for (String herec : film.getHerci()) {
                    if (!HerciAnimatori.containsKey(herec)) {
                        HerciAnimatori.put(herec, new ArrayList<>());
                    }
                    HerciAnimatori.get(herec).add(film);
                }
            }

            if (film.getHerci() != null && !film.getHerci().isEmpty()) {
                existujeHerec = true;

                for (String herec : film.getHerci()) {
                    if (!HerciAnimatori.containsKey(herec)) {
                        HerciAnimatori.put(herec, new ArrayList<>());
                    }
                    HerciAnimatori.get(herec).add(film);
                }
            }
        }

        // vytisknout informace o hercích a animátorech, kteří se podíleli na více než jednom filmu
        System.out.println("Herci a animatori, kteří se podíleli na více než jednom filmu:");
        for (Map.Entry<String, List<FilmNew>> entry : HerciAnimatori.entrySet()) {
            String jmeno = entry.getKey();
            List<FilmNew> filmy = entry.getValue();
            if (filmy.size() > 1) {
                System.out.println(jmeno + ":");
                for (FilmNew film : filmy) {
                    System.out.println("- " + film.getNazev() + " (" + film.getRokVydani() + ")");
                }
            }
        }
    }

    //Hotovo
    public void printFilms() {
        if (films.isEmpty()) {
            System.out.println("Seznam filmu je prázdný.");
        }
        
        for (FilmNew film : films) {
                System.out.println(film.toString());
        }
    }
    
    
	public void addRating() {
    	int hodnoceni = 0;
        boolean validHodnoceni = false;;
        boolean validSlHodnoceni = false;
        String slHodnoceni = null;
    	ArrayList<Integer> bodoveHodnoceni = new ArrayList<Integer>();
    	ArrayList<String> slovniHodnoceni = new ArrayList<String>();
    	
    	Scanner input = new Scanner(System.in);      
        System.out.println("Zadej název filmu, kterému chces pridat hodnoceni: ");
        String nazev = input.nextLine();
        TypFilmu typFilmu = getTypFilmu2(nazev);
                
		 do {
	            try {            	
	                if (typFilmu == TypFilmu.HRANY) {
	                	System.out.println("Napiš bodové hodnocení filmu. Pro hrané filmy platí, ze rozmezi je od 1 do 5 hvezdicek: ");
	                    hodnoceni = input.nextInt();
	                } else if (typFilmu == TypFilmu.HRANY) {
	                	System.out.println("Napiš bodové hodnocení filmu. Pro animovane filmy platí, ze rozmezi je od 1 do 10 bodu: ");
	                    hodnoceni = input.nextInt();
	                }
	                if (typFilmu == TypFilmu.HRANY && (hodnoceni < 1 || hodnoceni > 5)) {
	                    throw new IllegalArgumentException("Hodnocení musí být v rozmezí 1 až 5 pro hrané filmy.");
	                } else if (typFilmu == TypFilmu.HRANY && (hodnoceni < 1 || hodnoceni > 10)) {
	                    throw new IllegalArgumentException("Hodnocení musí být v rozmezí 1 až 10 pro animované filmy.");
	                }
	                validHodnoceni = true;
	            } catch (InputMismatchException e) {
	                System.out.println("Hodnocení musí být celé číslo.");
	                input.nextLine();
	            } catch (IllegalArgumentException e) {
	                System.out.println(e.getMessage());
	                input.nextLine(); 
	            }
	            
	        bodoveHodnoceni.add(hodnoceni);
	        } while (!validHodnoceni);
		 
		  
		 do {
	        	try {
	        		Scanner input2 = new Scanner(System.in);
	        		System.out.println("Napiš slovní hodnocení filmu: ");
	                slHodnoceni = input2.nextLine();
	                validSlHodnoceni = true;
	                
	        	} catch (InputMismatchException e) {
	                System.out.println("Došlo k chybě při zpracování vstupu: " + e.getMessage());
	                input.nextLine(); // přeskočení neplatného vstupu
	            } catch (Exception e) {
	                System.out.println("Neočekávaná chyba: " + e.getMessage());
	            }
	        	slovniHodnoceni.add(slHodnoceni);
	        }	while(!validSlHodnoceni);
	        
	}
	
	
	public TypFilmu getTypFilmu() {
	        if (this.animatori != null && !this.animatori.isEmpty()) {
	            return TypFilmu.ANIMOVANY;
	        } else {
	            return TypFilmu.HRANY;
	        }
	    }
	
	
	public TypFilmu getTypFilmu2(String nazev) {
		 for (FilmNew film : films) {
	          if (film.Nazev.equals(nazev)) {
	              return film.getTypFilmu();
	          }
	      }
	      return null; // Pokud se film s daným názvem v kolekci nenajde, vrátí se null
	  }
	
	public FilmNew findFilmByName(String nazev) {
	        for (FilmNew film : films) {
	            if (film.getNazev().equals(nazev)) {
	                return film;
	            } else {
	            System.out.println("Film s názvem " + nazev + " nebyl nalezen v databázi.");
	            }
	        }
	        return null;
	    }
	
	public void saveFilmToFile() {
	    String nazev = null;
	    boolean validNazev = false;
	    Scanner input = new Scanner(System.in);      
	    
		System.out.println("Zadej název filmu: ");
	        do {
	        	try {
	        		nazev = input.nextLine();
	        		validNazev = true;
	        	} catch (NoSuchElementException e) {
	        		System.err.println("Chyba: Není další vstup.");
	        		e.printStackTrace();
	        	} catch (IllegalStateException e) {
	        		System.err.println("Chyba: Instance Scanner je uzavřena.");
	        		e.printStackTrace();
	        	} catch (Exception e) {
	        		System.err.println("Chyba: Nastala obecná výjimka.");
	        		e.printStackTrace();
	        	}
	        } while (!validNazev);
	        
	    FilmNew film = findFilmByName(nazev);
	    if (film != null) {
	        String nazevSouboru = nazev + ".txt";
	        try {
	            PrintWriter writer = new PrintWriter(nazevSouboru, "UTF-8");
	            writer.println("Název: " + film.getNazev());
	            writer.println("Režisér: " + film.getReziser());
	            writer.println("Rok vydání: " + film.getRokVydani());
	            if (film.getTypFilmu() == TypFilmu.HRANY) {
	                writer.println("Herci: " + String.join(", ", film.getHerci()));
	            } else {
	                writer.println("Animátoři: " + String.join(", ", film.getAnimatori()));
	                writer.println("Věkové omezení: " + film.getVekovaOmezeni());
	            }
	            writer.println("Hodnocení:");
	            for (int i = 0; i < film.getBodoveHodnoceni().size(); i++) {
	                writer.println("Bodové hodnocení: " + film.getBodoveHodnoceni().get(i) + " - Slovní hodnocení:" + film.getSlovniHodnoceni().get(i));
	            }
	            writer.close();
	            System.out.println("Film byl úspěšně uložen do souboru " + nazevSouboru);
	        } catch (IOException e) {
	            System.out.println("Došlo k chybě při ukládání souboru: " + e.getMessage());
	        }
	    } else {
	        System.out.println("Film s názvem " + nazev + " nebyl nalezen v databázi.");
	    }
	}
	
	public void loadFilmFromFile() {
		
		// získání seznamu souborů
	    File dir = new File(".");
	    File[] files = dir.listFiles();
	    
	    // výpis názvů souborů s příponou .txt
	    System.out.print("Který z uložených filmů chcete vypsat: ");
	    for (File file : files) {
	        if (file.isFile() && file.getName().endsWith("\n")) {
	            System.out.print(file.getName() + ", ");
	        }
	    }
	    
	    Scanner input = new Scanner(System.in);
	    System.out.println("Zadej název souboru:");
	    boolean validNazev = false;
	    File file = null;
	   
	    do {
			try {
			    String nazev = input.nextLine();
			    file = new File(nazev + ".txt");
			    if(file.exists() && !file.isDirectory()) { 
			    	validNazev = true;
			    } else {
			    	System.err.println("Chyba: Soubor se zadaným názvem neexistuje.");
			    }
	} catch (NoSuchElementException e) {
		System.err.println("Chyba: Není další vstup.");
		e.printStackTrace();
	} catch (IllegalStateException e) {
		System.err.println("Chyba: Instance Scanner je uzavřena.");
		e.printStackTrace();
	} catch (Exception e) {
		System.err.println("Chyba: Nastala obecná výjimka.");
		e.printStackTrace();
	}
} while (!validNazev);
	    
	    try (Scanner scanner = new Scanner(file)) {
	        while (scanner.hasNextLine()) {
	            System.out.println(scanner.nextLine());
	        }
	    } catch (IOException e) {
	        System.out.println("Došlo k chybě při načítání souboru: " + e.getMessage());
	    }
	}
}


