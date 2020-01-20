package projectChatRMI.id1212.client;
//"ChatClientDriver"

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.util.Scanner;

import projectChatRMI.id1212.server.ServerIF;

public class Client {
  private static Registry registry;
  private static Scanner sc = new Scanner(System.in);
  private static ServerIF stub;
  private static ClientResponse obs;
  private static final String HELP = "-Type 'login' to login\n-Type 'register' register a new user\n-Type 'Cancel' to cancel an operation\n-Type 'Help' to display these options again.";
  private static Boolean loggedIn = false;
  
    private Client(){
      //Empty constructor
    }

    public static void main(String[] args) {
        try {
            connectToRegistry();
            stub = (ServerIF) registry.lookup("Chat");
            obs = new ClientResponse();
            System.out.println("***CHAT ROOM 2000***\n");
            System.out.println(HELP);
            while(sc.hasNext()){
               if(loggedIn) {
              	 break;
               }
              String input = sc.nextLine();
              switch(input) {
  				case("login"):
  					String[] info = new String[2];
  	                System.out.println("*Enter your username*");
  	                info[0] = sc.nextLine();
  	                System.out.println("*Enter your password*");
  	                info[1] = sc.nextLine();
  					if(input.equalsIgnoreCase("cancel")) {
  						System.out.println("Operation canceled!");
  						continue;
  					}
  		   			else if(input.isBlank() || input.isEmpty()) {
  		   				System.out.println("Fields cannot be blank!");
  		   				continue;
  		   			}
  		   			else if(input.equalsIgnoreCase("!")) {
  		   				System.out.println("Goodbye!");
  		   				quit();
  		   			}
  	    			login(info);
  	    			break;
  	    			
  				case("register"):
  					String[] newUser = new String[2];
  					System.out.println("*Enter a new and unique username*");
  					newUser[0] = sc.nextLine();
  					System.out.println("*Enter a password*");
  					newUser[1] = sc.nextLine();
  					if(input.equalsIgnoreCase("cancel")) {
  						System.out.println("Operation canceled!");
  						continue;
  					}
  		   			else if(input.isBlank() || input.isEmpty()) {
  		   				System.out.println("Fields cannot be blank!");
  		   				continue;
  		   			}
  		   			else if(input.equalsIgnoreCase("!")) {
  		   				System.out.println("Goodbye!");
  		   				quit();
  		   			}
  	                regNewUser(newUser); 
  					break;
  					
  				case("!"):
  					System.out.println("Good bye!");
  					quit();
  					
  				case("help"):
  					System.out.println(HELP);
  					break;
  					
  				default:
  					System.out.println("Not a valid command!");
  					break;
              }
            }
        }
            catch(Exception e) {
            	System.out.println("Server seems offline, try again later!");
            	e.printStackTrace();
            	quit();
            }
    }

    private static void connectToRegistry() throws RemoteException{
      try{
        LocateRegistry.getRegistry().list();
        registry = LocateRegistry.getRegistry(null);
      }catch(RemoteException noRegistryFound){
        System.out.println("Server seems to be offline, try again later.");
        quit();
      }
    }

    private static void quit(){
      System.exit(0);
    }

    private static void login(String[] input) throws RemoteException{
      if(input[1].isBlank() || input[1].isEmpty()) {
    	  return;
      }
      Boolean clearance = stub.login(input[0], input[1], obs);
      if(clearance) {
    	  new Chat(stub, input[0]).start();
    	  //chat.start();
    	  loggedIn = true;
      }
    }

    private static void regNewUser(String[] input) throws RemoteException{
      if(input[1].isBlank() || input[1].isEmpty()) {
      	  return;
      }
      stub.registerNewUser(input[0], input[1], obs);
    }
}


