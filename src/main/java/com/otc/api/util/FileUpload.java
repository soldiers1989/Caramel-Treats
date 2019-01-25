package com.otc.api.util;

import com.otc.api.domain.YioSysUser;
import com.otc.api.domain.YioUser;
import com.otc.api.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileUpload {

    /**
     *
     * @param url
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(String url, MultipartFile file, YioSysUser user) throws Exception {
        if (user==null){
            throw new MyException("40002");
        }
        if (file.isEmpty()) {
            throw new MyException("60001");
        }
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newfileName = String.valueOf(System.currentTimeMillis());
        String strPath =getPath(user.getId());
        File fileUrl =new File(url+strPath);
        if(!fileUrl.exists() && !fileUrl.isDirectory()){
            fileUrl.mkdirs();
        }
        String path = fileUrl +"/"+ newfileName + "." + prefix;
        File filePath = new File(path);
        file.transferTo(filePath);
        return strPath+"/"+ newfileName + "." + prefix;
    }

    public static String getPath(Integer id){
        String path ="";
        String str = "00000000000";
        String s = str.substring(0,11-id.toString().length())+id;
        for (int i=0;i<11;i=i+3){
            if (i>str.length()-3){
                path = path+"/"+s.substring(i,str.length());
            }else{
                path = path+"/"+s.substring(i,i+3);
            }
        }
        return path;
    }
}
