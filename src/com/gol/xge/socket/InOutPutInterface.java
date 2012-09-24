package com.gol.xge.socket;

import java.io.InputStream;


public interface InOutPutInterface {
    /*
     * 不同的接口实现不同的读取方式
     */
    public void onRead(InputStream input);
    
    /*
     * onWrite 和getNextMessage的实现基本是固定的，所以这个接口可能写成抽象父类也可以。暂时先这样
     * 
     * 把实现了SendInterface的数据对象加到消息队列中
     */
    public void onWrite(SendInterface contentObj);
    public void onWrite(byte[] content);
    /*
     * 取得消息队列第一条
     */
    public byte[] getNextMessage();
}
