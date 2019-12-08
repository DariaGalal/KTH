package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ResponseHandler extends UnicastRemoteObject implements ResponseInterface {

	private static final long serialVersionUID = 5574324812830209393L;

	protected ResponseHandler() throws RemoteException {
	}
	
	@Override
	public void sendMessage(String msg) {
		String[] response = msg.split(":");
		switch(response[0]) {
		
			case("LOGIN"): //trying to login
				if(response[1].equals("SUCCESS")){
					System.out.println("You have logged in!");
				}else if(response[1].equals("FAIL")) {
					System.out.println("Wrong username/password!");
				}
				break;
				
			case("REGISTER"): //trying to register a new user
				if(response[1].equals("SUCCESS")) {
					System.out.println("New user registered!");
				}else if(response[1].equals("FAIL")) {
					System.out.println("User name must be unique, try again!");
				}
				break;
			
			case("LOGOUT"):
				if(response[1].equals("SUCCESS")) {
					System.out.println("You have logged out!");
				}else if(response[1].equals("FAIL")) {
					System.out.println("Cant log out at this moment, try again!");
				}
				break;
				
			case("DOWNLOAD"): //trying to download a file
				if(response[1].equals("SUCCESS")) {
					System.out.println("Download finished!\nName: "+response[2]+"\nOwner: "+response[3]+"\nSize: "+response[4]+"\n");
				}else if(response[1].equals("FAIL")) {
					System.out.println("No such file exists!");
				}
				break;
			
			case("UPLOAD"): //trying to upload a file
				if(response[1].equals("SUCCESS")) {
					System.out.println("Uploaded your file successfully!");
				}else if(response[1].equals("FAIL")) {
					System.out.println("Could not upload file. Name must be unique!");
				}
				break;
			case("LOOKUP"):
				if(response[1].equals("SUCCESS")) {
					System.out.println("------------------\nName: "+response[2]+"\nOwner: "+response[3]+"\nSize: "+response[4]);
				}else if(response[1].equals("FAIL")) {
					System.out.println("No files are available");
				}
				break;
		}
	}

	@Override
	public void notifyUser(String event) throws RemoteException {
		if(event.isEmpty()) {
			System.out.println();
		}else {
			String[] list = event.split(":");
			System.out.println("[NOTICE]: User '"+list[0]+"' downloaded your file '"+list[1]+"'.");
		}
	}

}
