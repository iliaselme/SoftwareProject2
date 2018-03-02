package com.model;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;

/**
 * https://github.com/defuse/password-hashing
 * er werd aangeraden om iets anders te kiezen vb BCrypt
 * Gebruik JBCrypt, geen salt nodig in DB.
 * https://paragonie.com/blog/2016/02/how-safely-store-password-in-2016
 * http://www.mindrot.org/projects/jBCrypt/
**/
public class Login {

	public static String createHash(String password){
		String hash = BCrypt.hashpw(password,BCrypt.gensalt());
		return hash;
	}
	public static boolean verifyPassword(String pass, String correctHash){
		if (BCrypt.checkpw(pass,correctHash)){
			//System.out.println("Passwoord is correct");
			return true;
		}
		else {
			//System.out.println("Paswoord is fout");
			return false;
		}
	}

	//login_id(int11),username(vchar45),passwoord(vchar45),bevoegdheid(int11), medewerker_id(int)

	public static enum Bevoegdheid{
	ADMIN(1), WERKNEMER(2);
	private int value;
	Bevoegdheid(int value){this.value = value;}
	}

	private int login_id;
	private String username;
	private String password;
	private Bevoegdheid bevoegdheid;
	private int medewerker_id;

	@Override
	public String toString() {
		return "Login{" +
				"login_id=" + login_id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", bevoegdheid=" + bevoegdheid +
				", medewerker_id=" + medewerker_id +
				'}';
	}

	public Login() {
		super();
	}

	public Login(int login_id, String username, String password, Bevoegdheid bevoegdheid, int medewerker_id) {
		this.login_id = login_id;
		this.username = username;
		this.password = password;
		this.bevoegdheid = bevoegdheid;
		this.medewerker_id = medewerker_id;
	}

	public int getMedewerker_id() {
		return medewerker_id;
	}

	public void setMedewerker_id(int medewerker_id) {
		this.medewerker_id = medewerker_id;
	}

	public int getLogin_id() {
		return login_id;
	}

	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Bevoegdheid getBevoegdheid() {
		return bevoegdheid;
	}
	
	//public int getBevoegdheidInt() {return bevoegdheid.ordinal();}
	public int getBevoegdheidInt() {
		return bevoegdheid.value;
	}
	public void setBevoegdheid(int bevoegdheid) {
		for (Bevoegdheid b : Bevoegdheid.values()){
			if (b.value == bevoegdheid)
			this.bevoegdheid = b;
		}
	}

	public void setBevoegdheid(Bevoegdheid bevoegdheid) {
		this.bevoegdheid = bevoegdheid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Login login = (Login) o;

		if (getLogin_id() != login.getLogin_id()) return false;
		if (getMedewerker_id() != login.getMedewerker_id()) return false;
		if (!getUsername().equals(login.getUsername())) return false;
		if (!getPassword().equals(login.getPassword())) return false;
		return getBevoegdheid() == login.getBevoegdheid();

	}

	@Override
	public int hashCode() {
		int result = getLogin_id();
		result = 31 * result + getUsername().hashCode();
		result = 31 * result + getPassword().hashCode();
		result = 31 * result + getBevoegdheid().hashCode();
		result = 31 * result + getMedewerker_id();
		return result;
	}
}
