package com.gol.xge.socket.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.gol.xge.socket.SendInterface;
import com.gol.xge.socket.listener.InOutPutInterface;
/*
 * 一个实现的InOutPutInterface ，按行读取数据
 * 
 */
public class MessageHandlerA implements InOutPutInterface {
    
    List<byte[]> messageQueue = new ArrayList<byte[]>();

    @Override
    public void onRead(InputStream input) {
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        try {
            String instr = in.readLine();
            System.out.println("onRead " + instr );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void onWrite(SendInterface contentObj) {
        
        onWrite(contentObj.toByte());
    }

    @Override
    public byte[] getNextMessage() {
        if(messageQueue.size() > 0 ){
            byte[] temp = messageQueue.remove(0);
            System.out.println("getNextMessage " +temp);
            return temp;
        }
        return null;
    }

    @Override
    public void onWrite(byte[] content) {
        System.out.println("onWrite " +content);
        messageQueue.add(content);
        
    }

}
