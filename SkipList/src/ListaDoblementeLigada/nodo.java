/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaDoblementeLigada;

/**
 *
 * @author dai
 */
public class nodo <T extends Comparable<T>>
{
    nodo arriba,
         abajo,
         sig,
         ant;
    T elem;
    int nvl;

    public nodo(T elem, int nvl) {
        this.elem = elem;
        this.nvl = nvl;
    }   

    public T getElem() {
        return elem;
    }    

    public void setAnt(nodo ant) {
        this.ant = ant;
    }

    public void setSig(nodo sig) {
        this.sig = sig;
    }

    public void setArriba(nodo arriba) {
        this.arriba = arriba;
    }

    public void setAbajo(nodo abajo) {
        this.abajo = abajo;
    }
    
    
    
    
        
    /**
     * Tenemos una restriccion donde el nivel dado tiene que ser menor al nivel existente
     * y si el elemento es mayor entonces pasa
     * creamos un nuevo nodo
     * y si su elemento siguiente esta vacio se lo mete entre ese y su siguiente se convierte en
     * el anterior siguiente de lo contrario mete el nodo en sig y el sig se conviete en su sig.
     * si no busca que el papa esta
     * 
     * @param elem
     * @param nvl
     * @param papa 
     */
    public void inserta(T elem, int nvl, nodo papa)
    {
        if (this.nvl <= nvl && (sig==null || sig.getElem().compareTo(elem)>0))
        {
            nodo<T> nNodo = new nodo(elem, nvl);
            if (sig != null) {
                sig.setAnt(nNodo);
                nNodo.setSig(sig);
            }
            sig = nNodo;
            nNodo.setAnt(this);
            
            if (papa !=null) {
                papa.setArriba(nNodo);
                nNodo.setAbajo(papa);
            }
            if (abajo != null) {
                abajo.inserta(elem, nvl, papa);
            }
        }
    }
    
}
