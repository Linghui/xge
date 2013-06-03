import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;


public class Jiami {

    
    static String key = "test";//密码
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("jiami start");
        
        Calendar cal = Calendar.getInstance();
        System.out.println(" " + cal.getTime());
        
        String path = "/Users/xiaohuidexinge/test/jiami";
        jiamiDir(path);

        cal = Calendar.getInstance();
        System.out.println(" " + cal.getTime());
        
        System.out.println("jiami end");
    }

    
    
    public static void jiamiDir( String path ){
        
        System.out.println("jiamiDir start " + path);
        
        File dir = new File(path);
        File file[] = dir.listFiles();
        
        for (int i = 0; i < file.length; i++) {
            
            if( file[i].getName() .equals(".DS_Store")){
                continue;
            }
            
            if (file[i].isDirectory()){
                if( file[i].getAbsolutePath().endsWith("font")){
                    System.out.println("skip font " + path);
                    continue;
                } else {
                    jiamiDir(file[i].getAbsolutePath());    
                }
            }
            else{
                
                if( file[i].getAbsolutePath().endsWith("png")
                        || file[i].getAbsolutePath().endsWith("jpg") ){
                    jiamiFile(file[i].getAbsolutePath());    
                }
//                System.out.println(file[i].getAbsolutePath());
            }
        }
        
    }
    
    public static void jiamiFile( String file ){
        System.out.println("jiamiFile start " + file);
        
        RandomAccessFile raf = null;
        
        try {
            raf = new RandomAccessFile(file, "rw");

            int value = -1;
            while ((value = raf.read()) != -1) {
                long pointer = raf.getFilePointer();
                raf.seek(pointer - 1);
                raf.write(value ^ key.hashCode());
                //每个字节异或密码，请保证解密时密码前后相同
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("jiamiFile end");
    }
    
}
