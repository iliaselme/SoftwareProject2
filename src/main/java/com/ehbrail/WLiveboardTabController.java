package com.ehbrail;

import com.model.Liveboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import okhttp3.Response;
import org.controlsfx.control.textfield.TextFields;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.ehbrail.ApiCalls.getIRailLiveboard;
import static com.ehbrail.WerknemerController.toLocalDateTime;

/**
 * Created by jorda on 7/11/2016.
 */
public class WLiveboardTabController implements Initializable {
	private ResourceBundle language;
    private ArrayList<String> list;
    @FXML Label errorLabel;
    @FXML private Button searchButton;
    @FXML private TextField stationField;
    @FXML Label totalLabel;

    @FXML private TableView<Liveboard> tableView;
    @FXML private TableColumn<Liveboard, String> platform;
    @FXML private TableColumn<Liveboard, String> station;
    @FXML private TableColumn<Liveboard,LocalDateTime> departureTime;
    @FXML private TableColumn<Liveboard, Integer> delay;
    @FXML private TableColumn<Liveboard, String> vehicle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	language = resources;
    	
    	tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        list = LoginController.getList();
        TextFields.bindAutoCompletion(stationField,list);

        platform.setCellValueFactory(new PropertyValueFactory<Liveboard, String>("platform"));
        station.setCellValueFactory(new PropertyValueFactory<Liveboard, String>("station"));
        departureTime.setCellValueFactory(new PropertyValueFactory<Liveboard, LocalDateTime>("departureTime"));
        delay.setCellValueFactory(new PropertyValueFactory<Liveboard,Integer>("delay"));
        vehicle.setCellValueFactory(new PropertyValueFactory<Liveboard, String>("vehicle"));
    }

    @FXML
    private void searchAction(ActionEvent event) throws IOException {
    	boolean controleStation = true;
		
		if (list.contains(stationField.getText())) {
			controleStation = true;
		}else {
			controleStation = false;
		}
    	
        if (stationField.getText().isEmpty() || controleStation == false){
            errorLabel.setText(language.getString("giveStation"));
            tableView.getItems().clear();
        }
        else {
            try (Response response = getIRailLiveboard(stationField.getText())) {
                errorLabel.setText(" ");
                totalLabel.setText(" ");
                if (response.isSuccessful()){
                    String liveboardResponse = response.body().string();
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(new InputSource(new StringReader(liveboardResponse)));

                    List<Node> nodes = document.selectNodes("liveboard/departures/departure[@id]");
                    //Node station = document.selectSingleNode("liveboard/station");
                    //System.out.println(station.valueOf("@standardname"));
                    Node total = document.selectSingleNode("liveboard/departures");
                    if (total != null){
                	   totalLabel.setText(total.valueOf("@number"));
                	}
                    else 
                    	errorLabel.setText("Er werd geen data gevonden voor dit station.");
                    ObservableList<Liveboard> data = FXCollections.observableArrayList();
                    for (Node node : nodes) {
                        data.add(new Liveboard(node.selectSingleNode("platform").getText(),
                                node.selectSingleNode("station").getText(),
                                toLocalDateTime(node.selectSingleNode("time").getText()),
                                (Integer.parseInt(node.valueOf("@delay"))/60),
                                node.selectSingleNode("vehicle").getText()));

                       // System.out.println(" delay: "+ (Integer.parseInt(node.valueOf("@delay"))/60)+" minute, canceled: "+node.valueOf("@canceled") );
                    }
                    tableView.setItems(data);
                }
                else {
                    errorLabel.setText("Error occured, response code: " + response.code() + ", message:" + response.message());
                    totalLabel.setText(" ");
                    ObservableList<Liveboard> data = FXCollections.observableArrayList();
                    data.clear();
                    tableView.setItems(data);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
