package com.gol.xge;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class DecodeFileHandle extends FileHandle {

    private FileHandle fh;
    private String code = "test";
    public DecodeFileHandle(FileHandle fh, String code) {
        super(fh.path(), fh.type());
        this.fh = fh;
        this.code = code;
    }

    public DecodeFileHandle(File file, FileType type) {
        super(file, type);
    }

    @Override
    public byte[] readBytes () {
        Gdx.app.log("DecodeFileHandle", "readBytes");
        if( code == null ){
            return super.readBytes();
        }
        int value = -1;
        InputStream is = super.read();
        int cx = 0;
        byte[] decodeBytes = new byte[(int) super.length()];
        Gdx.app.log("DecodeFileHandle", " super.length() " + super.length() + " " + fh.name());
        try {
            while ((value = is.read()) != -1) {
                decodeBytes[cx] = (byte) (value ^ code.hashCode());
                cx++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return decodeBytes;
    }

    @Override

    public FileHandle parent () {
        Gdx.app.log("DecodeFileHandle ", "parent ");
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
        Gdx.app.log("DecodeFileHandle ", "child ");
        if (file.getPath().length() == 0) return new DecodeFileHandle(new File(name), fh.type());
        return new DecodeFileHandle(new File(file, name), type);
    }
    
}
