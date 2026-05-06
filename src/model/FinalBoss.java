package model;

import java.awt.Color;

public class FinalBoss extends Enemigo {

    public FinalBoss(int x, int y) {
        super(x, y);
        // Vida elevada por defecto para el boss final
        this.setVida(10000);
    }

    @Override
public void iniciarCuerpo(int x, int y) {

    this.addPixel(new Coordenada(x - 4, y - 4, Color.RED));
    this.addPixel(new Coordenada(x - 3, y - 4, Color.RED));
    this.addPixel(new Coordenada(x - 2, y - 4, Color.RED));
    this.addPixel(new Coordenada(x - 1, y - 4, Color.RED));
    this.addPixel(new Coordenada(x + 1, y - 4, Color.RED));
    this.addPixel(new Coordenada(x + 2, y - 4, Color.RED));
    this.addPixel(new Coordenada(x + 3, y - 4, Color.RED));
    this.addPixel(new Coordenada(x + 4, y - 4, Color.RED));

    this.addPixel(new Coordenada(x - 5, y - 3, Color.RED));
    this.addPixel(new Coordenada(x - 4, y - 3, Color.RED));
    this.addPixel(new Coordenada(x - 3, y - 3, Color.RED));
    this.addPixel(new Coordenada(x - 2, y - 3, Color.RED));
    this.addPixel(new Coordenada(x - 1, y - 3, Color.RED));
    this.addPixel(new Coordenada(x, y - 3, Color.RED));
    this.addPixel(new Coordenada(x + 1, y - 3, Color.RED));
    this.addPixel(new Coordenada(x + 2, y - 3, Color.RED));
    this.addPixel(new Coordenada(x + 3, y - 3, Color.RED));
    this.addPixel(new Coordenada(x + 4, y - 3, Color.RED));
    this.addPixel(new Coordenada(x + 5, y - 3, Color.RED));

    this.addPixel(new Coordenada(x - 6, y - 2, Color.RED));
    this.addPixel(new Coordenada(x - 5, y - 2, Color.RED));
    this.addPixel(new Coordenada(x - 4, y - 2, Color.RED));
    this.addPixel(new Coordenada(x - 3, y - 2, Color.RED));
    this.addPixel(new Coordenada(x - 2, y - 2, Color.RED));
    this.addPixel(new Coordenada(x - 1, y - 2, Color.RED));
    this.addPixel(new Coordenada(x, y - 2, Color.RED));
    this.addPixel(new Coordenada(x + 1, y - 2, Color.RED));
    this.addPixel(new Coordenada(x + 2, y - 2, Color.RED));
    this.addPixel(new Coordenada(x + 3, y - 2, Color.RED));
    this.addPixel(new Coordenada(x + 4, y - 2, Color.RED));
    this.addPixel(new Coordenada(x + 5, y - 2, Color.RED));
    this.addPixel(new Coordenada(x + 6, y - 2, Color.RED));

    this.addPixel(new Coordenada(x - 6, y - 1, Color.RED));
    this.addPixel(new Coordenada(x - 5, y - 1, Color.RED));
    this.addPixel(new Coordenada(x - 4, y - 1, Color.WHITE));
    this.addPixel(new Coordenada(x - 3, y - 1, Color.WHITE));
    this.addPixel(new Coordenada(x - 2, y - 1, Color.BLACK));
    this.addPixel(new Coordenada(x - 1, y - 1, Color.WHITE));
    this.addPixel(new Coordenada(x, y - 1, Color.RED));
    this.addPixel(new Coordenada(x + 1, y - 1, Color.BLACK));
    this.addPixel(new Coordenada(x + 2, y - 1, Color.WHITE));
    this.addPixel(new Coordenada(x + 3, y - 1, Color.WHITE));
    this.addPixel(new Coordenada(x + 4, y - 1, Color.BLACK));
    this.addPixel(new Coordenada(x + 5, y - 1, Color.WHITE));
    this.addPixel(new Coordenada(x + 6, y - 1, Color.RED));

    this.addPixel(new Coordenada(x - 6, y, Color.RED));
    this.addPixel(new Coordenada(x - 5, y, Color.RED));
    this.addPixel(new Coordenada(x - 4, y, Color.RED));
    this.addPixel(new Coordenada(x - 3, y, Color.RED));
    this.addPixel(new Coordenada(x - 2, y, Color.RED));
    this.addPixel(new Coordenada(x - 1, y, Color.BLACK));
    this.addPixel(new Coordenada(x, y, Color.RED));
    this.addPixel(new Coordenada(x + 1, y, Color.BLACK));
    this.addPixel(new Coordenada(x + 2, y, Color.RED));
    this.addPixel(new Coordenada(x + 3, y, Color.RED));
    this.addPixel(new Coordenada(x + 4, y, Color.RED));
    this.addPixel(new Coordenada(x + 5, y, Color.RED));
    this.addPixel(new Coordenada(x + 6, y, Color.RED));

    this.addPixel(new Coordenada(x - 6, y + 1, Color.RED));
    this.addPixel(new Coordenada(x - 5, y + 1, Color.WHITE));
    this.addPixel(new Coordenada(x - 4, y + 1, Color.WHITE));
    this.addPixel(new Coordenada(x - 3, y + 1, Color.WHITE));
    this.addPixel(new Coordenada(x - 2, y + 1, Color.RED));
    this.addPixel(new Coordenada(x - 1, y + 1, Color.RED));
    this.addPixel(new Coordenada(x, y + 1, Color.RED));
    this.addPixel(new Coordenada(x + 1, y + 1, Color.RED));
    this.addPixel(new Coordenada(x + 2, y + 1, Color.RED));
    this.addPixel(new Coordenada(x + 3, y + 1, Color.WHITE));
    this.addPixel(new Coordenada(x + 4, y + 1, Color.WHITE));
    this.addPixel(new Coordenada(x + 5, y + 1, Color.WHITE));
    this.addPixel(new Coordenada(x + 6, y + 1, Color.RED));

    this.addPixel(new Coordenada(x - 5, y + 2, Color.RED));
    this.addPixel(new Coordenada(x - 4, y + 2, Color.WHITE));
    this.addPixel(new Coordenada(x - 3, y + 2, Color.WHITE));
    this.addPixel(new Coordenada(x - 2, y + 2, Color.BLACK));
    this.addPixel(new Coordenada(x - 1, y + 2, Color.BLACK));
    this.addPixel(new Coordenada(x, y + 2, Color.WHITE));
    this.addPixel(new Coordenada(x + 1, y + 2, Color.BLACK));
    this.addPixel(new Coordenada(x + 2, y + 2, Color.BLACK));
    this.addPixel(new Coordenada(x + 3, y + 2, Color.WHITE));
    this.addPixel(new Coordenada(x + 4, y + 2, Color.WHITE));
    this.addPixel(new Coordenada(x + 5, y + 2, Color.RED));

    this.addPixel(new Coordenada(x - 4, y + 3, Color.RED));
    this.addPixel(new Coordenada(x - 3, y + 3, Color.RED));
    this.addPixel(new Coordenada(x - 2, y + 3, Color.RED));
    this.addPixel(new Coordenada(x - 1, y + 3, Color.RED));
    this.addPixel(new Coordenada(x, y + 3, Color.RED));
    this.addPixel(new Coordenada(x + 1, y + 3, Color.RED));
    this.addPixel(new Coordenada(x + 2, y + 3, Color.RED));
    this.addPixel(new Coordenada(x + 3, y + 3, Color.RED));
    this.addPixel(new Coordenada(x + 4, y + 3, Color.RED));

    this.addPixel(new Coordenada(x - 1, y + 4, Color.WHITE));
    this.addPixel(new Coordenada(x, y + 4, Color.BLACK));
    this.addPixel(new Coordenada(x + 1, y + 4, Color.WHITE));

    this.addPixel(new Coordenada(x - 2, y + 5, Color.BLACK));
    this.addPixel(new Coordenada(x - 1, y + 5, Color.BLACK));
    this.addPixel(new Coordenada(x, y + 5, Color.RED));
    this.addPixel(new Coordenada(x + 1, y + 5, Color.BLACK));
    this.addPixel(new Coordenada(x + 2, y + 5, Color.BLACK));

    this.addPixel(new Coordenada(x - 4, y + 6, Color.RED));
    this.addPixel(new Coordenada(x - 3, y + 6, Color.RED));
    this.addPixel(new Coordenada(x + 3, y + 6, Color.RED));
    this.addPixel(new Coordenada(x + 4, y + 6, Color.RED));

    this.addPixel(new Coordenada(x - 4, y + 7, Color.RED));
    this.addPixel(new Coordenada(x + 4, y + 7, Color.RED));
}
}
