package com.database;

import java.sql.*;
import java.util.ArrayList;
import com.model.Formule;



public class FormuleDAO {

    public static Formule getFormuleActive() {
    	Formule form=new Formule();
    	try (Connection con = DataSource.getConnection()){
            String query = "SELECT * FROM Formule WHERE active=1";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		            	form.setFormule(resultSet.getString("formule"));
		            	form.setActive(true);
		            	form.setFormuleId(resultSet.getInt("formule_id"));
		            	form.setMedewerkerId(resultSet.getInt("medewerker_id"));
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
    	form.setActive(true);
        return form;
    }

    public static boolean insertFormule(String formule, boolean active) {
        try (Connection con = DataSource.getConnection()){
            if (formule != null) {
                String query = "INSERT INTO Formule (formule, active) VALUES (?,?);";
                con.setAutoCommit(false);
                try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	                //preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	                preparedStatement.setString(1, formule);
	                preparedStatement.setBoolean(2, active);
	                preparedStatement.executeUpdate();
	                con.commit();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        } 
        return true;
    }

    public static boolean updateFormule(String formule,boolean active) {
        try (Connection con = DataSource.getConnection()){
            String query = "Update Formule SET active = ? WHERE formule = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setBoolean(1, active);
	            preparedStatement.setString(2, formule);
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

    public static ArrayList<Formule>getAllFormules() {
        ArrayList<Formule> formules =new ArrayList<Formule>();
        try (Connection con = DataSource.getConnection()){
            String query = "SELECT * FROM Formule";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		                formules.add(new Formule(
		                		resultSet.getString("formule"),
		                		resultSet.getBoolean("active"),
		                		resultSet.getInt("formule_id"),
		                		resultSet.getInt("medewerker_id")));
		            }
	            }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return formules;
    }

    public static Formule getFormule(String formule){
    	Formule form=new Formule();
    	form.setFormule(formule);
    	
    	try (Connection con = DataSource.getConnection()){
    		String query="SELECT * from Formule WHERE formule=?";
    		try(PreparedStatement preparedStatement=con.prepareStatement(query)){
    			preparedStatement.setString(1, formule);
    			preparedStatement.executeUpdate();
    			try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		                form.setFormule(resultSet.getString("formule"));
		                form.setActive(resultSet.getBoolean("active"));
		            }
	            }
            	catch (Exception x) {
            		System.out.println(x);
            	}
    		}
    		catch(Exception ex){
    			System.out.println(ex);
    		}
    	}catch(Exception ex){
    		System.out.println(ex);
    	}
    	
    	return form;
    }
}