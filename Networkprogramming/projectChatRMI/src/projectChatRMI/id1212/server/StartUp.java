package projectChatRMI.id1212.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import projectChatRMI.id1212.database.DatabaseHandler;

public class StartUp {
	
	private static Server server;
	private static final int DEFAULT_PORT = 1099;
	
    public static void main(String args[]) {
        try {
        	System.setProperty("java.rmi.server.hostname","192.168.56.1");
        	server = new Server(new DatabaseHandler());
            register();
            Naming.rebind("Chat", server);
            
            System.out.println("Server up and running on port "+ DEFAULT_PORT+".");
            DatabaseHandler database = new DatabaseHandler();
            System.out.println("*CURRENT REGISTERED USERS*");
            database.getAllUsers();
            System.out.println("---------------------------");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private static Registry register() throws RemoteException{
      try{
        //if there already is a registry - use it
        LocateRegistry.getRegistry().list();
        return LocateRegistry.getRegistry();
      }catch(RemoteException noRegistry){
        //if there is no registry, create a new one with the default port and use it
        return LocateRegistry.createRegistry(DEFAULT_PORT);
      }
    }
}
