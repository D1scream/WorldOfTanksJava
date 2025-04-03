package org.example;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class Tank {
    protected Point2D position;
    protected Double speed=1.0;
    protected Double rotation_speed=1.0;
    protected Point2D rotation;

    private Integer WIDTH = 10;
    private Integer HEIGHT = 10;

    public Tank(Point2D position, Point2D rotation) {
        this.position = position;
        this.rotation = rotation;
    }
    public Projectile shoot(){
        return new Projectile(position, rotation);
    }

    public void move(){
        position = new Point2D.Double(position.getX()+speed, position.getY()+speed);
    }

    public Projectile update(){
        return null;
    }

    public void draw(Graphics2D g) {
        double angle = Math.toDegrees(Math.atan2(rotation.getY(), rotation.getX()));

        int halfWidth = WIDTH / 2;
        int halfHeight = HEIGHT / 2;
        int[] xPoints = {-halfWidth, halfWidth, halfWidth, -halfWidth};
        int[] yPoints = {-halfHeight, -halfHeight, halfHeight, halfHeight};

        Path2D tankShape = new Path2D.Double();
        tankShape.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            tankShape.lineTo(xPoints[i], yPoints[i]);
        }
        tankShape.closePath();

        AffineTransform transform = new AffineTransform();
        transform.translate(position.getX(), position.getY());
        transform.rotate(Math.toRadians(angle));

        Shape rotatedTank = transform.createTransformedShape(tankShape);
        g.setColor(Color.GREEN);
        g.fill(rotatedTank);

        g.setColor(Color.RED);
        int lineLength = 20;
        double endX = position.getX() + rotation.getX() * lineLength;
        double endY = position.getY() + rotation.getY() * lineLength;
        g.drawLine((int) position.getX(), (int) position.getY(), (int) endX, (int) endY);
    }
}
