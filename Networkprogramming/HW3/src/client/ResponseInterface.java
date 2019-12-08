package client;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ResponseInterface extends Remote{
	//void getMessage() throws RemoteException;
	void sendMessage(String msg) throws RemoteException;
	void notifyUser(String event) throws RemoteException;
}
