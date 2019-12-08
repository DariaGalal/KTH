package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;

import model.Catalogue;

public class Client {
  private static Registry registry;
  private static Scanner sc = new Scanner(System.in);
  private static Catalogue stub;
  private static ResponseInterface obs;

    private Client(){
      //Empty constructor
    }

    public static void main(String[] args) {
        try {
            connectToRegistry();
            stub = (Catalogue) registry.lookup("Catalogue");
            obs = new ResponseHandler();
            System.out.println("***FILE HANDLER 2000***\n");
            System.out.println("-Type 'login' to login\n-Type 'register' register a new user\n-Type 'Cancel' to cancel an operation\nType 'Help' to display these options again.");
            while(sc.hasNext()){
              String input = sc.nextLine();
              if(input.equals("!")){
                quit();
              }
              if(input.equalsIgnoreCase("help")) {
            	  System.out.println("-Type 'login' to login\n-Type 'register' register a new user\n-Type '!' to quit\nType 'Help' to display these options again.");
              }
              if(input.equalsIgnoreCase("login")){
                System.out.println("*Enter your username and password*");
                input = sc.nextLine();
    			if(input.equalsIgnoreCase("cancel")) {
    				System.out.println("Operation canceled!");
    				continue;
    			}
    			login(input);
              }
              if(input.equalsIgnoreCase("register")){
                System.out.println("*Enter a new username and password*");
                input = sc.nextLine();
    			if(input.equalsIgnoreCase("cancel")) {
    				System.out.println("Operation canceled!");
    				continue;
    			}
                regNewUser(input); 
              }
            }
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    private static void connectToRegistry() throws RemoteException{
      try{
        LocateRegistry.getRegistry().list();
        registry = LocateRegistry.getRegistry(null);
      }catch(RemoteException noRegistryFound){
        System.out.println("Server seems to be offline, try again later.");
      }
    }

    private static void quit(){
      System.out.println("Good bye!");
      System.exit(0);
    }

    private static void login(String input) throws RemoteException{
      String[] nameAndPass = input.split(" ");
      Boolean clearance = stub.login(nameAndPass[0], nameAndPass[1], obs);
      if(clearance) {
    	  View view = new View(stub, nameAndPass[0], obs);
    	  view.handle();
      }
    }

    private static void regNewUser(String input) throws RemoteException{
      String[] nameAndPass = input.split(" ");
      stub.registerNewUser(nameAndPass[0], nameAndPass[1], obs);
    }
}

