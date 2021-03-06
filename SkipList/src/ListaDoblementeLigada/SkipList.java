package ListaDoblementeLigada;

import java.util.NoSuchElementException;
import java.util.Random;

public class SkipList<P extends Comparable<P>, T> {

    private Nodo<P, T> cabeza = new Nodo<P, T>(null, null, 0);
    final static Random random = new Random();

    public void inserta(P pos, T elem) {
        int nivel = 0;

        while (random.nextBoolean()) {
            nivel++;
        }

        while (cabeza.getNivel() < nivel) {
            Nodo<P, T> nuevaCabeza = new Nodo<P, T>(null, null, cabeza.getNivel() + 1);
            cabeza.setArriba(nuevaCabeza);
            nuevaCabeza.setAbajo(cabeza);
            cabeza = nuevaCabeza;
        }

        cabeza.inserta(pos, elem, nivel, null);
    }

    public T encuentra(P pos) {
        return cabeza.encuentra(pos).getElemento();
    }

    public void borra(P pos) {
        for (Nodo<P, T> node = cabeza.encuentra(pos); node != null; node = node.getAbajo()) {
            node.getAnt().setSig(node.getSig());
            if (node.getSig() != null) {
                node.getSig().setAnt(node.getAnt());
            }
        }

        while (cabeza.getSig() == null) {
            cabeza = cabeza.getAbajo();
            cabeza.setArriba(null);
        }
    }

    @Override
    public String toString() {
        return cabeza.toString();
    }

    public static void main(String[] args) {
        SkipList<Integer, String> sl = new SkipList<Integer, String>();
//        sl.inserta(3, "tres");
//        sl.inserta(6, "seis");
//        sl.inserta(2, "dos");
//        sl.inserta(5, "cinco");
//        sl.inserta(1, "uno");
//        try {
//            sl.inserta(1, "uno");
//            throw new IllegalStateException("error, esta permitiendo duplicados");
//        } catch (IllegalArgumentException e) {
//            System.out.println("funciona, no se puede repetir");
//        }
//
//        System.out.println(sl);
//        System.out.println("-------");
//        System.out.println(sl.encuentra(3).equals("tres"));
//        System.out.println(sl.encuentra(6).equals("seis"));
//        System.out.println(sl.encuentra(2).equals("dos"));
//        System.out.println(sl.encuentra(5).equals("cinco"));
//        System.out.println(sl.encuentra(1).equals("uno"));
//
//        sl.borra(6);
//        System.out.println(sl);
//        System.out.println("-------");
//
//        sl.borra(3);
//        System.out.println(sl);
//        System.out.println("-------");
//
//        try {
//            sl.encuentra(3);
//            throw new IllegalStateException("ERROR, no se deberia de encontrar");
//        } catch (NoSuchElementException e) {
//            System.out.println("Correcto, no se encontro");
//        }
        for (int i = 0; i < 64; i++) 
        {
            sl.inserta(i, "tres");
        }
        System.out.println(sl);
//        System.out.println("****************************************************");
//        sl.borra(15);
//        System.out.println(sl);
    }
}
