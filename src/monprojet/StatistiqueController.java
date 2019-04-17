/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monprojet;

import Entity.Categorie;
import Entity.Statistique;
import Services.ServiceCategorie;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart pie;
    @FXML
    private JFXButton retour;
    
    ServiceCategorie categorieS = new ServiceCategorie();
    Categorie c = new Categorie();
    

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //ObservableList ob =FXCollections.observableArrayList(categorieS.stat());
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        
        List<Statistique> listStat;
        listStat = categorieS.stat();
        //for(Statistique s : listStat)
        for(int i=0;i<listStat.size();i++)
        {
            
            //System.out.println("fin ajout a la chart");
            Categorie cat = categorieS.AfficherCategorieParID(listStat.get(i).getIdCategorie());
            pieChartData.add(new PieChart.Data(cat.getName(),listStat.get(i).getNbrServiceParCategorie()));
            //System.out.println("fin ajout a la chart");
        }

        pie.setData(pieChartData);
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

}
