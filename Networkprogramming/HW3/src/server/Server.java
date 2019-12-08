package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import database.UserDatabase;

public class Server {

  private static final int DEFAULT_PORT = 1099;
  private static StubManager cat;

    public Server() throws RemoteException {
      //Empty constructor
    }

    public static void main(String args[]) {
        try {
        	cat = new StubManager();
            register();
            Naming.rebind("Catalogue", cat);
            
            System.out.println("Server up and running on port "+ DEFAULT_PORT+".");
            UserDatabase db = new UserDatabase();
            System.out.println("*CURRENT REGISTERED USERS*");
            db.getAllUsers();
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

