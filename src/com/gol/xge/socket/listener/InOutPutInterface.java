package com.gol.xge.socket.listener;

import java.io.InputStream;

/*
 * 实现这个接口用于跟服务器进行通讯
 * 引擎会提供几个通用的接口实现 比如
 *  MessageHandlerA
 */

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
    public void onWrite(byte[] content);
    /*
     * 取得消息队列第一条
     */
    public byte[] getNextMessage();
}
