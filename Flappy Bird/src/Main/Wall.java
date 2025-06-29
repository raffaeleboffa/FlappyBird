package Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall {
    int x, y, width, height;
    String position; // up - down
    BufferedImage image;

    public Wall () {}
    public Wall (int x, int y, int height, String position) {
        this.x = x;
        this.y = y;
        width = 120;
        this.height = height;
        this.position = position;
        try {
            image = ImageIO.read(getClass().getResource("/Img/wall.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw (Graphics2D g2) {
        g2.drawImage(image, x, y, width, height, null);
    }
}
