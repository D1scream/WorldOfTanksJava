package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.HashSet;
import java.awt.geom.Point2D;

public class Control{
    private final KeyHandler keyHandler;

    public Control(KeyHandler keyHandler){
        this.keyHandler = keyHandler;
    }
    public Point2D getMovingVector() {
        int x = 0, y = 0;
        if (keyHandler.isKeyPressed(KeyEvent.VK_W)) y = -1;
        if (keyHandler.isKeyPressed(KeyEvent.VK_S)) y = 1;
        if (keyHandler.isKeyPressed(KeyEvent.VK_A)) x = -1;
        if (keyHandler.isKeyPressed(KeyEvent.VK_D)) x = 1;
        return new Point2D.Double(x, y);
    }

    public boolean getShoot() {
        return keyHandler.isKeyPressed(KeyEvent.VK_SPACE);
    }
}

class KeyHandler implements KeyListener {
    private final HashSet<Integer> pressedKeys = new HashSet<>();

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Не используется
    }

    public boolean isKeyPressed(int keyCode) {
        return pressedKeys.contains(keyCode);
    }
}

