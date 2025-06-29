package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bird {
    int x, y, width, height;
    int velocity;
    BufferedImage image, frame1, frame2;

    public Bird () {
        x = 100;
        y = 100;
        width = 39;
        height = 30;
        velocity = 0;
        try {
            frame1 = ImageIO.read(getClass().getResource("/Img/frame-1.png"));
            frame2 = ImageIO.read(getClass().getResource("/Img/frame-2.png"));
            image = frame1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw (Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }

    public void wings_start() {
        image = frame2;
    }
    public void wings_stop() {
        image = frame1;
    }
}
