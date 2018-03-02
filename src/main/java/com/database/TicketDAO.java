package com.database;
import com.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vik Mortier on 28/10/2016.
 */
public class TicketDAO {

    public static boolean writeTicket(Ticket ticket) {
    	DataSource.testConn();
    	boolean bool = false;
    	if (DataSource.dbStatus == "OFFLINE"){
    		com.persistentie.TicketCSV.addTicket(ticket);
    		bool = true;
    	}
    	else if (DataSource.testConn()){
	        try (Connection con = DataSource.getConnection()){
	            String query = "INSERT INTO Ticket (vertrek, aankomst, datum_aankoop, datum_heen, datum_terug, klasse, prijs, type, medewerker_id,korting_id,formule_id)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
	            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
		            preparedStatement.setString(1, ticket.getVertrekStation());
		            preparedStatement.setString(2, ticket.getEindStation());
		            preparedStatement.setObject(3, ticket.getDatumAankoop());
		            preparedStatement.setObject(4, ticket.getDatumHeen());
		            preparedStatement.setObject(5, ticket.getDatumTerug());
		            preparedStatement.setInt(6, ticket.getKlasse());
		            preparedStatement.setDouble(7, ticket.getPrijs());
		            preparedStatement.setInt(8, ticket.getType());
		            preparedStatement.setInt(9, ticket.getMedewerker_id());
		            preparedStatement.setInt(10, ticket.getKorting_id());
		            preparedStatement.setInt(11, ticket.getFormuleId());
		            preparedStatement.execute();
		            System.out.println(ticket.getFormuleId());
	            } catch (Exception ex) {
	                System.out.println(ex);
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	            bool = false;
	        } bool = true;
    	}
    	return bool;
    }

    public static List<Ticket> readTickets (){

        List<Ticket> tickets = new LinkedList<>();

        try (Connection con = DataSource.getConnection()){

            String query = "SELECT * FROM Ticket";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
            		ResultSet resultSet = preparedStatement.executeQuery()){
	            while (resultSet.next()) {
	
	                Ticket ticket = new Ticket();
	
	                ticket.setTicket_id(resultSet.getInt("ticket_id"));
	                ticket.setVertrekStation(resultSet.getString("vertrek"));
	                ticket.setEindStation(resultSet.getString("aankomst"));
	                ticket.setDatumAankoop(resultSet.getTimestamp("datum_aankoop").toLocalDateTime());
	                ticket.setDatumHeen(resultSet.getDate("datum_heen").toLocalDate());
	                ticket.setDatumTerug(resultSet.getDate("datum_terug").toLocalDate());
	                ticket.setKlasse(resultSet.getInt("klasse"));
	                ticket.setPrijs(resultSet.getDouble("prijs"));
	                ticket.setType(resultSet.getInt("type"));
                    ticket.setMedewerker_id(resultSet.getInt("medewerker_id"));
                    ticket.setTicket_id(resultSet.getInt("korting_id"));
	                tickets.add(ticket);
	            }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return tickets;
    }
}
