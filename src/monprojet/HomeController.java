/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monprojet;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton service;
    @FXML
    private JFXButton categorie;
    @FXML
    private JFXButton ajouterservice;
    @FXML
    private JFXButton serviceClient;
    @FXML
    private JFXButton stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void service(ActionEvent event)  throws IOException {
        
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ModifierServiceTechnicien.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Modification Service");
        primary.setScene(scene2);
        primary.show();
        
    }

    @FXML
    private void categorie(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AjouterCategorie.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Ajout Catégorie");
        primary.setScene(scene2);
        primary.show();
    
    }

    @FXML
    private void ajouterservice(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("AjoutService.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Ajout Service");
        primary.setScene(scene2);
        primary.show();
    }

    @FXML
    private void serviceClient(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ServiceClient.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Service");
        primary.setScene(scene2);
        primary.show();
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("Statistique");
        primary.setScene(scene2);
        primary.show();
    }
    
}
