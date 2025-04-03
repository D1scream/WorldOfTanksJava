package org.example;

import java.awt.*;
import java.awt.geom.Point2D;

public class ControlledTank extends Tank {
    private final Control control;
    public ControlledTank(Point2D position, Point2D rotate, Control control) {
        super(position,rotate);
        this.control = control;
    }

    Point2D rotateVector(Point2D vector, double angleDegrees) {
        double angleRad = Math.toRadians(angleDegrees);
        double cos = Math.cos(angleRad);
        double sin = Math.sin(angleRad);

        double newX = vector.getX() * cos - vector.getY() * sin;
        double newY = vector.getX() * sin + vector.getY() * cos;

        return new Point2D.Double(newX, newY);
    }

    @Override
    public void move(){
        Point2D directionVector = control.getMovingVector();
        System.out.println("Direction Vector: X=" + directionVector.getX() + ", Y=" + directionVector.getY());

        if(directionVector.getX() != 0){
            rotation = rotateVector(rotation, rotation_speed * directionVector.getX());
            directionVector = new Point2D.Double(0, directionVector.getY());
        }
        double dx = rotation.getX() * speed * directionVector.getY() * -1;
        double dy = rotation.getY() * speed * directionVector.getY() * -1;

        position.setLocation(position.getX() + dx, position.getY() + dy);
    }

    public Projectile checkShoot(){
        if(control.getShoot()) {
            return shoot();
        }
        return null;

    }

    @Override
    public Projectile update(){
        move();
        return checkShoot();
    }

//    @Override
//    public void draw(Graphics2D g) {
//
//    }
}
