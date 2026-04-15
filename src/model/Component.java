package model;

import java.awt.Color;

public interface Component{
    public void mover(int x, int y);
    public int getX();
    public int getY();
    public Color getColor();
}
