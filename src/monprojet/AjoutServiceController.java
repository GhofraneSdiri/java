/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monprojet;

import Entity.Categorie;
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

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class AjoutServiceController implements Initializable {

    @FXML
    private JFXTextField nomS;
    @FXML
    private JFXTextField prixS;
    @FXML
    private JFXTextArea descS;
    @FXML
    private TableColumn<Service, String> colNom;
    @FXML
    private TableColumn<Service, String> colDesc;
    @FXML
    private TableColumn<Service, String> colPrix;
    @FXML
    private ComboBox<String> comboC;
    @FXML
    private JFXButton ajouterS;
    @FXML
    private JFXButton retour;
    @FXML
    private TableView<Service> ListServices;

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
        ObservableList ob =FXCollections.observableArrayList(categorieS.afficherCategoriesName());
        comboC.setItems(ob);
        
        ListServices.setOnMouseClicked((MouseEvent event1) -> {  
            s = ListServices.getSelectionModel().getSelectedItem();
            nomS.setText(s.getNom());
            descS.setText(s.getDescription());
            prixS.setText(s.getPrix()+"");
        });
    }    

    
    public void actualiser (){
        
        ListServices.setVisible(true);
    
        List<Service> listS = serviceS.afficherService();
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
    private void ajouter(ActionEvent event) {
        
        s.setTechnecien(0);// a changer selon user connecter
        s.setNom(nomS.getText());
        s.setDescription(descS.getText());
        s.setPrix(Double.parseDouble(prixS.getText()));
        s.setCategorie_id(categorieS.AfficherIdCategorieParNom(comboC.getValue()));
        serviceS.ajouterService(s);
        actualiser();
        nomS.setText("");
        prixS.setText("");
        descS.setText("");
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("User");
        primary.setScene(scene2);
        primary.show();
    }
    
}
