package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ImageTest {

    @Test
    void test() {

        try {
            BufferedImage image = ImageIO.read(new File("D:/Desktop/1111.jpg"));
            assertNotNull(image);

            BufferedImage images = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(images);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
