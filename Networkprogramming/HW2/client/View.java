package client;

public class View{
  private final String WELCOME = "Welcome to the Hangman game!\nType '!' to quit at any time.\n\n";
  private final String HEADER = "\n|                  WORD                  |    ATTEMPTS LEFT    |    SCORE    |\n"
                                  +"------------------------------------------------------------------------------\n";
  private String hiddenWord;
  private String attempts;
  private String score;

  public View(){
    System.out.println(WELCOME);
    System.out.println(HEADER);
  }

  public void render(String message){
    store(message);
    display();
    //System.out.println(message);
  }

  private void store(String message){
    String[] splitter = message.split(":");
    hiddenWord = splitter[0];
    attempts = splitter[1];
    score = splitter[2];
  }

  private void display(){
	    String display = "";
	    String firstSpace = "                 ";
	    String secondSpace = "                 ";
	    int offset = 51-((hiddenWord.length())*2);
	    for(int i = 0; i<offset; i++){
	      firstSpace += " ";
	    }
	    display = hiddenWord+firstSpace+attempts+secondSpace+score;
	    System.out.println("\n"+display);
	  }
}
