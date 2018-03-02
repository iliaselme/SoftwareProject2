package com.ehbrail;
import com.model.Formule;
import com.model.Korting;
import com.model.Station;
import com.model.Ticket;
import com.model.Routes.Stationinfo;

import static com.ehbrail.ApiCalls.getIRailRoute;
import static com.ehbrail.ApiCalls.getIRailRouteXML;
import net.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import java.util.*;

import javafx.embed.swing.SwingNode;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import okhttp3.Response;

import org.controlsfx.control.textfield.TextFields;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.database.DataSource;
import com.database.FormuleDAO;
import com.database.KortingDAO;
import com.database.TicketDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * 
 * @author Pieter & Thijs
 *
 */

public class WTicketTabController implements Initializable {

	ArrayList<String> list;
	List<Korting> kortingen;
	private ResourceBundle language;
	   @FXML private TextField vanField;
	   @FXML private TextField naarField;
	   @FXML private Button switchStationsButton;
	   @FXML private Button koopTicketButton;
	   @FXML private RadioButton heenEnTerugRadio;
	   @FXML private ToggleGroup heenTerug;
	   @FXML private RadioButton heenRadio;
	   @FXML private DatePicker datumHeenDatePicker;
	   @FXML private RadioButton heenVertrekRadio;
	   @FXML private ToggleGroup heenVertrekAankomst;
	   @FXML private RadioButton heenAankomstRadio;
	   @FXML private Pane painTerug;
	   @FXML private DatePicker datumTerugDatePicker;
	   @FXML private RadioButton terugVertrekRadio;
	   @FXML private ToggleGroup terugVertrekAankomst;
	   @FXML private RadioButton terugAankomstRadio;
	   @FXML private RadioButton eersteKlasseRadio;
	   @FXML private ToggleGroup klasse;
	   @FXML private RadioButton tweedeKlasseRadio;
	   @FXML private Label terugLabel;
	   @FXML private ComboBox kortingCombo;

	   @FXML private Button resetTicketButton;


	@FXML
	void showPaneTerug(ActionEvent event) {
		painTerug.setVisible(false);
	}
	
    @FXML
    void showPaneHeenTerug(ActionEvent event) {
	    painTerug.setVisible(true);
    }
	
	@FXML
    void onClickResetTicket(ActionEvent event) {
    	vanField.clear();
    	naarField.clear();
    	datumHeenDatePicker.setValue(null);
    	datumTerugDatePicker.setValue(null);
    	tweedeKlasseRadio.setSelected(true);
    	heenVertrekRadio.setSelected(true);
    	terugVertrekRadio.setSelected(true);
    	heenRadio.setSelected(true);
    	painTerug.setVisible(false);
    }
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		language = resources;
		
