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
public class Service {
    
    private int id;
    private String nom;
    private Double prix;
    private String description;
    private int categorie_id;
    private int technecien;

    public Service() {
    }

    public Service(int id, String nom, Double prix, String description, int categorie_id, int technecien) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.categorie_id = categorie_id;
        this.technecien = technecien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getTechnecien() {
        return technecien;
    }

    public void setTechnecien(int technecien) {
        this.technecien = technecien;
    }
    
    
    
}
