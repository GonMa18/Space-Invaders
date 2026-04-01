package model;
import java.util.ArrayList;

public class Composite implements Component {
    protected int x;
    protected int y;
    protected  ArrayList<Coordenada> pixeles;

    public Composite(int x, int y) {
        this.x = x;
        this.y = y;
        this.pixeles = new ArrayList<>();
    }
    public void addPixel(Coordenada c) {
	pixeles.add(c);
    }
    public void mover(int x, int y) {
        this.x += x;
        this.y += y;
        for (Coordenada c : this.pixeles) {
            c.mover(x, y);
            //System.out.println("moviendome a " + c.getX() + ", " + c.getY());
        }
    }
    public boolean containPixel(int x, int y) {
        for (Coordenada c : this.pixeles) {
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
