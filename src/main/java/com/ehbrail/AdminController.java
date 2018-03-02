package com.ehbrail;

import com.model.Login;
import com.model.Werknemer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by jorda on 25/10/2016.
 */

public class AdminController implements Initializable {
    private ResourceBundle language;
    private static Login login;
    public static Login getLogin() {
        return login;
    }
    public static void setLogin(Login login) {
        AdminController.login = login;
    }

    Werknemer werknemer;

    @FXML Label usernameAdm;

    public Werknemer getWerknemer() {
        return werknemer;
    }
    public void setWerknemer(Werknemer werknemer) {
        this.werknemer = werknemer;
    }


    public void setTopBar(Werknemer werknemer){
        //this.login = login;
        this.werknemer = werknemer;
        usernameAdm.setText(MessageFormat.format(this.language.getString("WelkomBericht"),werknemer.getVoornaam(), werknemer.getNaam(),login.getUsername(),login.getBevoegdheid()));
    }

    @FXML private Button logoutButton;
    @FXML private TabPane aTabPane;
    @FXML private Tab aEmployeeTab;
    @FXML private AEmployeeTabController aEmployeeTabPageController;
    @FXML private Tab aPasswordChangeTab;
    @FXML private APasswordChangeTabController aPasswordChangeTabController;
    @FXML private Tab aReportTab;
    @FXML private AReportTabController aReportTabController;
    @FXML private Tab aPrizeFormulaTab;
    @FXML private APrizeFormulaTabController aPrizeFormulaTabController;
    @FXML private Tab aReductionTab;
    @FXML private AReductionTabController aReductionTabController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        language = resources;
    }

    @FXML private void onClickLogOut(ActionEvent event) throws IOException {
        logoutButton.getScene().getWindow().hide();
        //meld dat je graag een garbage collection wilt doen.
        System.gc();
        SoftwareProject newLoginScreen = new SoftwareProject();
        newLoginScreen.createLoginScreen(new Stage());
    }
}
