package battlecity;

import javax.swing.*;

public class BattleCity extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        BattleCity battleCity = new BattleCity();
    }
    public  BattleCity() {
        mp = new MyPanel();
        this.add(mp);
        Thread thread = new Thread(mp);
        thread.start();
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
