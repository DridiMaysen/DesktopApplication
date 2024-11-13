package controls;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.CrudClient;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import metier.Client;


public class ClientController implements Initializable{
	@FXML
    private Button btnadd;

    @FXML
    private Button btnclear;

    @FXML
    private Button btndelete;

    @FXML
    private Button btnupdate;
	@FXML
    private TableColumn<Client, String> coladd;

    @FXML
    private TableColumn<Client, String> colid;

    @FXML
    private TableColumn<Client, String> colmdp;

    @FXML
    private TableColumn<Client, String> colnom;

    @FXML
    private TableColumn<Client, String> colprenom;

    @FXML
    private TableColumn<Client, String> coltele;

    @FXML
    private TableView<Client> tableclient;

    @FXML
    private TextField txtadresse;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtnum;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtprenom;
    @FXML
    private ToggleGroup genre;

    @FXML
    private RadioButton radioF;

    @FXML
    private RadioButton radioH;
    @FXML
    private TableColumn<Client, String> colgenre;
    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane body;
    @FXML
    void charge(MouseEvent event) {
    	Client c=tableclient.getSelectionModel().getSelectedItem();
    	txtid.setText(c.getIdClient());
    	txtnom.setText(c.getNomClient());
    	txtprenom.setText(c.getPrenomClient());
    	txtadresse.setText(c.getAddClient());
    	txtnum.setText(c.getTeleClient());
    	txtpass.setText(c.getMdpClient());
    	if (c.getGenre()=="Femme") {
    		radioF.setSelected(true);
    	}else {
    		radioH.setSelected(true);
    	}
    	
    }

    @FXML
    void add(ActionEvent event) {
    	Client c;
    	if (radioF.isSelected()) {
    	 c =new Client (txtnom.getText(),txtprenom.getText(),txtpass.getText(),txtnum.getText(),txtadresse.getText(),radioF.getText());
    	 search_user();}
    	else {
    		 c =new Client (txtnom.getText(),txtprenom.getText(),txtpass.getText(),txtnum.getText(),txtadresse.getText(),radioH.getText());
    		 search_user();
    	}
    	int i=CrudClient.insert(c);
    	if(i!=0) {
        	Alert d= new Alert(AlertType.INFORMATION);
        	d.setTitle("Insertion Client");
        	d.setHeaderText(null);
        	d.setContentText("Insertion effecutée avec succes");
        	d.showAndWait();
        	}
        	else {
        		Alert d= new Alert(AlertType.ERROR);
        		d.setTitle("Insertion Client");
            	d.setHeaderText(null);
            	d.setContentText("Erreur d'insertion client");
            	d.showAndWait();
        	}
    	loadData();
    	search_user();

    }

    @FXML
    void clear(ActionEvent event) {
    	txtnom.setText(null);
    	txtprenom.setText(null);
    	txtpass.setText(null);
    	txtadresse.setText(null);
    	txtnum.setText(null);
    	txtid.setText(null);
    	radioF.setSelected(false);
    	radioH.setSelected(false);

    }

    @FXML
    void delete(ActionEvent event) {
    	Client c= tableclient.getSelectionModel().getSelectedItem();
    	int i=CrudClient.delete(c);
    	search_user();
    	if(i!=0) {
        	Alert d= new Alert(AlertType.INFORMATION);
        	d.setTitle("Suppression Client");
        	d.setHeaderText(null);
        	d.setContentText("Suppression effecutée avec succes");
        	d.showAndWait();
        	}
        	else {
        		Alert d= new Alert(AlertType.ERROR);
        		d.setTitle("Suppression client");
            	d.setHeaderText(null);
            	d.setContentText("Erreur de suppression client");
            	d.showAndWait();
        	}
    	loadData();
    	search_user();

    }

    @FXML
    void update(ActionEvent event) {
    	Client c= tableclient.getSelectionModel().getSelectedItem();
    	Alert d=new Alert(AlertType.CONFIRMATION);
    	d.setTitle("confirmation ");
    	d.setHeaderText(null);
    	d.setContentText("Voulez vous mettre a jour cette categorie?");
    	Optional<ButtonType> rep=d.showAndWait();
    	int i;
    	if(rep.get()==ButtonType.OK) {
    		if (radioF.isSelected()) {
    			i=CrudClient.update(c,txtnom.getText(),txtprenom.getText(),txtpass.getText(),txtnum.getText(),txtadresse.getText(),radioF.getText());
    			
    		}else {
    		 	i=CrudClient.update(c,txtnom.getText(),txtprenom.getText(),txtpass.getText(),txtnum.getText(),txtadresse.getText(),radioH.getText());
    		 	
    		}if(i!=0) {
        	Alert d1= new Alert(AlertType.INFORMATION);
        	d1.setTitle("Update Client");
        	d1.setHeaderText(null);
        	d1.setContentText("Update effecutée avec succes");
        	d1.showAndWait();
        	}
        	else {
        		Alert d1= new Alert(AlertType.ERROR);
        		d1.setTitle("Insertion Client");
            	d1.setHeaderText(null);
            	d1.setContentText("Erreur d'update client");
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
    	search_user();
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadData();
		search_user();
		
	}
	@FXML
	void search_user() {
		loadData();
		ObservableList<Client> dataList;
		dataList=CrudClient.getAll();
		FilteredList<Client>filteredData =new FilteredList<>(dataList,b->true);
		filterField.textProperty().addListener((observable,oldValue,newValue)->{
			filteredData.setPredicate(Client->{
				if(newValue==null || newValue.isEmpty()) {
					return true ;
				}
				String lowerCaseFilter =newValue.toLowerCase();
				if (Client.getIdClient().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Client.getNomClient().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Client.getPrenomClient().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Client.getMdpClient().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Client.getTeleClient().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Client.getAddClient().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
					return true;
				}else if(Client.getGenre().toLowerCase().indexOf(lowerCaseFilter)!=-1) 
					return true;
				else 
					return false;
			});
		});
		SortedList<Client> sortedData=new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableclient.comparatorProperty());
		tableclient.setItems(sortedData);
	}

	private void loadData() {
		// TODO Auto-generated method stub
		colid.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
		colprenom.setCellValueFactory(new PropertyValueFactory<>("prenomClient"));
		coltele.setCellValueFactory(new PropertyValueFactory<>("teleClient"));
		colmdp.setCellValueFactory(new PropertyValueFactory<>("mdpClient"));
		coladd.setCellValueFactory(new PropertyValueFactory<>("addClient"));
		colgenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
		tableclient.setItems(CrudClient.getAll());
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

}
