package server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import model.Game;
import model.Stats;
import util.Converter;

import java.io.File;
import java.util.HashMap;

public class Server {
  private static Selector selector;
  private static ServerSocketChannel server;
  private static HashMap<SocketChannel, Game> room = new HashMap<SocketChannel, Game>();
  private static File wordFile = new File("C://Users/happy/Desktop/Networkprogramming/HW2test2/words.txt");
  private static Converter conv = new Converter(wordFile);
  private static Boolean upAndRunning = false;
  private static ByteBuffer buffer = ByteBuffer.allocate(4096);
  private static ByteBuffer bufferOut = ByteBuffer.allocate(4096);
  private static String[] wordList;
  private static Game game;
  private static Stats currentStats;

    public static void main(String[] args) throws Exception {
      initServer();
      while(upAndRunning){
        selector.select();
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectedKeys.iterator();
        while(iterator.hasNext()){
          SelectionKey key = (SelectionKey)iterator.next();
          iterator.remove();
          if(key.isAcceptable()){
            registerClient();
          }
          if(key.isWritable()){
            sendToClient(key);
          }
          if(key.isReadable()){
            readFromClient(key);
          }
        }
      }
    }

    private static void initServer(){
      try{
        wordList = conv.getWords();
        selector = Selector.open();
        server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("127.0.0.1", 8888));
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        upAndRunning = true;
        System.out.println("Server is up and running..");
      }catch(Exception e){
        System.out.println(e);
      }
    }

    private static void registerClient(){
      try{
          SocketChannel client = server.accept();
          client.configureBlocking(false);
          client.register(selector, SelectionKey.OP_WRITE);
          System.out.println("A Player connected!");
          game = new Game(wordList);
          room.put(client, game);
          startNewGame(game);
        }catch(Exception e){
          System.out.println("A client tried to connect but failed.");
        }
    }

    private static void registerInput(Game game, String guess){
      currentStats = game.guess(guess);

    }

    private static void startNewGame(Game game){
      game.initialize();
      currentStats = game.getGameStats();
      System.out.println("Chosen word is: "+game.getChosenWord());
    }

    private static void sendToClient(SelectionKey key){
      try{
        SocketChannel client = (SocketChannel) key.channel();
        game = room.get(client);
        currentStats = game.getGameStats();
        String info = currentStats.getHiddenWord()+":"+currentStats.getAttempts()+":"+currentStats.getScore();
        bufferOut = ByteBuffer.wrap(info.getBytes());
        if(game.isNextGame()){
          startNewGame(game);
          currentStats = game.getGameStats();
          info = currentStats.getHiddenWord()+":"+currentStats.getAttempts()+":"+currentStats.getScore();
          bufferOut = ByteBuffer.wrap(info.getBytes());
          client.write(bufferOut);
        }
        client.write(bufferOut);
        bufferOut.compact();
        bufferOut.clear();
        client.register(selector, SelectionKey.OP_READ);
      }catch(Exception e){
        System.out.println("Could not send message to client!");
      }
    }

    private static void readFromClient(SelectionKey key) throws Exception {
        SocketChannel client = (SocketChannel) key.channel();
        if(client.read(buffer) != -1){
          game = room.get(client);
          client.read(buffer);
          String result = new String(buffer.array()).trim();
          System.out.println("Player wrote: "+result);
          registerInput(game, result);
          buffer.compact();
          buffer.clear();
          client.register(selector, SelectionKey.OP_WRITE);
        }else{
          buffer.clear();
        }
    }

}
