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
        setPreferredSize(new Dimension(600, 600));
        setFocusable(true);
        Control control = new Control();
        field = new Field();
        player = new ControlledTank(new Point2D.Double(300, 300), new Point2D.Double(0.0, 1.0), control);
        field.addTank(player);

        new Thread(this).start(); // Запуск игрового цикла
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1_000_000_000.0 / 60; // 60 FPS
        while (running) {
            long now = System.nanoTime();
            if (now - lastTime >= nsPerTick) {
                update();
                repaint();
                lastTime = now;
            }
        }
    }

    private void update() {
        field.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // Приводим Graphics к Graphics2D
        field.draw(g2d);
    }
}
