package com.gol.xge.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.gol.xge.socket.SocketManager;
import com.gol.xge.socket.implement.MessageHandlerA;

public class SocketTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Socket Testing Start");
        
        SocketTest SocketTest = new SocketTest();
        
        MessageHandlerA c1 = new MessageHandlerA();
        
        SocketManager SocketManager = new SocketManager("127.0.0.1",9876);
        SocketManager.setInOutPutInterface(c1);
        
        c1.onWrite("test\n".getBytes());
        
        SocketTest.listener.stopListener();
        
        System.out.println("Socket Testing End");
        
    }
    
    TestServerListener listener = null;
    
    public SocketTest(){
        listener = new TestServerListener();
        new Thread(listener).start();
    }
    
    public class TestServerListener implements Runnable {

        private static final String TAG = "ServerListener";
        private Socket client = null;
        private boolean isRunnable = false;

        public TestServerListener(){
            System.out.println(TAG + ":TestServerListener");
            
            isRunnable = true;
        }

        public void run() {
            System.out.println(TAG + ":listener start run !");
          ServerSocket server;
          try {
              server = new ServerSocket(9876);
              client = server.accept();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
            try {
                BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out=new PrintWriter(client.getOutputStream());
                while(isRunnable){
                    String str=in.readLine();
                    System.out.println(str);
                    out.println("has receive....");
                    out.flush();
                    if(str == null || "end".equals(str))
                       break;
                }       
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public void stopListener() {
            isRunnable = false;
        }

    }

}


