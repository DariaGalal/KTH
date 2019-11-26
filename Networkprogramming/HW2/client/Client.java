package client;

import java.net.*;
import java.nio.channels.*;

public class Client {
   private static Selector selector;
   private static SocketChannel sc;
   private static Boolean connected = false;
   private static Boolean initialized = false;

   public static void main(String[] args) throws Exception {
     initConnection();
     if(initialized && connected){
       Thread thr = new Thread(new ComManager(sc, selector));
       thr.start();
       }
   }

   private static void connect(){
     try{
       InetSocketAddress addr = new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8888);
       sc.connect(addr);
       sc.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
       connected = true;
     }catch(Exception e){
       System.out.println("Could not connect to server!");
     }
   }

   private static void initConnection(){
     try{
       selector = Selector.open();
       sc = SocketChannel.open();
       sc.configureBlocking(false);
       initialized = true;
       connect();
     }catch(Exception e){
       System.out.println("Could not initilize connection.");
     }
   }
}
