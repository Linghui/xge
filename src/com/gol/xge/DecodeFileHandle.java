package com.gol.xge;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class DecodeFileHandle extends FileHandle {

    private FileHandle fh;
    private String code = "test";
    static String key = "test";//密码
    

//    private int code = 0;
    
    public DecodeFileHandle(FileHandle fh, String code) {
        super(fh.path(), fh.type());
//        Gdx.app.log("DecodeFileHandle", "fh.path() " + fh.path());
//        Gdx.app.log("DecodeFileHandle", "fh.type() " + fh.type());
        this.fh = fh;
        this.code = code;
        
        if( this.code != null ){
            this.code += key;    
        }
    }

    public DecodeFileHandle(File file, FileType type) {
        super(file, type);
    }

    @Override
    public byte[] readBytes () {
//        Gdx.app.log("DecodeFileHandle", "readBytes");
        if( code == null ){
            return fh.readBytes();
        }
        

        int base = 2;
        int sqindex = 1;
        int enIndex = base;
        
        int fileIndex = 0;
        
        int value = -1;
        InputStream is = fh.read();
        int cx = 0;
        byte[] decodeBytes = new byte[(int) fh.length()];
//        Gdx.app.log("DecodeFileHandle", " fh.length() " + fh.length() + " " + fh.name());
        try {
            while ((value = is.read()) != -1) {
                
                if( fileIndex == enIndex){

                    decodeBytes[cx] = (byte) (value ^ code.hashCode());
                    sqindex++;
                    enIndex = (int) Math.pow(base, sqindex);
                } else {

                    decodeBytes[cx] = (byte) (value);
                }
                
                
                cx++;
                fileIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return decodeBytes;
    }
    public InputStream read () {
        return fh.read();
    }
    
    public BufferedInputStream read (int bufferSize) {
        return fh.read(bufferSize);
    }
    
    public Reader reader () {
        return fh.reader();
    }

    public Reader reader (String charset) {
        return fh.reader(charset);
    }
    
    public BufferedReader reader (int bufferSize) {
        return fh.reader(bufferSize);
    }
    
    public BufferedReader reader (int bufferSize, String charset) {
        return fh.reader(bufferSize, charset);
    }
    
    public String readString () {
        return fh.readString(null);
    }
    public String readString (String charset) {
        return fh.readString(charset);
    }
    
    @Override
    public FileHandle parent () {
//        Gdx.app.log("DecodeFileHandle ", "parent ");
        File parent = file.getParentFile();
        if (parent == null) {
            if (type == FileType.Absolute)
                parent = new File("/");
            else
                parent = new File("");
        }
        return new DecodeFileHandle(parent, type);
    }
    
    @Override
    public FileHandle child (String name) {
//        Gdx.app.log("DecodeFileHandle ", "child ");
        if (file.getPath().length() == 0) return new DecodeFileHandle(new File(name), fh.type());
        return new DecodeFileHandle(new File(file, name), type);
    }
    
    public boolean exists () {
        if( fh == null ){
            return false;
        }
        return fh.exists();
    }
    
}
