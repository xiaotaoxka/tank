package com.wzt.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics graphics) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScanner = offScreenImage.getGraphics();
        Color c = gOffScanner.getColor();
        gOffScanner.setColor(Color.BLACK);
        gOffScanner.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScanner.setColor(c);
        paint(gOffScanner);
        graphics.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawString("子弹的数量：" + bullets.size(), 10, 60);
        graphics.setColor(c);

        myTank.paint(graphics);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(graphics);
        }

        // x += 10;
        // y += 10;
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
