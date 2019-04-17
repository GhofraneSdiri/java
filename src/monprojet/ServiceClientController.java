/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monprojet;

import Entity.ObjectHolder;
import Entity.Service;
import Services.ServiceCategorie;
import Services.ServiceService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Ghofrane
 */
public class ServiceClientController implements Initializable {

    @FXML
    private JFXTextField nomS;
    @FXML
    private JFXTextField prixS;
    @FXML
    private JFXTextArea descS;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField CategorieS;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton pdf;
    @FXML
    private TableView<Service> ListServices;
    @FXML
    private TableColumn<Service, String> colNom;
    @FXML
    private TableColumn<Service, String> colDesc;
    @FXML
    private TableColumn<Service, String> colPrix;
    
    ObservableList<Service> data;
    Service s = new Service();
    ServiceService serviceS = new ServiceService();
    ServiceCategorie categorieService = new ServiceCategorie();
    @FXML
    private Button notif;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualiser();
        ListServices.setOnMouseClicked((MouseEvent event1) -> {  
            s = ListServices.getSelectionModel().getSelectedItem();
            CategorieS.setText(categorieService.AfficherCategorieParID(s.getCategorie_id()).getName());
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
    private void retour(ActionEvent event)  throws IOException {
        Stage primary = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene2 = new Scene(root2);    
        primary.setTitle("home");
        primary.setScene(scene2);
        primary.show();
    }

        @FXML
    private void pdf(ActionEvent event) throws IOException, DocumentException, java.io.IOException {
        
        Node source = (Node) event.getSource();

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PDF Files(.pdf)", ".pdf");
        chooser.getExtensionFilters().add(filter);

        File file = chooser.showSaveDialog(source.getScene().getWindow());
        if (file != null) {
            exporterPdf(file);

        }
        
    }
    
    public void exporterPdf(File file) throws IOException, DocumentException, java.io.IOException {
        Document doc = new Document();
        ObservableList<Service> r,f;
        f=(ObservableList<Service>) ListServices.getItems();
        r=(ObservableList<Service>) ListServices.getSelectionModel().getSelectedItems();
        
        PdfWriter.getInstance(doc, new FileOutputStream(file));
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Phrase("Nos Services", FontFactory.getFont("Arial", 20, Font.BOLDITALIC)));
        doc.add(new Paragraph("   "));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Service ", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        Service rs = new Service();
        ServiceService res = new ServiceService();

        List<Service>  lRes = new ArrayList<>();
        
        lRes = res.afficherService();

        for (Service a : lRes) {
        
            cell = new PdfPCell(new Phrase(a.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(a.getDescription(), FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(a.getPrix()+"", FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            table.addCell(cell);

           
        }

        doc.add(table);
        doc.close();
        Desktop.getDesktop().open(file);}

    @FXML
    private void notifier(ActionEvent event) {
        int y=ObjectHolder.getInstance().getEtat_button_ajouter();
        
         if  (ObjectHolder.getInstance().getEtat_button_ajouter()==0)

        {  
            Notifications.create()
               .title("Un nouveau service est lancé")
               .text("Veuillez consulter")
               .graphic(null)
               //.hideAfter(Duration.seconds(5))
               //.position(Pos.TOP_RIGHT)
                    .showConfirm();
           
                  /*.onAction(new EventHandler<ActionEvent>()
               {
                   @Override
              public void handle(ActionEvent event) {
                  System.out.println("clicked on notification");
               } });
        */
        // TODO
          actualiser();
               System.out.println(y);
       
    }
    }
    
}

