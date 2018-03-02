package com.ehbrail;

import com.database.DataSource;
import com.model.Korting;
import com.model.Login;
import com.model.Werknemer;
import com.model.Routes.Stationinfo;
import com.persistentie.Cache;
import com.sun.istack.internal.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.JobBuilder;
import org.quartz.JobBuilder.*;
import org.quartz.JobDetail;
import org.quartz.TriggerBuilder.*;
import org.quartz.SimpleScheduleBuilder.*;



/**
*
* @author Vik Mortier
* voor javaFX toe te voegen gebruik: https://www.eclipse.org/efxclipse/install.html#for-the-lazy
* Hier zit de void Main();
*/

public class SoftwareProject extends Application {
	// logger
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger();
	
	private static Scheduler scheduler;
	public static Cache cache;
	
    private BorderPane borderPane = new BorderPane();
   @Override
   public void start(Stage stage) throws Exception {
	   stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	       @Override
	       public void handle(WindowEvent e) {
	    	  try {
				scheduler.shutdown();
			} catch (SchedulerException e1) {
				logger.error(e1);
			}
	          Platform.exit();
	          System.exit(0);
	       }
	    });
      createLoginScreen(stage);
   }

   public void createLoginScreen(Stage stage){
       try {
           loadView(new Locale("NL"));
           borderPane.setTop(createComboBox());
           Scene scene = new Scene(borderPane);
           stage.setTitle("EhB-Rail  |  Login");
           stage.getIcons().add(new Image("com/ehbrail/EHBRail.png"));
           scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
           stage.setScene(scene);
           stage.setResizable(false);
           stage.show();
       }
       catch (Exception e){
           e.printStackTrace();
       }
   }


    private ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        ObservableList<String> options = FXCollections.observableArrayList("NL","FR","EN");
        comboBox.setItems(options);
        comboBox.setValue(options.get(0));
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(event -> loadView(new Locale(comboBox.getSelectionModel().getSelectedItem())));
        return comboBox;
    }

    private void loadView(Locale locale){
        try {
            Locale.setDefault(locale);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setResources(ResourceBundle.getBundle("Language", locale));
            Pane pane = (AnchorPane) fxmlLoader.load(this.getClass().getResource("Login.fxml").openStream());
            borderPane.setCenter(pane);
        }
        catch (IOException e){e.printStackTrace();}
    }


   /**
    * @param args the command line arguments
 * @throws SchedulerException 
    */
   public static void main(String[] args) throws SchedulerException {
	   logger.info("Start Applicatie");
	   
	   // initialisatie scheduler
	   // job1 - statusupdater
	   JobDetail statusJob = JobBuilder.newJob(JobUpdateStatus.class)
			   .withIdentity("StatusJob")
			   .build();
	   
	   Trigger statusTrigger = TriggerBuilder.newTrigger()
				.withIdentity("StatusTrigger", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(30).repeatForever())
				.build();
	   
	   // job2 - persistente tickets doorsturen
	   JobDetail persistentTicketJob = JobBuilder.newJob(JobSendPersistentTickets.class)
			   .withIdentity("TicketJob")
			   .build();
	   
	   Trigger persistentTicketTrigger = TriggerBuilder.newTrigger()
				.withIdentity("TicketTrigger", "group1")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(60).repeatForever())
				.build();
	   
	   // job3 - cache initialiseren
	   JobDetail cacheJob = JobBuilder.newJob(JobPopulateCache.class)
			   .withIdentity("CacheJob")
			   .build();
	   
	   Trigger cacheTrigger = TriggerBuilder.newTrigger()
			   .withIdentity("CacheTrigger", "group1")
			   .withSchedule(
					   SimpleScheduleBuilder.simpleSchedule()
					   .withIntervalInSeconds(60).repeatForever())
			   .build();
	   
	   scheduler = new StdSchedulerFactory().getScheduler();
	   scheduler.start();
	   
	   scheduler.scheduleJob(persistentTicketJob, persistentTicketTrigger);
	   scheduler.scheduleJob(statusJob, statusTrigger);
	   scheduler.scheduleJob(cacheJob, cacheTrigger);

       launch(args);
   }
   
}