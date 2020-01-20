package projectChatRMI.id1212.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import projectChatRMI.id1212.client.ClientIF;
import projectChatRMI.id1212.client.ClientResponseIF;

public interface ServerIF extends Remote {
	//pre-chatting
	Boolean login(String username, String password, ClientResponseIF obs) throws RemoteException;
	void registerNewUser(String username, String password, ClientResponseIF obs) throws RemoteException;
	
	//chatting
	void registerChatClient(ClientIF client, String username) throws RemoteException;
	void broadcast(String msg) throws RemoteException;
	void getOnlineUsers(ClientIF client) throws RemoteException;
	void getHelp(ClientIF client) throws RemoteException;
	void logout(ClientIF client, String username) throws RemoteException;
//	void getFriendList(ClientIF client) throws RemoteException;
//	void addFriend(ClientIF client, String friend) throws RemoteException;
	void whisper(ClientIF client, String friend, String msg, String user) throws RemoteException;
	//void removeFriend(String username) throws RemoteException;
	//void chatWithFriend(ClientIF client, String username) throws RemoteException;
}
