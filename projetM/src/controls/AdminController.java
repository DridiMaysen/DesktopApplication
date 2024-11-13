package controls;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import dao.SingletonConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class AdminController {
	String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");

    @FXML
    private Button btnSign;
    
    @FXML
    private Label  lblmsg;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtnom;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtprenom;

    @FXML
    private AnchorPane body;

   Stage stage;
    @FXML
    void login(ActionEvent event) throws IOException {
    	if (txtnom.getText().isBlank() || txtpass.getText().isBlank()) {
    		lblmsg.setText("the userName and the Password are required!");
    	    lblmsg.setStyle(errorMessage);
    	    if(txtnom.getText().isBlank())
    	    	txtnom.setStyle(errorStyle);
    	    else 
    	    	txtpass.setStyle(errorStyle);
    	}
    	else if (ConnectUser(txtnom.getText(),txtpass.getText())){
    		stage=(Stage) body.getScene().getWindow();
    		stage.close();
    		try {
    			Parent root = FXMLLoader.load(getClass().getResource("/views/ViewAdmin.fxml"));
    			Scene scene = new Scene(root);
    			Stage stage= new Stage();
    			stage.setScene(scene);
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} 
    	else {
		  Alert alert = new Alert(Alert.AlertType.ERROR);
		  alert.setTitle("Admin doesn't exist");
		  alert.setHeaderText("let's verify yourn inputs");
		  alert.show();
    	}
		

    }
    public static boolean ConnectUser(String Name, String Password) {
    	String rq="select * from admin where nomAdmin=? and mdpAdmin=?;";
    	PreparedStatement ps;
    	ResultSet rs;
    	try {
			ps=SingletonConnection.getCon().prepareStatement(rq);
				ps.setString(1, Name);
				ps.setString(2, Password);
			    rs=ps.executeQuery();
				if (rs.next()) {
					return true;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    	
    }
    

}