		list = LoginController.getList();
		kortingen=KortingDAO.getActieveKortingen();
		TextFields.bindAutoCompletion(vanField, list);
		TextFields.bindAutoCompletion(naarField, list);
		for(Korting k:kortingen){
			kortingCombo.getItems().addAll(k);
		}
		
	}

	@FXML
	void onClickKoopTicket(ActionEvent event) {
		String vertrekStation = vanField.getText();
		String eindStation = naarField.getText();
		Korting korting=(Korting) kortingCombo.getValue();
		int type, klasse, heen, terug;
		LocalDate datumHeen = datumHeenDatePicker.getValue();
		LocalDate datumTerug = datumTerugDatePicker.getValue();
		LocalDateTime datumAankoop = LocalDateTime.now(ZoneId.of("Europe/Brussels"));
		LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Europe/Brussels"));
		Formule formule = FormuleDAO.getFormuleActive();
		list = LoginController.getList();
		/*
		 * Voorbeeld localDate, LocaldateTime (present) LocalDate todayLocalDate
		 * = LocalDate.now(ZoneId.of( "Europe/Brussels" ) ); LocalDateTime
		 * todayLocalDateTime = LocalDateTime.now(ZoneId.of( "Europe/Brussels"
		 * ));
		 */
    	if (controleerVanField() == false || vertrekStation.equals(eindStation) || controleerNaarField() == false || vertrekStation.isEmpty() || eindStation.isEmpty() ){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setHeaderText(null);
    		alert.setContentText("FOUTIEVE STATIONS");
    		alert.showAndWait();
    	} else {
    		if(heenRadio.isSelected()){
        		type = 0; //Indien heen reis
        		datumTerug = datumHeen;
        	} else{
        		type = 1; //Indien heen en terug reis
        	}
    		if(eersteKlasseRadio.isSelected()){
        		klasse = 1; //Indien eerste klasse
        	} else {
        		klasse = 2; // indien tweede klasse
        	} 
        	if(heenVertrekRadio.isSelected()){
        		heen = 0;
        	} else {
        		heen = 1;
        	} 
        	if(terugVertrekRadio.isSelected()){
        		terug = 0;
        	} else{
        		terug = 1;
        	}
        	
        	double prijs=berekenPrijs(vertrekStation, eindStation);
        	prijs*=((double)(100-korting.getPercentage())/(double)100);
        	Ticket ticket = new Ticket(vertrekStation,eindStation,1,klasse,type,prijs,datumAankoop,datumHeen,datumTerug,WerknemerController.getLogin().getMedewerker_id(),korting.getKorting(),formule.getFormuleId());
        	
        	TicketDAO.writeTicket(ticket);

			createPDF(ticket,language);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(language.getString("ticketAangemaakt"));
			alert.showAndWait();

			System.out.println(ticket.toString());
			vanField.clear();
			naarField.clear();
			datumHeenDatePicker.setValue(null);
			datumTerugDatePicker.setValue(null);
			tweedeKlasseRadio.setSelected(true);
			heenVertrekRadio.setSelected(true);
			terugVertrekRadio.setSelected(true);
			heenRadio.setSelected(true);
			painTerug.setVisible(false);
			kortingCombo.setValue(null);
		}
	}

	public boolean controleerVanField() {
		list = LoginController.getList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(vanField.getText())) {
				return true;
			}
		}

		return false;
	}
   
    public boolean controleerNaarField(){
    	list = LoginController.getList();
    	for (int i = 0; i < list.size();i++){
    		if(list.get(i).equals(naarField.getText())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    private double berekenAfstand(String vertrekStation, String eindStation){
    	Station aankomst=new Station(), vertrek= new Station();
    	try (Response resp=getIRailRouteXML(vertrekStation, eindStation)){
    		if (resp.isSuccessful()){
                String routeResponse = resp.body().string();
                SAXReader reader = new SAXReader();
                Document document = reader.read(new InputSource(new StringReader(routeResponse)));

                org.dom4j.Node startNode = document.selectSingleNode("//connection/departure/station");
                org.dom4j.Node eindNode = document.selectSingleNode("//connection/arrival/station");
                if(startNode==null || eindNode==null){
                	//label om error te weergeven als de stations niet teruggevonden worden.
                }
                else{
                	vertrek.setLatitude(Double.parseDouble(startNode.valueOf("@locationX")));
                	vertrek.setLongitude((Double.parseDouble(startNode.valueOf("@locationY"))));		
                	aankomst.setLatitude(Double.parseDouble(eindNode.valueOf("@locationX")));
                	aankomst.setLongitude((Double.parseDouble(eindNode.valueOf("@locationY"))));		
                }
    		}
		} catch (IOException | DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	double hulp=Math.pow(Math.sin(Math.toRadians(aankomst.getLatitude()-vertrek.getLatitude())/2),2)+
    			Math.cos(Math.toRadians(aankomst.getLatitude()))*Math.cos(Math.toRadians(vertrek.getLatitude()))*
    			Math.pow(Math.sin(Math.toRadians((aankomst.getLongitude()-vertrek.getLongitude())/2)),2);
    	double afstand=2*Math.atan2(Math.sqrt(hulp),Math.sqrt(1-hulp))*6371;
    	return afstand;
    }
    
    private double berekenOfflineAfstand(String vertrekStation, String eindStation){
    	List<Stationinfo> lijst = SoftwareProject.cache.getStationsInfo();
    	Stationinfo	vertrek = null;
    	Stationinfo aankomst = null;
    	Double afstand = 0.0;
    	for(Stationinfo s: lijst){
    		if(s.getName() == vertrekStation){
    			vertrek = s;
    		}
    		if(s.getName() == eindStation){
    			aankomst = s;
    		}
    	} 
    	if (vertrek != null || aankomst != null){
	    	//lat = y, lon = x
	    	double hulp=Math.pow(Math.sin(Math.toRadians(Double.parseDouble(aankomst.getLocationY())-Double.parseDouble(vertrek.getLocationY()))/2),2)+
	    			Math.cos(Math.toRadians(Double.parseDouble(aankomst.getLocationY())))*Math.cos(Math.toRadians(Double.parseDouble(vertrek.getLocationX())))*
	    			Math.pow(Math.sin(Math.toRadians((Double.parseDouble(aankomst.getLocationX())-Double.parseDouble(vertrek.getLocationX()))/2)),2);
	    	afstand=2*Math.atan2(Math.sqrt(hulp),Math.sqrt(1-hulp))*6371;
    	}
		return afstand;
	}
    
    private int getAantalTussenStations(String vertrekStation, String eindStation){
    	int aantal=0;
    	
    	try (Response resp=getIRailRouteXML(vertrekStation, eindStation)){
    		if (resp.isSuccessful()){
                String routeResponse = resp.body().string();
                SAXReader reader = new SAXReader();
                Document document = reader.read(new InputSource(new StringReader(routeResponse)));

                org.dom4j.Node tussenNode = document.selectSingleNode("//connection/vias");
                if(tussenNode==null){
                	//label om error te weergeven als de stations niet teruggevonden worden.
                }
                else{
                	aantal=Integer.parseInt(tussenNode.valueOf("@number"));
                }
    		}
		} catch (IOException | DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return aantal;
    }
    
    private double berekenPrijs(String vertrekStation, String eindStation){
    	double prijs = 0,duur;
			if(isInternetReachable()){
				double afstand = berekenAfstand(vertrekStation, eindStation);
				int aantal=getAantalTussenStations(vertrekStation,eindStation);
				duur=getDuratieRoute(vertrekStation,eindStation);
				Formule form=FormuleDAO.getFormuleActive();
				String formule=form.getFormule();
				if(!formule.contains("x")){
					afstand=0;
					formule+="1*x";
				}
				if(!formule.contains("y")){
					aantal=0;
					formule+="1*y";
				}
				if(!formule.contains("z")){
					duur=0;
					formule+="1*z";
				}
				Expression e=new ExpressionBuilder(formule).variables("x","y","z").build().setVariable("x", afstand).setVariable("y", aantal).setVariable("z", duur);
				prijs=e.evaluate();
			}
			
			else {
				double afstand = berekenOfflineAfstand(vertrekStation, eindStation);
				String formule = SoftwareProject.cache.getFormule().getFormule();
				if(!formule.contains("x")){
					afstand=0;
					formule+="1*y";
				}
				int aantal = 0;
				duur = 0;
				
				Expression e = new ExpressionBuilder(formule).variables("x","y","z").build().setVariable("x", afstand).setVariable("y", aantal).setVariable("z", duur);
				prijs=e.evaluate();
				//
				String out = String.valueOf(prijs);
			}
	    return prijs;
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

	
	private void createPDF(Ticket ticket, ResourceBundle language) {
		try {
			JasperPrint jasperPrint = null;
			HashMap<String, Object> params = new HashMap<String, Object>();
			// parameters voor Resourcebundle
			String van = language.getString("RouteInfo.van");
			String tot = language.getString("RouteInfo.naar");
			String op = language.getString("Ticket.op");
			String klasseLabel = language.getString("Abonnement.klasse");

			// Parameters van ticket
			int ticket_id = 15;

			if (ticket.getType() == 0) {
				String typeTekst = language.getString("Ticket.enkelereis");
				String type = language.getString("Ticket.enkel");
				params.put("typeTekst", typeTekst);
				params.put("type", type);
			}
			if (ticket.getType() == 1) {
				String typeTekst = language.getString("Ticket.heenenterugreis");
				String type = language.getString("Ticket.heenenterug");
				params.put("typeTekst", typeTekst);
				params.put("type", type);
			}

			String klasseTekst = String.valueOf(ticket.getKlasse()) + "e";
			String vertrekStation = ticket.getVertrekStation();
			String eindStation = ticket.getEindStation();
			String prijs = String.format("%.2f", ticket.getPrijs());
			LocalDateTime datumAankoop = ticket.getDatumAankoop(); // LocalDateTime.now(ZoneId.of(
																	// "Europe/Brussels"
																	// ));
			LocalDate datumHeen = ticket.getDatumHeen(); // LocalDate.of(2016,12,16);

			int datumAankoop_year = datumAankoop.getYear();
			int datumAankoop_month = datumAankoop.getMonthValue();
			int datumAankoop_day = datumAankoop.getDayOfMonth();
			int datumAankoop_hour = datumAankoop.getHour();
			int datumAankoop_minute = datumAankoop.getMinute();
			int datumHeen_year = datumHeen.getYear();
			int datumHeen_month = datumHeen.getMonthValue();
			int datumHeen_day = datumHeen.getDayOfMonth();
			String realPath = "src/main/resources/com/ehbrail";
			params.put("realPath", realPath);
			// Resourcebundle
			params.put("van", van);
			params.put("tot", tot);
			params.put("op", op);
			params.put("klasseLabel", klasseLabel);
			// Ticket params
			params.put("ticket_id", ticket_id);
			params.put("vertrekStation", vertrekStation);
			params.put("eindStation", eindStation);
			params.put("klasseTekst", klasseTekst);
			params.put("prijs", prijs);
			params.put("datumAankoop_year", datumAankoop_year);
			params.put("datumAankoop_month", datumAankoop_month);
			params.put("datumAankoop_day", datumAankoop_day);
			params.put("datumAankoop_hour", datumAankoop_hour);
			params.put("datumAankoop_minute", datumAankoop_minute);
			params.put("datumHeen_year", datumHeen_year);
			params.put("datumHeen_month", datumHeen_month);
			params.put("datumHeen_day", datumHeen_day);

			System.out.println("Generating PDF...");
			// JasperReport jasperReport =
			// JasperCompileManager.compileReport("src/main/resources/TrainTicket.jrxml");
			// jasperPrint = JasperFillManager.fillReport(jasperReport,params,
			// new JREmptyDataSource());
			jasperPrint = JasperFillManager.fillReport("src/main/resources/TrainTicket.jasper", params,
					new JREmptyDataSource());

			/**
			 * Stage stage = new Stage(); final SwingNode swingNode = new
			 * SwingNode(); swingNode.setContent(new JRViewer(jasperPrint));
			 * StackPane pane = new StackPane();
			 * pane.getChildren().add(swingNode); stage.setScene(new Scene(pane,
			 * 800, 800)); stage.show();
			 **/

			JRPrintPreview printPreview = new JRPrintPreview(jasperPrint);
			printPreview.show();
			/**
			 * //JasperExportManager.exportReportToPdfFile(jasperPrint,
			 * "trainTicket.pdf"); JasperViewer jasperViewer = new
			 * JasperViewer(jasperPrint); jasperViewer.setVisible(true);
			 **/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public double getDuratieRoute(String vertrekStation, String eindStation){
    	double duur=0;
    	   	
    	try (Response resp=getIRailRouteXML(vertrekStation, eindStation)){
    		if (resp.isSuccessful()){
    			System.out.println(resp);
                String routeResponse = resp.body().string();
                SAXReader reader = new SAXReader();
                Document document = reader.read(new InputSource(new StringReader(routeResponse)));
                System.out.println(document);
                org.dom4j.Node vertrekNode = document.selectSingleNode("//connection/duration");
                //org.dom4j.Node aankomstNode = document.selectSingleNode("connection/arrival");
                if(vertrekNode==null){
                	//label om error te weergeven als de stations niet teruggevonden worden.
                	System.out.println("Vertreknode is null");
                }
                else{
                	duur+=Integer.parseInt(vertrekNode.getText());
                	duur/=60;
                }
    		}
		} catch (IOException | DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return duur;
    }

	public static boolean isInternetReachable()
	   {
	       try {
	           URL url = new URL("http://www.google.com");
	           HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
	           Object objData = urlConnect.getContent();

	       } catch (Exception e) {              
	           e.printStackTrace();
	           return false;
	       }

	       return true;
	   }
}