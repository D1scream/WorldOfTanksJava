package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Field {
    private int width = 600;
    private int height = 600;
    private HashSet<Tank> tankList = new HashSet<>();
    private HashSet<Projectile> projectileList = new HashSet<>();

    public void addTank(Tank tank) {
        tankList.add(tank);
    }

    public void update(){
        for(Tank tank : tankList){
            Projectile projectile = tank.update();
            if(projectile != null){
                projectileList.add(projectile);
            }
        }

        ArrayList<Projectile> toRemove = new ArrayList<>();
        for (Projectile projectile : projectileList) {
            projectile.update();
            if (!(0 <= projectile.getPosition().getX() && projectile.getPosition().getX() <= width
                    && 0 <= projectile.getPosition().getY() && projectile.getPosition().getY() <= height)) {
                toRemove.add(projectile);
            }
        }
        toRemove.forEach(projectileList::remove);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);

        for (Tank tank : tankList) {
            tank.draw(g);
        }

        for (Projectile projectile : projectileList) {
            projectile.draw(g);
        }
    }
}
