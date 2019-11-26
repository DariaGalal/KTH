import java.io.Serializable;
import java.util.*;

public class Stats implements Serializable{
  private int attempts;
  private int score;
  private String[] hiddenWord;

  public Stats(String[] hiddenWord, int attempts){
    this.hiddenWord = hiddenWord;
    this.attempts = attempts;
  }

  public Stats(String[] hiddenWord, int attempts, int score){
    this.hiddenWord = hiddenWord;
    this.attempts = attempts;
    this.score = score;
  }

  public int getScore(){
    return this.score;
  }

  public int getAttempts(){
    return this.attempts;
  }

  public void reduceAttempts(){
    this.attempts--;
  }

  public String[] getHiddenWord(){
    return this.hiddenWord;
  }

  public void updateHiddenWord(String[] word){
    this.hiddenWord = word;
  }

}
