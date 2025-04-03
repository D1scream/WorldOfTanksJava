package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;

public class Game extends JPanel implements Runnable{
    private boolean running = true;
    private Field field;
    private Tank player;

    public Game() {
        KeyHandler keyHandler = new KeyHandler();

        addKeyListener(keyHandler);
        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);
        requestFocusInWindow();

        Control control = new Control(keyHandler);
        field = new Field();
        player = new ControlledTank(new Point2D.Double(300, 300), new Point2D.Double(0.0, 1.0), control);
        field.addTank(player);

        new Thread(this).start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1_000_000_000.0 / 144;
        double delta = 0.0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta -= 1;
            }

            repaint();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void update() {
        field.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        field.draw(g2d);
    }
}
