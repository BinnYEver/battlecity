package battlecity;


import java.util.Vector;

public class EnemyTank extends Tank{
    Vector<Bullet> enemyTankBullet = new Vector<>();
    Boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
