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
public class Compacta {

    public static void main(String[] args) {
        ListaEncadeada listaCompacta = new ListaEncadeada();
        compacta(listaCompacta);
    }

    static void compacta(ListaEncadeada lista) {
        try {
            File arquivoLido = new File("original.txt");
            FileReader fr = new FileReader(arquivoLido);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();//ou builder
            StringBuffer novalinha = new StringBuffer();
            String linha;

            while ((linha = br.readLine()) != null) {
                String palavra = "";

                for (int i = 0; i < linha.length(); i++) { //percorre a linha caracter por caracter
                    if (Character.isLetter(linha.charAt(i))) { // ve se o caracter é letra
                        palavra += linha.charAt(i); // vai formando a palavra
                    } else {
                        if (!lista.buscaLinear(palavra) || lista.vazia()) {// adiciona na lista se a palavra formada ainda nao existir nela
                            lista.insereInicio(palavra);
                            novalinha.append(palavra);
                        } else if (!palavra.equals("")) { // se ja tiver a palavra forma, só muda para a posiçao
                            palavra = String.valueOf(lista.posicaoNo(palavra));
                            novalinha.append(palavra);
                        }
                        novalinha.append(linha.charAt(i)); // construi a nova linha
                        palavra = "";
                    }
                }
                palavra = (lista.buscaLinear(palavra) && !palavra.equals("")) ? String.valueOf(lista.posicaoNo(palavra)) : palavra;
                novalinha.append(palavra); // adiciona a ultima palavra na linha
                sb.append(novalinha);// adiciona a linha no arquivo
                sb.append("\n");
                novalinha.delete(0, novalinha.length());
            }
            sb.append("0");
            fr.close();
            EscreveArquivo("compactado", sb, true);
        } catch (IOException e) {
            System.out.println("ocorreu um erro ao ler ou compactar o arquivo!");
        }
    }

    static void EscreveArquivo(String novoNome, StringBuffer conteudo, boolean compacta) {
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
