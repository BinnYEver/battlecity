package battlecity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank myTank = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;

    public MyPanel() {
        myTank = new MyTank(100, 100);// the initial position of my tank
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirection(2);
            Bullet enemyBullet = new Bullet(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
            enemyTank.enemyTankBullet.add(enemyBullet);
            new Thread(enemyBullet).start();

            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        if (myTank.bullet != null && myTank.bullet.isLive) {
            System.out.println("bullet has been paint");
            g.fill3DRect(myTank.bullet.getX(), myTank.bullet.getY(), 1, 1, false);

        }
        for (EnemyTank one : enemyTanks) {
            if (one.isLive) {
                drawTank(one.getX(), one.getY(), g, one.getDirection(), 1);
                for (int i = 0; i < one.enemyTankBullet.size(); i++) {
                    Bullet bullet = one.enemyTankBullet.get(i);
                    if (bullet.isLive) {
                        g.fill3DRect(bullet.getX(), bullet.getY(), 1, 1, false);
                    } else {
                        one.enemyTankBullet.remove(bullet);
                    }
                }
            }


        }

    }

    public static void hitTank(Bullet bullet, EnemyTank enemyTank) {
        switch (enemyTank.getDirection()) {
            case 0:
            case 2:
                if (bullet.getX() > enemyTank.getX() && bullet.getX() < enemyTank.getX() + 40
                        && bullet.getY() > enemyTank.getY() && bullet.getY() < enemyTank.getY() + 60) {
                    bullet.isLive = false;
                    enemyTank.isLive = false;
                }
                break;
            case 1:
            case 3:
                if (bullet.getX() > enemyTank.getX() && bullet.getX() < enemyTank.getX() + 60
                        && bullet.getY() > enemyTank.getY() && bullet.getY() < enemyTank.getY() + 40) {
                    bullet.isLive = false;
                    enemyTank.isLive = false;
                }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direction) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (myTank.getDirection() != 0) {
                myTank.setDirection(0);
            } else {
                myTank.MoveUp();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (myTank.getDirection() != 1) {
                myTank.setDirection(1);
            } else {
                myTank.MoveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (myTank.getDirection() != 2) {
                myTank.setDirection(2);
            } else {
                myTank.MoveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (myTank.getDirection() != 3) {
                myTank.setDirection(3);
            } else {
                myTank.MoveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("J has been pressed");
            myTank.ShotBullet();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (myTank.bullet.isLive) {
                for (EnemyTank one : enemyTanks) {
                    hitTank(myTank.bullet, one);
                }
            }

            this.repaint();
        }

    }
}
