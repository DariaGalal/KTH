package projectChatRMI.id1212.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import projectChatRMI.id1212.server.ServerIF;

public class Chat extends UnicastRemoteObject implements ClientIF{

	private static final long serialVersionUID = -1226747934790228414L;
	private ServerIF server;
	private String username;
	private Scanner sc;
	private String msg;


	protected Chat(ServerIF server, String username) throws RemoteException {
		this.server = server;
		this.username = username;
		server.registerChatClient(this, username);
	}

	@Override
	public void getMessages(String msg) throws RemoteException {
		System.out.println(msg);
	}

	
	public void start() throws RemoteException {
		System.out.println("*Weclome to the chatroom!*\nUsers currently online:");
		server.getOnlineUsers(this);
		System.out.println("\n");
		sc = new Scanner(System.in);
		while(true) {
			System.out.print(">");
			msg = sc.nextLine();
			if(msg.equalsIgnoreCase("/quit")) {
				quit();
			}else if(msg.equalsIgnoreCase("/online")) {
				server.getOnlineUsers(this);
			}else if(msg.equalsIgnoreCase("/help")) {
				server.getHelp(this);
			}else if(msg.equalsIgnoreCase("/whisper") || msg.equalsIgnoreCase("/w")) {
				System.out.println("Choose a user to whisper to:");
				server.getOnlineUsers(this);
				String user = sc.nextLine();
				System.out.println("Enter your message:");
				String message = sc.nextLine();
				server.whisper(this, user, message, username);
			}
			else {
				try {
					server.broadcast(username+": "+msg);
				}catch(RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void serverMessage(Object obj) throws RemoteException {
		System.out.println(obj.toString());
	}
	
	private void quit() throws RemoteException {
		System.out.println("Good bye!");
		server.logout(this, username);
		server.broadcast(username+" has left the chat!");
		sc.close();
		System.exit(0);
	}

}
