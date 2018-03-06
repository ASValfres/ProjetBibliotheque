package projet;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

// Classe de gestion de la Bibliotheque
public class Bibliotheque implements Serializable {

    private static final long serialVersionUID = 262L;

    public int dernierLecteur;

    // -----------------------------------------------
    //Attributs
    // -----------------------------------------------
    private HashMap<Integer, Lecteur> dicoLecteur;
    private HashMap<Integer, Ouvrage> dicoOuvrage;

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
            dateNaiss = EntreesSorties.lireDate("Entrez la date de naissance du lecteur :");
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
        EntreesSorties.afficherMessage("Le numéro du nouveau lecteur est " + this.dernierLecteur);
        EntreesSorties.afficherMessage("Fin de saisie");

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
    //                     Ouvrage
    // ----------------------------------------------- 
    public void nouvelOuvrage() {
        Integer ISBN = EntreesSorties.lireEntier("Entrez l'ISBN : ");
        if (this.getOuvrage(ISBN) != null) {
            EntreesSorties.afficherMessage("Cet Ouvrage existe déja : ");
            this.getOuvrage(ISBN).afficherInfos();
        } else {
            String titre = EntreesSorties.lireChaine("Entrez le titre : ");
            String nomEditeur = EntreesSorties.lireChaine("Entrez le nom de l'éditeur : ");
            GregorianCalendar dateParution = EntreesSorties.lireDate("Entrez la date de parution : ");
            String nomAuteur = EntreesSorties.lireChaine("Entrez le nom de l'auteur : ");

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
                        EntreesSorties.afficherMessage("Saisie incorrecte, veuillez recommencer.");
                        break;
                }
            } while (i == 0);

            

            Ouvrage O = new Ouvrage(ISBN, titre, nomEditeur, dateParution, nomAuteur, publif);

            lierOuvrageBibliotheque(O, ISBN);
            EntreesSorties.afficherMessage("Saisie du premier exemplaire.");
            this.nouvelExemplaire(O);

        }
    }

    public void consulterOuvrage() {
        Integer ISBN = EntreesSorties.lireEntier("Entrez le numero ISBN de l'ouvrage que vous souhaitez consulter : ");
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

            EntreesSorties.afficherMessage("Fin de saisie");

            O.nouvelExemplaire(dateReception, empruntable);

        }

    }

    public void nouvelExemplaire(Ouvrage ouvrage) {
        GregorianCalendar dateReception = EntreesSorties.lireDate("Entrez la date de réception : ");
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

        EntreesSorties.afficherMessage("Fin de saisie");

        ouvrage.nouvelExemplaire(dateReception, empruntable);

    }



public void consulterExemplaireOuvrage(){
        int ISBN = EntreesSorties.lireEntier("Entrez l'ISBN de l'ouvrage dont vous voulez les exemplaires : ");
        Ouvrage ouvrage=getOuvrage(ISBN);
        ouvrage.afficherReduit();
        ouvrage.afficherExemplaire();
    }
 
}
