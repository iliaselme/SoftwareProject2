package com.ehbrail;

import com.model.Login;
import com.model.VerlorenVoorwerp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;

import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import static com.database.LoginDAO.changePassbyLogin;
import static com.database.LoginDAO.getLoginByID;

/**
 * Created by jorda on 10/11/2016.
 */
public class WPasswordChangeTabController implements Initializable {
	private ResourceBundle language;
	@FXML
	Label accountLabel;
	@FXML
	PasswordField oldPasswordField;
	@FXML
	PasswordField newPasswordField;
	@FXML
	PasswordField copyNewPasswordField;
	@FXML
	Button changePassButton;
	@FXML
	private ImageView image;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		language = resources;
		accountLabel.setText(WerknemerController.getLogin().getUsername());
		image.setImage(new Image("/com/ehbrail/0.png"));
		newPasswordField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if (!newValue.isEmpty()) {
						image.setImage(sterkteWachtwoord());
					} else {
						image.setImage(new Image("/com/ehbrail/0.png"));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private void createAlertBox(String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}

	@FXML
	private void changePassAction(ActionEvent event) throws IOException {
		Image img = new Image("/com/ehbrail/checkmark.png");
		Login login = getLoginByID(WerknemerController.getLogin().getLogin_id());
		if (!oldPasswordField.getText().isEmpty() && !newPasswordField.getText().isEmpty()
				&& !copyNewPasswordField.getText().isEmpty()) {
			if (Login.verifyPassword(oldPasswordField.getText(), login.getPassword())) {
				if (getScore()>=4){
					if (newPasswordField.getText().equals(copyNewPasswordField.getText())) {
						String newHashPass = Login.createHash(newPasswordField.getText());
						if (changePassbyLogin(login, newHashPass)) {
							oldPasswordField.clear();
							newPasswordField.clear();
							copyNewPasswordField.clear();
							Notifications.create().title("Succes").text("Succesfully set a new password!").darkStyle()
									.position(Pos.TOP_CENTER).graphic(new ImageView(img)).show();
						} else
							createAlertBox(language.getString("oops"), null, language.getString("failNewPassword"));
					} else
						createAlertBox(language.getString("wrongPassword"), null,
								language.getString("passwordNotEqual"));
				}else
					createAlertBox(language.getString("failNewPassword"),null,language.getString("passwoordSterkte"));
			} else
				createAlertBox(language.getString("wrongPassword"), null, language.getString("passwordNotCorrect"));
		} else
			createAlertBox(language.getString("ongeldigeVelden"), null, language.getString("ongeldigeVelden"));
	}

	public Image sterkteWachtwoord() {
		Image img = new Image("/com/ehbrail/" + getScore() + ".png");
		return img;
	}

	public int getScore() {
		// gebaseerd op http://www.dreamincode.net/forums/topic/253950-java-password-checker/
		boolean lengthCheck = false;
		boolean lengthCheck2 = false;
		boolean upperCheck = false;
		boolean lowerCheck = false;
		boolean digitCheck = false;
		String password;

		password = newPasswordField.getText();

		int score = 0;

		for (int i = 0; i < password.length(); i++) {
			char s = password.charAt(i);

			if (Character.isUpperCase(s))
				upperCheck = true;
			if (Character.isLowerCase(s))
				lowerCheck = true;
			if (Character.isDigit(s))
				digitCheck = true;
			if (password.length() >= 6)
				lengthCheck = true;
			if (password.length() >= 12)
				lengthCheck2 = true;
		}
		if (upperCheck)
			score++;
		if (lowerCheck)
			score++;
		if (digitCheck)
			score++;
		if (lengthCheck)
			score++;
		if (lengthCheck2)
			score++;

		return score;
	}
}
