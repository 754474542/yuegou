package com.yuegou.utils;

import org.springframework.web.multipart.MultipartFile;

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

}
