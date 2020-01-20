package projectChatRMI.id1212.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientIF extends Remote {
	void getMessages(String msg) throws RemoteException;
	void serverMessage(Object obj) throws RemoteException;
}
