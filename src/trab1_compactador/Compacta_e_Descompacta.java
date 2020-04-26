/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compactador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author fabio, diego, valter
 */
public class Compacta_e_Descompacta {

    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();
        compacta(lista);
    }

    static void compacta(ListaEncadeada list) {
        try {
            File arquivoLido = new File("original.txt");   
            FileReader fr = new FileReader(arquivoLido);   
            BufferedReader br = new BufferedReader(fr); 
            StringBuffer sb = new StringBuffer();    
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.split("[ \\!\\\"\\#\\$\\%\\s&'()*+,-./:;<=>?@\\[\\]_`{|}~]");
                //String[] palavras = linha.split("[.,\\s'-@]");

                for (int i = 0; i < palavras.length; i++) {
                    if (!list.buscaLinear(palavras[i]) || list.vazia()) {
                        list.insereInicio(palavras[i]);
                    } else {
                        linha = linha.replace(palavras[i], String.valueOf(list.posicaoNo(palavras[i])));
                    }
                }
                sb.append(linha);
                sb.append("\n");
            }
            sb.append("0");
            fr.close();    
            EscreveArquivo("compactado", sb);
        } catch (IOException e) {
            System.out.println("ocorreu um erro ao ler ou compactar o arquivo!");
        }
    }

    static void EscreveArquivo(String novoNome, StringBuffer conteudo) {
        File arquivo = new File(novoNome + ".txt");
        FileWriter escritor;
        try {
            escritor = new FileWriter(arquivo);
            escritor.append(conteudo);
            escritor.close();
            System.out.println("Arquivo compactado!");
        } catch (IOException ex) {
            System.out.println("ocorreu um erro ao escrever arquivo!");
        }
    }
}
