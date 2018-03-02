package com.ehbrail;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import com.database.AbonnementDAO;
import com.database.KlantDAO;
import com.model.Abonnement;
import com.model.Klant;
import com.model.VerlorenVoorwerp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Created by Ilias El Mesaoudi on 14/11/2016.
 */

public class WAbonnementTabController implements Initializable {

	private ResourceBundle language;
	ObservableList<Klant> lijstKlanten = FXCollections.observableArrayList(KlantDAO.getAll());

	ObservableList<String> lijstReductie = FXCollections.observableArrayList("Senior", "18-25", "-18");
	ArrayList<String> list;

	@FXML
	private TextField vanField;

	@FXML
	private Button switchStationsButton;

	@FXML
	private RadioButton ritRadioButton;

	@FXML
	private RadioButton belgieRadioButton;

	@FXML
	private DatePicker datepickerBegin;

	@FXML
	private TextField naarField;

	@FXML
	private DatePicker datepickerEinde;

	@FXML
	private RadioButton eersteRadioButton;

	@FXML
	private RadioButton tweedeRadioButton;

	@FXML
	private ChoiceBox<String> kortingenid;

	@FXML
	private Label prijsid;

	@FXML
	private Button koopbutton;

	@FXML
	private Button berekenPrijsId;

	@FXML
	private ToggleGroup soortAbonnement;

	@FXML
	private ToggleGroup klasse;

	@FXML
	private TextField textButton;

	@FXML
	private TableView<Klant> tableview;

	@FXML
	private TableColumn<Klant, Integer> id;

	@FXML
	private TableColumn<Klant, String> voornaamid;

	@FXML
	private TableColumn<Klant, String> achternaamid;

	@FXML
	private Label klantidOnclick;

	@FXML
	private Button resetButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableview.setItems(lijstKlanten);

		language = resources;

		id.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("klantid"));
		voornaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("voornaam"));
		achternaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("naam"));

		kortingenid.setItems(lijstReductie);
		list = LoginController.getList();
		TextFields.bindAutoCompletion(vanField, list);
		TextFields.bindAutoCompletion(naarField, list);

		ritRadioButton.setSelected(true);
		tweedeRadioButton.setSelected(true);

		FilteredList<Klant> filteredData = new FilteredList<Klant>(lijstKlanten, p -> true);
		textButton.textProperty().addListener(((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Klant -> {
				if ((newValue == null) || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (Klant.getNaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (Klant.getVoornaam().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				return false;
			});
		}));

		SortedList<Klant> sortedList = new SortedList<Klant>(filteredData);
		sortedList.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(sortedList);

	}

	@FXML
	private void switchStations(ActionEvent event) throws IOException {
		try {
			String temp = vanField.getText();
			vanField.setText(naarField.getText());
			naarField.setText(temp);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	void HeelBelgie(ActionEvent event) {
		vanField.setDisable(true);
		naarField.setDisable(true);
		vanField.clear();
		naarField.clear();
	}

	@FXML
	void selecteerRit(ActionEvent event) {
		vanField.setDisable(false);
		naarField.setDisable(false);
	}

	@FXML
	void koopAbonnement(ActionEvent event) {
		String van = null, naar = null;
		int klasse = 0;
		LocalDate begin;
		LocalDate einde;
		double prijs = 300;
		LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Europe/Brussels"));
		begin = datepickerBegin.getValue();
		einde = datepickerEinde.getValue();

		if (ritRadioButton.isSelected()) {
			van = vanField.getText();
			naar = naarField.getText();
		}
		if (belgieRadioButton.isSelected()) {
			van = "belgie";
			naar = "Belguim";
		}


		if (van == null && naar == null && van.equals(naar) || klantidOnclick.getText() == "" || controleerVanField()
				|| controleerNaarField() || begin == null || vanField.getText().isEmpty()
				&& naarField.getText().isEmpty() && !belgieRadioButton.isSelected() || begin.isBefore(todayLocalDate)
				|| (ritRadioButton.isSelected() && (einde == null || einde.isBefore(begin)))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText(language.getString("giveStation") + language.getString("controleDate")
					+ language.getString("controleNietZelfdeStation"));

			alert.showAndWait();

		} else {

			int klantId = Integer.parseInt(klantidOnclick.getText());

			if (eersteRadioButton.isSelected()) {
				klasse = 1;
			} else if (tweedeRadioButton.isSelected()) {
				klasse = 2;
			}

			if (kortingenid.getValue() == "Senior") {
				prijs = prijs - 50;
			} else if (kortingenid.getValue() == "18-25") {
				prijs = prijs - 80;
			} else if (kortingenid.getValue() == "-18") {
				prijs = prijs - 150;
			}

			AbonnementDAO.writeAbonnement(new Abonnement(1, klantId , WerknemerController.getLogin().getMedewerker_id(),
					klasse, 1, prijs, null, van, naar, begin, einde, "Brussel", 1, LocalDateTime.now(ZoneId.of("Europe/Brussels"))));

			clearVelden();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Information Alert");
			alert.setContentText(language.getString("abonnementCreated"));
			alert.show();

		}

	}

	@FXML
	void prijsBerekening(ActionEvent event) {
		double prijs = 300;
		if (kortingenid.getValue() == "Senior") {
			prijs = prijs - 50;
		} else if (kortingenid.getValue() == "18-25") {
			prijs = prijs - 80;
		} else if (kortingenid.getValue() == "-18") {
			prijs = prijs - 150;
		}

		String totaal = String.valueOf(prijs);
		prijsid.setText(totaal);

	}

	@FXML
	void selectInformaties(MouseEvent event) {
		if (event.getClickCount() == 2 && tableview.getSelectionModel().getSelectedItem() != null) {

			Klant klant = tableview.getSelectionModel().getSelectedItem();
			String str = Integer.toString(klant.getKlantid());
			klantidOnclick.setText(str);

		}
	}

	public void clearVelden() {

		vanField.clear();
		naarField.clear();		
		ritRadioButton.setSelected(true);
		belgieRadioButton.setSelected(false);
		eersteRadioButton.setSelected(false);
		tweedeRadioButton.setSelected(true);
		datepickerBegin.setValue(null);
		datepickerEinde.setValue(null);
		prijsid.setText(null);
		kortingenid.setValue(null);
		tableview.setItems(lijstKlanten);
		klantidOnclick.setText("");


	}

	public boolean controleerVanField() {
		list = LoginController.getList();

		if (belgieRadioButton.isSelected()) {
			return false;
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(vanField.getText())) {
				return false;
			}
		}

		return true;
	}

	public boolean controleerNaarField() {
		list = LoginController.getList();

		if (belgieRadioButton.isSelected()) {
			return false;
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(naarField.getText())) {
				return false;
			}
		}

		return true;
	}

	@FXML
	void reset(ActionEvent event) {
		clearVelden();		
	}

}
