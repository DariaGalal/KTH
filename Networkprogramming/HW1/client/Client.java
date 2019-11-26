//package client;
import java.util.*;
import java.net.*;
import java.io.*;

public class Client{
  private static Socket socket;
  private static final int PORT = 8888;
  private static DataOutputStream toServer;
  private static View view;
  private static Scanner input;
  private static String message;

  private static void connect(){
    try{
      InetAddress host = InetAddress.getByName("127.0.0.1");
      SocketAddress server = new InetSocketAddress(host, PORT);
      socket = new Socket();
      socket.connect(server);
    }catch(Exception e){
      System.out.println(e+"\nCould not connect to server.");
    }
  }

  public static void main(String[] args){
    //main will be the thread where we read the users input
    //thread will read server input
    input = new Scanner(System.in);
    welcome();
    message = input.nextLine();
    if(message.equals("!")){
      quit();
    }
    else if(message.equalsIgnoreCase("s")){
      input.reset();
      connect();
      if(socket.isConnected()){
        createStream();
        Thread thread = new Thread(new View(socket));
        thread.start();
      }
      else{
        quit();
      }
      while(!socket.isClosed()){
        message = input.nextLine();
        if(message.equals("!")){
          quit();
        }
          try{
            toServer.writeUTF(message);
            toServer.flush();
          }catch(Exception e){
            System.out.println("Lost connection to server");
          }
      }
    }
  }

  private static void welcome(){
    String welcome =
    "*************THE HANGMAN GAME*************\n\r"
    +"-------------------------------------------------------------\n\r"
    +"*RULES*\n\r"
    +"|A random word is chosen for you to guess.\n\r"
    +"|You can guess one letter at a time or the WHOLE word, not parts of it.\n\r"
    +"|You have the same number of attempts as number of letters in the word.\n\r"
    +"|The number of attempts left will reduce for each attempt you made.\n\r"
    +"|You will recieve one point for each successfully guessed word.\n\r"
    +"|One point will be deducted for each failed game.\n\r"
    +"--------------------------------------------------------------\n\r"
    +"*COMMANDS*\n\r"
    +"|Press 'S' to start the game.\n\r"
    +"|Type '!' to quit at any time.\n\r"
    +"-------------------------------------------------------------\n\r";
    System.out.println(welcome);
  }

  private static void createStream(){
    try{
      toServer = new DataOutputStream(socket.getOutputStream());
    }catch(Exception e){}
  }

  private final static void quit(){
    try{
      System.out.println("Good bye!");
      toServer.close();
      socket.close();
      System.exit(0);
    }catch(Exception e){

    }
  }
}
