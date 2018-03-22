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
        //  this.dateEmprunt = new GregorianCalendar();
        this.dateEmprunt = EntreesSorties.lireDate("Date d'Emprunt ?");
        this.dateRelance = null;
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

    private void miseAJourDateRelance() {
        this.setDateRelance(new GregorianCalendar());
    }

    private void setDateRelance(GregorianCalendar date) {
        this.dateRelance = date;
    }

    public void relancerLecteur() {
        if (this.retard()) {
            if (this.retardRelance()) {
                this.getLecteur().afficherInfos();
                this.getExemplaire().afficherInfos();
                this.getExemplaire().getOuvrage().afficherReduit();
                this.miseAJourDateRelance();
            }
            EntreesSorties.afficherMessage("Lecteur relancé pour cet emprunt.\n\n");
        }
    }

    private boolean retardRelance() {
        if (this.getDateRelance() == null) {   
            return true;
        }
        if (this.retard()) {
            GregorianCalendar dateDuJour = new GregorianCalendar();
            GregorianCalendar test = this.getDateRelance();
            GregorianCalendar test2 = (GregorianCalendar) test.clone();
          test2.add(Calendar.DATE, 7);
            if (test2.before(dateDuJour) ) {               
                return true;
            } else {
                return false;
            }
        }
        else return false;
    }

    private boolean retard() {
        GregorianCalendar dateDuJour = new GregorianCalendar();
        GregorianCalendar test = this.getDateEmprunt();
        GregorianCalendar test2 = (GregorianCalendar) test.clone();
        test2.add(Calendar.DATE, 15);
        // CFO  EntreesSorties.ecrireDateComplete(test2);
//        test.add(Calendar.YEAR, -dateDuJour.get(Calendar.YEAR));
//        test.add(Calendar.MONTH, -dateDuJour.get(Calendar.MONTH));
//        test.add(Calendar.DATE, -dateDuJour.get(Calendar.DATE));
        return (test2.before(dateDuJour));
    }

}
