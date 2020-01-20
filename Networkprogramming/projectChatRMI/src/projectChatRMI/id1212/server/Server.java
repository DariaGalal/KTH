package projectChatRMI.id1212.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import projectChatRMI.id1212.client.ClientIF;
import projectChatRMI.id1212.client.ClientResponseIF;
import projectChatRMI.id1212.database.DatabaseHandler;
import projectChatRMI.id1212.model.ChatFormat;
import projectChatRMI.id1212.model.User;

public class Server extends UnicastRemoteObject implements ServerIF{
	
	//fix: friends added in hashmap gets removed after logout

	private static final long serialVersionUID = 1L;
	private ArrayList<ClientIF> clients;
	private HashMap<String, ClientIF> onlineUsers;
	//private HashMap<ClientIF, HashMap<String, ClientIF>> friendList;
	private DatabaseHandler database;
	private ChatFormat format;
	
	protected Server(DatabaseHandler database) throws RemoteException {
		this.clients = new ArrayList<ClientIF>();
		this.database = database;
		this.onlineUsers = new HashMap<String, ClientIF>();
		this.format = new ChatFormat();
		//friendList = new HashMap<ClientIF, HashMap<String, ClientIF>>();
	}

	@Override
	public synchronized void registerChatClient(ClientIF client, String username) throws RemoteException {
		this.clients.add(client);
		this.onlineUsers.put(username, client);
		broadcast(username+" has joined the chat!");
		//this.friendList.put(client, new HashMap<String, ClientIF>());
	}

	@Override
	public synchronized void broadcast(String msg) throws RemoteException {
		int i = 0;
		while(i < clients.size()) {
			clients.get(i++).getMessages(msg);
		}
	}

	@Override
	public Boolean login(String username, String password, ClientResponseIF obs) throws RemoteException {
		try {
			if(database.verify(new User(username, password))) {
				obs.printServerResponse("LOGIN:TRUE:"+username);
				return true;
			}else {
				obs.printServerResponse("LOGIN:FALSE");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void registerNewUser(String username, String password, ClientResponseIF obs) throws RemoteException {
		try {
			if(!database.exists(username)) {
				database.registerUser(new User(username, password));
				obs.printServerResponse("REGISTER:TRUE");
			}else {
				obs.printServerResponse("REGISTER:FALSE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getOnlineUsers(ClientIF client) throws RemoteException {
        Set<String> keys = onlineUsers.keySet();
        for(String key: keys){
        	if(onlineUsers.get(key).equals(client)) {
        		continue;
        	}else {
        		client.serverMessage(key);
        	}
        }
	}

	@Override
	public void logout(ClientIF client, String username) throws RemoteException {
		clients.remove(client);
		onlineUsers.remove(username);
	}

	@Override
	public void getHelp(ClientIF client) throws RemoteException {
		client.serverMessage(format.getHelp());
	}

//	@Override
//	public void getFriendList(ClientIF client) throws RemoteException {
//		ArrayList<String> list = friends.getList();
//		if(list.size()<1) {
//			client.serverMessage("You have no favourites :(");
//		}else {
//			for(int i = 0; i<list.size(); i++) {
//				client.serverMessage(list.get(i));
//			}
//		}
//	}

//	@Override
//	public void addFriend(ClientIF client, String friend) throws RemoteException {
//		if(isOnline(friend)) {
//			friends.addFavourite(friend);
//			client.serverMessage(friend+" has been added to your favourite list!");
//		}else {
//			client.serverMessage("Can't find user, user must be registered and online!");
//		}
//	}
	
	private Boolean isOnline(String friend) {
		return onlineUsers.containsKey(friend);
	}

	@Override
	public void whisper(ClientIF client, String whisperTo, String msg, String user) throws RemoteException {
		if(!isOnline(whisperTo)) {
			client.serverMessage("User is not online!");
		}else {
			ClientIF friend = onlineUsers.get(whisperTo);
			clients.get(clients.indexOf(friend)).getMessages(onlineUsers.get(user)+" whispered: "+msg);
		}
	}

}
