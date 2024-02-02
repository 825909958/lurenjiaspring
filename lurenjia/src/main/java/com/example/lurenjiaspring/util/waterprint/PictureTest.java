package com.example.lurenjiaspring.util.waterprint;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author 82590
 */
public class PictureTest {
    @Test
    public void contextLoads() {
        try {
            InputStream inputStream = new FileInputStream("D:\\images/cat.jpg");
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            Graphics2D graphics2D = bufferedImage.createGraphics();
            Font font = new Font("黑体", Font.BOLD, 40);
            graphics2D.setFont(font);
            graphics2D.drawString("我是一只可爱的小猫", 800, 800);
            ImageIO.write(bufferedImage, "jpg", new FileOutputStream("catOutPut.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

