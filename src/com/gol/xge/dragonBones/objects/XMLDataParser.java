package com.gol.xge.dragonBones.objects;

import java.io.IOException;
import java.util.zip.Inflater;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.gol.xge.dragonBones.utils.BytesType;

public class XMLDataParser {
    
    public static SkeletonAndTextureAtlasData paresXMLData(byte[] bytes) throws Exception{
        SkeletonAndTextureAtlasData atlasData = null;
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
            String skeletonXmlStr = new String(result);
            System.out.println(" result " + skeletonXmlStr);
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
            String textureXmlStr = new String(result);
            System.out.println(" result " + textureXmlStr);
            // read texture xml done
            
            // read texture data
            index = index - textureXmlength;
            byte[] textureBytes = new byte[index];
            System.arraycopy(bytes, 0, textureBytes, 0, index);
            
            FileHandle handle = Gdx.files.external("data/test.png");
            handle.writeBytes(textureBytes, false);
            
            XmlReader reader = new XmlReader();
            Element skeletonElement = reader.parse(skeletonXmlStr);
            Element textureElement = reader.parse(textureXmlStr);
            
            atlasData = new SkeletonAndTextureAtlasData(skeletonElement, textureElement, textureBytes);
            
        } else if (type.equals(BytesType.ZIP)){
            throw new Exception("Uncompress Error");
        } else {
            throw new Exception("Unknown Data Error");
        }
        
        return atlasData;
    }

    public static TextureAtlas paresTextureData(Element textureElement,
            byte[] textureBytes) {

        TextureAtlas atlas = new TextureAtlas();
        Texture texture = null;
        try {
            Pixmap pixmap = new Pixmap(new Gdx2DPixmap(textureBytes, 0, textureBytes.length, 0));
            texture = new Texture(pixmap); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        Array<Element>  subTextureElement = textureElement.getChildrenByName("SubTexture");
        for(Element element : subTextureElement){
            System.out.println(element.getAttribute("name"));
            atlas.addRegion(element.getAttribute("name")
                            , texture
                            , element.getIntAttribute("x")
                            , element.getIntAttribute("y")
                            , element.getIntAttribute("width")
                            , element.getIntAttribute("height"));
        }
        
        return atlas;
    }
}
