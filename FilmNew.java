package film;

import java.util.ArrayList;
import film.FilmNew.TypFilmu;

public class FilmNew {
	  String Nazev;
	  String reziser;
	  int rokVydani;
	  ArrayList<String> herci;
	  ArrayList<String> animatori;
	  ArrayList<Integer> bodoveHodnoceni;
	  ArrayList<String> slovniHodnoceni;
	  int vekovaOmezeni;
	  String url; 
	  String user; 
	  String password;
	  
	  public FilmNew(String Nazev, String reziser, int rokVydani, ArrayList<String> herci, ArrayList<Integer> bodoveHodnoceni, ArrayList<String> slovniHodnoceni) {
	    this.Nazev = Nazev;
	    this.reziser = reziser;
	    this.rokVydani = rokVydani;
	    this.herci = herci;
	    this.bodoveHodnoceni = bodoveHodnoceni;
	    this.slovniHodnoceni = slovniHodnoceni;
	  }

	  public FilmNew(String Nazev, String reziser, int rokVydani, ArrayList<String> animatori, ArrayList<Integer> bodoveHodnoceni, ArrayList<String> slovniHodnoceni, int vekovaOmezeni) {
	    this.Nazev = Nazev;
	    this.reziser = reziser;
	    this.rokVydani = rokVydani;
	    this.animatori = animatori;
	    this.bodoveHodnoceni = bodoveHodnoceni;
	    this.slovniHodnoceni = slovniHodnoceni;
	    this.vekovaOmezeni = vekovaOmezeni;
	  }
	  	  
	  public enum TypFilmu {
		    HRANY,
		    ANIMOVANY
		}
	  	  
	  public TypFilmu getTypFilmu() {
	        if (this.animatori != null && !this.animatori.isEmpty()) {
	            return TypFilmu.ANIMOVANY;
	        } else {
	            return TypFilmu.HRANY;
	        }
	    }
	  	 
	  public String toString() {
		    if (getTypFilmu() == TypFilmu.ANIMOVANY) {
		            return "Název filmu: " + Nazev + ", animátoři: " + animatori.toString() + ", rok vydání: " + rokVydani + ", doporučený věk:" + vekovaOmezeni;
		        }else {		      
		            return "Název filmu: " + Nazev + ", herci: " + herci.toString() + ", režie: " + reziser + ", rok vydání: " + rokVydani;
		        } 	
		}
	  
	  public String getNazev() {
	    return Nazev;
	  }

	  public void setNazev(String nazev) {
	    this.Nazev = nazev;
	  }

	  public String getReziser() {
	    return reziser;
	  }

	  public void setReziser(String reziser) {
	    this.reziser = reziser;
	  }

	  public int getRokVydani() {
	    return rokVydani;
	  }

	  public void setRokVydani(int rokVydani) {
	    this.rokVydani = rokVydani;
	  }

	  public ArrayList<String> getHerci() {
	    return (ArrayList<String>) herci;
	  }
	  
	  public void setHerci(ArrayList<String> herci) {
	    this.herci = herci;
	  }

	  public ArrayList<String> getAnimatori() {
	    return animatori;
	  }

	  public void setAnimatori(ArrayList<String> animatori) {
	    this.animatori = animatori;
	  }

	  public ArrayList<Integer> getBodoveHodnoceni(){
		 return bodoveHodnoceni;
	  }
	  
	  public void setBodoveHodnoceni(ArrayList<Integer> bodoveHodnoceni) {
		 this.bodoveHodnoceni = bodoveHodnoceni;
	  }
	  	
	  public ArrayList<String> getSlovniHodnoceni() {
		return slovniHodnoceni;
	  }

	  public void setSlovniHodnoceni(ArrayList<String> slovniHodnoceni) {
		this.slovniHodnoceni = slovniHodnoceni;
	  }
	  	  
	  public int getVekovaOmezeni() {
	    return vekovaOmezeni;
	  }

	  public void setVekovaOmezeni(int vekovaOmezeni) {
	    this.vekovaOmezeni = vekovaOmezeni;
	  }
	  
	  public void DatabaseManager(String url, String user, String password) {
	        this.url = url;
	        this.user = user;
	        this.password = password;
	    }
	  
	  public void addHodnoceni(int hodnoceni, String slHodnoceni) {
		    bodoveHodnoceni.add(hodnoceni);
		    slovniHodnoceni.add(slHodnoceni);
		}	
}