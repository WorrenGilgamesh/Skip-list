/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaDoblementeLigada;

import java.util.NoSuchElementException;

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
    public void inserta(T elem, int nvl, nodo papa)
    {
        if (this.nvl <= nvl && (sig==null || sig.getElem().compareTo(elem)>0))
        {
            nodo<T> nNodo = new nodo(elem, nvl);
            if (sig != null) 
            {
                sig.setAnt(nNodo);
                nNodo.setSig(sig);
            }
            sig = nNodo;
            nNodo.setAnt(this);
            
            if (papa !=null) 
            {
                papa.setArriba(nNodo);
                nNodo.setAbajo(papa);
            }
            if (abajo != null) 
                abajo.inserta(elem, nvl, papa);            
        }else if (sig != null && sig.getElem().compareTo(elem)<0) 
            sig.inserta(elem, nvl, papa);
        else if (sig != null && sig.getElem().compareTo(elem)==0) 
            throw new IllegalArgumentException("Es invalido metere duplicados");
        else if (abajo != null) 
            abajo.inserta(elem, nvl, papa);        
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
    public nodo<T> encuentra(T elem)
    {
        if (sig != null) 
        {
            int resCompareTo = sig.getElem().compareTo(elem);
            
            if (resCompareTo == 0)
            return sig;
            else if (resCompareTo <0)
                return sig.encuentra(elem);
            else if (abajo !=null) 
                return abajo.encuentra(elem);
            else
                throw new NoSuchElementException();           
        }else if (abajo != null)
            return abajo.encuentra(elem);
        else
            throw new NoSuchElementException();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        nodo<T> piso = this; 
        
        return sb.toString();
    }
}
