package model;

public class Game{
	  private String[] wordList;
	  private String[] hiddenWord;
	  private String chosenWord;
	  private Stats gameStats;
	  private int score;
	  private boolean nextGame = false;

	  public Game(String[] wordList){
	    this.wordList = wordList;
	    this.score = 0;
	  }

	  public void initialize(){
	    chosenWord = generateRandomWord(wordList);
	    makeHidden(chosenWord);
	    gameStats = new Stats(hiddenWord, chosenWord.length(), score);
	  }

	  public Stats guess(String guess){
	    this.nextGame = false;
	    if(guess.length() == 1){
	      this.gameStats.reduceAttempts();
	      updateLetter(guess);
	    }
	    else if(guess.length() == chosenWord.length()){
	     	this.gameStats.reduceAttempts();
	      updateWord(guess);
	    }
	    return winLoseOrContinue();
	  }

	  private Stats winLoseOrContinue(){
	    if(isEqual()){
	      this.score++;
	      this.nextGame = true;
	      return this.gameStats;
	    }
	    else if(gameStats.getIntAttempts() < 1){
	      score--;
	      this.nextGame = true;
	      return this.gameStats;
	    }
	    this.nextGame = false;
	    return this.gameStats;
	  }

	  private boolean isEqual(){
	    String word = arrayToString(hiddenWord);
	    if(word.equalsIgnoreCase(chosenWord)){
	      return true;
	    }
	    else{
	      return false;
	    }
	  }

	  private String arrayToString(String[] array){
	    String word = "";
	    for(int i = 0; i < array.length; i++){
	      word += array[i];
	    }
	    return word;
	  }

	  private void updateLetter(String guess){
	    for(int i = 0; i<hiddenWord.length; i++){
	      if(guess.charAt(0)==Character.toUpperCase(chosenWord.charAt(i)) || Character.toUpperCase(guess.charAt(0))==chosenWord.charAt(i) || Character.toUpperCase(guess.charAt(0))==Character.toUpperCase(chosenWord.charAt(i))){
	        hiddenWord[i] = guess.toLowerCase();
	      }
	    }
	    gameStats.updateHiddenWord(hiddenWord);
	  }

	  private void updateWord(String guess){
	    if(guess.equalsIgnoreCase(chosenWord)){
	      for(int i = 0; i<chosenWord.length(); i++){
	        hiddenWord[i] = String.valueOf(chosenWord.charAt(i));
	      }
	    }
	    gameStats.updateHiddenWord(hiddenWord);
	  }

	  private void makeHidden(String word){
	    hiddenWord = new String[word.length()];
	    for(int i = 0; i<word.length(); i++){
	      hiddenWord[i] = "_";
	    }
	  }

	  private String generateRandomWord(String[] wordList){
	    int randomNumber = (int)(Math.random()*(wordList.length+1));
	    String randomWord = wordList[randomNumber];
	    return randomWord;
	  }

	  public String getChosenWord(){
	    return chosenWord;
	  }

		public Stats getGameStats(){
			return gameStats;
		}

	  public boolean isNextGame(){
	    return nextGame;
	  }

	}
