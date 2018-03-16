package projet;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static projet.EntreesSorties.ecrireDate;

// Classe de gestion de la Bibliotheque
public class Bibliotheque implements Serializable {

    private static final long serialVersionUID = 262L;

    public int dernierLecteur;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private HashMap<Integer, Lecteur> dicoLecteur;
    private HashMap<Integer, Ouvrage> dicoOuvrage;
    private ArrayList<Emprunt> listeEmprunts;

    /*
     * Le dictionnaire de lecteur permet à bibliotheque de
     * garantir l'unicité de ces derniers, et facilitent les recherches et créations.
     */
    // -----------------------------------------------
    //Constructeur
    // -----------------------------------------------
    public Bibliotheque() {
        this.setLecteurs(new HashMap<Integer, Lecteur>());
        this.dernierLecteur = 0;
        this.setOuvrages(new HashMap<Integer, Ouvrage>());
        this.listeEmprunts = new ArrayList<>();

    }

    // -----------------------------------------------
    //                      Lecteur
    // ----------------------------------------------- 
    // -----------------------------------------------
    // Methodes
    // -----------------------------------------------
    /*
     * La méthode nouveauLecteur permet de créé un lecteur en demandant la saisie de son numéro
     * nom, prénom, date de naissance, adresse et numéro de téléphone.
     * L'age doit être compris entre 3 et 110 ans
     * Le lecteur est identifié par son numéro, si celui ci existe déjà dans le dictionnaire
     * de bibliothèque, un message d'erreur est affiché.
     * Une fois le nouveau lecteur créé, il est ajouté au dictionnaire de lecteur
     * afin de garantir la cohérence des données.
     */
    public void nouveauLecteur() {

        dernierLecteur = dernierLecteur + 1;

        String nom = EntreesSorties.lireChaine("Entrez le nom :");
        String prenom = EntreesSorties.lireChaine("Entrez le prenom :");
        Integer age;

        GregorianCalendar dateNaiss, dateNaissComp;
        GregorianCalendar dateActuelle = new GregorianCalendar();
        do {
            dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur :");   //calcul l'age du lecteur
            dateNaissComp = new GregorianCalendar(dateActuelle.get(GregorianCalendar.YEAR), dateNaiss.get(GregorianCalendar.MONTH), dateNaiss.get(GregorianCalendar.DATE));
            if (dateNaissComp.before(dateActuelle)) {
                age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR);
            } else {
                age = dateActuelle.get(GregorianCalendar.YEAR) - dateNaiss.get(GregorianCalendar.YEAR) - 1;
            }
            if ((age <= 3) | (age >= 110)) {
                EntreesSorties.afficherMessage("Age incorrect (" + age + "), veuillez recommencer.");
            } else {
                EntreesSorties.afficherMessage("Age du lecteur : " + age + " ans");
            }
        } while ((age <= 3) | (age >= 110));
        String adresse = EntreesSorties.lireChaine("Entrez l'adresse :");
        String tel = EntreesSorties.lireChaine("Entrez le numero de telephone :");
        while (tel.length() != 10) {
            tel = EntreesSorties.lireChaine("Le numero de telephone est faux, veuillez recommencer:");
        }
        EntreesSorties.afficherMessage("Le numéro du nouveau lecteur est " + this.dernierLecteur);
        EntreesSorties.afficherMessage("\n \nFin de saisie");

        Lecteur L = new Lecteur(nom, prenom, dernierLecteur, dateNaiss, adresse, tel);
        lierLecteurBibliotheque(L, dernierLecteur);

    }

    /*
     * La méthode consulterLecteur permet d'afficher l'ensemble des informations relatives à
     * un lecteur, par la saisie de son identifiant (numéro de lecteur).
     * Si le numéro de lecteur n'est pas dans la base de données de bibliotheque un message d'erreur est
     * renvoyé a l'utilisateur.
     */
    public void consulterLecteur() {
        Integer numLecteur = EntreesSorties.lireEntier("Entrez le numero du lecteur : ");

        Lecteur L = getLecteur(numLecteur);

        if (L != null) {
            L.afficherLecteur();
        } else {
            EntreesSorties.afficherMessage("Aucun lecteur n'est associe a ce numero.");
        }
    }

// -----------------------------------------------
    // Private
