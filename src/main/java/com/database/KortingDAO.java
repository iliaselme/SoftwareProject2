package com.database;


import com.model.Korting;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Michael
 * 
 */

public class KortingDAO {

	public static boolean insertKorting(Korting korting) {


	    try (Connection con = DataSource.getConnection()){
	         String query = "INSERT INTO Korting (korting_id, naam, percentage, actief, beschrijving) values (NULL,?, ?, ?, ?)" ;
	            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
		            
		            preparedStatement.setString(1, korting.getNaam());
		            preparedStatement.setInt(2, korting.getPercentage());
		            preparedStatement.setBoolean(3, korting.isActief());
		            preparedStatement.setString(4, korting.getBeschrijving());
		
		            preparedStatement.execute();
	            } catch (Exception ex) {
	                System.out.println(ex);
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	            return false;
	        } return true;
	    }

    public static List<Korting> getKortingen(){

	        List<Korting> kortingen = new LinkedList<>();

	        try (Connection con = DataSource.getConnection()){

	            String query = "SELECT * FROM Korting";
	            try (PreparedStatement preparedStatement = con.prepareStatement(query);
	            		ResultSet resultSet = preparedStatement.executeQuery()){
		            while (resultSet.next()) {
		
		                Korting korting= new Korting();
		                korting.setKorting(resultSet.getInt("korting_id"));
		                korting.setNaam(resultSet.getString("naam"));
		                korting.setBeschrijving(resultSet.getString("beschrijving"));
		                korting.setPercentage(resultSet.getInt("percentage"));
		                korting.setActief(resultSet.getBoolean("actief"));
		                
		                kortingen.add(korting);
		            }
	            } catch (Exception ex) {
	                System.out.println(ex);
	            }

	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	        return kortingen;
    }
    
    public static List<Korting> getActieveKortingen(){

        List<Korting> kortingen = new LinkedList<>();

        try (Connection con = DataSource.getConnection()){

            String query = "SELECT * FROM Korting where actief=true";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
            		ResultSet resultSet = preparedStatement.executeQuery()){
	            while (resultSet.next()) {
	
	                Korting korting= new Korting();
	                korting.setKorting(resultSet.getInt("korting_id"));
	                korting.setNaam(resultSet.getString("naam"));
	                korting.setBeschrijving(resultSet.getString("beschrijving"));
	                korting.setPercentage(resultSet.getInt("percentage"));
	                korting.setActief(resultSet.getBoolean("actief"));
	                
	                kortingen.add(korting);
	            }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return kortingen;
}
    
    public static boolean updateKorting(Korting korting) {
        try (Connection con = DataSource.getConnection()){
            String query = "Update Korting SET actief = ?, naam=?, beschrijving=?, percentage=? WHERE korting_id= ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setBoolean(1, korting.isActief());
	            preparedStatement.setString(2, korting.getNaam());
	            preparedStatement.setString(3, korting.getBeschrijving());
	            preparedStatement.setInt(4, korting.getPercentage());
	            preparedStatement.setInt(5, korting.getKorting());
	            preparedStatement.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }    
 
}

