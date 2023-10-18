package com.example.lurenjiaspring.controller.file;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 下载功能
 *
 * @author DELL
 */
@RestController
public class FileCommon {

    @GetMapping("/downLoadFile")
    public void downLoadFile(HttpServletResponse response) throws Exception {
        long starttime = System.currentTimeMillis();
        String path = "https://img-home.csdnimg.cn/images/20230817060240.png";
        String filename = "test";
        URL url = new URL(path);
        URLConnection conn = url.openConnection();
        InputStream inputStream = conn.getInputStream();
        response.reset();
        //文件类型自动判断
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("UTF-8");
        //将Content-Disposition暴露给前端
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        //将文件名放入header的Content-Disposition中
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));


        byte[] buffer = new byte[1024 * 1024 * 10];
        int len;

        BufferedOutputStream outputStream =new BufferedOutputStream(response.getOutputStream()) ;
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
        long endtime = System.currentTimeMillis();
        System.out.println("下载时间为："+(endtime-starttime)/1000+"秒");
    }
    public static void main(String[] args) throws Exception{
        String path = System.getProperty("user.dir") + File.separator  + "nio.txt";
        FileOutputStream fos = new FileOutputStream(path);

        FileChannel channel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = "aaaaaaaaa你好".getBytes(StandardCharsets.UTF_8);
        //byte[] bytes = {1, 2, 3, 4, 5};
        for (int i = 0; i < bytes.length; i++) {
            buffer.put(bytes[i]);
        }
        // 重置到初始位置
        buffer.flip();
        channel.write(buffer);
        fos.close();
    }


}