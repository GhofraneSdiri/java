/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entity.Categorie;
import Entity.Statistique;
import Interfaces.ICategorie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ghofrane
 */
public class ServiceCategorie implements ICategorie{

    MyDB myDB;

    public ServiceCategorie() {
        myDB = myDB.getInsatance();
    }

    @Override
    public void ajouterCategorie(Categorie c) {
        
        try 
        {
            Statement stm = myDB.getConnection().createStatement();

            stm.executeUpdate("insert into categorie (name) values ('"+c.getName()+"')");
   
            System.out.println("Ajout OK!");
            
        }
        catch(SQLException ex)
        {
            System.out.println("probleme d'ajout!");
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void supprimerCategorie(Categorie c) {
        try {
            
            Statement stm = myDB.getConnection().createStatement();
            stm.executeUpdate("DELETE FROM categorie WHERE id = " + c.getId() + ";");
        
        } catch (SQLException ex) {
            System.out.println("probleme de suppression!");
        }
    }

    @Override
    public void modifierCategorie(Categorie c) {
        try {

            Statement stm = myDB.getConnection().createStatement();
            stm.executeUpdate("UPDATE categorie SET name='"+c.getName()+"' WHERE id ="+c.getId() );

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean VerifierExistance(Categorie c) {
        
        boolean exist = false;
        List<String> listNom = new ArrayList<String>();
        
        try
        {
            Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select name from categorie where name ='"+c.getName()+"'");
            
            while(rest.next())
            {
                String nom = new String();
                rest.getString(1);
                listNom.add(nom);
            }
            if (listNom.size()>0)
                exist = true;
            
        }catch (SQLException ex) {
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    @Override
    public List<Categorie> afficherCategorie() {
        ObservableList<Categorie> categories = FXCollections.observableArrayList();
        try
        {
         Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select * from categorie ");
        while(rest.next())
        {
            Categorie c = new Categorie();
                c.setId(rest.getInt(1));
                c.setName(rest.getString(2));
            categories.add(c);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return categories;
    
    }

    @Override
    public Categorie AfficherCategorieParID(int idCategorie) {
        Categorie c = new Categorie();
        try
        {
            System.out.println("debut methode");
            Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select * from categorie where id = "+idCategorie);
            
            while(rest.next())
                {
                    System.out.println("debut recuperation");
                    c.setId(rest.getInt(1));
                    c.setName(rest.getString(2));
                    
                }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return c;
    }
    
    

    @Override
    public List<String> afficherCategoriesName() { 
        ObservableList<String> categoriesName = FXCollections.observableArrayList();
        try
        {
         Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select name from categorie ");
        while(rest.next())
        {
            String c = rest.getString(1);
            categoriesName.add(c);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return categoriesName;
    
    }

    
    @Override
    public int AfficherIdCategorieParNom(String nom) {
        int id = 0;
        try
        {
            Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select id from categorie where name = '"+nom+"'");
            System.out.println("select id from categorie where name = '"+nom+"'");
            while(rest.next())
                {
                    id =rest.getInt(1);
                }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage ID");
        }
        return id;
    }
    
    @Override
    public List<Statistique> stat() {
        List<Statistique> listStat = new ArrayList<>();
        
        try
        {
         Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select COUNT(*), categorie_id from service GROUP BY categorie_id;");
            while(rest.next())
            {
                Statistique c = new Statistique();
                c.setNbrServiceParCategorie(rest.getInt(1));
                c.setIdCategorie(rest.getInt(2));
                System.out.println("le service sous l ID = "+c.getIdCategorie()+
                        "admet "+c.getNbrServiceParCategorie()+" Services");
                listStat.add(c);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage stat");
        }
        return listStat;
    
    }
    
}
