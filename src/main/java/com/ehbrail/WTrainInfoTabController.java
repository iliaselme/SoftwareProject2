package com.ehbrail;

import com.model.TrainInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import okhttp3.Response;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javax.security.auth.callback.LanguageCallback;

import static com.ehbrail.ApiCalls.getTrainInfoByID;
import static com.ehbrail.WerknemerController.convertISO8601;

/**
 * Created by jorda on 28/10/2016.
 */
public class WTrainInfoTabController implements Initializable {
	
	private ResourceBundle language;

    @FXML
    Label errorRequestLabel;
    @FXML
    private Label totalStops;
    @FXML
    private Button search;
    @FXML
    private TextField treinID;

    @FXML private TableView<TrainInfo> tableView;
    @FXML private TableColumn<TrainInfo,Integer> stopID;
    @FXML private TableColumn<TrainInfo, String> station;
    @FXML private TableColumn<TrainInfo, LocalDateTime> time;
    @FXML private TableColumn<TrainInfo, String> platform;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	language = resources;
    	tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	
        stopID.setCellValueFactory(new PropertyValueFactory<TrainInfo, Integer>("stopID"));
        station.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("station"));
        time.setCellValueFactory(new PropertyValueFactory<TrainInfo, LocalDateTime>("time"));
        platform.setCellValueFactory(new PropertyValueFactory<TrainInfo, String>("platform"));
    }

    @FXML
    private void searchAction(ActionEvent event) throws IOException {
        if (treinID.getText().isEmpty()){
            errorRequestLabel.setText(language.getString("EnterTrainId"));
            tableView.getItems().clear();
        }
        else {
            // Staat in try-with-resources. Je moet altijd zorgen dat de Response gesloten wordt nadat je ermee klaar bent.
            try (Response response = getTrainInfoByID(treinID.getText())) {
                //vb treinID = IC545    S23671
                errorRequestLabel.setText("");

                if (response.isSuccessful()) {
                    String trainResponse = response.body().string();
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(new InputSource(new StringReader(trainResponse)));

                    List<Node> nodes = document.selectNodes("vehicleinformation/stops/stop[@id]");
                    Node totalStopNode = document.selectSingleNode("vehicleinformation/stops[@number]");
                    if (totalStopNode != null)
                    {totalStops.setText("Total stops: " + totalStopNode.valueOf("@number"));}
                    else errorRequestLabel.setText(language.getString("NoDataForThisTrain"));


                    ObservableList<TrainInfo> data = FXCollections.observableArrayList();
                    for (Node node : nodes) {
                        data.add(new TrainInfo(Integer.parseInt(node.valueOf("@id")),
                                node.selectSingleNode("station").getText(),
                                convertISO8601(node.selectSingleNode("time").valueOf("@formatted")),
                                node.selectSingleNode("platform").getText()
                        ));
                    }
                    tableView.setItems(data);
                } else {
                    errorRequestLabel.setText("Error occured, response code: " + response.code() + ", message:" + response.message());
                    totalStops.setText(" ");
                    ObservableList<TrainInfo> data = FXCollections.observableArrayList();
                    data.clear();
                    tableView.setItems(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}