// -----------------------------------------------
    // -----------------------------------------------
    // Setters
    // -----------------------------------------------
    private void setLecteurs(HashMap<Integer, Lecteur> dicoLecteur) {
        this.dicoLecteur = dicoLecteur;
    }

    // -----------------------------------------------
    // Mï¿½thodes
    // -----------------------------------------------
    /*
     * La méthode getLecteur permet de rechercher dans la base de donnée de bibliotheque un objet
     * lecteur identifié par son numéro, et de renvoyer l'objet. (ou la donnée null s'il n'est pas trouvé)
     */
    private Lecteur getLecteur(Integer numLecteur) {
        return dicoLecteur.get(numLecteur);
    }

    /*
     * La méthode lierLecteurBibliotheque permet d'ajouter un lecteur a la base de donnée de bibliotheque.
     */
    private void lierLecteurBibliotheque(Lecteur L, Integer numLecteur) {
        this.dicoLecteur.put(numLecteur, L);
    }

    /*
     * La méthode lesLecteurs permet de créer un iterator sur les lecteurs, dans le but de les parcourir
     * pour eventuellement les relancer.
     */
    private Iterator<Lecteur> lesLecteurs() {
        return dicoLecteur.values().iterator();
    }

    // -----------------------------------------------
    //                     Emprunt
    // ----------------------------------------------- 
    public void consulterEmpruntsLecteur() {
        int numeroLecteur = EntreesSorties.lireEntier("Entrez numéro de lecteur : ");
        Lecteur l = getLecteur(numeroLecteur);
        while (l == null && numeroLecteur != 0) {
            numeroLecteur = EntreesSorties.lireEntier("Ceci n'est pas un numéro de lecteur valide.\nEntrez numéro de lecteur, ou entrez 0 pour annuler : ");
            l = getLecteur(numeroLecteur);
        }
        if (l != null) {
            l.afficherInfos();
            l.consulterEmpruntsLecteur();
        }
    }

    public void emprunterExemplaire() {
        boolean w = false;
        Lecteur l = null;
        Exemplaire e = null;
        Emprunt m = null;

        int ISBN = EntreesSorties.lireEntier("Entrez un numéro ISBN :");
        Ouvrage o = this.getOuvrage(ISBN);
        while (o == null && ISBN != 0) {
            ISBN = EntreesSorties.lireEntier("Ceci n'est pas un numéro ISBN présent dans la bibliothèque. \nEntrez un numéro ISBN, ou entrez 0 pour annuler :");
            o = this.getOuvrage(ISBN);
        }
        
        if (o != null) {
            int numeroLecteur = EntreesSorties.lireEntier("Entrez un numéro de lecteur :");
            l = this.getLecteur(numeroLecteur);
            while (l == null && numeroLecteur != 0) {
                numeroLecteur = EntreesSorties.lireEntier("Ceci n'est pas un numero de lecteur valide. \nEntrez un numéro de lecteur, ou entrez 0 pour annuler :");
                l = this.getLecteur(numeroLecteur);
            }
            
            int numeroExemplaire = EntreesSorties.lireEntier("Entrez un numéro d'exemplaire :");

            if (l != null) {
                if (!this.publicCompatible(l, o)) {
                    EntreesSorties.afficherMessage("ce public n'est pas compatible.");
                } else {
                    if (!l.lecteurDispo()) {
                        EntreesSorties.afficherMessage("Ce lecteur a déjà trop d'emprunts !");
                    } else {
                        e = o.getExemplairePrecis(numeroExemplaire);

                        while (e == null && numeroExemplaire != 0) {
                            numeroExemplaire = EntreesSorties.lireEntier("Ceci n'est pas un numéro d'exemplaire valide. \nEntrez un numéro d'exemplaire, ou entrez 0 pour annuler :");
                            e = o.getExemplairePrecis(numeroExemplaire);
                        }
                        
                        while(numeroExemplaire != 0 && e.getEmprunt() != null){
                            numeroExemplaire = EntreesSorties.lireEntier("Ceci est un exemplaire déjà emprunté.\nEntrez un numéro d'exemplaire, ou entrez 0 pour annuler :");
                            e = o.getExemplairePrecis(numeroExemplaire);
                        }
                        
                        if (e != null) {
                            if (e.exemplaireDispo()) {
                                w = true;
                            } else {
                                EntreesSorties.afficherMessage("Cet exemplaire n'est pas empruntable. ");
                            }
                        }
                    }

                }
            }

        }

        if (w) {
            m = new Emprunt(l, e);
            e.lierEmpruntExemplaire(m);
            l.lierEmpruntLecteur(m);
            m.setDateEmprunt();
            this.lierBibliothequeEmprunt(m);
            EntreesSorties.afficherMessage("Emprunt créé !");
        }

    }

    public void lierBibliothequeEmprunt(Emprunt m) {
        this.listeEmprunts.add(m);
    }

    private boolean publicCompatible(Lecteur l, Ouvrage o) {
        if (l.calculAge() >= o.getPublic().getAge()) {
            return true;
        }
        return false;
    }

    // -----------------------------------------------
    //                     Ouvrage
    // ----------------------------------------------- 
    public void nouvelOuvrage() {
        Integer ISBN = EntreesSorties.lireEntier("Entrez l'ISBN : ");
        if (this.getOuvrage(ISBN) != null) {
            EntreesSorties.afficherMessage("Cet Ouvrage existe déja : \n\n");
            this.getOuvrage(ISBN).afficherInfos();
        } else {
            String titre = EntreesSorties.lireChaine("Entrez le titre : ");
            String nomAuteur = EntreesSorties.lireChaine("Entrez le nom de l'auteur : ");

            GregorianCalendar dateParution = EntreesSorties.lireDate("Entrez la date de parution : ");

            GregorianCalendar now = new GregorianCalendar();
            if (dateParution.compareTo(now) > 0) {
                do {
                    dateParution = EntreesSorties.lireDate("La date de parution est incorrecte (postérieure à aujourd'hui). \n\nVeuillez entrer la bonne date de parution :");
                } while (dateParution.compareTo(now) > 0);
            }
            String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur : ");

            Public publif;
            String publicConcerné;
            int i = 0; //valeur sentinelle si prend la valeur de 1 signifie que l'entrée est bonne

            do {
                publicConcerné = EntreesSorties.lireChaine("Entrez le public concerné (ENF pour enfant, ADO pour adolescent et ADU pour adulte) :");
                switch (publicConcerné.toUpperCase()) {
                    case "ENF":
                        publif = Public.enfant;
                        i = 1;
                        break;
                    case "ADU":
                        publif = Public.adulte;
                        i = 1;
                        break;
                    case "ADO":
                        publif = Public.adolescent;
                        i = 1;
                        break;
                    default:
                        publif = Public.adulte;
                        EntreesSorties.afficherMessage("Saisie incorrecte, veuillez recommencer.\n\n");
                        break;
                }
            } while (i == 0);

            Ouvrage O = new Ouvrage(ISBN, titre, nomEditeur, dateParution, nomAuteur, publif);

            lierOuvrageBibliotheque(O, ISBN);
            EntreesSorties.afficherMessage("\n ---------------------------");
            EntreesSorties.afficherMessage("Saisie du premier exemplaire ? (O/N)");
            String reponse = EntreesSorties.lireChaine(" ---------------------------\n");

            int fin = 0;
            do {
                switch (reponse.toUpperCase()) {
                    case "O":
                        fin = 1;
                        break;
                    case "N":
                        fin = 2;
                        break;
                    default:
                        EntreesSorties.afficherMessage("Saisie incorrecte, veuillez recommencer.\n ");
                        break;
                }
            } while (fin == 0);

            if (fin == 1) {
                this.nouvelExemplaire(O);
            }

        }
    }

    public void consulterOuvrage() {
        Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN de l'ouvrage que vous souhaitez consulter : \n");
        Ouvrage O = getOuvrage(ISBN);

        if (O != null) {
            O.afficherInfos();
        } else {
            EntreesSorties.afficherMessage("Aucun ouvrage n'est associe a ce numero ISBN.");
        }
    }

    private Ouvrage getOuvrage(Integer ISBN) {
        return dicoOuvrage.get(ISBN);
    }

    private void lierOuvrageBibliotheque(Ouvrage o, Integer ISBN) {
        this.dicoOuvrage.put(ISBN, o);
    }

    private void setOuvrages(HashMap<Integer, Ouvrage> dicoOuvrage) {
        this.dicoOuvrage = dicoOuvrage;
    }

    // -----------------------------------------------
    //                     EXEMPLAIRE
    // ----------------------------------------------- 
    public void nouvelExemplaire() {
        int ISBN = EntreesSorties.lireEntier("Entrez l'ISBN : ");
        Ouvrage O = getOuvrage(ISBN);
        if (O == null) {
            EntreesSorties.afficherMessage("Aucun ouvrage n'a cet ISBN");
        } else {
            GregorianCalendar dateReception = EntreesSorties.lireDate("Entrez la date de réception : ");
            if (comparerDateRSupDateP(dateReception, O) != true) {
                do {
                    dateReception = EntreesSorties.lireDate("Veuillez entrer la bonne date de réception :");
                } while (comparerDateRSupDateP(dateReception, O) != true);
            }

            Boolean empruntable;

            int i = 0; //valeur sentinelle si prend la valeur de 1 signifie que l'entrée est bonne

            do {
                String emprunt = EntreesSorties.lireChaine("Empruntable ? O si oui, N si non : ");

                switch (emprunt.toUpperCase()) {
                    case "O":
                        empruntable = true;
                        i = 1;
                        break;
                    case "N":
                        empruntable = false;
                        i = 1;
                        break;
                    default:
                        empruntable = false;
                        EntreesSorties.afficherMessage("Saisie incorrecte, veuillez recommencer.");
                        break;
                }
            } while (i == 0);

            EntreesSorties.afficherMessage("\n\nFin de saisie");

            O.nouvelExemplaire(dateReception, empruntable);

        }

    }

    public void nouvelExemplaire(Ouvrage ouvrage) {

        GregorianCalendar dateReception = EntreesSorties.lireDate("Entrez la date de réception : ");
        if (comparerDateRSupDateP(dateReception, ouvrage) != true) {
            do {
                dateReception = EntreesSorties.lireDate("Veuillez entrer la bonne date de réception :");
            } while (comparerDateRSupDateP(dateReception, ouvrage) != true);
        }
        Boolean empruntable;

        int i = 0; //valeur sentinelle si prend la valeur de 1 signifie que l'entrée est bonne

        do {
            String emprunt = EntreesSorties.lireChaine("Empruntable ? O si oui, N si non : ");

            switch (emprunt.toUpperCase()) {
                case "O":
                    empruntable = true;
                    i = 1;
                    break;
                case "N":
                    empruntable = false;
                    i = 1;
                    break;
                default:
                    empruntable = false;
                    EntreesSorties.afficherMessage("Saisie incorrecte, veuillez recommencer.");
                    break;
            }
        } while (i == 0);

        EntreesSorties.afficherMessage("\n\nFin de saisie");

        ouvrage.nouvelExemplaire(dateReception, empruntable);

    }

    public void consulterExemplaireOuvrage() {
        int ISBN = EntreesSorties.lireEntier("Entrez l'ISBN de l'ouvrage dont vous voulez les exemplaires : ");
        Ouvrage ouvrage = getOuvrage(ISBN);
        ouvrage.afficherReduit();
        ouvrage.afficherExemplaire();
    }

    public Boolean comparerDateRSupDateP(GregorianCalendar dateReception, Ouvrage o) {
        GregorianCalendar parution = o.getDateParution();
        GregorianCalendar now = new GregorianCalendar();
        if (parution.compareTo(dateReception) <= 0 && dateReception.compareTo(now) < 0) {
            return true;
        } else if (dateReception.compareTo(now) >= 0) {
            System.out.println("La date de réception est incorrecte (dans le futur).");
            return false;
        } else {
            System.out.println("La date de réception est incorrecte (antérieure à la date de parution : " + ecrireDate(parution) + ").");
            return false;
        }
    }

    //------------------------------------
    //        BIBLIOTHEQUE     Rendre exemplaire  Gaby (voir exemplaire)
    //-------------------------------------
    public void rendreExemplaire() {
        int ISBN = EntreesSorties.lireEntier("Entrez l'ISBN : ");
        Ouvrage o = getOuvrage(ISBN);
        while (o == null && ISBN != 0) {
            ISBN = EntreesSorties.lireEntier("Ceci n'est pas un numéro ISBN présent dans la bibliothèque. \nEntrez un numéro ISBN, ou entrez 0 pour annuler :");
            o = this.getOuvrage(ISBN);
        }
        if (o != null) {
            int numeroExemplaire = EntreesSorties.lireEntier("Entrez le numéro d'exemplaire : ");
            Exemplaire e = o.getExemplairePrecis(numeroExemplaire);

            while (e == null && numeroExemplaire != 0) {
                numeroExemplaire = EntreesSorties.lireEntier("Ceci n'est pas un numéro d'exemplaire valide. \nEntrez un numéro d'exemplaire, ou entrez 0 pour annuler :");
                e = o.getExemplairePrecis(numeroExemplaire);
            }

            if (e != null) {
                Emprunt m = e.rendreExemplaire();
                
                supprimerEmprunt(m);
             
            }
            
                
            

        }
    }

    private void supprimerEmprunt(Emprunt m) { //super long car continue une fois trouvé
        for (Emprunt e : listeEmprunts) {
            if (e == m) {
                listeEmprunts.remove(e);
                break;
            }
        }
    }
     public void relancerLecteur(){
          EntreesSorties.afficherMessage("Relance des lecteurs en retard en cours.");
          for(Emprunt m : getEmprunts()){
              
              m.relancerLecteur();
          }
           EntreesSorties.afficherMessage("Relance des lecteurs en retard terminée.");
}
      private ArrayList<Emprunt> getEmprunts(){
          return this.listeEmprunts;
      }
}
  






//RELANCER LECTEUR, pourquoi ça affiche pas les infos exemplaires et tout et tout wesh, ca me saoule cousin