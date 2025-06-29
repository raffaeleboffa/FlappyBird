package Main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    double gravity;
    boolean play, sconfitta;

    Bird bird = new Bird();
    Wall[] walls = new Wall[4];

    int time_wings;
    boolean start_wings;

    public GamePanel () {
        gravity = 2;
        play = false;
        sconfitta = false;

        for (int i = 0; i < 4; i++) walls[i] = new Wall();
    }

    public void startThread () {
        play = true;
        sconfitta = false;

        Thread th = new Thread(this);
        th.start();
    }

    @Override
    public void run () {
        bird = new Bird();
        time_wings = 0;
        start_wings = false;

        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                walls[i] = new Wall(
                        500+(200*i),
                        0,
                        (int)(Math.random()*270 + 45),
                        "up"
                );
            }
            else  walls[i] = new Wall(
                    500+(200*(i-1)),
                    walls[i-1].height + 120,
                    380,
                    "down"
            );
        }

        while (play) {
            if (start_wings) {
                time_wings++;
                bird.wings_start();
                if (time_wings == 5) {
                    time_wings = 0;
                    start_wings = false;
                    bird.wings_stop();
                }
            }

            // bird

            bird.velocity += gravity;
            bird.y += bird.velocity;

            // check collision

            if (bird.y <= 0 || (bird.y + bird.height) >= 460) sconfitta = true;
            for (int i = 0; i < 4; i++) { // funzionante solo il primo angolo
                if (bird.x >= walls[i].x && bird.x <= (walls[i].x + walls[i].width) && bird.y >= walls[i].y && bird.y <= walls[i].height)
                    sconfitta = true;
                else if (bird.x + bird.width >= walls[i].x && bird.x + bird.width <= (walls[i].x + walls[i].width) && bird.y + bird.height >= walls[i].y && bird.y + bird.height <= walls[i].height)
                    sconfitta = true;
            }

            // walls

            for (int i = 0; i < 4; i++) {
                walls[i].x += -4;
                if (walls[i].x + walls[i].width < 0) {
                    if (i < 2) {
                        walls[i].x = walls[i + 2].x + 400;
                        if (i == 0) walls[0].height = (int)(Math.random()*270 + 45);
                        else walls[1].y = walls[i-1].height + 120;
                    }
                    else {
                        walls[i].x = walls[0].x + 400;
                        if (i == 2) walls[2].height = (int)(Math.random()*270 + 45);
                        else walls[3].y = walls[i-1].height + 120;
                    }
                }
            }

            // repaint

            repaint();

            // sleep

            if (sconfitta) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                play = false;

                repaint();
            }

            try {
                Thread.sleep(55);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //@Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < 4; i++) walls[i].draw(g2);
        bird.draw(g2);

        if (sconfitta) {
            g2.setFont(new Font("Algerian", Font.BOLD, 30));
            g2.setColor(new Color(165, 14, 14));
            g2.drawString("Hai Perso!", 150, 200);
        }

        if (!play) {
            g2.setFont(new Font("Algerian", Font.BOLD, 20));
            g2.setColor(new Color(165, 14, 14));
            g2.drawString("Premi Spazio per iniziare", 110, 300);
        }
    }
}
