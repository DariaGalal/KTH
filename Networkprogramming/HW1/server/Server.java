import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

  private static final int DEFAULT_PORT = 8888;
  private static ServerSocket srvSocket;
  private static Socket clSocket;
  private static File wordFile = new File("C://Users/happy/Desktop/Networkprog/HW1/utils/words.txt");
  private static Converter conv = new Converter(wordFile);
  private static String[] wordList;
  private static int sessionID = 1;

    public static void main(String[] args) {
      try{
        srvSocket = new ServerSocket(DEFAULT_PORT);
        wordList = conv.getWords();

        while(true){
          System.out.println("Local server is up on port " + DEFAULT_PORT +", waiting for client connections...\n");
          clSocket = srvSocket.accept();
          System.out.println("------------------------\nPlayer "+sessionID+" has connected!\n------------------------");
          Thread thread = new Thread(new Session(clSocket, wordList, sessionID));
          thread.start();
          sessionID++;
        }
      }
      catch(Exception e){
        System.out.println(e.getLocalizedMessage());
      }
  }
}
