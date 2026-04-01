package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import javax.swing.Timer;

@SuppressWarnings("deprecation")
public class Espacio extends Observable {
    private static Espacio miEspacio; // Singleton
    private ArrayList<Enemigo> enemigos; // Lista de enemigos
    private Jugador jugador; // El jugador
    private static int ancho;
    private static int alto;

    private Rojo prueba;

    //// TIMER////
    private Timer timer; // Timer para actualizar el juego
    private int ticks; // Contador de frames para controlar la frecuencia de actualización

    private Espacio() {
        this.ancho = 160;
        this.alto = 120;
        this.enemigos = new ArrayList<>();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public static Espacio getInstance() { // Singleton
        if (miEspacio == null) {
            miEspacio = new Espacio();
        }
        return miEspacio;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public static int getAnchura() {
        return ancho;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public static int getAltura() {
        return alto;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public Jugador getJugador() {
        return jugador;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public void cambiarAMain() {
        inicializar(); // Para crear el juego al pasar a la pantalla principal
        iniciarTimer(); // Para iniciar el timer que actualiza el juego
        setChanged(); // Para notificar a la vista que el juego ha cambiado (se ha inicializado)
        notifyObservers(new Object[] { "iniciar", null }); // Para que la vista se actualice
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    private void inicializar() { // TODO --> enemigos no se generen ni  fuera ni uno encima del otro
        this.jugador = new Rojo(100, 100);
        
        enemigos.clear(); // Limpiamos enemigos por si ya habia una partida anterior

        // Crear entre 4 y 8 enemigos en y=5 con x aleatoria (sin tocarse entre ellos)
        Random rn = new Random();
        int numEnemigos = 4 + rn.nextInt(5); // 4, 5, 6, 7 u 8
        List<Integer> posicionesUsadas = new ArrayList<>();
        for (int i = 0; i < numEnemigos; i++) {
            int x;
            do {
                x = rn.nextInt(ancho-3); // x aleatorio entre 0 y 99
            } while (posicionOcupadaOAdyacente(posicionesUsadas, x));
            posicionesUsadas.add(x);
            enemigos.add(new Enemigo(x, 20));
        }

    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public int[][] generarMatriz() { // TODO

        int[][] matriz;
        // Recorremos la matriz
        matriz = new int[alto][ancho];

        if (jugador != null && jugador.sigueVivo()) {
            for (Coordenada c : jugador.getCoordenadas()) {
                if (jugador.cuerpo.containPixel(c.getX(), c.getY())) {
                    matriz[c.getY()][c.getX()] = 1; // Jugador
                }
            }
        }

        for (Enemigo e : enemigos) {
            if (e.sigueVivo()){
                for (Coordenada c : e.getCoordenadas()) {
                    if (e.cuerpo.containPixel(c.getX(), c.getY())) {
                        matriz[c.getY()][c.getX()] = 2; // Enemigo
                    }
                }
            }
        }

        if (jugador != null && jugador.sigueVivo()) {
            if (jugador.getDisparos() != null) {/*  */
                for (Disparo d : jugador.getDisparos()) {
                    if (d.isShooting()) {
                        int dx = d.getX();
                        int dy = d.getY();
                        if (dx >= 0 && dx < ancho && dy >= 0 && dy < alto) {
                            matriz[dy][dx] = 3; // Disparo
                        }
                    }
                }
            }
        }

        /* for (int[] i : matriz) {
            for (int j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
            */
        return matriz; 
        
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    private void notificarVista(Object arg) { // Para notificar a la vista que el juego ha cambiado
        setChanged();
        notifyObservers(arg);
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    // Comprueba si x coincide o es adyacente a alguna posicion ya usada
    private boolean posicionOcupadaOAdyacente(List<Integer> posiciones, int x) { //TODO
        for (int pos : posiciones) {
            if (Math.abs(pos - x) <= 6) { // misma posicion o a distancia 1
                return true;
            }
        }
        return false;
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public void moverJugador(int x, int y) {
        if (jugador != null && jugador.sigueVivo()) { // Si el colega sigue vivo
            jugador.cuerpo.mover(x, y); // Me muevo a la nueva posicion

            notificarVista(new Object[] { "actualizar", generarMatriz() }); // Aqui hay que pasarle la matriz
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public void disparar() {
        if (jugador != null && jugador.sigueVivo()) {
            jugador.disparar();
            //System.out.println("disparar espacio");
            notificarVista(new Object[] { "actualizar", generarMatriz() }); // Aqui hay que pasarle la matriz
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    // Metodo para mover todos los disparos un pixel arriba --> TIMER 50ms
    public void actualizarDisparo() {
        if (jugador == null)
            return;

        ArrayList<Disparo> disparos = jugador.getDisparos();
        if (disparos == null) {
            //System.out.println("ff2"); // Si no hay disparos, no hay muertes que comprobar
            return;
        }
        for (Disparo disparo : disparos) {
            if (disparo.isShooting()) {
                disparo.subir();
                comprobarMuertes(disparo);
            }
        }
        jugador.limpiarDisparos(); // Elimina los disparos que ya no están activos

        notificarVista(new Object[] { "actualizar", generarMatriz() }); // Aqui hay que pasarle la matriz
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    // Metodo para movel los enemigos un pixel abajo --> TIMER 200ms
    public void actualizarEnemigos() {
        ArrayList<Disparo> disparos = jugador.getDisparos();
        for (Enemigo e : enemigos) { // Muevo todos los enem que siguen vivos
            if (e.sigueVivo()) {
                e.cuerpo.mover(0, 1); // Mueve un pixel hacia abajo (y+1)
                //System.out.println("enemigo bajando");
                if (disparos != null) {
                    for (Disparo disparo : disparos) {
                        if (disparo.isShooting() && disparo != null) {
                            comprobarMuertes(disparo); // Comprueba si el disparo ha dado a algun enemigo
                        }
                    }
                }
            }
        }
        notificarVista(new Object[] { "actualizar", generarMatriz() }); // Aqui hay que pasarle la matriz
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    private void comprobarMuertes(Disparo d) {
        for (Enemigo e : enemigos) {
            if (e.sigueVivo()) {
                for (Coordenada c : e.getCoordenadas()) {
                    int ex = c.getX();
                    int ey = c.getY();
                    if (ex == d.getX() && Math.abs(ey - d.getY()) <= 1) {
                        e.setSigueJugando(false); // El enemigo muere
                        d.setShoot(false);
                    }
                }
                // notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean isGameOver() { // Enemigos llegan a la fila del jugador o muere //TODO, modularidad, metodos privados de comprobar que los enemigos hayan llegado al final y otro de choque entre jugador y enemigos

        if (jugador == null || !jugador.sigueVivo())
            return true; // Reviso que haya muerto el jugador
                         // Si sigue vivo:
                         // Paso a revisar donde estan los enemigos

        if (enemigosHanLlegadoAlFinal() || enemigoChocaConJugador()) {
            notificarVista(new Object[] { "perder", null }); // Notifico a la vista que el jugador ha perdido
        }
        return false;
    }

    private boolean enemigosHanLlegadoAlFinal() {
        for (Enemigo e : enemigos) {
            if (e.sigueVivo()) {
                for (Coordenada c : e.getCoordenadas()) {
                    if (c.getY() >= alto - 1) {
                        return true; // Un enemigo ha llegado al final del tablero
                    }
                }
            }
        }
        return false; // Ningún enemigo ha llegado al final del tablero
    }

    private boolean enemigoChocaConJugador() {
        for (Enemigo e : enemigos) {
            if (e.sigueVivo()) {
                for (Coordenada ec : e.getCoordenadas()) {
                    for (Coordenada j : jugador.getCoordenadas()) {
                        if (ec.getX() == j.getX() && ec.getY() == j.getY()) {
                            return true; // Un enemigo ha chocado con el jugador
                        }
                    }
                }
            }
        }
        return false; // Ningún enemigo ha chocado con el jugador
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public boolean isVictory() {
        for (Enemigo e : enemigos) { // Reviso que no quede ningun enemigo vivo
            if (e.sigueVivo()) {
                return false; // Todavia queda algun enemigo vivo
            }
        }
        notificarVista(new Object[] { "ganar", null }); // Notifico a la vista que el jugador ha ganado
        return true; // Todos los enemigos han muerto --> victoria

    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    // =====================================================================
    // TIMER
    // =====================================================================

    private void iniciarTimer() {
        ticks = 0; // Reiniciamos el contador de frames
        timer = new Timer(50, new ActionListener() { // TICK cada 50ms --> DISPAROS
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDisparo(); // Actualizamos el disparo cada 50ms
                ticks++;

                // TICK cada 200ms = 4 TICKS de 50ms --> ENEMIGOS

                if (ticks % 4 == 0) { // Cada 200ms (4*50ms)
                    actualizarEnemigos(); // Actualizamos los enemigos cada 200ms
                }

                // Comprobar GameOver
                if (isGameOver() || isVictory()) {
                    detenerTimer(); // Detenemos el timer si el juego ha terminado

                    // notificarVista(generarMatriz()); //Aqui hay que pasarle la matriz
                }
            }

        });
        timer.start(); // Iniciamos el timer
    }

    public void detenerTimer() {
        if (timer != null) {
            timer.stop(); // Detenemos el timer
            timer = null; // Liberamos la referencia al timer
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

}