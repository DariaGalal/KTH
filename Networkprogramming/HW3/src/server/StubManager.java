package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import client.ResponseInterface;
import database.UserDatabase;
import model.Catalogue;
import model.Filum;
import model.User;

public class StubManager extends UnicastRemoteObject implements Catalogue{
	
	private static final long serialVersionUID = 2182821403444773365L;

	protected StubManager() throws RemoteException {
	}

	UserDatabase database = new UserDatabase();
	private List<String> activeUsers = new ArrayList<String>();
	private HashMap<String, ResponseInterface> events = new HashMap<String, ResponseInterface>(); //<username, event code>
	private String update = "";
	private final String LOGIN_SUCCESS = "LOGIN:SUCCESS";
	private final String LOGIN_FAIL = "LOGIN:FAIL";
	private final String REGISTER_SUCCESS = "REGISTER:SUCCESS";
	private final String REGISTER_FAIL = "REGISTER:FAIL";
	private final String LOGOUT_SUCCESS = "LOGOUT:SUCCESS";
	private final String LOGOUT_FAIL = "LOGOUT:FAIL";
	private final String DOWNLOAD_SUCCESS = "DOWNLOAD:SUCCESS";
	private final String DOWNLOAD_FAIL = "DOWNLOAD:FAIL";
	private final String UPLOAD_SUCCESS = "UPLOAD:SUCCESS";
	private final String UPLOAD_FAIL = "UPLOAD:FAIL";
	private final String LOOKUP_SUCCESS = "LOOKUP:SUCCESS";
	private final String LOOKUP_FAIL = "LOOKUP:FAIL";
	
	

	@Override
	public void uploadFile(String fileName, String owner, int size, ResponseInterface obs) throws RemoteException {
		try {
			Boolean alreadyExists = database.lookup(fileName);
			if(!alreadyExists) {
				Filum file = new Filum(fileName, owner, size);
				database.upload(file);
				obs.sendMessage(UPLOAD_SUCCESS);
			}else {
				obs.sendMessage(UPLOAD_FAIL);
			}
		} catch (SQLException e) {
			System.out.println("Could not upload file");
			e.printStackTrace();
			obs.sendMessage(UPLOAD_FAIL);
		}
	}

	@Override
	public void downloadFile(String fileName, String requestingUser, ResponseInterface obs) throws RemoteException {
		try {
			if(database.lookup(fileName)) {
				Filum file = database.download(fileName);
				String data = ":"+file.getFileName()+":"+file.getOwner()+":"+file.getSize();
				obs.sendMessage(DOWNLOAD_SUCCESS+data);
				//checks if file owner is online and if the file owner is not the same as the requester
				int i;
				if( (i = activeUsers.indexOf(file.getOwner())) != -1) {
					if(file.getOwner().equals(activeUsers.get(i)) && !requestingUser.equals(file.getOwner())) {
						this.update = requestingUser+":"+file.getFileName();
						generateUpdate(this.update, events.get(file.getOwner()));
					}
				}
			}else {
				obs.sendMessage(DOWNLOAD_FAIL);
			}
		} catch (SQLException e) {
			obs.sendMessage(DOWNLOAD_FAIL);
		}
	}
	
	@Override
	public Boolean login(String userName, String password, ResponseInterface obs) throws RemoteException {
		if(userName.isEmpty() || password.isEmpty()) {
			obs.sendMessage(LOGIN_FAIL);
			return false;
		}else {
    		User user = new User(userName, password);
    		try {
    			if(database.verify(user)) {
    				System.out.println(user.getUsername()+" logged in!");
    				activeUsers.add(userName);
    				events.put(userName, obs);
    				obs.sendMessage(LOGIN_SUCCESS);
    				return true;
    			}else {
    				obs.sendMessage(LOGIN_FAIL);
    				return false;
    			}
    		} catch (SQLException e) {
    			obs.sendMessage(LOGIN_FAIL);
    			return false;
    			}
		}
	}

	@Override
	public void registerNewUser(String userName, String password, ResponseInterface obs) throws RemoteException {
    	try {
			if(!database.exists(userName)) {
				User user = new User(userName, password);
				database.registerUser(user);
				System.out.println("New user registered: "+user.getUsername());
				obs.sendMessage(REGISTER_SUCCESS);
			}else {
				obs.sendMessage(REGISTER_FAIL);
			}
		} catch (SQLException e) {
			obs.sendMessage(REGISTER_FAIL);
		}
	}
	
	@Override
	public void logout(String user, ResponseInterface obs) throws RemoteException {
		try {
			activeUsers.remove(activeUsers.indexOf(user));
			obs.sendMessage(LOGOUT_SUCCESS);
		}catch(IndexOutOfBoundsException e) {
			obs.sendMessage(LOGOUT_FAIL);
		}
	}

	@Override
	public void getAllFiles(ResponseInterface obs) throws RemoteException {
		try {
			List<Filum> list = database.getAllFiles();
			if(!list.isEmpty()) {
				for(int i = 0; i<list.size(); i++) {
					Filum file = list.get(i);
					String data = ":"+file.getFileName()+":"+file.getOwner()+":"+file.getSize();
					obs.sendMessage(LOOKUP_SUCCESS+data);
				}
			}
		} catch (SQLException e) {
			obs.sendMessage(LOOKUP_FAIL);
		}
	}

	@Override
	public void generateUpdate(String update, ResponseInterface obs) throws RemoteException {
		this.update = update;
		
	}

	@Override
	public void getUpdates(ResponseInterface obs) throws RemoteException {
		obs.notifyUser(this.update);
		this.update = "";
		
	}

}
