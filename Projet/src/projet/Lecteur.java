
package projet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;

// Classe de gestion de Lecteur
public class Lecteur implements Serializable {

    private static final long serialVersionUID = 422L;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private String nom;
    private String prenom;
    private Integer numLecteur;
    private GregorianCalendar dateNaiss;
    private String adresse;
    private String tel;
    private ArrayList<Emprunt> emprunts;

    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Lecteur(String nom, String prenom, Integer numLecteur, GregorianCalendar dateNaiss, String adresse, String tel) {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setNumLecteur(numLecteur);
        this.setDateNaiss(dateNaiss);
        this.setAdresse(adresse);
        this.setTel(tel);
        this.emprunts=new ArrayList<>();
    }

// -----------------------------------------------
    // Public
// -----------------------------------------------
    // -----------------------------------------------
    //Getters
    // -----------------------------------------------
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Integer getNumLecteur() {
        return numLecteur;
    }

    public GregorianCalendar getDateNaiss() {
        return dateNaiss;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }
    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------

    /*
		 * La methode afficherLecteur affiche l'ensemble des informations relatives à un lecteur.
     */
    public void afficherLecteur() {
        System.out.println("Numero lecteur : " + this.getNumLecteur());
        System.out.println("Nom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom());
        System.out.println("Age : " + this.calculAge() + " ans");
        System.out.println("Né en "+EntreesSorties.ecrireDate(this.getDateNaiss()));
        System.out.println("Adresse : " + this.getAdresse());
        System.out.println("Telephone : " + this.getTel());
        EntreesSorties.afficherMessage("");
    }

    /*
		 * la methode calculAge permet de determiner l'age des lecteurs grace a leur date de naissance
		 * et la date actuelle. De cette façon, il n'y a pas de mise a jour a faire sur l'age des lecteurs.
     */
    public Integer calculAge() {
        Integer age;
        GregorianCalendar dateNaissComp;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
        if (dateNaissComp.before(dateActuelle)) {
            age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR);
        } else {
            age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR) - 1;
        }
        return age;
    }

// -----------------------------------------------
    // Private
// -----------------------------------------------
    // -----------------------------------------------
    //Setters
    // -----------------------------------------------
    private void setNom(String nom) {
        this.nom = nom;
    }

    private void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    private void setNumLecteur(Integer numLecteur) {
        this.numLecteur = numLecteur;
    }

    private void setDateNaiss(GregorianCalendar dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    private void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    private void setTel(String tel) {
        this.tel = tel;
    }
    public void afficherInfos(){
        System.out.println();
        System.out.println();
        EntreesSorties.afficherMessage("Numero lecteur : " + this.getNumLecteur() +"\nNom et prenom du lecteur: " + this.getNom() + " " + this.getPrenom() + " a emprunté :");
    }
    public void consulterEmpruntsLecteur(){
        if (this.getEmprunts().isEmpty()){
            EntreesSorties.afficherMessage("Aucun emprunt pour ce lecteur.");
        }
        else {
            for (Emprunt  m : this.getEmprunts()){
            
             m.consulterEmpruntsLecteur();
            }
       
        }
    }
    private ArrayList<Emprunt> getEmprunts(){
        return this.emprunts;
    }
    public  boolean lecteurDispo(){
        if (this.getEmprunts().size() == 5){
            return false;
        }
        return true;
    }
    public void lierEmpruntLecteur(Emprunt m){
        this.emprunts.add(m);
    }
    
    
    
     //-----------------------------------------
    //  LECTEUR      Rendre exemplaire Gaby voir lecteur
    //-----------------------------------------
    
    
    public void rendreExemplaire(Emprunt m){
        supprimerEmprunt(m);    
    }
  
    private void supprimerEmprunt(Emprunt m){
        for(Emprunt e : this.emprunts){
            if(e==m){
                emprunts.remove(e);
            }
        }
    }  

}
