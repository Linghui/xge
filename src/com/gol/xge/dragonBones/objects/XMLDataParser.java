package com.gol.xge.dragonBones.objects;

import java.util.zip.Inflater;

import javax.print.DocFlavor.BYTE_ARRAY;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.gol.xge.dragonBones.utils.BytesType;
import com.gol.xge.util.SByteBuffer;
import com.sun.corba.se.impl.ior.ByteBuffer;

public class XMLDataParser {
    
    public static SkeletonAndTextureAtlasData paresXMLData(byte[] bytes) throws Exception{
        String type = BytesType.getType(bytes);
        System.out.println("type " + type);
        System.out.println("SkeletonAndTextureAtlasData paresXMLData type" + type);
        if(type.equals(BytesType.SWF)
                || type.equals(BytesType.PNG)
                || type.equals(BytesType.JPG)){

            // read skeleton xml
            int index = bytes.length - 4;
            byte[] lengthBytes = new byte[4];
            
            System.arraycopy(bytes, index, lengthBytes, 0, 4);
            int skeletonXmlength = BytesType.byteToInt(lengthBytes);
            
            byte[] skeletonXmlBytes = new byte[skeletonXmlength];
            System.arraycopy(bytes, index - skeletonXmlength, skeletonXmlBytes, 0, skeletonXmlength);
            
            System.out.println("total size " + bytes.length);
            System.out.println("skeleton xml size " + skeletonXmlength);
            
            Inflater decompresser = new Inflater();
            decompresser.setInput(skeletonXmlBytes, 0, skeletonXmlength);
            byte[] result = new byte[65535];
            int resultLength = decompresser.inflate(result);
            decompresser.end();
            System.out.println(" resultLength " + resultLength);
            System.out.println(" result " + new String(result));
            // read skeleton xml done
            
            // read texture xml
            index = index - skeletonXmlength - 4;
            lengthBytes = new byte[4];
            System.arraycopy(bytes, index, lengthBytes, 0, 4);
            int textureXmlength = BytesType.byteToInt(lengthBytes);
            
            System.out.println("texture xml size " + textureXmlength);
            byte[] textureXmlBytes = new byte[textureXmlength];
            System.arraycopy(bytes, index - textureXmlength, textureXmlBytes, 0, textureXmlength);
            
            decompresser = new Inflater();
            decompresser.setInput(textureXmlBytes, 0, textureXmlength);
            result = new byte[65535];
            resultLength = decompresser.inflate(result);
            decompresser.end();

            System.out.println(" resultLength " + resultLength);
            System.out.println(" result " + new String(result));
            // read texture xml done
            
            // read texture data
            index = index - textureXmlength;
            byte[] textureBytes = new byte[index];
            System.arraycopy(bytes, 0, textureBytes, 0, index);
            
            FileHandle handle = Gdx.files.external("data/test.png");
            handle.writeBytes(textureBytes, false);
            
            
        } else if (type.equals(BytesType.ZIP)){
            throw new Exception("Uncompress Error");
        } else {
            throw new Exception("Unknown Data Error");
        }
        
        return null;
    }
}
