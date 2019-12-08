package model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import client.ResponseInterface;

public interface Catalogue extends Remote {
	
    Boolean login(String userName, String password, ResponseInterface obs) throws RemoteException;

    void registerNewUser(String userName, String password, ResponseInterface obs) throws RemoteException;
	
	void uploadFile(String fileName, String owner, int size, ResponseInterface obs) throws RemoteException;
	
	void downloadFile(String fileName, String requestingUser, ResponseInterface obs) throws RemoteException;
	
	void logout(String user, ResponseInterface obs) throws RemoteException;
	
	void getAllFiles(ResponseInterface obs) throws RemoteException;
	
	void generateUpdate(String message, ResponseInterface obs) throws RemoteException;

	void getUpdates(ResponseInterface obs) throws RemoteException;
	
}
