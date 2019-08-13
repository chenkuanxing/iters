package com.xinghui.utils;

import com.xinghui.exception.CustException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class FileUtils {
    public static void  fileUpload(MultipartFile file,String url,String newFileName){
        try {
            if (file.isEmpty()) {
                throw new CustException("文件为空！");
            }
            // 设置文件存储路径
            String replace = url.replace("\\:", ":").replace("\\\\", "\\").replace("&", "\\");
            String path = replace + "/" + newFileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
