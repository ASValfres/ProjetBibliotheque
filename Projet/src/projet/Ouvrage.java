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
public class Ouvrage {
    private int ISBN;
    private String titre;
    private String nomEd;
    private Date dateParution;
    private Public publif;
    private int dernierExemplaire; 
    private String nomAuteur;
    
    public Ouvrage(int ISBN, String titre, String nomEd, Date dateParution, String nomAuteur, Public publif){

        this.ISBN = ISBN;
        this.titre = titre;
        this.nomEd = nomEd;
        this.dateParution = dateParution;
        this.nomAuteur = nomAuteur;
        this.publif = publif;
    }
}

