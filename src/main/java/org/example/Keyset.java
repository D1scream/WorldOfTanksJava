package org.example;

public class Keyset {
    String actionUp;
    String actionDown;
    String actionLeft;
    String actionRight;
    String actionDivision;

    public Keyset(String keyUp, String keyDown, String keyLeft, String keyRight, String keyDivision) {
        this.actionUp = keyUp;
        this.actionDown = keyDown;
        this.actionLeft = keyLeft;
        this.actionRight = keyRight;
        this.actionDivision = keyDivision;
    }
}
