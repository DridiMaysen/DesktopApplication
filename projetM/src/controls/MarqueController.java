package controls;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.CrudMarque;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import metier.Marque;


public class MarqueController implements Initializable {
	@FXML
    private TableColumn<Marque, String> colid;

    @FXML
    private TableColumn<Marque, String> colnom;

    @FXML
    private TableView<Marque> tablemarque;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtnom;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane body;
    
    @FXML
    void charge(MouseEvent event) {
    	Marque m=tablemarque.getSelectionModel().getSelectedItem();
    	txtnom.setText(m.getNomMarque());
    	txtid.setText(m.getIdMarque());

    }
    @FXML
    void changer(MouseEvent event) {
    	AnchorPane p;
    	try {
    		p = FXMLLoader.load(getClass().getResource("/views/ViewAdmin.fxml"));
			body.getChildren().clear();
			body.getChildren().add(p);
		} catch(Exception e) {
			e.printStackTrace();
		}

    }


    @FXML
    void add(ActionEvent event) {
    	Marque m =new Marque (txtnom.getText());
    	int i=CrudMarque.insert(m);
    	search_marque();
    	if(i!=0) {
        	Alert d= new Alert(AlertType.INFORMATION);
        	d.setTitle("Insertion Marque");
        	d.setHeaderText(null);
        	d.setContentText("Insertion effecutée avec succes");
        	d.showAndWait();
        	}
        	else {
        		Alert d= new Alert(AlertType.ERROR);
        		d.setTitle("Insertion Marque");
            	d.setHeaderText(null);
            	d.setContentText("Erreur d'insertion marque");
            	d.showAndWait();
        	}
    	loadData();
    	search_marque();
    	

    }

    @FXML
    void clear(ActionEvent event) {
    	txtnom.setText(null);
    	txtid.setText(null);

    }

    @FXML
    void delete(ActionEvent event) {
    	Marque m=tablemarque.getSelectionModel().getSelectedItem();
    	int i=CrudMarque.delete(m);
    	search_marque();
    	if(i!=0) {
        	Alert d= new Alert(AlertType.INFORMATION);
        	d.setTitle("Suppression Marque");
        	d.setHeaderText(null);
        	d.setContentText("Suppression effecutée avec succes");
        	d.showAndWait();
        	}
        	else {
        		Alert d= new Alert(AlertType.ERROR);
        		d.setTitle("Suppression marque");
            	d.setHeaderText(null);
            	d.setContentText("Erreur de suppression marque");
            	d.showAndWait();
        	}
    	loadData();
    	search_marque();
    	

    }

    private void loadData() {
		// TODO Auto-generated method stub
		colid.setCellValueFactory(new PropertyValueFactory<>("idMarque"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("nomMarque"));
		tablemarque.setItems(CrudMarque.getAll());
	}


	@FXML
    void update(ActionEvent event) {
		Marque m= tablemarque.getSelectionModel().getSelectedItem();
		Alert d=new Alert(AlertType.CONFIRMATION);
    	d.setTitle("confirmation ");
    	d.setHeaderText(null);
    	d.setContentText("Voulez vous mettre a jour cette categorie?");
    	Optional<ButtonType> rep=d.showAndWait();
    	if(rep.get()==ButtonType.OK) {
    	int i=CrudMarque.update(m,txtnom.getText());
    	search_marque();
    	if(i!=0) {
        	Alert d1= new Alert(AlertType.INFORMATION);
        	d1.setTitle("Update Marque");
        	d1.setHeaderText(null);
        	d1.setContentText("Update effecutée avec succes");
        	d1.showAndWait();
        	}
        	else {
        		Alert d1= new Alert(AlertType.ERROR);
        		d1.setTitle("Insertion Marque");
            	d1.setHeaderText(null);
            	d1.setContentText("Erreur d'update marque");
            	d1.showAndWait();
        	}}
    	else {
    		Alert d1=new Alert(AlertType.INFORMATION);
    		d1.setTitle("MAJ ");
    		d1.setHeaderText(null);
    		d1.setContentText("MAJ annullée");
    		d1.showAndWait();
    	}
    	loadData();
    	search_marque();
    	

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadData();
		search_marque();
	}
	@FXML
	void search_marque() {
		loadData();
		ObservableList<Marque> dataList;
		dataList=CrudMarque.getAll();
		FilteredList<Marque>filteredData =new FilteredList<>(dataList,b->true);
		filterField.textProperty().addListener((observable,oldValue,newValue)->{
			filteredData.setPredicate(Marque->{
				if(newValue==null || newValue.isEmpty()) {
					return true ;
				}
				String lowerCaseFilter =newValue.toLowerCase();
				if (Marque.getIdMarque().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Marque.getNomMarque().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}
				else 
					return false;
			});
		});
		SortedList<Marque> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablemarque.comparatorProperty());
		tablemarque.setItems(sortedData);
	}

}
