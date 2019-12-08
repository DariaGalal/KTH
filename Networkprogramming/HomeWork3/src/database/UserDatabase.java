package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Filum;
import model.User;

public class UserDatabase {
	
	private Connection connection = null;
	private String URL = "jdbc:sqlite:C:\\Users\\happy\\eclipse-workspace\\HW3\\src\\database\\homework3.sqlite";
	
	public UserDatabase() {
		try {
			this.initDatabase();
		} catch (ClassNotFoundException e) {
			
		}
	}
	
	private void initDatabase() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		try{
			connection = DriverManager.getConnection(URL);
		}
		catch(Exception e) {
			System.out.println("Could not initiate database");
		}
	}

	public boolean verify(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Username FROM User WHERE Username = ? AND Password = ?");
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		ResultSet res = statement.executeQuery();
		if(res.next()) {
			res.close();
			statement.clearParameters();
			statement.close();
			return true;
		}
		return false;
	}

	public boolean exists(String userName) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Username FROM User WHERE Username = ?");
		statement.setString(1, userName);
		ResultSet res = statement.executeQuery();
		if(res.next()) {
			res.close();
			statement.clearParameters();
			statement.close();
			return true;
		}
		return false;
	}

	public void registerUser(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO User (Username, Password) VALUES (?, ?)");
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.executeUpdate();
		statement.clearParameters();
		statement.close();
		
	}

	public void getAllUsers() throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Username FROM User");
		ResultSet res = statement.executeQuery();
		while(res.next()) {
			System.out.println(res.getString(1));
		}
	}
	
	public void upload(Filum file) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO Catalogue (Filename, Owner, Size) VALUES (?, ?, ?)");
		statement.setString(1, file.getFileName());
		statement.setString(2, file.getOwner());
		statement.setInt(3, file.getSize());
		statement.executeUpdate();
		statement.clearParameters();
		statement.close();
	}
	
	public Filum download(String fileName) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Filename, Owner, Size FROM Catalogue WHERE Filename = ?");
		statement.setString(1, fileName);
		ResultSet rs = statement.executeQuery();
		Filum file = new Filum(rs.getString(1), rs.getString(2), rs.getInt(3));
		return file;
	}
	
	public boolean lookup(String fileName) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Filename FROM Catalogue WHERE Filename = ?");
		statement.setString(1, fileName);
		ResultSet rs = statement.executeQuery();
		if(rs.next()) {
			rs.close();
			statement.close();
			return true;
		}
		rs.close();
		statement.close();
		return false;
	}
	
	public List<Filum> getAllFiles() throws SQLException { 
		List<Filum> listOfFiles = new ArrayList<Filum>();
		PreparedStatement statement = connection.prepareStatement("SELECT Filename, Owner, Size FROM Catalogue");
		ResultSet res = statement.executeQuery();
		while(res.next()) {
			Filum file = new Filum(res.getString(1), res.getString(2), res.getInt(3));
			listOfFiles.add(file);
		}
		res.close();
		statement.close();
		return listOfFiles;
	}
	
}
