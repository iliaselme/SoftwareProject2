package com.ehbrail;

import com.database.FormuleDAO;
import com.model.Formule;
import com.model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.database.LoginDAO.changePassbyLogin;
import static com.database.LoginDAO.getLoginByID;
import static com.database.FormuleDAO.getFormuleActive;
import static com.database.FormuleDAO.updateFormule;
import static com.database.FormuleDAO.insertFormule;


/**
 * @author Michael
 */
public class APrizeFormulaTabController implements Initializable {
    @FXML private Label accountLabel;
    @FXML private Label oldFormulaField;
    @FXML private TextField newFormulaField;
    @FXML private Button changeFormulaButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	accountLabel.setText(AdminController.getLogin().getUsername());
        oldFormulaField.setText(FormuleDAO.getFormuleActive().getFormule());
    }

    private void createAlertBox(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void changeFormulaAction(ActionEvent event) throws IOException {
        Image img = new Image("/com/ehbrail/checkmark.png");
        Login login = getLoginByID(AdminController.getLogin().getLogin_id());
        if (!newFormulaField.getText().isEmpty()){
        	if(checkContent(newFormulaField.getText())){
        		updateFormule(FormuleDAO.getFormuleActive().getFormule(),false); 
        		if(FormuleDAO.getFormule(newFormulaField.getText())==new Formule(newFormulaField.getText(),false,0)){
        			if(updateFormule(newFormulaField.getText(),true)){
        				newFormulaField.clear();
        				oldFormulaField.setText(FormuleDAO.getFormuleActive().getFormule());
        				Notifications.create()
        						.title("Succes")
        						.text("Nieuwe formule actief")
        						.darkStyle()
        						.position(Pos.TOP_CENTER)
        						.graphic(new ImageView(img))
        						.show();
        				
        			}
        			else createAlertBox("Oops, Something went wrong!",null,"Failed to set new formula!");
        		}
        		else{
        			if(insertFormule(newFormulaField.getText(),true)){
        				newFormulaField.clear();
        				oldFormulaField.setText(FormuleDAO.getFormuleActive().getFormule());
        				Notifications.create()
        						.title("Succes")
        						.text("Succesfully added the formula!")
        						.darkStyle()
        						.position(Pos.TOP_CENTER)
        						.graphic(new ImageView(img))
        						.show();
        			}
        			else createAlertBox("Oops, Something went wrong!",null,"Failed to set new formula!");
        		}
        	}
        	else createAlertBox("Foute formule",null,"De formule bevat onbekende characters!");
        }
        else createAlertBox("Ongeldige velden",null,"Het formule veld moet ingevuld worden.");
    }
    
    public boolean checkContent(String inhoud){
    	Pattern p= Pattern.compile("xyzXYZ+-*//0-9");
    	Matcher m=p.matcher(inhoud);
    	
    	return !m.matches();
    	
    }
}
