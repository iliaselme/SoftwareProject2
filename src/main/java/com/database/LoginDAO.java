package com.database;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.model.Login;


public class LoginDAO {

    public Login getLoginByUsername(String username) {
        Login login = new Login();
        try (Connection con = DataSource.getConnection()){
            String query = "SELECT * FROM Login WHERE username=? LIMIT 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setString(1, username);
	            try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		                login.setLogin_id(resultSet.getInt("login_id"));
		                login.setUsername(resultSet.getString("username"));
		                login.setPassword(resultSet.getString("passwoord"));
		                login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
		                login.setMedewerker_id(resultSet.getInt("medewerker_id"));
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
        return login;
    }

    public static boolean insertLogin(Login login) {
        try (Connection con = DataSource.getConnection()){
            if (login != null) {
                String query = "INSERT INTO Login (login_id,username,passwoord,bevoegdheid,medewerker_id) VALUES (NULL,?,?,?,?);";
                con.setAutoCommit(false);
                try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	                //preparedStatement = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	                preparedStatement.setString(1, login.getUsername());
	                preparedStatement.setString(2, login.getPassword());
	                preparedStatement.setInt(3, login.getBevoegdheidInt());
	                preparedStatement.setInt(4, login.getMedewerker_id());
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

    public static boolean updateLoginBevoegdheid(Login login) {
        try (Connection con = DataSource.getConnection()){
            String query = "Update Login SET bevoegdheid = ? WHERE medewerker_id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setInt(1, login.getBevoegdheidInt());
	            preparedStatement.setInt(2, login.getMedewerker_id());
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

    public static boolean changePassbyMedewerker(Login login) {
        try (Connection con = DataSource.getConnection()){
            try (PreparedStatement st = con.prepareStatement("UPDATE Login SET passwoord = ? WHERE medewerker_id= ?")){
	            st.setString(1, login.getPassword());
	            st.setInt(2, login.getMedewerker_id());
	            st.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            System.exit(1);
            return false;
        } return true;
    }

    public static boolean changePassbyLogin(Login login, String HashedPass) {
        try (Connection con = DataSource.getConnection()){
            try (PreparedStatement st = con.prepareStatement("UPDATE Login SET passwoord = ? WHERE login_id= ?")){
	            st.setString(1, HashedPass);
	            st.setInt(2, login.getLogin_id());
	            st.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
            System.exit(1);
            return false;
        } return true;
    }

    public static Login getLoginByID(int id) {
        Login login = new Login();
        try (Connection con = DataSource.getConnection()){
            String query = "SELECT * FROM Login WHERE login_id=? LIMIT 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)){
	            preparedStatement.setInt(1, id);
	            try (ResultSet resultSet = preparedStatement.executeQuery()){
		            //login_id(int11),username(vchar45),passwoord(vchar64),bevoegdheid(int11),medewerker_id(int11)
		            while (resultSet.next()) {
		                login.setLogin_id(resultSet.getInt("login_id"));
		                login.setUsername(resultSet.getString("username"));
		                login.setPassword(resultSet.getString("passwoord"));
		                login.setBevoegdheid(resultSet.getInt("bevoegdheid"));
		                login.setMedewerker_id(resultSet.getInt("medewerker_id"));
		            }
	            }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return login;
    }

}