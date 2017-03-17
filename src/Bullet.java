import java.awt.*;

/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public class Bullet extends Entity {

    private int bulletTimer;

    public Bullet(Color color, int x, int dx, int y, int width, int height, Game game, int index) {
        super(color, x, dx, y, width, height, game, index);
    }
    @Override
    public void move() {
        setY(getY() - getDx());
        if(getY()+getHeight() > getGame().getHeight()){
            kill();
        }
    }
    @Override
    public void paint(Graphics g) {
            g.setColor(Color.ORANGE);
            g.fillRect(getX() - getWidth() / 2, getY(), getWidth(), getHeight());

    }
    @Override
    public void kill() {
        getGame().removeBullet(getIndex());
    }
}
