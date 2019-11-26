import java.io.*;
import java.net.*;

public class View implements Runnable{
  private Socket socket;
  private ObjectInputStream fromServer;
  private String message = "";
  private int remainingAttempts;
  private int currentScore;
  private String[] hiddenWord;
  private final String GAME_LAYOUT = "\n|                  WORD                  |    ATTEMPTS LEFT    |    SCORE    |\n"
                                  +"------------------------------------------------------------------------------\n";

  public View(Socket socket){
    try{
      this.socket = socket;
      this.fromServer = new ObjectInputStream(socket.getInputStream());
    }catch(Exception e){

    }
  }

  public void run(){
    System.out.println(GAME_LAYOUT);
    while(!socket.isClosed()){
      interpret(listen());
      render();
    }
  }

  private Stats listen(){
    try{
      return (Stats)fromServer.readObject();
    }catch(Exception e){
      System.out.println("Lost connection to server!");
    }
    return null;
  }

  private void interpret(Stats info){
    this.remainingAttempts = info.getAttempts();
    this.currentScore = info.getScore();
    this.hiddenWord = info.getHiddenWord();
    try{
      fromServer.reset();
    }catch(Exception e){}
  }

  private void render(){
    String display = "";
    String firstSpace = "";
    String secondSpace = "                 ";
    int offset = 51-((hiddenWord.length)*3);
    for(int i = 0; i<offset; i++){
      firstSpace += " ";
    }
    String word = beautify();
    display = word+firstSpace+remainingAttempts+secondSpace+currentScore;
    System.out.println(display);
  }

  private String beautify(){
    String word = "";
    for(int i = 0; i<this.hiddenWord.length; i++){
      word += hiddenWord[i]+", ";
    }
    return word;
  }
}
