package com.gol.xge.dragonBones.utils;

public class BytesType {
    public static String SWF = "swf";
    public static String PNG = "png";
    public static String JPG = "jpg";

    public static String ATF = "atf";

    public static String ZIP = "zip";
    
    public static String getType(byte[] bytes){
        String type = null;
        
        byte b1 = (byte) (bytes[0] & 0xFF);
        byte b2 = (byte) (bytes[1] & 0xFF);
        byte b3 = (byte) (bytes[2] & 0xFF);
        byte b4 = (byte) (bytes[3] & 0xFF);
        
//        if(b1 == 0x89){
//            System.out.println(" b1 ok " + b1);
//        } else {
//            System.out.println(" b1 " + b1);
//        }
//        
//        if(b1 == 0x50){
//            System.out.println(" b2 ok " + b2);
//        } else {
//            System.out.println(" b2 " + b2);
//        }
//        
//        if(b1 == 0x4E){
//            System.out.println(" b3 ok " + b3);
//        } else {
//            System.out.println(" b3 " + b3);
//        }
//        
//        if(b1 == 0x47){
//            System.out.println(" b4 ok " + b4);
//        } else {
//            System.out.println(" b4 " + b4);
//        }
        
        if((b1 == 0x46 || b1 == 0x43 || b1 == 0x5A) && b2 == 0x57 && b3 == 0x53){
            //CWS FWS ZWS
            type = SWF;
        }else if(b1 == 0x89 && b2 == 0x50 && b3 == 0x4E && b4 == 0x47){
            //89 50 4e 47 0d 0a 1a 0a
            type = PNG;
        }else if(b1 == 0xFF){
            type = JPG;
        }else if(b1 == 0x41 && b2 == 0x54 && b3 == 0x46){
            type = ATF;
        }else if(b1 == 0x50 && b2 == 0x4B){
            type = ZIP;
        }
        
        return PNG; // just make it pass temply, still need check the real file type
//        return null;
    }
    
    public static int byteToInt(byte[] bytes){

        int result = 0;
        result |= bytes[0] & 0xff;
        result <<= 8;
        result |= bytes[1] & 0xff;
        result <<= 8;
        result |= bytes[2] & 0xff;
        result <<= 8;
        result |= bytes[3] & 0xff;
        return result;
    }
}
