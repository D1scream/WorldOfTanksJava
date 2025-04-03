package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tank Game");
        Game game = new Game();
        KeyHandler keyHandler = new KeyHandler();

        game.addKeyListener(keyHandler);
        game.setFocusable(true);
        game.requestFocusInWindow();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        SwingUtilities.invokeLater(() -> game.requestFocusInWindow());
    }

}