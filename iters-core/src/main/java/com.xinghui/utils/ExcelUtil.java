package com.xinghui.utils;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Component
@Slf4j
public class ExcelUtil {

    public ResponseEntity getResponseEntitys(byte[] excelByte,String filename, HttpServletRequest request){
        ResponseEntity<byte[]> responseEntitys=null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //解决文件中文乱码
            String agent = request.getHeader("User-Agent").toLowerCase();

            //识别IE浏览器
            if (agent.contains("msie") || agent.contains("like gecko") ) {
                filename = URLEncoder.encode(filename, "UTF-8");
            }//Win 10 IE浏览器
            if(agent != null && (agent.indexOf("msie") != -1 ||
                    (agent.indexOf("rv") != -1 && agent.indexOf("firefox") == -1))){
                filename = URLEncoder.encode(filename, "UTF-8");
            }//IE浏览器
            else {
                filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");}//firefox浏览器
            headers.setContentDispositionFormData("attachment",filename);  //解决文件名中文乱码问题
            responseEntitys=new ResponseEntity<byte[]>(excelByte, headers, HttpStatus.OK);
        } catch (Exception e) {
        }
        return responseEntitys;
    }

    public ByteArrayOutputStream getExcel(String excelName, Class<?> class1, List<?> list) throws IOException {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        Workbook workbook=null ;
        BufferedOutputStream bufferedOutPut=null;
        try {
            ExportParams exportParams=new ExportParams();
            exportParams.setSheetName(excelName);
            exportParams.setTitle(excelName);
            workbook = ExcelExportUtil.exportExcel(exportParams, class1,list);
            bufferedOutPut = new BufferedOutputStream(outputStream);
            workbook.write(bufferedOutPut);
        } catch (Exception e1) {
            log.error(e1.getMessage(),e1);
        }finally {
            outputStream.close();
            if(workbook!=null)
                workbook.close();
            if(bufferedOutPut!=null)
                bufferedOutPut.close();
        }
        return outputStream;
    }
}
