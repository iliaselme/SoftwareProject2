package com.ehbrail;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.database.AdresDAO;
import com.database.KlantDAO;
import com.database.VerlorenVoorwerpDAO;
import com.ibm.icu.text.MessageFormat;
import com.model.Adres;
import com.model.Klant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EditKlantController implements Initializable {

	private ResourceBundle language;

	@FXML
	private TextField filter;

	@FXML
	private TableView<Klant> tableview;

	@FXML
	private TableColumn<Klant, Integer> id;

	@FXML
	private TableColumn<Klant, Integer> adresid;

	@FXML
	private TableColumn<Klant, String> voornaamid;

	@FXML
	private TableColumn<Klant, String> achternaamid;

	@FXML
	private TableColumn<Klant, LocalDate> datumid;

	@FXML
	private TableColumn<Klant, String> nummerid;

	@FXML
	private TableColumn<Klant, String> commentaarid;

	@FXML
	private TextField voornaamText;

	@FXML
	private TextField naamText;

	@FXML
	private DatePicker datepicker;

	@FXML
	private TextField gsmText;

	@FXML
	private TextArea commentaarText;

	@FXML
	private TextField plaatsnaamText;

	@FXML
	private TextField postcodeText;

	@FXML
	private TextField straatText;

	@FXML
	private TextField huisnummerText;

	@FXML
	private TextField brievenbusText;

	@FXML
	private Button resetButton;

	@FXML
	private Button updateButton;

	@FXML
	private Label adresidHidden;

	@FXML
	private Label idHidden;

	@FXML
	private Button reloadButton;

	@FXML
	private TextField idText;
	
	@FXML
	private Button deleteButton;

	private ObservableList<Klant> data = FXCollections.observableArrayList(KlantDAO.getAll());;

	ArrayList<String> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		language = resources;

		tableview.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
		
		data = FXCollections.observableArrayList(KlantDAO.getAll());

		refresh();

		commentaarText.setWrapText(true);
		adresidHidden.setText("");

		id.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("klantid"));
		adresid.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("adresid"));
		voornaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("voornaam"));
		achternaamid.setCellValueFactory(new PropertyValueFactory<Klant, String>("naam"));
		datumid.setCellValueFactory(new PropertyValueFactory<Klant, LocalDate>("geboortedatum"));
		nummerid.setCellValueFactory(new PropertyValueFactory<Klant, String>("gsmnummer"));
		commentaarid.setCellValueFactory(new PropertyValueFactory<Klant, String>("commentaar"));

		FilteredList<Klant> filteredData = new FilteredList<Klant>(data, p -> true);
		filter.textProperty().addListener(((observable, oldValue, newValue) -> {
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
	void reload(ActionEvent event) {
		refresh();

	}

	@FXML
	void reset(ActionEvent event) {

		clear();

	}

	@FXML
	void update(ActionEvent event) {

		LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Europe/Brussels"));
		LocalDate datum = datepicker.getValue();

		if (voornaamText.getText().isEmpty() || naamText.getText().isEmpty() || plaatsnaamText.getText().isEmpty()
				|| postcodeText.getText().isEmpty() || straatText.getText().isEmpty()
				|| huisnummerText.getText().isEmpty() || brievenbusText.getText().isEmpty()
				|| gsmText.getText().isEmpty() || (datepicker.getValue() == null || datum.isAfter(todayLocalDate))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(null);
			alert.setContentText(language.getString("alertFieldsCorrect") + "\n" + language.getString("controleDate"));
			alert.showAndWait();

		} else {
			int klantAdresId = Integer.valueOf(adresidHidden.getText());
			int klantid = Integer.valueOf(idHidden.getText());
			Klant klant = new Klant(klantid, klantAdresId, datepicker.getValue(), gsmText.getText(),
					commentaarText.getText(), true, naamText.getText(), voornaamText.getText(), LocalDateTime.now(ZoneId.of("Europe/Brussels")));

			int huisnr = Integer.valueOf(huisnummerText.getText());
			int postcode = Integer.valueOf(postcodeText.getText());
			Adres adres = new Adres(klantAdresId, plaatsnaamText.getText(), straatText.getText(), huisnr,
					brievenbusText.getText(), postcode);

			if (KlantDAO.updateVoorwerp(klant) == true && AdresDAO.updateVoorwerp(adres) == true) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(language.getString("klantUpdate"));
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText(language.getString("ietsFout"));
				alert.showAndWait();
			}

			refresh();
			clear();

		}

	}

	@FXML
	void selectInformaties(MouseEvent event) {
		if (event.getClickCount() == 2 && tableview.getSelectionModel().getSelectedItem() != null) {

			Klant klant = tableview.getSelectionModel().getSelectedItem();
			
			idText.setText(String.valueOf(klant.getKlantid()));
		
			voornaamText.setText(klant.getVoornaam());
			naamText.setText(klant.getNaam());
			datepicker.setValue(klant.getGeboortedatum());
			gsmText.setText(klant.getGsmnummer());
			commentaarText.setText(klant.getCommentaar());
			adresidHidden.setText(String.valueOf(klant.getAdresid()));
			idHidden.setText(String.valueOf(klant.getKlantid()));

			AdresDAO adresDAO = new AdresDAO();
			Adres adres = adresDAO.getAdres(klant.getAdresid());
			if (adres != null) {
				plaatsnaamText.setText(adres.getPlaatsnaam());
				String postcode = String.valueOf(adres.getPostcode());
				postcodeText.setText(postcode);
				straatText.setText(adres.getStraat());
				String huisnr = String.valueOf(adres.getHuisnr());
				huisnummerText.setText(huisnr);
				brievenbusText.setText(adres.getBrievenbus());

			}
		}

	}

	public void refresh() {
		data = FXCollections.observableArrayList(KlantDAO.getAll());
		tableview.setItems(data);
	}

	public void clear() {
		adresidHidden.setText("");
		idHidden.setText("");
		voornaamText.clear();
		naamText.clear();
		datepicker.setValue(null);
		gsmText.clear();
		commentaarText.clear();
		plaatsnaamText.clear();
		postcodeText.clear();
		straatText.clear();
		huisnummerText.clear();
		brievenbusText.clear();
		filter.clear();
		voornaamText.requestFocus();
		idText.clear();
		tableview.getSelectionModel().clearSelection();
		reloadButton.setDefaultButton(false);
		resetButton.setDefaultButton(false);
		updateButton.setDefaultButton(false);
		deleteButton.setDefaultButton(false);
		
	}

	@FXML
	void delete(ActionEvent event) {
		try {
			int id = Integer.parseInt(idText.getText());
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText(MessageFormat.format(language.getString("alertVoorDelete"), id));
			Optional<ButtonType> action = alert.showAndWait();
			if (action.get() == ButtonType.OK) {
				KlantDAO.deleteKlant(id);
			}
			idText.clear();

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error");
			alert.setContentText(language.getString("alertTextVeld"));
			alert.show();
		}
		refresh();
		clear();

	}

}
