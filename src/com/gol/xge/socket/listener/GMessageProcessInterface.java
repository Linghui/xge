package com.gol.xge.socket.listener;

public interface GMessageProcessInterface {
    public void sendMessage(byte[] message);
    public boolean processMessage(byte[] message);
}
