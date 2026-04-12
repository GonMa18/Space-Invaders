package model;
import java.util.ArrayList;

public class Composite implements Component {
    private int x;
    private int y;
    private ArrayList<Coordenada> pixeles;

    public Composite(int x, int y) {
        this.x = x;
        this.y = y;
        this.pixeles = new ArrayList<>();
    }
    public void addPixel(Coordenada c) {
	p
        for (Coordenada c : this.pixeles) {
            c.mover(dx, dy);
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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ArrayList<Coordenada> getPixeles() {
        return pixeles;
    }
}
