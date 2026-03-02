import java.util.ArrayList;
import java.util.List;

public class Observable {
	private List<Observer> listaObservadores = new ArrayList<>();	//Lista de observadores

    public void addObserver(Observer o) {	//Anadir observador a la lista
        listaObservadores.add(o);
    }

    public void removeObserver(Observer o) {	//Eliminar observador de la lista
        listaObservadores.remove(o);
    }

    protected void notificarObservadores() {	//Actualizar
        for (Observer o : listaObservadores) {
            o.actualizar();
        }
    }
}
