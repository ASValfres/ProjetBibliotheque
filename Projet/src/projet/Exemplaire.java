/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author valfres
 */
public class Exemplaire {
    private Boolean empruntable;
    private int numeroexemplaire;
    private GregorianCalendar datereception;
    private Ouvrage ouvrage;
    
    public Exemplaire(Ouvrage ouvrage, Boolean empruntable,GregorianCalendar dateReception, int numeroExemplaire){
        this.empruntable=empruntable;
        this.numeroexemplaire=numeroExemplaire;
        this.datereception=dateReception;
        this.ouvrage=ouvrage;
        ouvrage.incrementationNbEx();
        ouvrage.lierExemplaireOuvrage(this);
    }
    

    
    public void afficherInfos(int ISBN, int numeroExemplaire){
        Exemplaire exemplaire=getOuvrage(ISBN).getExemplaire(numeroExemplaire);
        
        System.out.println("\nNuméro exemplaire :"+exemplaire.numeroexemplaire);
        if(exemplaire.empruntable){
            System.out.println("Empruntable");
        }
        else{
            System.out.println("Non empruntable");
        }
        System.out.println("Date reception : "+exemplaire.datereception);
           
    }
    
    
    public void afficherExememplaire(){
        this.ouvrage.afficherReduit();
        
        //faire un itérateur qui prends tout les Exemplaire de l'Array list ou
        // de ensemble[Exemplaires] et qui affiche les infos de chaque
        
        for (int i=0;i<this.ouvrage.exemplaires.size();i++){
            System.out.println("\nNuméro exemplaire :"+this.numeroexemplaire);
            if(this.empruntable){
                System.out.println("Empruntable");
            }
            else{
                System.out.println("Non empruntable");
            }
            System.out.println("Date reception : "+this.datereception);
        }
    }
    
}
