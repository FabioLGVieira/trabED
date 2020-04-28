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
        //descompacta(lista);
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
                palavra = (lista.buscaLinear(palavra)) ? String.valueOf(lista.posicaoNo(palavra)) : palavra;
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
            if (compacta) {
                System.out.println("Arquivo compactado!");
            } else {
                System.out.println("Arquivo descompactado!");
            }
        } catch (IOException ex) {
            System.out.println("ocorreu um erro ao escrever arquivo!");
        }
    }

    static void descompacta(ListaEncadeada lista) {
        try {
            File arquivoLido = new File("compactado.txt");
            FileReader fr = new FileReader(arquivoLido);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();//ou builder
            StringBuffer novalinha = new StringBuffer();
            String linha;

            while ((linha = br.readLine()) != null) {
                String palavra = "";

                for (int i = 0; i < linha.length(); i++) {
                    if (Character.isDigit(linha.charAt(i))) {
                        palavra += linha.charAt(i);
                    } else {
                        if (!lista.buscaLinear(palavra) || lista.vazia()) {
                            lista.insereInicio(palavra);
                            novalinha.append(palavra);
                        } else if (!palavra.equals("")) {
                            palavra = String.valueOf(lista.posicaoNo(palavra));
                            novalinha.append(palavra);
                        }
                        novalinha.append(linha.charAt(i));
                        palavra = "";
                    }
                }

                sb.append(novalinha);
                sb.append("\n");
                sb.delete(0, sb.length());
            }
            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.split("([-#@.*&: ]+ ?)");
                //String[] palavras = linha.split("[.,\\s'-@]");
                int[] numeros = new int[palavras.length];
                int j = 0;
                for (int i = 0; i < palavras.length; i++) {

                    if (isNumeric(palavras[i]) && !palavras[i].equals("0")) {
                        //numeros[j] = Integer.parseInt(palavras[i]);
                        //j++;
                        //linha = linha.replace(palavras[i], lista.PalaravaPosicaoNo(Integer.parseInt(palavras[i])));
                    }
                }

                sb.append(linha);
                sb.append("\n");
            }
            fr.close();
            EscreveArquivo("descompactado", sb, false);
        } catch (IOException e) {
            System.out.println("ocorreu um erro ao ler ou descompactar o arquivo!");
        }
    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
