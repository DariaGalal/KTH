package model;

public class Stats {
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

	  public String getScore(){
	    return Integer.toString(score);
	  }

	  public String getAttempts(){
	    return Integer.toString(attempts);
	  }

    public int getIntAttempts(){
      return attempts;
    }

	  public void reduceAttempts(){
	    this.attempts--;
	  }

	  public String getHiddenWord(){
	    return convert(hiddenWord);
	  }

	  public void updateHiddenWord(String[] word){
	    this.hiddenWord = word;
	  }

    private String convert(String[] array){
      String s = "";
      for(int i = 0; i<array.length; i++){
        s += array[i]+" ";
      }
      return s;
    }

	}
