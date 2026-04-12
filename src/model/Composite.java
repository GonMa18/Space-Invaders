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
	pixeles.add(c);
    }
    public void mover(int dx, int dy) {
        for (Coordenada c : this.pixeles) {
            if (c.getX() + dx < 0 || c.getY() + dy < 0 || c.getX() + dx >= 160 || c.getY() + dy >= 120) {
                return; // No mover si alguno de los pixeles se saldría del espacio
            }
        }
        this.x += dx;
        this.y += dy;
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
