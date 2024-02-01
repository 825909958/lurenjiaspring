package com.example.lurenjiaspring.controller.file;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.example.lurenjiaspring.entity.excel.TestTemplate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.lurenjiaspring.security.until.Constants.UTF8;
import static com.example.lurenjiaspring.util.resource.ConfigLoader.getValue;

/**
 * 下载功能
 *
 * @author DELL
 */
@RestController
public class FileCommon {
    @Value("${file.path}")
    private static String filePath;

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

        BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
        }
        inputStream.close();
        outputStream.close();
        long endtime = System.currentTimeMillis();
        System.out.println("下载时间为：" + (endtime - starttime) / 1000 + "秒");
    }

    @Test
    public void nioDownload() throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "nio.txt";
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

    @Test
    public void testExcelSort() {
        try {
//            exportExcel(new Response(), testTemplates, map, "test.xlsx", property + "testTemplate.xlsx");
//            exportExcelTest( testTemplates, map, "test.xlsx", property + "file:target/excelTemplate/testTemplate.xlsx");
            String outFileName = "test.xlsx";
            List<List<Object>> lists = new ArrayList<>();
            lists.add(CollectionUtil.newArrayList("1", "2"));
            exportExcelNewTest(lists, outFileName);
//            exportExcelNewTest( testTemplates, map, "test.xlsx",  "file:target/excelTemplate/testTemplate.xlsx");
        } catch (Exception e) {
            System.out.println("e = " + e);
            ;
        }
    }

    @Test
    public void testExcelTemplate() throws Exception {
        String property = System.getProperty("user.dir");
        System.out.println("property = " + property);
        List<TestTemplate> testTemplates = new ArrayList<>();
        TestTemplate tht = new TestTemplate("tht", "26");
        TestTemplate aaa = new TestTemplate("aaa", "21");
        testTemplates.add(tht);
        testTemplates.add(aaa);

        HashMap<String, Object> map = new HashMap<>();
        map.put("time", "2023-10-21");
        exportExcelTemplateTest(testTemplates, map, "test.xlsx", "file:target/excelTemplate/testTemplate.xlsx");

    }

    /**
     * 导出复杂表头的Excel 先单组数据填充，再多组数据填充
     *
     * @param response
     * @param list             多组数据List
     * @param map              单组数据Map
     * @param outFileName      导出的Excel名称
     * @param templateFileName Excel模板的路径名称
     * @throws Exception
     */
    public static void exportExcel(HttpServletResponse response, List<TestTemplate> list, Map<String, Object> map,
                                   String outFileName, String templateFileName) throws Exception {
        //告诉response下载的是excel文件
        response.setContentType("application/vnd.ms-excel");
        //告诉response使用utf-8编码格式
        response.setCharacterEncoding("utf-8");
        //.withTemplate(templateFileName)就是读取模板
        //.write(ExcelUtil.getOutputStream(outFileName, response))是将数据写入文件，并交给response
        templateFileName = "D:\\project\\lurenjiaspring\\lurenjia\\target\\classes\\testTemplate.xlsx";
        ExcelWriter excelWriter = EasyExcel.write(getOutputStream(outFileName, response)).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
        // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
        // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
        //.direction(WriteDirectionEnum.VERTICAL)这个是设置list填入的时候是纵向填入
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).forceNewRow(Boolean.FALSE).build();
        //这里是将list填充到excel中。
        //会去找模板上对应的数据填入，例如模板中的{list.getGoodsName}就是下面List集合中名为goodsName字段对应的数据
        //new FillWrapper("list", selectOrderDTO.getSelectOrderGoodsDTOS())前面的参数是设置一个填入的list名
        //后面的参数是获得的list，里面就包含了要填入的数据
        //.fill()主要就是将数据填入excel中
        excelWriter.fill(new FillWrapper(list), fillConfig, writeSheet);
        //这里是将一些普通数据放到map中，方便填入，可以看getStringObjectMap()。
        //map的String是对应的名称，Object就是数据了。
        //将数据填入
        excelWriter.fill(map, writeSheet);
        //关闭
        excelWriter.finish();
    }

    /**
     * 顺序写法
     */
    public static void exportExcelNewTest(List<List<Object>> lists, String outFileName) throws Exception {
        // 按顺序写法
        FileOutputStream fileOutputStream = new FileOutputStream(outFileName);
        ExcelWriter excelWriter = EasyExcel.write(fileOutputStream).excelType(ExcelTypeEnum.XLSX).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("测试sheet").build();

        // 按顺序填入 数据格式要求 List<List<Object>>

        excelWriter.write(lists, writeSheet);
        //关闭
        excelWriter.finish();
    }

    /**
     * 模板写法
     */
    public static void exportExcelTemplateTest(List<TestTemplate> list, Map<String, Object> map,
                                               String outFileName, String templateFileName) throws Exception {
        Resource resource = new PathMatchingResourcePatternResolver().getResource(templateFileName);
        // 模板写法
        ExcelWriter excelWriter = EasyExcel.write(outFileName).withTemplate(resource.getInputStream()).build();
        //        ExcelWriter excelWriter = EasyExcel.write(getOutputStream(outFileName, response)).withTemplate(templateFileName).build();
        // 模板写设置sheetName会报错
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.VERTICAL).forceNewRow(Boolean.TRUE).build();
        // {.变量}
        List<Map<String, Object>> maps = new ArrayList<>();
        for (TestTemplate testTemplate : list) {
            Map<String, Object> stringObjectMap = BeanUtil.beanToMap(testTemplate);
            maps.add(stringObjectMap);

        }
        // 模板填充 数据格式要求List<Map<String, Object>>
        excelWriter.fill(maps, fillConfig, writeSheet);
        //  区别上面没有点 {变量}
        excelWriter.fill(map, writeSheet);

        //关闭
        excelWriter.finish();
    }

    /**
     * 这是ExcelUtil.getOutputStream
     * 这里就是将文件下载交给了浏览器
     *
     * @return
     */
    public static OutputStream getOutputStream(String name, HttpServletResponse response) throws Exception {
        //这里是对文件的重命名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String date = sdf.format(new Date());
        String fileName = new String(name.getBytes(), UTF8) + date + ".xlsx";
        // 这里文件名如果涉及中文一定要使用URL编码,否则会乱码
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        return response.getOutputStream();
    }

}