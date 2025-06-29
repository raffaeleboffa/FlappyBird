package Main;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements KeyListener {
    static JFrame frame = new JFrame("Flappy Bird");
    static GamePanel gp = new GamePanel();

    public static void main(String[] args) {
        frame.addKeyListener(new Main());
        frame.setResizable(false);
        frame.setBounds(50, 50, 500, 500);

        frame.add(gp);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            if (!gp.play) {
                gp.startThread();
            } else {
                gp.bird.velocity = -15;
                gp.start_wings = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
