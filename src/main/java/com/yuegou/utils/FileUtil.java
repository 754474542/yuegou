package com.yuegou.utils;

import com.yuegou.controller.pretreatment.Code;
import com.yuegou.controller.pretreatment.exceptionhandle.FileFailedException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileUtil {

    public static boolean saveFile(MultipartFile multipartFile,String url) {
        File files = new File(url);
        if (!files.isFile()){
            try {
                files.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (
                InputStream is = multipartFile.getInputStream();
                OutputStream os = new FileOutputStream(url);
        ) {
            byte[] bytes = new byte[1024 * 8];
            int read;
            while ((read = is.read(bytes)) != -1){
                os.write(bytes,0,read);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return files.isFile();
    }

    public static boolean deleteFile(String url){
        File file = new File(url);
        return file.delete();
    }

    public static byte[] queryFile(String url){
        File file = new File(url);
        System.out.println(url);
        if (!file.isFile()) throw new FileFailedException(Code.SELECT_ERR,"文件不存在");
        try(
            FileInputStream fis = new FileInputStream(file);
            ){
            byte [] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] fileToByte(String path) {
        File img = new File(path);
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(bytes);
        return bytes;
    }

}
