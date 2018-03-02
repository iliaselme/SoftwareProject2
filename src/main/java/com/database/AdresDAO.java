package com.database;

import com.model.Adres;
import com.model.Klant;
import com.model.VerlorenVoorwerp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jordan Vander Elst
 */
public class AdresDAO {
	public ArrayList<Adres> getAll() {
		ArrayList<Adres> adresList = new ArrayList<>();
		try (Connection con = DataSource.getConnection()) {
			try (Statement st = con.createStatement()) {
				try (ResultSet rs = st.executeQuery("SELECT * FROM Adres;")) {

					while (rs.next()) {
						Adres adres = new Adres();
						adres.setAdres_id(rs.getInt("adres_id"));
						adres.setPlaatsnaam(rs.getString("plaatsnaam"));
						adres.setStraat(rs.getString("straat"));
						adres.setHuisnr(rs.getInt("huisnr"));
						adres.setBrievenbus(rs.getString("brievenbus"));
						adres.setPostcode(rs.getInt("postcode"));

						adresList.add(adres);
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
		return adresList;
	}

	public static void insertAdres(Adres adres) {
		AdresDAO adresDAO = new AdresDAO();
		try (Connection con = DataSource.getConnection()) {

			if (adres != null) {
				// Check of adres al in de DB zit
				List<Adres> adresList = adresDAO.checkAdres(adres);
				if (adresList.size() == 0) {

					String query = "INSERT INTO Adres (adres_id,plaatsnaam,straat,huisnr,brievenbus,postcode) VALUES (NULL,?,?,?,?,?);";
					con.setAutoCommit(false);
					try (PreparedStatement preparedStatement = con.prepareStatement(query,
							PreparedStatement.RETURN_GENERATED_KEYS)) {
						preparedStatement.setString(1, adres.getPlaatsnaam());
						preparedStatement.setString(2, adres.getStraat());
						preparedStatement.setInt(3, adres.getHuisnr());
						preparedStatement.setString(4, adres.getBrievenbus());
						preparedStatement.setInt(5, adres.getPostcode());

						preparedStatement.executeUpdate();
						con.commit();
						try (ResultSet resultset = preparedStatement.getGeneratedKeys()) {
							resultset.next();
							adres.setAdres_id(resultset.getInt(1));
						} catch (Exception ex) {
							System.out.println(ex);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public ArrayList<Adres> checkAdres(Adres adres) throws SQLException {
		ArrayList<Adres> adresList = new ArrayList<>();
		try (Connection con = DataSource.getConnection()) {
			String sql = "SELECT * FROM Adres WHERE plaatsnaam= ? AND straat=? AND huisnr=? AND brievenbus=? AND postcode=?;";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, adres.getPlaatsnaam());
				preparedStatement.setString(2, adres.getStraat());
				preparedStatement.setInt(3, adres.getHuisnr());
				preparedStatement.setString(4, adres.getBrievenbus());
				preparedStatement.setInt(5, adres.getPostcode());

				try (ResultSet rs = preparedStatement.executeQuery()) {
					while (rs.next()) {
						Adres adres1 = new Adres();
						adres1.setAdres_id(rs.getInt("adres_id"));
						adres1.setPlaatsnaam(rs.getString("plaatsnaam"));
						adres1.setStraat(rs.getString("straat"));
						adres1.setHuisnr(rs.getInt("huisnr"));
						adres1.setBrievenbus(rs.getString("brievenbus"));
						adres1.setPostcode(rs.getInt("postcode"));

						adresList.add(adres1);
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if (adresList.size() > 0) {
				System.out.println("Dit adres stond al in de DB en werd dus niet toegevoegd!! Met ID: ");
				adresList.forEach(adres1 -> System.out.println(adres1.getAdres_id()));
			} else
				System.out.println("Gz dit adres bestond nog niet en is toegevoegd aan de DB");

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return adresList;
	}

	// methode om de adresid te kennen na het creeren van een adres voor een
	// klant bij klantDAO
	public int getAdresId(Adres adres) {
		if (adres == null)
			return -1;

		int adresid = 0;
		try (Connection con = DataSource.getConnection(); Statement st = con.createStatement()) {
			ResultSet rs = st.executeQuery("Select adres_id from Adres where huisnr =" + adres.getHuisnr()
					+ " AND postcode= " + adres.getPostcode() + " AND brievenbus =\"" + adres.getBrievenbus()
					+ "\" AND plaatsnaam=\"" + adres.getPlaatsnaam() + "\" AND straat = \"" + adres.getStraat() + "\"");
			while (rs.next()) {
				adresid = rs.getInt(1);
			}
			return adresid;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Adres getAdres(int adresid) {
		AdresDAO adresDAO = new AdresDAO();
		if (adresid != 0) {

			try (Connection con = DataSource.getConnection()) {

				String sql = "Select * from Adres where adres_id=?";
				try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
					preparedStatement.setInt(1, adresid);
					try (ResultSet rs = preparedStatement.executeQuery()) {
						while (rs.next()) {
							Adres adres = new Adres();
							adres.setPlaatsnaam(rs.getString("plaatsnaam"));
							adres.setStraat(rs.getString("straat"));
							adres.setHuisnr(rs.getInt("huisnr"));
							adres.setBrievenbus(rs.getString("brievenbus"));
							adres.setPostcode(rs.getInt("postcode"));
							return adres;
						}
					}

				}

			} catch (SQLException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}
		}

		return null;
	}
	
	public static boolean updateVoorwerp(Adres adres) {
		if (adres== null) {
			return false;
		}
		try (Connection con = DataSource.getConnection()){
			String pushStatement = "UPDATE Adres SET plaatsnaam= ?, straat=?, huisnr=?, brievenbus=?, postcode=? WHERE adres_id=?;";

			con.setAutoCommit(false);
			try (PreparedStatement update = con.prepareStatement(pushStatement)){
				update.setString(1, adres.getPlaatsnaam());
				update.setString(2, adres.getStraat());
				update.setInt(3, adres.getHuisnr());
				update.setString(4, adres.getBrievenbus());
				update.setInt(5, adres.getPostcode());
				update.setInt(6, adres.getAdres_id());
	
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
	

	// TODO update en delete methode voorzien
}
