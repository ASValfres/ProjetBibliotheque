/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author valfres
 */
public class Pouet {
    
    int Pouete;
     /*
//Bibliothèque 

    public class Bibliotheque implements Serializable {
    private static final long serialVersionUID = 262L;
    public int dernierLecteur;
    private HashMap<Integer, Lecteur> dicoLecteur;
    private HashMap<Integer, Ouvrage> dicoOuvrage;

    public Bibliotheque();
    public void consulterLecteur();
    private void setLecteurs(HashMap<Integer, Lecteur> dicoLecteur);
    private Lecteur getLecteur(Integer numLecteur);
    private void lierLecteurBibliotheque(Lecteur L, Integer numLecteur);
    private Iterator<Lecteur> lesLecteurs();
    public void nouvelOuvrage();
    public void consulterOuvrage();

    private Ouvrage getOuvrage(Integer ISBN);
    private void lierOuvrageBibliotheque(Ouvrage o, Integer ISBN);
    private void setOuvrages(HashMap<Integer, Ouvrage> dicoOuvrage);
    

}

//Lecteur
    public class Lecteur implements Serializable {
    private static final long serialVersionUID = 422L;
    private String nom;
    private String prenom;
    private Integer numLecteur;
    private GregorianCalendar dateNaiss;
    private String adresse;
    private String tel;
    

    public Lecteur(String nom, String prenom, Integer numLecteur, GregorianCalendar dateNaiss,
    String adresse, String tel);
    
    public String getNom();
    public String getPrenom();
    public Integer getNumLecteur();
    public GregorianCalendar getDateNaiss();
    public String getAdresse();
    public String getTel();
    public void afficherLecteur();
    public Integer calculAge();

    private void setNom(String nom);
    private void setPrenom(String prenom);
    private void setNumLecteur(Integer numLecteur);
    private void setDateNaiss(GregorianCalendar dateNaiss);
    private void setAdresse(String adresse);
    private void setTel(String tel);
}

//ouvrage

    public class Ouvrage implements Serializable {
    
    private int dernierExemplaire;
    private ArrayList<Exemplaire> exemplaires;
    private int ISBN;
    private String titre;
    private String nomEditeur;
    private GregorianCalendar dateParution;
    private Public publif;
    private String nomAuteur;
    
    public Ouvrage(int ISBN, String titre, String nomEditeur, GregorianCalendar dateParution, String nomAuteur, Public publif);
    
    public ArrayList getExemplaire();
    public Public getPublic();
    public GregorianCalendar getDateParution();
    public void incrementationNbEx();
    public void afficherExemplaire();
    public void afficherInfos();
    public void lierExemplaireOuvrage(Exemplaire e);
    public void afficherReduit();
    public void nouvelExemplaire(GregorianCalendar dateReception, Boolean empruntable);
}
   


//Public

    public enum Public;


//Exemplaire

    public class Exemplaire implements Serializable {
    private Boolean empruntable;
    private int numeroexemplaire;
    private GregorianCalendar datereception;
    private Ouvrage ouvrage;
    
    public Exemplaire(Ouvrage ouvrage, Boolean empruntable,GregorianCalendar dateReception, int numeroExemplaire);
    
    public void afficherExemplaire();
    public void lierOuvrageExemplaire(Ouvrage o);
    
}
*/
}
