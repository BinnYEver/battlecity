package battlecity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    MyTank myTank = null;

    public MyPanel() {
        myTank = new MyTank(100, 100);// the inital position of my tank
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);
        drawTank(myTank.getX(), myTank.getY(), g, 0, 0);

        g.fillOval(10,10,20,20);
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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
