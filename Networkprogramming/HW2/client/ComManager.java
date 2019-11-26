package client;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class ComManager implements Runnable{
  private SocketChannel client;
  private Selector selector;
  private ByteBuffer bbOut = ByteBuffer.allocate(4096);
  private ByteBuffer bbIn = ByteBuffer.allocate(4096);
  private BufferedReader input = null;
  private View view;

  public ComManager(SocketChannel sc, Selector selector){
    this.client = sc;
    this.selector = selector;
    this.view = new View();
    this.input = new BufferedReader(new InputStreamReader(System.in));
  }

  public void run(){
    try{
      while(true){
        if(selector.select() > 0){
          processKeys(selector.selectedKeys());
        }
      }
    }catch(Exception e){
      System.out.println("we hit the rock bottom :-(");
    }
  }

  private void processKeys(Set<SelectionKey> keySet) throws Exception{
    Iterator<SelectionKey> iterator = keySet.iterator();
    SelectionKey key = null;
    while(iterator.hasNext()){
      key = (SelectionKey)iterator.next();
      iterator.remove();
    }
    if(key.isConnectable()){
      handleConnection(key);
    }
    if(key.isReadable()){
      readFromServer(key);
    }
    if(key.isWritable()){
      sendToServer(key);
    }
  }

  private void handleConnection(SelectionKey key){
    try{
      SocketChannel sc = (SocketChannel)key.channel();
      while(sc.isConnectionPending()){
        sc.finishConnect();
      }
    }catch(Exception e){
      key.cancel();
      System.out.println("Could not handle connection");
    }
  }

  private void sendToServer(SelectionKey key){
    try{
      bbOut.clear();
      SocketChannel sc = (SocketChannel)key.channel();
      System.out.print("\nGuess: ");
      String guess = input.readLine();
      if(guess.equals("!")){
        client.close();
        System.exit(0);
      }
      else if(!guess.isEmpty()){
        bbOut = ByteBuffer.wrap(guess.getBytes());
        sc.write(bbOut);
        bbOut.compact();
        bbOut.clear();
        sc.register(selector, SelectionKey.OP_READ);
      }
    }catch(Exception e){
      System.out.println("Could not send to server. Server might be offline.");
    }
  }

  private void readFromServer(SelectionKey key){
    try{
      SocketChannel server = (SocketChannel) key.channel();
      bbIn.clear();
      if(server.read(bbIn) != -1){
        server.read(bbIn);
        String result = new String(bbIn.array()).trim();
        view.render(result);
        bbIn.compact();
        bbIn.clear();
        server.register(selector, SelectionKey.OP_WRITE);
      }

    }
    catch(Exception e){
      System.out.println("Could not read from server");
    }
  }
}
