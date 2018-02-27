
package projet;

public class MenuBiblio {

    private Bibliotheque bibliotheque;

    public MenuBiblio(Bibliotheque bibliotheque) {
        bibliotheque = bibliotheque;
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
            EntreesSorties.afficherMessage("| Quitter : 0                                            |");
            EntreesSorties.afficherMessage(" ========================================================");
            menu = EntreesSorties.lireEntier();

            switch (menu) {
                case 1: {
                    this.menuLecteur();
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

}
