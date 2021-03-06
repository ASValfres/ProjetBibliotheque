package projet;

public class MenuBiblio {

    private Bibliotheque bibliotheque;

    public MenuBiblio(Bibliotheque bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    /*
	 * menuPrincipal permet à l'utilisateur de selectionner un type de sous menu (Lecteur, Ouvrage ou Exemplaire) 
	 * où il effectuera par la suite l'action désirée. Si l'utilisateur a fini d'utiliuser le programme, il choisit l'option Quitter.
     */
    public void menuPrincipal() {
        Integer menu;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("|                   Menu Principal                       |");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Menu Lecteur : 1                                       |");
            EntreesSorties.afficherMessage("| Menu Ouvrage : 2                                       |");
            EntreesSorties.afficherMessage("| Menu Exemplaire : 3                                    |");
            EntreesSorties.afficherMessage("| Menu Emprunt : 4                                       |");
            EntreesSorties.afficherMessage("| Quitter : 0                                            |");
            EntreesSorties.afficherMessage(" ========================================================");
            menu = EntreesSorties.lireEntier();

            switch (menu) {
                case 1: {
                    this.menuLecteur();
                    break;
                }
                case 2: {
                    this.menuOuvrage();
                    break;
                }
                case 3: {
                    this.menuExemplaire();
                    break;
                }
                case 4: {
                    this.menuEmprunt();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menu != 0);
    }

    /* menuLect permet d'effectuer une série d'action concernant les utilisateur (lecteurs) de la bibliothèque.
	 * Une fois une action effectuée, l'utilisateur sera rediriger vers ce même menu afin de pouvoir selectionner
	 * une nouvelle fois une action concernant les lecteurs.
	 * "Retour Menu Principal" renvoi l'utilisateur au menu principal.
     */
    public void menuLecteur() {
        Integer menuLect;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouveau Lecteur : 1                                    |");
            EntreesSorties.afficherMessage("| Consulter Lecteur : 2                                  |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuLect = EntreesSorties.lireEntier();

            switch (menuLect) {
                case 1: {
                    bibliotheque.nouveauLecteur();
                    break;
                }
                case 2: {
                    bibliotheque.consulterLecteur();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menuLect != 0);
    }

    public void menuOuvrage() {
        Integer menuOuv;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouvel ouvrage : 1                                     |");
            EntreesSorties.afficherMessage("| Consulter ouvrage : 2                                  |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuOuv = EntreesSorties.lireEntier();

            switch (menuOuv) {
                case 1: {
                    bibliotheque.nouvelOuvrage();
                    break;
                }
                case 2: {
                    bibliotheque.consulterOuvrage();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menuOuv != 0);
    }

    public void menuExemplaire() {
        Integer menuEx;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouvel exemplaire : 1                                  |");
            EntreesSorties.afficherMessage("| Consulter exemplaires d'un ouvrage : 2                 |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuEx = EntreesSorties.lireEntier();

            switch (menuEx) {
                case 1: {

                    bibliotheque.nouvelExemplaire();
                    break;
                }
                case 2: {
                    bibliotheque.consulterExemplaireOuvrage();
                    break;
                }
                default: {
                    break;
                }
            }
        } while (menuEx != 0);
    }

    public void menuEmprunt() {
        Integer menuEmp;
        do {
            EntreesSorties.afficherMessage(" ========================================================");
            EntreesSorties.afficherMessage("| Saisissez un numero correspondant :                    |");
            EntreesSorties.afficherMessage("| Nouvel Emprunt : 1                                     |");
            EntreesSorties.afficherMessage("| Consulter Emprunts Lecteur : 2                         |");
            EntreesSorties.afficherMessage("| Rendre Exemplaire : 3                                  |");
            EntreesSorties.afficherMessage("| Relancer lecteurs : 4                                  |");
            EntreesSorties.afficherMessage("| Retour Menu Principal : 0                              |");
            EntreesSorties.afficherMessage(" ========================================================");
            menuEmp = EntreesSorties.lireEntier();

            switch (menuEmp) {
                case 1:
                    bibliotheque.emprunterExemplaire();
                    break;

                case 2:
                    bibliotheque.consulterEmpruntsLecteur();
                    break;

                case 3:
                    bibliotheque.rendreExemplaire();
                    break;

                case 4:
                    bibliotheque.relancerLecteur();
                    break;

                default:
                    break;

            }
        } while (menuEmp != 0);
    }
}
