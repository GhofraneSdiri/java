/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Categorie;
import Entity.Statistique;
import java.util.List;

/**
 *
 * @author Ghofrane
 */
public interface ICategorie {
    public void ajouterCategorie(Categorie c);
    public void supprimerCategorie(Categorie c);
    public void modifierCategorie(Categorie c);
    public List<Categorie> afficherCategorie();
    public Categorie AfficherCategorieParID(int idCategorie);
    public List<String> afficherCategoriesName();
    public int AfficherIdCategorieParNom(String nom);
    public boolean VerifierExistance(Categorie c);
    public List<Statistique> stat();
    
}
