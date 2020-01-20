package projectChatRMI.id1212.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientResponseIF extends Remote{
	void printServerResponse(String message) throws RemoteException;
	void printChatInfo(String message) throws RemoteException;
}
