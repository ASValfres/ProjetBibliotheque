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
}
