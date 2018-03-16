/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author valfres
 */
public class Emprunt implements Serializable {

    private Lecteur lecteur;
    private Exemplaire exemplaire;
    private GregorianCalendar dateEmprunt;
    private GregorianCalendar dateRelance;

    public Emprunt(Lecteur lecteur, Exemplaire exemplaire) {
        this.lecteur = lecteur;
        this.exemplaire = exemplaire;
        this.dateEmprunt = EntreesSorties.lireDate("Date d'Emprunt ?");
    }

    public void consulterEmpruntsLecteur() {
        EntreesSorties.afficherMessage("\nEmprunté le :" + EntreesSorties.ecrireDate(getDateEmprunt()));
        this.getExemplaire().consulterEmpruntsLecteur();
    }

    public Exemplaire getExemplaire() {
        return this.exemplaire;

    }

    public Lecteur getLecteur() {
        return this.lecteur;
    }

    public GregorianCalendar getDateEmprunt() {
        return this.dateEmprunt;
    }

    public GregorianCalendar getDateRelance() {
        return this.dateRelance;
    }

    //-----------------------------------------
    //  EMPRUNT      Rendre exemplaire Gaby voir lecteur
    //-----------------------------------------
    public void rendreExemplaire() {
        Lecteur l = getLecteur();
        l.rendreExemplaire(this);
        supprimerLecteur();
        supprimerExemplaire();

    }

    private void supprimerLecteur() {
        this.lecteur = null;
    }

    private void supprimerExemplaire() {
        this.exemplaire = null;
    }

    public void setDateEmprunt() {
        this.dateEmprunt = new GregorianCalendar();
    }

    public void relancerLecteur() {
        EntreesSorties.afficherMessage("POUETPOUUUUUUUUUUUUEEEEET");
        if (this.retard()) {
            EntreesSorties.afficherMessage("PLOUUUUUUUF");
            if (this.retardRelance()) {
                this.getLecteur().afficherInfos();
                this.getExemplaire().afficherInfos();
                this.getExemplaire().getOuvrage().afficherReduit();
                this.miseAJourDateRelance();
            }
        }
    }

    private void miseAJourDateRelance(){
        this.setDateRelance(new GregorianCalendar());
    }
    private void setDateRelance(GregorianCalendar date){
        this.dateRelance = date;
    }
    private boolean retardRelance() {
        if (this.getDateRelance() == null) {
            return false;
        }
        GregorianCalendar dateDuJour = new GregorianCalendar();
        GregorianCalendar test = this.getDateRelance();

        test.add(Calendar.YEAR, -dateDuJour.get(Calendar.YEAR));
        test.add(Calendar.MONTH, -dateDuJour.get(Calendar.MONTH));
        test.add(Calendar.DATE, -dateDuJour.get(Calendar.DATE));
        if (test.get(Calendar.DATE) > 7) {
            return true;
        } else {
            return false;
        }
        
    }

    private boolean retard() {
        GregorianCalendar dateDuJour = new GregorianCalendar();
        GregorianCalendar test = this.getDateEmprunt();
        test.add(Calendar.DATE, 15);
//        test.add(Calendar.YEAR, -dateDuJour.get(Calendar.YEAR));
//        test.add(Calendar.MONTH, -dateDuJour.get(Calendar.MONTH));
//        test.add(Calendar.DATE, -dateDuJour.get(Calendar.DATE));
        return (test.before(dateDuJour)) ;
    }

}
