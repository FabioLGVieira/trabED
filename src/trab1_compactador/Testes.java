/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compactador;


/**
 *
 * @author fabio,diego,valter
 */
public class Testes {
    

    public static void main(String[] args) {
        
        Compacta_e_Descompacta compactador = new Compacta_e_Descompacta();
        compactador.compacta("original");
        compactador.descompacta("compactado");
    }
}
