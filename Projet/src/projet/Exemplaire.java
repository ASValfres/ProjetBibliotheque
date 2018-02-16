/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Date;

/**
 *
 * @author valfres
 */
public class Exemplaire {
    private Boolean _empruntable;
    private int _numeroexemplaire;
    private Date _datereception;
    
    public Exemplaire(Ouvrage ouvrage, Boolean empruntable,Date dateReception, int numeroExemplaire){
        this._empruntable=empruntable;
        this._numeroexemplaire=numeroExemplaire;
        this._datereception=dateReception;
        lierExemplaireOuvrage(ouvrage);
    }
    
    public Exemplaire getExemplaire(int ISBN, int numeroExemplaire){
        Exemplaire exemplaire=new Exemplaire();
        exemplaire=getExemplaire(ISBN, numeroExemplaire);
        return exemplaire;
    }
    
    public void afficherInfos(int ISBN, int numeroExemplaire){
        Exemplaire exemplaire=new Exemplaire();
        exemplaire=getExemplaire(ISBN, numeroExemplaire);
        
        System.out.println("\nNuméro exemplaire :"+exemplaire._numeroexemplaire);
        if(exemplaire._empruntable){
            System.out.println("Empruntable");
        }
        else{
            System.out.println("Non empruntable");
        }
        System.out.println("Date reception : "+exemplaire._datereception);
           
    }
    
    public void afficherExem
    
}
