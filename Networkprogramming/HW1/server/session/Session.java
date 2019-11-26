import java.io.*;
import java.net.*;

public class Session implements Runnable{
  private Socket socket;
  private Game game;
  private int sessionID;
  private ObjectOutputStream toPlayer;
  private DataInputStream fromPlayer;
  private String message;

  public Session(Socket socket, String[] wordList, int sessionID){
    this.socket = socket;
    this.game = new Game(wordList);
    this.sessionID = sessionID;
    this.message = "";
    try{
      this.toPlayer = new ObjectOutputStream(socket.getOutputStream());
      this.fromPlayer = new DataInputStream(socket.getInputStream());
    }catch(Exception e){
      System.out.println("Could not create session");
    }
  }

  public void run(){
    game.initialize();
    System.out.println("Player "+sessionID+" started a game with the word '"+game.getChosenWord()+"'");
    write(game.getGameStats());
    while(!socket.isClosed()){
      write(game.guess(listen()));
      if(game.isNextGame()){
        game.initialize();
        write(game.getGameStats());
        System.out.println("Player "+sessionID+" started another game with the word '"+game.getChosenWord()+"'");
      }
    }
    quit();
  }

  private void write(Stats info){
    try{
      //Thread.sleep(3000);
      toPlayer.writeObject(info);
      toPlayer.flush();
      toPlayer.reset();
    }catch(Exception e){
    }
  }

  private String listen(){
    try{
      String playerInput = fromPlayer.readUTF();
      return playerInput;
    }
    catch(Exception e){
      quit();
    }
    return null;
  }

  private void quit(){
    try{
      toPlayer.close();
      fromPlayer.close();
      socket.close();
      System.out.println("Player "+sessionID+" disconnected.");
    }catch(Exception e){

    }
  }

}
