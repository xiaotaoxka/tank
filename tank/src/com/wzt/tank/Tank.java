package com.wzt.tank;

import java.awt.*;

public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = false;

    private TankFrame tf = null;

    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics graphics) {
        // Color c = graphics.getColor();
        // graphics.setColor(Color.YELLOW);
        // graphics.fillRect(x, y, 50, 50);
        // graphics.setColor(c);

        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf));
    }
}
