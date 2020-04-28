/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compactador;

import java.util.LinkedList;

/**
 *
 * @author fabio, diego, valter
 */
public class No {
    static private int topo;
    private int pos;
    private String palavra;
    private No prox;

    public No(String palavra, No prox) {
        this.pos = ++topo;
        this.palavra = palavra;
        this.prox = prox;
    }

    public int getPos() {
        return pos;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
    
    public void resetTopo(){
    this.topo = 0;}
}
