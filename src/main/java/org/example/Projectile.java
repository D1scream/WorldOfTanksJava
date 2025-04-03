package org.example;

import java.awt.*;
import java.awt.geom.Point2D;

public class Projectile {
    private int speed=10;
    private Point2D rotation;
    private Point2D position;
    public Projectile(Point2D position, Point2D rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Point2D getPosition() {
        return position;
    }

    public void move() {
        double dx = rotation.getX() * speed;
        double dy = rotation.getY() * speed;

        position = new Point2D.Double(position.getX() + dx, position.getY() + dy);
    }

    public void update(){
        move();
    }
    public void draw(Graphics2D g) {

    }
}
