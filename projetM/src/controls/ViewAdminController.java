package controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewAdminController {
	@FXML
	private AnchorPane body;
	@FXML
    private Button btngerem;

    @FXML
    private Button btngererc;

    @FXML
    private Button btngererp;

    @FXML
    private Button btngererv;
    Stage stage;
    @FXML
    void logout(MouseEvent event) {
    	Alert d= new Alert(AlertType.INFORMATION);
    	d.setTitle("LogOut");
    	d.setHeaderText("You're about to logout!");
    	d.setContentText("Do you want to logOut!");
    	if (d.showAndWait().get()==ButtonType.OK) {
    		stage=(Stage) body.getScene().getWindow();
    		stage.close();
    		
    	}
    	

    }

    @FXML
    void gereProduit(ActionEvent event) {
    	AnchorPane p;
    	try {
    		p = FXMLLoader.load(getClass().getResource("/views/ProduitViews.fxml"));
			body.getChildren().clear();
			body.getChildren().add(p);
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void gererClient(ActionEvent event) {
    	AnchorPane p;
    	try {
    		p = FXMLLoader.load(getClass().getResource("/views/ClientViews.fxml"));
			body.getChildren().clear();
			body.getChildren().add(p);
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void gererMarque(ActionEvent event) {
    	AnchorPane p;
    	try {
    		p = FXMLLoader.load(getClass().getResource("/views/MarqueViews.fxml"));
			body.getChildren().clear();
			body.getChildren().add(p);
		} catch(Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void gererVente(ActionEvent event) {
    	AnchorPane p;
    	try {
    		p = FXMLLoader.load(getClass().getResource("/views/VenteViews.fxml"));
			body.getChildren().clear();
			body.getChildren().add(p);
		} catch(Exception e) {
			e.printStackTrace();
		}
    	


    }
   

}
