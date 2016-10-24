package ListaDoblementeLigada;
import java.util.NoSuchElementException;

public class Nodo<P extends Comparable<P>,T> {
    private Nodo<P,T>
            arriba,
            abajo,
            sig,
            ant;
    private P posicion;
    private T elemento;
    private int nivel;

    public Nodo(P posicion, T elemento, int nivel) {
        this.posicion = posicion;
        this.elemento = elemento;
        this.nivel = nivel;
    }

    /**
     * Tenemos una restriccion donde el nivel dado tiene que ser menor al nivel existente
     * y si el elemento es mayor entonces pasa
     * creamos un nuevo nodo
     * y si su elemento siguiente esta vacio se lo mete entre ese y su siguiente se convierte en
     * el anterior siguiente de lo contrario mete el nodo en sig y el sig se conviete en su sig.
     * si no busca en el papa si hay algo, si hay el papa del papa se pone el nodo y el abajo nodo el papa (Cambian de lugares)
     * si no pasa la anterior restriccion
     * busca que el siguiente sea tenga un elemento y sea menor que elemento que se quiere poner.
     * si es asi lo inserta, de ser lo contrario
     * busca que el siguiente elmento tenga un elemento y sea igual para mandar un error y si pasa
     * por esas restricciones y el que tiene abajo existe se lo inserta
     * 
     * 
     * @param elem
     * @param nvl
     * @param papa 
     */
    public void inserta(P posicion, T elemento, int nivel, Nodo<P,T> papa) {
        if (this.nivel <= nivel && (sig == null || sig.getPosicion().compareTo(posicion) > 0)) {
            Nodo<P, T> nuevoNodo = new Nodo<P, T>(posicion, elemento, this.nivel);

            if (sig != null) {
                sig.setAnt(nuevoNodo);
                nuevoNodo.setSig(sig);
            }

            sig = nuevoNodo;
            nuevoNodo.setAnt(this);

            if (papa != null) {
                nuevoNodo.setArriba(papa);
                papa.setAbajo(nuevoNodo);
            }

            if (abajo != null) {
                abajo.inserta(posicion, elemento, nivel, nuevoNodo);
            }
        } else if (sig != null && sig.getPosicion().compareTo(posicion) < 0) {
            sig.inserta(posicion, elemento, nivel, papa);
        } else if (sig != null && sig.getPosicion().compareTo(posicion) == 0) {
            throw new IllegalArgumentException("No se permite elementos duplicados");
        } else if (abajo != null) {
            abajo.inserta(posicion, elemento, nivel, papa);
        }
    }

    /**
     * Ponemos una restriccion donde tiene que encontrar un elemento siguiente
     * por casos practicos meteremos en una variable la comparacion entre elementos
     * si la comparacion es igual que 0 lo encontro
     * si es menor pasara al sig hasta y repite lo anterior
     * si no lo encuentra lanza un error
     * si no se encuentra en esa linea pasa a la siguiente y repite lo anteriores pasos
     * y si no lo encuentra con eso lanza un error
     * 
     * @param elem
     * @return 
     */
    public Nodo<P, T> encuentra(P posicion) {
        if (sig != null) {
            int resComparable = sig.getPosicion().compareTo(posicion);

            if (resComparable == 0) {
                return sig;
            } else if (resComparable < 0) {
                return sig.encuentra(posicion);
            } else if (abajo != null) {
                return abajo.encuentra(posicion);
            } else {
                throw new NoSuchElementException();
            }
        } else if (abajo != null) {
            return abajo.encuentra(posicion);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Nodo<P, T> bottom = this;

        while (bottom.getAbajo() != null) {
            bottom = bottom.getAbajo();
        }
        Nodo<P, T> node = bottom;
        while(node != null)
        {
            sb.append('[').append((node.getPosicion() == null) ? "☺" : node.getPosicion().toString()).append(']');
            node = node.getArriba();
        }

        if (bottom.sig != null) {
            sb.append('\n').append(bottom.sig.toString());
        }

        return sb.toString();
    }

    public P getPosicion() {
        return posicion;
    }

    public T getElemento() {
        return elemento;
    }

    public void setAnt(Nodo nodo) {
        ant = nodo;
    }

    public void setSig(Nodo nodo) {
        sig = nodo;
    }

    public void setAbajo(Nodo nodo) {
        abajo = nodo;
    }

    public void setArriba(Nodo nodo) {
        arriba = nodo;
    }

    public Nodo<P,T> getArriba() {
        return arriba;
    }

    public Nodo<P,T> getAbajo() {
        return abajo;
    }

    public Nodo<P,T> getAnt() {
        return ant;
    }

    public Nodo<P,T> getSig() {
        return sig;
    }

    public int getNivel() {
        return nivel;
    }
}