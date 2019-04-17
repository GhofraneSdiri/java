/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monprojet;

import Entity.Service;
import Services.ServiceCategorie;
import Services.ServiceService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfxtras.labs.icalendarfx.properties.component.descriptive.Description;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class ModifierServiceTechnicienController implements Initializable {

    @FXML
    private TableView<Service> ListServices;
    @FXML
    private TableColumn<Service, String> colNom;
    @FXML
    private TableColumn<Service, String> colDesc;
    @FXML
    private TableColumn<Service, String> colPrix;
    @FXML
    private JFXTextField nomS;
    @FXML
    private JFXTextField prixS;
    @FXML
    private JFXTextArea descS;
    @FXML
    private JFXTextField categorie;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton supprimer;

    
    ObservableList<Service> data;
    Service s = new Service();
    ServiceService serviceS = new ServiceService();
    ServiceCategorie categorieS = new ServiceCategorie();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiser();
        
        ListServices.setOnMouseClicked((MouseEvent event1) -> {  
            s = ListServices.getSelectionModel().getSelectedItem();
            categorie.setText(categorieS.AfficherCategorieParID(s.getCategorie_id()).getName());
            nomS.setText(s.getNom());
            descS.setText(s.getDescription());
            prixS.setText(s.getPrix()+"");
        });
    }    
    
    public void actualiser (){
        
        ListServices.setVisible(true);
    
        List<Service> listS = serviceS.AfficherServiceTechnicien(0);// selon a changer selon user connecter 
        data = FXCollections.observableArrayList();
        listS.stream().forEach(( j) -> {
            data.add(new Service(j.getId(),j.getNom(),j.getPrix(),j.getDescription(),j.getCategorie_id(),j.getTechnecien()));
            ListServices.setItems(data);
        });
        
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }

    @FXML
    private void modifier(ActionEvent event) {
        s.setNom(nomS.getText());
        s.setDescription(descS.getText());
        s.setPrix(Double.parseDouble(prixS.getText()));
        
        serviceS.modifierService(s);
        
        actualiser();
        
        categorie.setText("");
        nomS.setText("");
        prixS.setText("");
        descS.setText("");
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
        ObservableList table = ListServices.getItems();
        
        search.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                ListServices.setItems(table);
            }
            String value = newValue.toLowerCase();
            ObservableList<Service> subentries = FXCollections.observableArrayList();

            long count = ListServices.getColumns().stream().count();
            for (int i = 0; i < ListServices.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + ListServices.getColumns().get(j).getCellData(i);
                    //if (entry.toLowerCase().equals(value)

                    if (entry.toLowerCase().contains(CharSequence.class.cast(value))) 
                    {
                        subentries.add(ListServices.getItems().get(i));
                        break;
                    }
                }
            }
            ListServices.setItems(subentries);
        });
        actualiser();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        serviceS.supprimerService(s);
        actualiser();
        categorie.setText("");
        nomS.setText("");
        prixS.setText("");
        descS.setText("");
    }
    
}
