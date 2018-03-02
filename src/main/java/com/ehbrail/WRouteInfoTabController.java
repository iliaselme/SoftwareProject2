package com.ehbrail;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import okhttp3.Response;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.model.Routes.*;

import static com.ehbrail.ApiCalls.getExtendedIRailRoute;
import static com.ehbrail.ApiCalls.getIRailRoute;
import static com.ehbrail.WerknemerController.toLocalDateTime;

/**
 * Created by jorda on 28/10/2016.
 *
 * Code van spinners komt van: http://stackoverflow.com/questions/30886746/how-can-i-set-3-values-in-spinner
 */
public class WRouteInfoTabController implements Initializable {
    ArrayList<String> list;

    @FXML private Button switchStationsButton;
    @FXML private Button planRouteButton;
    @FXML private Button getLastRouteButton;
    @FXML private Button getFirstRouteButton;
    @FXML private Button getLaterRouteButton;
    @FXML private Button getEarlierRouteButton;
    @FXML private TextField vanField;
    @FXML private TextField naarField;
    @FXML private TreeView<String> treeRoute;
    @FXML private Label errorLabel;
    @FXML private DatePicker dateText;
    @FXML private RadioButton aankomstRadio;
    @FXML private RadioButton vertrekRadio;
    @FXML private ToggleGroup timeSel;
    private String radioText;

    @FXML private Spinner<Integer> hourSpinner ;
    @FXML private Spinner<Integer> minuteSpinner ;
    @FXML Label timeSpinnerlabel;

    private ReadOnlyObjectWrapper<Duration> time = new ReadOnlyObjectWrapper<>();
    private String timeSpinnerText;
    private long timeSpinnerSeconds;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //------------INIT--------------------------//
        list = LoginController.getList();
        TextFields.bindAutoCompletion(vanField,list);
        TextFields.bindAutoCompletion(naarField,list);
        dateText.setValue(LocalDate.now());
        LocalTime localTime = LocalTime.now();
        hourSpinner.getValueFactory().setValue(localTime.getHour());
        minuteSpinner.getValueFactory().setValue(localTime.getMinute());
        //------------TimeSpinner--------------------------//
        minuteSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue.intValue() == 59 && newValue.intValue() == 0) {
                hourSpinner.increment();
            }
            if (oldValue.intValue() == 0 && newValue.intValue() == 59) {
                hourSpinner.decrement();
            }
        });

        time.bind(Bindings.createObjectBinding(this::computeTime, hourSpinner.valueProperty(),
                minuteSpinner.valueProperty()));

        //--Label binden aan de veranderende timespinners--/
        timeSpinnerlabel.textProperty().bind(Bindings.createStringBinding(() -> {
            long s = getTime().getSeconds() ;
            String s1 = String.format("%02d:%02d", s / 3600, (s / 60) % 60);
            timeSpinnerText = String.format("%02d%02d", s / 3600, (s / 60) % 60);
            timeSpinnerSeconds = s;
            return s1;
        }, timeProperty()));


//---------------Radiobutton---------------------------------//
        timeSel.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (timeSel.getSelectedToggle() != null) {
                    RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle(); // Cast object to radio button
                    //System.out.println("Selected Radio Button - "+chk.getText());
                    if (chk.getText().equals("Vertrek")){radioText = "depart";}
                    else radioText = "arrive";
                }
            }
        });
        vertrekRadio.setSelected(true);
