package controls;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import dao.CrudProduit;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import metier.Produit;

public class ProduitController implements Initializable {
	@FXML
    private TableColumn<Produit, String> coldes;

    @FXML
    private TableColumn<Produit, String> colid;

    @FXML
    private TableColumn<Produit, String> colnom;

    @FXML
    private TableColumn<Produit, String> colprix;

    @FXML
    private TableColumn<Produit, String> colquant;
    @FXML
    private AnchorPane body;
    @FXML
    private TextField filterField;

    @FXML
    private TableView<Produit> tableProduit;

    @FXML
    private TextField txtdes;

    @FXML
    private TextField txtidp;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtprix;

    @FXML
    private TextField txtquant;
    @FXML
    private TableColumn<Produit, String> colidMarque;
    @FXML
    private ComboBox<String> combomarque;

    @FXML
    private Spinner<Integer> spinqt;
    @FXML
    void charge(MouseEvent event) {
    	Produit p=tableProduit.getSelectionModel().getSelectedItem();
    	txtidp.setText(p.getIdProduit());
    	txtnom.setText(p.getNomProduit());
    	txtdes.setText( p.getDescriptionProduit());
    	txtprix.setText(p.getPrixProduit());
    	spinqt.getValueFactory().setValue(p.getQuantiteProduit());
    	combomarque.setValue(p.getMarque());

    }

    @FXML
    void add(ActionEvent event) {
    	Produit p= new Produit(txtnom.getText(),txtdes.getText(),txtprix.getText(),spinqt.getValue(),combomarque.getSelectionModel().getSelectedItem());
    	int i=CrudProduit.insert(p);
    	search_prod();
    	if(i!=0) {
    	Alert d= new Alert(AlertType.INFORMATION);
    	d.setTitle("Insertion Produit");
    	d.setHeaderText(null);
    	d.setContentText("Insertion effecutée avec succes");
    	d.showAndWait();
    	}
    	else {
    		Alert d= new Alert(AlertType.ERROR);
    		d.setTitle("Insertion Produit");
        	d.setHeaderText(null);
        	d.setContentText("Erreur d'insertion produit");
        	d.showAndWait();
    	} loadData();
    	search_prod();
    	
    	

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
    void clear(ActionEvent event) {
    	txtidp.setText(null);
    	txtnom.setText(null);
    	txtdes.setText(null);
    	txtprix.setText(null);
    	spinqt.getValueFactory().setValue(0);
    	combomarque.getSelectionModel().selectFirst();

    }

    @FXML
    void delete(ActionEvent event) {
    	Produit p=tableProduit.getSelectionModel().getSelectedItem();
    	int i=CrudProduit.delete(p);
    	search_prod();
    	if(i!=0) {
    	Alert d= new Alert(AlertType.INFORMATION);
    	d.setTitle("Suppression Produit");
    	d.setHeaderText(null);
    	d.setContentText("Suppression effecutée avec succes");
    	d.showAndWait();
    	}
    	else {
    		Alert d= new Alert(AlertType.ERROR);
    		d.setTitle("Suppression Produit");
        	d.setHeaderText(null);
        	d.setContentText("Erreur de suppression produit");
        	d.showAndWait();
    	}
    	loadData();
    	search_prod();

    }

    @FXML
    void update(ActionEvent event) {
    	Produit p= tableProduit.getSelectionModel().getSelectedItem();
    	Alert d=new Alert(AlertType.CONFIRMATION);
    	d.setTitle("confirmation ");
    	d.setHeaderText(null);
    	d.setContentText("Voulez vous mettre a jour cette categorie?");
    	Optional<ButtonType> rep=d.showAndWait();
    	if(rep.get()==ButtonType.OK) {
    	int i=CrudProduit.update(p,txtnom.getText(),txtdes.getText(),txtprix.getText(),spinqt.getValue(),combomarque.getSelectionModel().getSelectedItem());
    	search_prod();
    	if(i!=0) {
    	Alert d1= new Alert(AlertType.INFORMATION);
    	d1.setTitle("Update Produit");
    	d1.setHeaderText(null);
    	d1.setContentText("Update effecutée avec succes");
    	d1.showAndWait();
    	}
    	else {
    		Alert d1= new Alert(AlertType.ERROR);
    		d1.setTitle("Update Produit");
        	d1.setHeaderText(null);
        	d1.setContentText("Erreur d'update produit");
        	d1.showAndWait();
    	}
    	}
    	else {
    		Alert d1=new Alert(AlertType.INFORMATION);
    		d1.setTitle("MAJ ");
    		d1.setHeaderText(null);
    		d1.setContentText("MAJ annullée");
    		d1.showAndWait();
    	}
    	loadData();

    	search_prod();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combomarque.setItems(CrudProduit.getNoms());
		combomarque.getSelectionModel().selectFirst();
		loadData();
		search_prod();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		colid.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
		coldes.setCellValueFactory(new PropertyValueFactory<>("descriptionProduit"));
		colprix.setCellValueFactory(new PropertyValueFactory<>("prixProduit"));
		colquant.setCellValueFactory(new PropertyValueFactory<>("quantiteProduit"));
		colidMarque.setCellValueFactory(new PropertyValueFactory<>("marque"));
		tableProduit.setItems(CrudProduit.getAll());
	}
	@FXML
	void search_prod() {
		loadData();
		ObservableList<Produit> dataList;
		dataList=CrudProduit.getAll();
		FilteredList<Produit>filteredData =new FilteredList<>(dataList,b->true);
		filterField.textProperty().addListener((observable,oldValue,newValue)->{
			filteredData.setPredicate(Produit->{
				if(newValue==null || newValue.isEmpty()) {
					return true ;
				}
				String lowerCaseFilter =newValue.toLowerCase();
				if (Produit.getIdProduit().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Produit.getNomProduit().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Produit.getDescriptionProduit().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(String.valueOf(Produit.getQuantiteProduit()).toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Produit.getPrixProduit().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Produit.getMarque().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}
				else 
					return false;
			});
		});
		SortedList<Produit> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableProduit.comparatorProperty());
		tableProduit.setItems(sortedData);
	}

}
