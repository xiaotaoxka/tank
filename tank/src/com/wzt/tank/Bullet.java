package com.wzt.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;

    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private TankFrame tf = null;

    private int x, y;
    private Dir dir;

    private boolean live = true;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics graphics) {
        if (!live) {
            tf.bullets.remove(this);
        }

        // Color c = graphics.getColor();
        // graphics.setColor(Color.RED);
        // graphics.fillOval(x, y, WIDTH, HEIGHT);
        // graphics.setColor(c);

        switch (dir) {
            case LEFT:
                graphics.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                graphics.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                graphics.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                graphics.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
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

        if (this.x < 0 || this.y < 0 || this.x > TankFrame.GAME_WIDTH || this.y > TankFrame.GAME_HEIGHT)
            live = false;
    }
}
