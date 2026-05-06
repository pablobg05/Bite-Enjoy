package Clases;

import Clases.PedidoClass;
import Clases.PedidoClass;
import java.util.*;

public class ArbolBPlus {

    class Nodo {//El nodo con el que se va a trabajar ( así como lo que vimos del b normal muchá )
        ArrayList<Long> llaves = new ArrayList<>(); 
        ArrayList<PedidoClass> pedidos = new ArrayList<>(); //le agregue esta onda para poder usar el objeto del pedido que ya tenemos
        ArrayList<Nodo> hijos = new ArrayList<>();
        boolean esHoja;
        Nodo siguiente; 

        Nodo(boolean esHoja) {
            this.esHoja = esHoja;
        }
    }

    private Nodo raiz;
    private int T; // El grado mínimo (es cuantas claves pueda almacenar por nodo)

    public ArbolBPlus(int t) {
        this.T = t;
        this.raiz = new Nodo(true); // Al principio el árbol es solo una hoja vacía
    }

    // EL MÉTODO PARA GUARDAR: Recibe la fecha en milis y el pedido completo
    public void insertar(long llave, PedidoClass pedido) {
        
        if (raiz.llaves.size() == 2 * T - 1) {//si ya está llena, crece para arriba
            Nodo s = new Nodo(false); // Nueva raíz
            s.hijos.add(raiz);
            dividir(s, 0, raiz); // Partimos la raíz vieja en dos
            raiz = s; // La nueva raíz ahora manda
        }
        insertarNoLleno(raiz, llave, pedido);
    }

    // Esta función es cuando se llenó el nodo
    private void dividir(Nodo padre, int i, Nodo hijo) {
        Nodo nuevo = new Nodo(hijo.esHoja); // El "hermano" que va a nacer
        int medio = T - 1; // donde lo dividimos
        long llaveSubida = hijo.llaves.get(medio); // La llave que sube al padre

        // Pasamos la mitad de las llaves (y pedidos) al hermano nuevo
        for (int j = medio; j < hijo.llaves.size(); j++) {
            nuevo.llaves.add(hijo.llaves.get(j));
            if (hijo.esHoja) {
                nuevo.pedidos.add(hijo.pedidos.get(j));
            }
        }

        if (!hijo.esHoja) {
            // Si es una rama, movemos también a los "hijos de los hijos"
            nuevo.llaves.remove(0); // En ramas, la llave que sube no se queda abajo
            for (int j = medio + 1; j < hijo.hijos.size(); j++) {
                nuevo.hijos.add(hijo.hijos.get(j));
            }
            hijo.hijos.subList(medio + 1, hijo.hijos.size()).clear();
        } else {
            // SI ES HOJA aqui se van enlazando las hojas
            nuevo.siguiente = hijo.siguiente;
            hijo.siguiente = nuevo;
        }

        // Limpiamos los datos que ya movimos al hermano nuevo
        hijo.llaves.subList(medio, hijo.llaves.size()).clear();
        if (hijo.esHoja) {
            hijo.pedidos.subList(medio, hijo.pedidos.size()).clear();
        }

        // Le avisamos al padre que ahora tiene un nuevo hijo y una nueva llave
        padre.llaves.add(i, llaveSubida);
        padre.hijos.add(i + 1, nuevo);
    }

    // Busca dónde meter el dato recorriendo el árbol hacia abajo
    private void insertarNoLleno(Nodo x, long k, PedidoClass pedido) {
        int i = x.llaves.size() - 1;

        if (x.esHoja) {
            // Si llegamos a una hoja, buscamos su lugar exacto para que quede ordenado
            while (i >= 0 && k < x.llaves.get(i)) {
                i--;
            }
            x.llaves.add(i + 1, k);
            x.pedidos.add(i + 1, pedido);
        } else {
            // Si no es hoja, seguimos bajando por el hijo correcto
            while (i >= 0 && k < x.llaves.get(i)) {
                i--;
            }
            i++;
            // Si el hijo por el que vamos a bajar está lleno, lo dividimos primero
            if (x.hijos.get(i).llaves.size() == 2 * T - 1) {
                dividir(x, i, x.hijos.get(i));
                if (k > x.llaves.get(i)) {
                    i++;
                }
            }
            insertarNoLleno(x.hijos.get(i), k, pedido);
        }
    }

    // DEVUELVE LOS DATOS ORDENADOS PARA LA TABLA (PABLO - esta es la que tenés que llamar en la tabla ahora, eso le tenés que mover)
    public List<PedidoClass> obtenerTodoElHistorial() {
        List<PedidoClass> historial = new ArrayList<>();
        Nodo actual = raiz;

        // Primero bajamos todo a la izquierda hasta encontrar la primera hoja
        while (actual != null && !actual.esHoja) {
            actual = actual.hijos.get(0);
        }

        // ahora recorremos la lista por medio de las hojas
        while (actual != null) {
            historial.addAll(actual.pedidos);
            actual = actual.siguiente; // Saltamos al siguiente nodo
        }
        return historial;
    }
}