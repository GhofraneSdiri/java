/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monprojet;

import Entity.Categorie;
import Services.ServiceCategorie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class AjouterCategorieController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXButton supprimer;
    @FXML
    private TableView<Categorie> ListCategoris;
    @FXML
    private TableColumn<Categorie, String> colNom;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton services;
    
    ObservableList<Categorie> data;
    
    ServiceCategorie categorieS = new ServiceCategorie();
    Categorie c = new Categorie();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiser();
        
        ListCategoris.setOnMouseClicked((MouseEvent event1) -> {  
            c = ListCategoris.getSelectionModel().getSelectedItem();
            nom.setText(c.getName());
        });
    }    

    public void actualiser (){
        
        ListCategoris.setVisible(true);
    
        List<Categorie> listC = categorieS.afficherCategorie();
        data = FXCollections.observableArrayList();
        listC.stream().forEach(( j) -> {
            data.add(new Categorie(j.getId(),j.getName()));
            ListCategoris.setItems(data);
        });
        
        colNom.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        categorieS.supprimerCategorie(c);
        actualiser();
        nom.setText("");
    }

    @FXML
    private void modifier(ActionEvent event) {
        c.setName(nom.getText());
        categorieS.modifierCategorie(c);
        actualiser();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        c.setName(nom.getText());
        if(categorieS.VerifierExistance(c)==false)
        {
            categorieS.ajouterCategorie(c);
            actualiser();
        }else
        {
            System.out.println("nom existant");
           
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene2 = new Scene(root2);    
            primary.setTitle("home");
            primary.setScene(scene2);
            primary.show();
    }

    @FXML
    private void find(ActionEvent event) {
        ObservableList table = ListCategoris.getItems();
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
	            if (oldValue != null && (newValue.length() < oldValue.length())) {
	                ListCategoris.setItems(table);
	            }
	            String value = newValue.toLowerCase();
	            ObservableList<Categorie> subentries = FXCollections.observableArrayList();

	            long count = ListCategoris.getColumns().stream().count();
	            for (int i = 0; i < ListCategoris.getItems().size(); i++) {
	                for (int j = 0; j < count; j++) {
	                    String entry = "" + ListCategoris.getColumns().get(j).getCellData(i);
	                    
                                    
                            if (entry.toLowerCase().contains(CharSequence.class.cast(value))) 
                            {
	                        subentries.add(ListCategoris.getItems().get(i));
	                        break;
                            }
	                }
	            }
	            ListCategoris.setItems(subentries);
	        });
	        actualiser();
	
    }

    @FXML
    private void services(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("ModifierServiceAdmin.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("modofication service");
        primary.setScene(scene2);
        primary.show();
    }
    
}
