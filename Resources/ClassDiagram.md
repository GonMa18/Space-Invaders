```mermaid
classDiagram

%% =====================
%% MODELO
%% =====================

class Disparo {
  +int x
  +int y
  +mover()
}

class Enemigo {
  +int vida
  +int x
  +int y
  +mover()
}

class Espacio {
  +int ancho
  +int alto
  +actualizar()
}

class Jugador {
  +int puntuacion
  +disparar()
}

class Nave {
  +int x
  +int y
  +mover()
}

%% =====================
%% VISTAS
%% =====================

class StartFrame {
  +mostrarMenu()
}

class MainFrame {
  +iniciarJuego()
  +actualizarVista()
}

class FinishFrame {
  +mostrarResultado()
}

%% =====================
%% RELACIONES DEL MODELO
%% =====================

Jugador --> Nave
Jugador --> Disparo
Espacio --> Enemigo
Espacio --> Disparo
Espacio --> Nave

%% =====================
%% RELACIONES MVC
%% =====================

StartFrame ..> Jugador
MainFrame ..> Jugador
MainFrame ..> Espacio
MainFrame ..> Enemigo
MainFrame ..> Nave
MainFrame ..> Disparo
FinishFrame ..> Jugador