//--------------------------------------------------------//
    }

    public ReadOnlyObjectProperty<Duration> timeProperty() {
        return time.getReadOnlyProperty() ;
    }
    public Duration getTime() {
        return timeProperty().get();
    }
    private Duration computeTime() {
        int minutes = minuteSpinner.getValue();
        int hours = hourSpinner.getValue();
        int totalSeconds = (hours * 60 + minutes) * 60;
        return Duration.ofSeconds(totalSeconds);
    }

    private String dateFormat (LocalDate date){
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyy");
        String format_1=date.format(formatDate);
        //System.out.println("Current date in format '': "+format_1);
        return format_1;
    }
   @FXML private void switchStations(ActionEvent event) throws IOException {
        try {
            String temp = vanField.getText();
            vanField.setText(naarField.getText());
            naarField.setText(temp);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

/**
    @FXML
    private void onClickplanRoutea(ActionEvent event) throws IOException {
        if (vanField.getText().isEmpty() || naarField.getText().isEmpty()) errorLabel.setText("Gelieve een vertrek EN aankomst station mee te geven!");
        else {
            try {
                Response response = getIRailRoute(vanField.getText(), naarField.getText());
                if (response.isSuccessful()) {
                    String jsonString = response.body().string();
                    ObjectMapper mapper = JsonFactory.create();
                    IrailRoute irailRoute = mapper.readValue(jsonString, IrailRoute.class);
                    TreeItem<String> rootItem = new TreeItem<String>("Routes");
                    createTreeItems(rootItem, irailRoute);
                    rootItem.setExpanded(true);
                    treeRoute.setRoot(rootItem);
                    errorLabel.setText(" ");
                } else {
                    errorLabel.setText("Error, responsecode = " + response.networkResponse().code() + ", With message: " + response.networkResponse().message());
                    TreeItem<String> rootItem = new TreeItem<String>("Routes");
                    treeRoute.setRoot(rootItem);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
**/

private void createExtendedIRailRoute(String time, String radioText){
    if (vanField.getText().isEmpty() || naarField.getText().isEmpty()) {
        errorLabel.setText("Gelieve een vertrek EN aankomst station mee te geven!");
        TreeItem<String> rootItem = new TreeItem<String>("Routes");
        treeRoute.setRoot(rootItem);
    }
    else {
        try (Response response = getExtendedIRailRoute(vanField.getText(), naarField.getText(),dateFormat(dateText.getValue()),time,radioText)) {
            System.out.println(response.networkResponse());
            if (response.isSuccessful()) {
                String jsonString = response.body().string();
                ObjectMapper mapper = JsonFactory.create();
                IrailRoute irailRoute = mapper.readValue(jsonString, IrailRoute.class);
                TreeItem<String> rootItem = new TreeItem<String>("Routes");
                createTreeItems(rootItem, irailRoute);
                rootItem.setExpanded(true);
                treeRoute.setRoot(rootItem);
                errorLabel.setText(" ");
            } else {
                errorLabel.setText("Error, responsecode = " + response.networkResponse().code() + ", With message: " + response.networkResponse().message());
                TreeItem<String> rootItem = new TreeItem<String>("Routes");
                treeRoute.setRoot(rootItem);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

    @FXML private void onClickGetEarlierRoute(ActionEvent event) throws IOException {
        hourSpinner.getValueFactory().setValue(hourSpinner.getValue()-1);
        createExtendedIRailRoute(timeSpinnerText,radioText);
    }

    @FXML private void onClickGetLaterRoute(ActionEvent event) throws IOException {
        hourSpinner.getValueFactory().setValue(hourSpinner.getValue()+1);
        //long s;
        //s = timeSpinnerSeconds + 3600;
        //timeSpinnerText = String.format("%02d%02d", s / 3600, (s / 60) % 60);
        createExtendedIRailRoute(timeSpinnerText,radioText);
    }
    @FXML private void onClickGetFirstRoute(ActionEvent event) throws IOException {
        createExtendedIRailRoute("0000","depart");
    }
    @FXML private void onClickGetLastRoute(ActionEvent event) throws IOException {
        createExtendedIRailRoute("2359","arrive");
    }
    @FXML private void onClickplanRoute(ActionEvent event) throws IOException {
    createExtendedIRailRoute(timeSpinnerText,radioText);
    }

    private void createTreeItems(TreeItem<String> rootItem, IrailRoute irailRoute) throws IOException {
        TreeItem<String> rootItem1 = null;
        for (Connection connection : irailRoute.getConnection()) {
            String departureTimeString = connection.getDeparture().getTime();
            String arrivalTimeString = connection.getArrival().getTime();
            //LocalDateTime departureTime = toLocalDateTime(departureTimeString);
            //LocalDateTime arrivalTime = toLocalDateTime(arrivalTimeString);
            String duration = connection.getDuration();
            String departureStation = connection.getDeparture().getStation();
            String arrivalStation = connection.getArrival().getStation();
            String transfers = " ";

            if (connection.getVias() != null) {
                transfers = "  -  "+connection.getVias().getNumber()+"  transfers";
            }

            rootItem1 = new TreeItem<String>(toLocalDateTime(departureTimeString).toLocalTime() + " > " + toLocalDateTime(arrivalTimeString).toLocalTime() + "\n" + (Integer.parseInt(duration) / 60) + " minuten"+transfers);
            TreeItem<String> line1 = new TreeItem<String>(toLocalDateTime(departureTimeString).toLocalTime() + "\t" + departureStation + "\t Delay: " + (Integer.parseInt(connection.getDeparture().getDelay()) / 60) + " minuten\t " + connection.getDeparture().getPlatform());
            TreeItem<String> line2 = new TreeItem<String>(connection.getDeparture().getDirection().getName() + " " + connection.getDeparture().getVehicle());
            rootItem1.getChildren().add(line1);
            rootItem1.getChildren().add(line2);

            if (connection.getVias() != null) {
                for (Via via : connection.getVias().getVia()) {
                    TreeItem<String> line4 = new TreeItem<String>(toLocalDateTime(via.getArrival().getTime()).toLocalTime() + " " + via.getStationinfo().getName() + " " + via.getArrival().getPlatform());
                    TreeItem<String> line5 = new TreeItem<String>(Integer.parseInt(via.getTimeBetween()) / 60 + " minutes before departure from platform  " + via.getArrival().getPlatform() + " to platform " + via.getDeparture().getPlatform());
                    TreeItem<String> line6 = new TreeItem<String>(toLocalDateTime(via.getDeparture().getTime()).toLocalTime() + " " + via.getStationinfo().getName() + " " + via.getDeparture().getPlatform());
                    rootItem1.getChildren().add(line4);
                    rootItem1.getChildren().add(line5);
                    rootItem1.getChildren().add(line6);
                }
                TreeItem<String> line7 = new TreeItem<String>(connection.getArrival().getDirection().getName() + " " + connection.getArrival().getVehicle());
                rootItem1.getChildren().add(line7);
            }
            TreeItem<String> line8 = new TreeItem<String>(toLocalDateTime(arrivalTimeString).toLocalTime() + "\t" + arrivalStation +"\t Delay: " + (Integer.parseInt(connection.getArrival().getDelay()) / 60) + " minuten\t " + connection.getArrival().getPlatform());
            rootItem1.getChildren().add(line8);
            rootItem.getChildren().add(rootItem1);
        }
    }

}
