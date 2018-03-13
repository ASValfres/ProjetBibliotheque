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
    
    public Exemplaire(Ouvrage ouvrage, Boolean empruntable,GregorianCalendar dateReception, int numeroExemplaire){
        this.empruntable=empruntable;
        this.numeroexemplaire=numeroExemplaire;
        this.datereception=dateReception;
        this.ouvrage=ouvrage;
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
        
            
    }
    
    public void lierOuvrageExemplaire(Ouvrage o){
        this.ouvrage=o;
    }
    
    private Ouvrage getOuvrage(){
        return this.ouvrage;
    }
    
    public void consulterEmpruntsLecteur(){
        this.getOuvrage().afficherReduit();
        
    }
    public void afficherInfos(){
        EntreesSorties.afficherMessage("N° Exemplaire : " + this.numeroexemplaire+"." );
    }
    
    public int getNumeroExemplaire(){
        
         return this.numeroexemplaire;
    }
       
}
