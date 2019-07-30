package com.xinghui.utils;

import com.xinghui.exception.CustException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FileUtils {
    public static void  fileUpload(MultipartFile file,String url){
        try {
            URL save = Thread.currentThread().getContextClassLoader().getResource("");
            String str = save.toString();
            str = str.substring(5, str.length());
            str = str.replaceAll("%20", " ");
            int num = str.indexOf("iters");//wgbs 为项目名，应用到不同的项目中，这个需要修改！
            str = str.substring(0, num + "iters".length());
            if (file.isEmpty()) {
                throw new CustException("文件为空！");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 设置文件存储路径
            String filePath = url;
            String path = str + filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
