package util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Converter{
	  private String[] words;
	  private FileReader fr;
	  private BufferedReader br;
	  private ArrayList<String> list;

	  public Converter(File file){
	    this.words = makeList(file);
	  }

	  public String[] getWords(){
	    return words;
	  }

	  private String[] makeList(File wordList){
	    try{
	      FileReader fileReader = new FileReader(wordList);
	      BufferedReader bufferedReader = new BufferedReader(fileReader);
	      ArrayList<String> list = new ArrayList<String>();
	      String word = "";
	        while ((word = bufferedReader.readLine()) != null){
	          list.add(word);
	        }
	        bufferedReader.close();
	        return list.toArray(new String[list.size()]);
	      }
	      catch(Exception e){
	        System.out.println("File error: " + e);
	      }
	      return null;
	    }
	}
