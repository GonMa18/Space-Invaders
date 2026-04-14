package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import javax.swing.Timer;

@SuppressWarnings("deprecation")
public class Espacio extends Observable {
    private static Espacio miEspacio; // Singleton
    private ArrayList<Enemigo> enemigos; // Lista de enemigos
    private Jugador jugador; // El jugador
    private String colorJugadorSeleccionado;
    private static int ancho;
    private static int alto;

    private Rojo prueba;

    //// TIMER////
    private Timer timer; // Timer para actualizar el juego
    private int ticks; // Contador de frames para controlar la frecuencia de actualización
    private boolean loteActualizacionActivo;

    private Espacio() {
        this.ancho = 160;
        this.alto = 120;
        this.enemigos = new ArrayList<>();
        this.colorJugadorSeleccionado = "rojo";
        this.loteActualizacionActivo = false;
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

    public void setColorJugador(String color) {
        if ("azul".equals(color) || "verde".equals(color) || "rojo".equals(color)) {
            this.colorJugadorSeleccionado = color;
        } else {
            this.colorJugadorSeleccionado = "rojo";
        }
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
        this.jugador = FactoryNaves.getFactory().crearJugador(ancho/2, alto-(alto/8), colorJugadorSeleccionado); // Creo el jugador en la parte inferior central del espacio
        enemigos.clear(); // Limpiamos enemigos por si ya habia una partida anterior

        // Crear entre 4 y 8 enemigos evitando solape real de pixeles entre naves
        Random rn = new Random();
        int numEnemigos = 4 + rn.nextInt(5); // 4, 5, 6, 7 u 8
        int yObjetivo = alto / 10;
        int maxIntentos = 100;
        int margenSeparacion = 1;

        for (int i = 0; i < numEnemigos; i++) {
            boolean colocado = false;

            for (int intento = 0; intento < maxIntentos && !colocado; intento++) {
                int x = rn.nextInt(ancho - 10) + 5;
                int yTemporal = rn.nextInt(alto);

                Enemigo candidato = new Enemigo(x, yTemporal);
                candidato.mover(0, yObjetivo - yTemporal);

                boolean solapa = false;
                for (Enemigo existente : enemigos) {
                    for (Coordenada c : candidato.getCoordenadas()) {
                        for (int dx = -margenSeparacion; dx <= margenSeparacion && !solapa; dx++) {
                            for (int dy = -margenSeparacion; dy <= margenSeparacion; dy++) {
                                if (existente.containPixel(c.getX() + dx, c.getY() + dy)) {
                                    solapa = true;
                                    break;
                                }
                            }
                        }
                        if (solapa) {
                            break;
                        }
                    }
                    if (solapa) {
                        break;
                    }
                }

                if (!solapa) {
                    enemigos.add(candidato);
                    colocado = true;
                }
            }
        }

    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public int[][] generarMatriz() { // TODO

        int[][] matriz;
        // Recorremos la matriz
        matriz = new int[alto][ancho];

        if (jugador != null && jugador.sigueVivo()) {
            for (Coordenada c : jugador.getCoordenadas()) {
                int x = c.getX();
                int y = c.getY();
                if (x >= 0 && x < ancho && y >= 0 && y < alto) {
                    matriz[y][x] = colorToInt(c.getColor()); // Jugador
                }
            }
        }

        for (Enemigo e : enemigos) {
            if (e.sigueVivo()){
                for (Coordenada c : e.getCoordenadas()) {
                    int x = c.getX();
                    int y = c.getY();
                    if (x >= 0 && x < ancho && y >= 0 && y < alto) {
                        matriz[y][x] = colorToInt(c.getColor()); // Enemigo
                    }
                }
            }
        }

        if (jugador != null && jugador.sigueVivo()) {
            if (jugador.getDisparos() != null) {
            	for (Disparo d : jugador.getDisparos()) {
                    if (d.isShooting()) {
                        for (Coordenada c : d.getPixeles()) {
                            int dx = c.getX();
                            int dy = c.getY();
                            if (dx >= 0 && dx < ancho && dy >= 0 && dy < alto) {
                                matriz[dy][dx] = 3;
                            }
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

    private int colorToInt(Color color) {
        if (color.equals(Color.WHITE)) {
            return 1;
        } else if (color.equals(Color.RED)) {
            return 2;
        } else if (color.equals(Color.YELLOW)) {
            return 3;
        } else if (color.equals(Color.CYAN)) {
            return 4;
        } else if (color.equals(Color.MAGENTA)) {
            return 5;
        } else if (color.equals(Color.ORANGE)) {
            return 6;
        } else if (color.equals(Color.GREEN)) {
            return 7;
        } else if (color.equals(Color.BLUE)) {
            return 8;
        } else if (color.equals(Color.PINK)) {
            return 9;
        }
        return 0; // Espacio vacío
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    private void notificarVista(Object arg) { // Para notificar a la vista que el juego ha cambiado
        setChanged();
        notifyObservers(arg);
    }

    public void solicitarActualizacion() {
        if (!loteActualizacionActivo) {
            notificarVista(new Object[] { "actualizar", generarMatriz() });
        }
    }

    public void iniciarLoteActualizacion() {
        loteActualizacionActivo = true;
    }

    public void finalizarLoteActualizacion() {
        loteActualizacionActivo = false;
        notificarVista(new Object[] { "actualizar", generarMatriz() });
    }

    public void notificarActualizacion() {
        solicitarActualizacion();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public void moverJugador(int x, int y) {
        if (jugador != null && jugador.sigueVivo()) { // Si el colega sigue vivo
            jugador.mover(x, y); // Me muevo a la nueva posicion
        }
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public void disparar() {
        if (jugador != null && jugador.sigueVivo()) {
            jugador.disparar();
            //System.out.println("disparar espacio");
            //notificarVista(new Object[] { "actualizar", generarMatriz() }); // Aqui hay que pasarle la matriz
        }
    }
    
    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    public void cambiarTipoBala(int tipo) {
		if (jugador != null && jugador.sigueVivo()) {
			if (tipo == 0) {
				jugador.changestrategyBala(new BalaPixel());
			}else if (tipo == 1) {
				jugador.changestrategyBala(new BalaFlecha());
			}else if (tipo == 2) {
				jugador.changestrategyBala(new BalaRombo());
			}else {
                System.out.println("Tipo de bala no válido");
            }
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
        solicitarActualizacion();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    // Metodo para movel los enemigos un pixel abajo --> TIMER 200ms
    public void actualizarEnemigos() {
        ArrayList<Disparo> disparos = jugador.getDisparos();
        for (Enemigo e : enemigos) { // Muevo todos los enem que siguen vivos
            if (e.sigueVivo()) {
                e.mover(0, 1); // Mueve un pixel hacia abajo (y+1)
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
        solicitarActualizacion();
    }

    // --------------------------------------------------------------------------------------------------------------------------------------------------------

    private void comprobarMuertes(Disparo d) {
        for (Enemigo e : enemigos) {
            if (e.sigueVivo()) {
            	for(Coordenada pixelBala : d.getPixeles()) {
                    for (Coordenada c : e.getCoordenadas()) {
                        int ex = c.getX();
                        int ey = c.getY();
                        if (ex == pixelBala.getX() && Math.abs(ey - pixelBala.getY()) <= 1) {
                            e.setSigueJugando(false); // El enemigo muere
                            d.setShoot(false);
                        }
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
        timer = new Timer(10, new ActionListener() { // TICK base aproximado de 60 FPS
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarLoteActualizacion();
                try {
                    ticks++;
                    actualizarDisparo();

                    // Aproximadamente cada 192ms (12 * 16ms)
                    if (ticks % 20 == 0) {
                        actualizarEnemigos();
                    }

                    // Comprobar GameOver
                    if (isGameOver() || isVictory()) {
                        detenerTimer(); // Detenemos el timer si el juego ha terminado
                    }
                } finally {
                    finalizarLoteActualizacion();
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