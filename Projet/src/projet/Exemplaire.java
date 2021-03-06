/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author valfres
 */
public class Exemplaire implements Serializable {
    private Boolean empruntable;
    private int numeroexemplaire;
    private GregorianCalendar datereception;
    private Ouvrage ouvrage;
    private Emprunt emprunt;
    
    public Exemplaire(Ouvrage ouvrage, Boolean empruntable,GregorianCalendar dateReception, int numeroExemplaire){
        this.empruntable=empruntable;
        this.numeroexemplaire=numeroExemplaire;
        this.datereception=dateReception;
        this.ouvrage=ouvrage;
        this.emprunt=null;
        this.lierOuvrageExemplaire(ouvrage);
        EntreesSorties.afficherMessage("Exemplaire "+this.numeroexemplaire+" créé !");
    }
    
    
    public void afficherExemplaire(){
        
        
        //faire un itérateur qui prends tout les Exemplaire de l'Array list ou
        // de ensemble[Exemplaires] et qui affiche les infos de chaque
        
        
            System.out.println("\nNuméro exemplaire :"+this.numeroexemplaire);
            if(this.empruntable){
                System.out.println("Empruntable");
                
            }
            else{
                System.out.println("Non empruntable");
            }
            System.out.println("Date reception : "+EntreesSorties.ecrireDate(datereception));
            if (this.getEmprunt() != null){
                EntreesSorties.afficherMessage("Emprunté le " + EntreesSorties.ecrireDate(this.getEmprunt().getDateEmprunt()) +".");
                EntreesSorties.afficherMessage("Par "+ this.emprunt.getLecteur().getNom()+ " "+this.emprunt.getLecteur().getPrenom());
            }
            
    }
    
    public void lierOuvrageExemplaire(Ouvrage o){
        this.ouvrage=o;
    }
    
    public Ouvrage getOuvrage(){
        return this.ouvrage;
    }
    
    public void consulterEmpruntsLecteur(){
        this.getOuvrage().afficherReduit();
        EntreesSorties.afficherMessage("N° Exemplaire : " + this.numeroexemplaire+"." );
        
    }
    public void afficherInfos(){
        EntreesSorties.afficherMessage("N° Exemplaire : " + this.numeroexemplaire+"." );
    }
    
    public int getNumeroExemplaire(){
     return this.numeroexemplaire;
    }
    
    public boolean exemplaireDispo(){
        return this.empruntable;
    }
    public void lierEmpruntExemplaire(Emprunt m){
        this.emprunt = m;
    }
    public Emprunt getEmprunt(){
        return this.emprunt;
    }
    

    
    
    public Emprunt rendreExemplaire(){
        Emprunt m= this.getEmprunt();  
        if(m!=null){    
            m.rendreExemplaire();
            supprimerEmprunt();
            EntreesSorties.afficherMessage("Exemplaire rendu !");
        }     
        else {
            EntreesSorties.afficherMessage("Cet exemplaire n'est pas emprunté.");
        }
        return m; 
    }
  
    
    private void supprimerEmprunt(){
        this.emprunt=null;
    }

    
       
}
