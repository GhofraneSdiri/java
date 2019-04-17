/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Ghofrane
 */
public class Statistique {
    
    private int idCategorie;
    private int nbrServiceParCategorie;

    public Statistique() {
    }

    public Statistique(int idCategorie, int nbrServiceParCategorie) {
        this.idCategorie = idCategorie;
        this.nbrServiceParCategorie = nbrServiceParCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getNbrServiceParCategorie() {
        return nbrServiceParCategorie;
    }

    public void setNbrServiceParCategorie(int nbrServiceParCategorie) {
        this.nbrServiceParCategorie = nbrServiceParCategorie;
    }
    
    
    
}
