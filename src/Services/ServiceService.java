/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entity.Categorie;
import Entity.Service;
import Interfaces.IService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ghofrane
 */
public class ServiceService implements IService{

    MyDB myDB;

    public ServiceService() {
        myDB = myDB.getInsatance();
    }
    
    @Override
    public void ajouterService(Service s) {
        
        try 
        {
            Statement stm = myDB.getConnection().createStatement();
            String query = "insert into service (categorie_id, technecien, nom, prix, description) values ("+
                    s.getCategorie_id()+","+
                    s.getTechnecien()+",'"+
                    s.getNom()+"',"+
                    s.getPrix()+",'"+
                    s.getDescription()+"')";        
            stm.executeUpdate(query);
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
    public void supprimerService(Service s) {
        try {
            
            Statement stm = myDB.getConnection().createStatement();
            stm.executeUpdate("DELETE FROM service WHERE id = " + s.getId());
        
        } catch (SQLException ex) {
            System.out.println("probleme de suppression!");
        }
    }

    @Override
    public void modifierService(Service s) {
        try {
            String query = "UPDATE service SET categorie_id="+s.getCategorie_id()
                    +", technecien="+s.getTechnecien()
                    +", nom='"+s.getNom()
                    +"', prix="+s.getPrix()
                    +", description='"+s.getDescription()
                    + "' WHERE id ="+s.getId();      
            Statement stm = myDB.getConnection().createStatement();
            stm.executeUpdate(query);
            System.out.println(query);

        } catch (SQLException ex) {
            System.out.println("probleme de modification!");
            Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Service> afficherService() {
        ObservableList<Service> services = FXCollections.observableArrayList();
        try
        {
         Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select * from service ");
        while(rest.next())
        {
            Service s = new Service();
                s.setId(rest.getInt(1));
                s.setCategorie_id(rest.getInt(2));
                s.setTechnecien(rest.getInt(3));
                s.setNom(rest.getString(4));
                s.setPrix(rest.getDouble(5));
                s.setDescription(rest.getString(6));
            services.add(s);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return services;
    
    }

    @Override
    public Service AfficherServiceParID(int idService) {
        Service s = new Service();
        try
        {
            Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select * from service where id = "+idService);
            
            while(rest.next())
                {
                    s.setId(rest.getInt(1));
                    s.setCategorie_id(rest.getInt(2));
                    s.setTechnecien(rest.getInt(3));
                    s.setDescription(rest.getString(4));
                    s.setPrix(rest.getDouble(5));
                    s.setDescription(rest.getString(6));

                }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return s;
    }

    
    
    @Override
    public List<Service> AfficherServiceTechnicien(int idTechnicien) {
        ObservableList<Service> services = FXCollections.observableArrayList();
        try
        {
         Statement stm = myDB.getConnection().createStatement();
            ResultSet rest=stm.executeQuery("select * from service where technecien ="+idTechnicien);
        while(rest.next())
        {
            Service s = new Service();
                s.setId(rest.getInt(1));
                s.setCategorie_id(rest.getInt(2));
                s.setTechnecien(rest.getInt(3));
                s.setNom(rest.getString(4));
                s.setPrix(rest.getDouble(5));
                s.setDescription(rest.getString(6));
            services.add(s);
        }
        }
        catch (SQLException ex)
        {
            System.out.println("Probleme affichage");
        }
        return services;
    }
    
}
