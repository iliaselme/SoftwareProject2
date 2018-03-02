package com.database;

import java.sql.*;
import java.util.ArrayList;

import com.model.Klant;
import com.model.VerlorenVoorwerp;

/**
 *
 * @author Ilias El Mesaoudi created 18-11-2016
 **/

public class KlantDAO {

	public static boolean insertKlant(Klant klant) {
		if (klant == null) {
			return false;
		}
		try (Connection con = DataSource.getConnection()){
			String pushStatement = "INSERT INTO Klant (adres_id, datum_aanmaak, geboortedatum,gsmnummer,commentaar,actief,naam,voornaam) VALUES (?,?,?,?,?,?,?,?);";
			con.setAutoCommit(false);
			try (PreparedStatement preparedPush = con.prepareStatement(pushStatement)) {
				preparedPush.setInt(1, klant.getAdresid());
				preparedPush.setObject(2, klant.getAankoopDatum());
				preparedPush.setObject(3, klant.getGeboortedatum());
				preparedPush.setString(4, klant.getGsmnummer());
				preparedPush.setString(5, klant.getCommentaar());
				preparedPush.setBoolean(6, klant.isActief());
				preparedPush.setString(7, klant.getNaam());
				preparedPush.setString(8, klant.getVoornaam());


				preparedPush.executeUpdate();
				con.commit();
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Klant> getAll() {

		ArrayList<Klant> list = new ArrayList<>();
		try (Connection con = DataSource.getConnection()){
			try (Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM Klant where actief=1")){
				while (rs.next()) {
					Klant klant = new Klant();
					klant.setKlantid(rs.getInt("klant_id"));
					klant.setAdresid(rs.getInt("adres_id"));
					klant.setVoornaam(rs.getString("voornaam"));
					klant.setNaam(rs.getString("naam"));
					klant.setGeboortedatum(rs.getDate("geboortedatum").toLocalDate());
					klant.setGsmnummer(rs.getString("gsmnummer"));
					klant.setCommentaar(rs.getString("commentaar"));
					klant.setAankoopDatum(rs.getTimestamp("datum_aanmaak").toLocalDateTime());
					list.add(klant);
				}
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public static ArrayList<Klant> getAllKlanten() {

		ArrayList<Klant> list = new ArrayList<>();
		try (Connection con = DataSource.getConnection()){
			try (Statement st = con.createStatement();
				 ResultSet rs = st.executeQuery("SELECT * FROM Klant")){
				while (rs.next()) {
					Klant klant = new Klant();
					klant.setKlantid(rs.getInt("klant_id"));
					klant.setAdresid(rs.getInt("adres_id"));
					klant.setVoornaam(rs.getString("voornaam"));
					klant.setNaam(rs.getString("naam"));
					klant.setGeboortedatum(rs.getDate("geboortedatum").toLocalDate());
					klant.setGsmnummer(rs.getString("gsmnummer"));
					klant.setCommentaar(rs.getString("commentaar"));
					klant.setAankoopDatum(rs.getTimestamp("datum_aanmaak").toLocalDateTime());
					list.add(klant);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}
	
	public static boolean updateVoorwerp(Klant klant) {
		if (klant == null) {
			return false;
		}
		try (Connection con = DataSource.getConnection()){
			String pushStatement = "UPDATE Klant SET geboortedatum= ?, gsmnummer=?, commentaar=?, naam=?, voornaam=? WHERE klant_id=?;";

			con.setAutoCommit(false);
			try (PreparedStatement update = con.prepareStatement(pushStatement)){
				update.setObject(1, klant.getGeboortedatum());
				update.setString(2, klant.getGsmnummer());
				update.setString(3, klant.getCommentaar());
				update.setString(4, klant.getNaam());
				update.setString(5, klant.getVoornaam());
				update.setInt(6, klant.getKlantid());
	
				int aantalVeranderingen = update.executeUpdate();
				con.commit();
	
				if (aantalVeranderingen == 1)
					return true;
				return false;
			} catch (Exception ex) {
                 System.out.println(ex);
             }
		} catch (SQLException e) {
			e.printStackTrace();
		} return false;
	}
	
	public static boolean deleteKlant(int id) {

		if (id < 0)
			return false;
		try (Connection con = DataSource.getConnection()){
			try (PreparedStatement st = con.prepareStatement("UPDATE Klant SET actief= 0 where klant_id= ?")){
				st.setInt(1, id);
				st.executeUpdate();
				return true;
			} catch (Exception ex) {
                System.out.println(ex);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}