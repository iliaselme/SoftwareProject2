package com.database;

import com.model.Login;
import com.model.Werknemer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vik Mortier on 4/11/2016.
 */
public class WerknemerDAO {

    public static boolean deleteWerknemer (Werknemer werknemer) {

        try (Connection con = DataSource.getConnection()){
            String query = "Update Medewerker SET actief = ? WHERE medewerker_id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){

            preparedStatement.setBoolean(1, werknemer.isActief());
            preparedStatement.setInt(2, werknemer.getWerknemerId());
            preparedStatement.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean updateWerknemer (Werknemer werknemer) {

        try (Connection con = DataSource.getConnection()){
            String query = "Update Medewerker SET actief = ?, naam = ?, voornaam = ? WHERE medewerker_id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setBoolean(1, werknemer.isActief());
	            preparedStatement.setString(2, werknemer.getNaam());
	            preparedStatement.setString(3, werknemer.getVoornaam());
	            preparedStatement.setInt(4, werknemer.getWerknemerId());
	
	            preparedStatement.executeUpdate();
            }
            catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean insertWerknemer(Werknemer werknemer) {

        try (Connection con = DataSource.getConnection()){
            String query = "INSERT INTO Medewerker (actief, naam, voornaam)" + "VALUES (?, ?, ?) ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)){
	            preparedStatement.setBoolean(1, werknemer.isActief());
	            preparedStatement.setString(2, werknemer.getNaam());
	            preparedStatement.setString(3, werknemer.getVoornaam());
	
	            preparedStatement.execute();
	
	            try (ResultSet resultset = preparedStatement.getGeneratedKeys()){
		            resultset.next();
		            werknemer.setWerknemerId(resultset.getInt(1));
	            } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch(Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }

    public static List<Werknemer> getAllWerknemers () {

        List<Werknemer> werknemers = new ArrayList<>();
        try (Connection con = DataSource.getConnection()){
            String query = "SELECT * FROM Medewerker, Login WHERE Medewerker.medewerker_id = Login.medewerker_id ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);
            		ResultSet resultSet = preparedStatement.executeQuery();){
					while (resultSet.next()) {
		                Werknemer werknemer = new Werknemer();
		                Login login = new Login();
		
		                werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
		                werknemer.setActief(resultSet.getBoolean("actief"));
		                werknemer.setNaam(resultSet.getString("naam"));
		                werknemer.setVoornaam(resultSet.getString("voornaam"));
		                login.setUsername(resultSet.getString("username"));
		                login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
		                login.setPassword(resultSet.getString("passwoord"));
		                login.setLogin_id(resultSet.getInt("login_id"));
		                werknemer.setLogin(login);
		                werknemers.add(werknemer);
					}
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception ex){
            System.out.println(ex);
        }

        return werknemers;
    }

    public static Werknemer getWerknemerById(int medewerker_id){
        Werknemer werknemer = new Werknemer();
        Login login = new Login();
        try (Connection con = DataSource.getConnection()){
        	String query = "SELECT * FROM Medewerker, Login WHERE Medewerker.medewerker_id = ? AND Medewerker.medewerker_id = Login.medewerker_id ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
                preparedStatement.setInt(1, medewerker_id);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
	                while (resultSet.next()) {
	                    werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
	                    werknemer.setActief(resultSet.getBoolean("actief"));
	                    werknemer.setNaam(resultSet.getString("naam"));
	                    werknemer.setVoornaam(resultSet.getString("voornaam"));
	                    login.setUsername(resultSet.getString("username"));
	                    login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
	                    login.setPassword(resultSet.getString("passwoord"));
	                    login.setLogin_id(resultSet.getInt("login_id"));
	                    werknemer.setLogin(login);
	                }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return werknemer;
    }

    public static Werknemer getWerkById(int medewerker_id) {
        Werknemer werknemer = new Werknemer();
        try (Connection con = DataSource.getConnection()){
            String query = "SELECT * FROM Medewerker WHERE medewerker_id=? LIMIT 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
                preparedStatement.setInt(1, medewerker_id);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
	                while (resultSet.next()) {
	                    werknemer.setWerknemerId(resultSet.getInt("medewerker_id"));
	                    werknemer.setActief(resultSet.getBoolean("actief"));
	                    werknemer.setNaam(resultSet.getString("naam"));
	                    werknemer.setVoornaam(resultSet.getString("voornaam"));
	                }
                }
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return werknemer;
    }
}
