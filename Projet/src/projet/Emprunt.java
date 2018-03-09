/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.GregorianCalendar;

/**
 *
 * @author valfres
 */
public class Emprunt {
    private Lecteur lecteur;
    private Exemplaire exemplaire;
    private GregorianCalendar dateEmprunt;
    private GregorianCalendar dateRelance;
    
    public Emprunt(Lecteur lecteur, Exemplaire exemplaire){
        this.lecteur = lecteur;
        this.exemplaire = exemplaire;
        this.dateEmprunt =  new GregorianCalendar();
    }
    
    public void consulterEmpruntsLecteur(){
        EntreesSorties.afficherMessage("Emprunté le :" +this.getDateEmprunt()+"\n");
        this.getExemplaire().consulterEmpruntsLecteur();
    }
    
    public Exemplaire getExemplaire(){
        return this.exemplaire;
    
    }
    public Lecteur getLecteur(){
        return this.lecteur;
    }
    
    public GregorianCalendar getDateEmprunt(){
        return this.dateEmprunt;
    }
    
    public GregorianCalendar getDateRelance(){
        return this.dateRelance;
    }      
    
  
            
 }