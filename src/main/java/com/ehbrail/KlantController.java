package com.ehbrail;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javax.security.auth.callback.LanguageCallback;

import com.database.AdresDAO;
import com.database.KlantDAO;
import com.model.Adres;
import com.model.Klant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class KlantController implements Initializable {
		
	private ResourceBundle language;

	@FXML
	private TextField voornaamid;

	@FXML
	private TextField naamid;

	@FXML
	private DatePicker datepicker;

	@FXML
	private TextField gsmid;

	@FXML
	private TextField plaatsnaamid;

	@FXML
	private TextField postcodeid;

	@FXML
	private TextField straatid;

	@FXML
	private TextField huisnummerid;

	@FXML
	private TextField brievenbusid;

	@FXML
	private Button toevoegenButton;

	@FXML
	private Button cancelButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		language = resources;
		
	}
	
	
	@FXML
	void toevoegen(ActionEvent event) {
		
		
		
		//TODO controles aanpassen
		if (voornaamid.getText() != "" && naamid.getText() != "" && datepicker.getValue() != null
				&& gsmid.getText() != "" && plaatsnaamid.getText() != "" && postcodeid.getText() != ""
				&& straatid.getText() != "" && huisnummerid.getText() != "" && brievenbusid.getText() != "") {
			
			
			int postcode = Integer.parseInt(postcodeid.getText());
			int huisnummer = Integer.parseInt(huisnummerid.getText());
			
			Adres adres = new Adres(-1, plaatsnaamid.getText(), straatid.getText(), huisnummer, brievenbusid.getText(),
					postcode);
			/**
			AdresDAO a = new AdresDAO();
			a.insertAdres(adres);
			int adresid = a.getAdresId(adres);
			Klant klant = new Klant(adresid, datepicker.getValue(), gsmid.getText(), "", true, naamid.getText(),
					voornaamid.getText());
			KlantDAO.insertKlant(klant);
			 **/

			AdresDAO.insertAdres(adres);
			if (adres.getAdres_id() > 0) {
				Klant klant = new Klant(adres.getAdres_id(), datepicker.getValue(), gsmid.getText(), "", true, naamid.getText(),
						voornaamid.getText(), LocalDateTime.now(ZoneId.of("Europe/Brussels")));
				KlantDAO.insertKlant(klant);
			}
			clearVelden();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Information Alert");
			alert.setContentText(language.getString("klantToegevoegd"));
			alert.show();

		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle(language.getString("alertOngeldigeVelden"));
			alert.setHeaderText(null);
			alert.setContentText(language.getString("alertFieldsCorrect"));
			alert.show();
		}

	}

	public void clearVelden() {
		voornaamid.clear();
		naamid.clear();
		datepicker.setValue(null);
		gsmid.clear();
		plaatsnaamid.clear();
		postcodeid.clear();
		straatid.clear();
		huisnummerid.clear();
		brievenbusid.clear();
	}

	
}
