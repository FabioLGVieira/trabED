/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compactador;

/**
 *
 * @author fabio, diego, valter
 */
public class ListaEncadeada {
    private No ini;
    
    public ListaEncadeada(){
        this.ini=null;
    }
    
    public boolean vazia(){
        return ini==null;
    }
    
    public void insereInicio(String palavra){
        No novo = new No(palavra,ini);
        ini=novo;   
    }
    
    public boolean buscaLinear(String palavra){
        No temp=ini;
        
        while(temp!=null){
            if(temp.getPalavra().equals(palavra)){
                return true;
            }
            temp=temp.getProx();
        }
        return false;
    }
    
    public int posicaoNo(String palavra){
        No temp=ini;
        
        while(temp!=null){
            if(temp.getPalavra().equals(palavra)){
                return temp.getPos();
            }
            temp=temp.getProx();
        }
        return -1;
    }
}
