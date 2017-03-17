import java.awt.*;

/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public class Alien extends Entity {

    public Alien(Color color, int x, int dx, int y, int width, int height, Game game, int index){
        super(color, x, dx, y, width, height, game, index);
    }
    @Override
    public void move() {

        setX(getX()+getDx());

        if (getX() + getWidth() > getGame().getWidth()) {
            setDx(getDx() * -1);
            setY(getY()+25);
        }
        if(getX() < 0){
            setDx(getDx() *-1);
            setY(getY()+25);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void kill() {
        getGame().removeAlien(getIndex());
    }

}
