package com.database;

import com.ehbrail.WerknemerController;
import com.model.Abonnement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vik Mortier on 30/10/2016.
 */
public class AbonnementDAO {

    public static boolean writeAbonnement(Abonnement abonnement) {
        try (Connection con = DataSource.getConnection()){
            if (con != null) {
                //Waar word de medewerker geschreven die dit abo aanmaakt? Bestaan er geen andere types zoals bvb zone brussel. Functie nodig om de id v/d klant op te halen.

                String query = "INSERT INTO Abonnement (klant_id, datum_aankoop, begindatum, einddatum, actief, klasse, vertrek, aankomst, prijs,medewerker_id,station,korting_id)" + "values (?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
                try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	                preparedStatement.setInt(1, abonnement.getKlant_id());
                    preparedStatement.setObject(2, abonnement.getAankoopDatum());
	                preparedStatement.setObject(3, abonnement.getBeginDatum());
	                preparedStatement.setObject(4, abonnement.getEindDatum());
	                preparedStatement.setInt(5, abonnement.getActief());
	                preparedStatement.setInt(6, abonnement.getKlasse());
	                preparedStatement.setString(7, abonnement.getBeginStation());
	                preparedStatement.setString(8, abonnement.getEindStation());
	                preparedStatement.setDouble(9, abonnement.getPrijs());
	                preparedStatement.setInt(10, abonnement.getMedewerker_id()); //HARDCODED TIJDELIJK
	                preparedStatement.setString(11, abonnement.getStation()); //HARDCODED TIJDELIJK
	                preparedStatement.setInt(12, abonnement.getKorting_id()); //HARDCODED
	                
	                
	                preparedStatement.execute();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        } return true;
    }

    public static boolean checkAbonnement(int klant_id){
        //checkt voor een bestaand abonnement van een bepaald klantID.

        Boolean check = false;

        try (Connection con = DataSource.getConnection()){
            if (con != null) {
                String query = "SELECT * FROM Abonnement WHERE klant_id= ?";
                try(PreparedStatement preparedStatement = con.prepareStatement(query)){
	                preparedStatement.setInt(1, klant_id);
	                try (ResultSet resultSet = preparedStatement.executeQuery()){
		                while (resultSet.next()) {
		                    check = true;
		                }
	                } catch (Exception ex) {
	                    System.out.println(ex);
	                }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } return check;
    }

    public static List<Abonnement> readAbonnements (){

        List<Abonnement> abonnements = new LinkedList<>();
        try (Connection con = DataSource.getConnection()){

            String query = "SELECT * FROM Abonnement";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {

                    Abonnement abonnement = new Abonnement();

                    abonnement.setAbonnement_id(resultSet.getInt("abonnement_id"));
                    abonnement.setKlant_id(resultSet.getInt("klant_id"));
                    abonnement.setAankoopDatum(resultSet.getTimestamp("datum_aankoop").toLocalDateTime());
                    abonnement.setBeginStation(resultSet.getString("vertrek"));
                    abonnement.setEindStation(resultSet.getString("aankomst"));
                    abonnement.setBeginDatum(resultSet.getDate("begindatum").toLocalDate());
                    abonnement.setEindDatum(resultSet.getDate("einddatum").toLocalDate());
                    abonnement.setActief(resultSet.getInt("actief"));
                    abonnement.setKlasse(resultSet.getInt("klasse"));
                    abonnement.setBeginStation(resultSet.getString("vertrek"));
                    abonnement.setEindStation(resultSet.getString("aankomst"));
                    abonnement.setPrijs(resultSet.getDouble("prijs"));
                    abonnement.setMedewerker_id(resultSet.getInt("medewerker_id"));
                    abonnement.setStation(resultSet.getString("station"));
                    abonnement.setKorting_id(resultSet.getInt("korting_id"));

                    abonnements.add(abonnement);

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return abonnements;
    }
}
