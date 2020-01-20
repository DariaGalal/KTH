package projectChatRMI.id1212.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientResponse extends UnicastRemoteObject implements ClientResponseIF {

	private static final long serialVersionUID = 1L;

	protected ClientResponse() throws RemoteException {
		//empty constructor
	}

	@Override
	public void printServerResponse(String message) throws RemoteException {
		String[] info = message.split(":");
		switch(info[0]){
			case("LOGIN"):
				if(info[1].equals("TRUE")) {
					System.out.println("Welcome "+info[2]);
				}else {
					System.out.println("Wrong username and/or password, try again!");
				}
			break;
			
			case("REGISTER"):
				if(info[1].equals("TRUE")) {
					System.out.println("You have successfully registered a new user, you can now log in!");
				}else {
					System.out.println("Username already exists, try again!");
				}
		}
	}

	@Override
	public void printChatInfo(String message) throws RemoteException {
		System.out.println(message);
		
	}

}
