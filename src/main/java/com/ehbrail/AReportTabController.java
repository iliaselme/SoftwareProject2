package com.ehbrail;

import com.database.AbonnementDAO;
import com.database.KlantDAO;
import com.database.TicketDAO;
import com.model.Abonnement;
import com.model.Klant;
import com.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * Created by jorda on 25/11/2016.
 */
public class AReportTabController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private BarChart<String, Integer> barChartAbo;

    @FXML
    private BarChart<String, Integer> barChartKlant;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private CategoryAxis xAxisAbo;

    @FXML
    private CategoryAxis xAxisKlant;


    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    private ResourceBundle language;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] months = DateFormatSymbols.getInstance(resources.getLocale()).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
        xAxisAbo.setCategories(monthNames);
        setChartDataTicket();
        setChartDataAbo();
        setChartDataKlant();
    }

    private void setChartDataKlant() {
        int[] monthCounter = new int[12];
        List<Klant> klantenList = KlantDAO.getAllKlanten();
        for (Klant klant : klantenList){
            int month =  klant.getAankoopDatum().getMonthValue()-1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChartKlant.getData().add(series);

    }

    private void setChartDataAbo() {
        int[] monthCounter = new int[12];
        List<Abonnement> abonnementList = AbonnementDAO.readAbonnements();
        for (Abonnement abo : abonnementList){
            int month =  abo.getAankoopDatum().getMonthValue()-1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChartAbo.getData().add(series);
    }

    public void setChartDataTicket() {
        int[] monthCounter = new int[12];
        List<Ticket> ticketList = TicketDAO.readTickets();
        for (Ticket ticket : ticketList) {
            int month = ticket.getDatumHeen().getMonthValue() - 1;
            monthCounter[month]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(series);
    }
}
