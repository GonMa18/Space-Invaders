package model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

public class Composite extends Observable implements Component  {
    private int x;
    private int y;
    private ArrayList<Component> pixeles;

    public Composite(int x, int y) {
        this.x = x;
        this.y = y;
        this.pixeles = new ArrayList<>();
    }
    public void addPixel(Component c) {
	pixeles.add(c);
    }
//    public void moverNave(int dx, int dy) {
//        for (Component c : this.pixeles) {
//            if (c.getX() + dx < 0 || c.getY() + dy < 0 || c.getX() + dx >= 160 || c.getY() + dy >= 120) {
//                return; // No mover si alguno de los pixeles se saldría del espacio
//            }
//        }
//        this.x += dx;
//        this.y += dy;
//        for (Component c : this.pixeles) {
//            c.mover(dx, dy);
//        }
//        Espacio.getInstance().solicitarActualizacion();
//    }
    
    public void moverNave(int dx, int dy) {
        boolean fueraDeLimites = pixeles.stream().anyMatch(c -> c.getX() + dx < 0 || c.getY() + dy < 0 || c.getX() + dx >= 160 || c.getY() + dy >= 120);
        if (fueraDeLimites) return;

        this.x += dx;
        this.y += dy;
        pixeles.forEach(c -> c.mover(dx, dy));
        Espacio.getInstance().solicitarActualizacion();
    }

    public void moverEnemigo(int dx, int dy) {
        // Sin validación de límites - los enemigos se mueven libremente
        this.x += dx;
        this.y += dy;
        pixeles.forEach(c -> c.mover(dx, dy));
        // NO llamar a solicitarActualizacion() aquí - lo hace el llamador
    }

    public void mover(int dx, int dy) {
        for (Component c : this.pixeles) {
            c.mover(dx, dy);
        }
    }
    public boolean containPixel(int x, int y) {
//        for (Component c : this.pixeles) {
//            if (c.getX() == x && c.getY() == y) {
//                return true;
//            }
//        }
//        return false;
    	return pixeles.stream().anyMatch(c -> c.getX() == x && c.getY() ==y);
    	}
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ArrayList<Component> getPixeles() {
        return pixeles;
    }

    public Color getColor() {
        if (pixeles.isEmpty()) {
            return null;
        }
        return pixeles.get(0).getColor();
    	
    }

    public Color getColor(int x, int y) {
//        for (Component c : this.pixeles) {
//            if (c.getX() == x && c.getY() == y) {
//                return c.getColor();
//            }
//        }
//        return null; // Si no se encuentra el pixel, devuelve null
    	return pixeles.stream().filter(c -> c.getX() == x && c.getY() == y).map(c -> c.getColor()).findFirst().orElse(null)        ;
    }
}
