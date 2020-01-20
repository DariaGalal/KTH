package projectChatRMI.id1212.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import projectChatRMI.id1212.model.User;

public class DatabaseHandler {
	
	private Connection connection = null;
	private String URL = "jdbc:sqlite:C:\\Users\\happy\\eclipse-workspace\\projectChatRMI\\src\\projectChatRMI\\id1212\\database\\project.sqlite";
	
	public DatabaseHandler() {
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

	public boolean exists(String username) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Username FROM User WHERE Username = ?");
		statement.setString(1, username);
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
	
	public int getIndexOfUser(String user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT Id FROM User WHERE Username = ?");
		statement.setString(1, user);
		ResultSet res = statement.executeQuery();
		return res.getInt(1);
	}

}
