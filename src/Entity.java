import java.awt.*;

/**
 * Created by Aidan Moulder on 3/15/2017.
 */
public abstract class Entity {

    private Game game;
    private Color color;
    private int x, dx, y, width, height, index;



    public Entity(Color color, int x, int dx, int y, int width, int height, Game game, int index) {

        this.game = game;
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.index = index;
        this.dx = dx;

    }

    public abstract void move();
    public abstract void paint(Graphics g);
    public abstract void kill();
    public boolean collides(Entity other){
        return getBounds().intersects(other.getBounds());
    }
    public void decrementIndex(){
        index--;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }
    public abstract void checkCollision();

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(double x) {
        this.x = (int)Math.round(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }



}
