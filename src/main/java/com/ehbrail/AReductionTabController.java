package com.ehbrail;

import com.database.FormuleDAO;
import com.database.KortingDAO;
import com.model.Formule;
import com.model.Korting;
import com.model.Liveboard;
import com.model.Login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;

import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
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
 * 
 * http://docs.oracle.com/javafx/2/ui_controls/table-view.htm (gebruikt om table te editen)
 */
public class AReductionTabController implements Initializable {
    @FXML private TextField newKortingNaam;
    @FXML private TextArea newKortingBeschrijving;
    @FXML private TextField newKortingPercentage;
    @FXML private Button changeReductionButton;
    @FXML private TableView<Korting> tableKorting;
    @FXML private TableColumn<Korting, String> kortingNaam;
    @FXML private TableColumn<Korting, String> kortingBeschrijving;
    @FXML private TableColumn<Korting, Integer> kortingPercentage;
    @FXML private TableColumn<Korting, Boolean> kortingActief;
    private List<Korting> tussen;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	tableKorting.setEditable(true);
    	kortingNaam.setCellValueFactory(new PropertyValueFactory<Korting, String>("naam"));
    	kortingNaam.setCellFactory(TextFieldTableCell.forTableColumn());
    	kortingNaam.setOnEditCommit(
    		new EventHandler<CellEditEvent<Korting,String>>() {
    			@Override
    			public void handle(CellEditEvent<Korting,String> k){
    				((Korting) k.getTableView().getItems().get(
    						k.getTablePosition().getRow())
    						).setNaam(k.getNewValue());
    			}
    		}
    	);
    	
    	
    	kortingBeschrijving.setCellValueFactory(new PropertyValueFactory<Korting, String>("beschrijving"));
    	kortingBeschrijving.setCellFactory(TextFieldTableCell.forTableColumn());
    	kortingBeschrijving.setOnEditCommit(
        		new EventHandler<CellEditEvent<Korting,String>>() {
        			@Override
        			public void handle(CellEditEvent<Korting,String> k){
        				((Korting) k.getTableView().getItems().get(
        						k.getTablePosition().getRow())
        						).setBeschrijving(k.getNewValue());
        			}
        		}
        	);
    	
    	kortingPercentage.setCellValueFactory(new PropertyValueFactory<Korting, Integer>("percentage"));
    	kortingPercentage.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    	kortingPercentage.setOnEditCommit(
        		new EventHandler<CellEditEvent<Korting,Integer>>() {
        			@Override
        			public void handle(CellEditEvent<Korting,Integer> k){
        				((Korting) k.getTableView().getItems().get(
        						k.getTablePosition().getRow())
        						).setPercentage(k.getNewValue());
        			}
        		}
        	);
    	
    	kortingActief.setCellValueFactory(new PropertyValueFactory<Korting, Boolean>("actief"));
    	kortingActief.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
    	kortingActief.setOnEditCommit(
        		new EventHandler<CellEditEvent<Korting,Boolean>>() {
        			@Override
        			public void handle(CellEditEvent<Korting,Boolean> k){
        				((Korting) k.getTableView().getItems().get(
        						k.getTablePosition().getRow())
        						).setActief(k.getNewValue());
        			}
        		}
        	);
    	
    	tussen=KortingDAO.getKortingen();
    	ObservableList<Korting> data = FXCollections.observableArrayList();
    	for(Korting k:tussen){
    		data.add(new Korting(k.getKorting(),
    				k.getNaam(),
    				k.getBeschrijving(),
    				k.getPercentage(),
    				k.isActief()));
    	}
    	tableKorting.setItems(data);    
    	//tableKorting.getColumns().addAll(kortingNaam,kortingBeschrijving,kortingPercentage,kortingActief);
    }

    private void createAlertBox(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void changeReductionAction(ActionEvent event) throws IOException {
        Image img = new Image("/com/ehbrail/checkmark.png");
        Login login = getLoginByID(AdminController.getLogin().getLogin_id());
        if (!newKortingNaam.getText().isEmpty()&&!newKortingBeschrijving.getText().isEmpty()&&!newKortingPercentage.getText().isEmpty()){
        		if(KortingDAO.insertKorting(new Korting(newKortingNaam.getText(),newKortingBeschrijving.getText(),Integer.parseInt(newKortingPercentage.getText().replaceAll("[\\D]", "")),true))){ 
        					newKortingNaam.clear();
        					newKortingPercentage.clear();
        					newKortingBeschrijving.clear();
        					tussen=KortingDAO.getKortingen();
        					ObservableList<Korting> data = FXCollections.observableArrayList();
        					for(Korting k:tussen){
        			    		data.add(new Korting(k.getKorting(),
        			    				k.getNaam(),
        			    				k.getBeschrijving(),
        			    				k.getPercentage(),
        			    				k.isActief()));
        			    	}
        			    	tableKorting.setItems(data); 
        					Notifications.create()
        						.title("Succes")
        						.text("Nieuwe korting aangemaakt")
        						.darkStyle()
        						.position(Pos.TOP_CENTER)
        						.graphic(new ImageView(img))
        						.show();
        				
        		}
        		else createAlertBox("Oops, Something went wrong!",null,"Failed to set new korting!");
        }
        for(Korting k:tableKorting.getItems()){
        	for(Korting k2:tussen){
        		if(k2.getKorting()==k.getKorting()){
        			if(!k2.equals(k)){
        				k2.setNaam(k.getNaam());
        				k2.setBeschrijving(k.getBeschrijving());
        				k2.setPercentage(k.getPercentage());
        				k2.setActief(k.isActief());
        				if(KortingDAO.updateKorting(k2)){
        					Notifications.create()
        					.title("Succes")
        					.text("Korting aangepast")
        					.darkStyle()
        					.position(Pos.TOP_CENTER)
        					.graphic(new ImageView(img))
    						.show();
    			
        				}
        				else createAlertBox("Oops, Something went wrong!",null,"Failed to alter korting!");
        			}
        		}
        	}
        }
    }    
}
