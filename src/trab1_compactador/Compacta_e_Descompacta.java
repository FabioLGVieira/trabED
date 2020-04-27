 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trab1_compactador;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import jdk.nashorn.internal.runtime.JSType;

/**
 *
 * @author fabio, diego, valter
 */
public class Compacta_e_Descompacta {

    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();
        compacta(lista);
        descompacta(lista);
        System.out.println();
    }

    static void compacta(ListaEncadeada lista) {
        try {
            File arquivoLido = new File("original.txt");
            FileReader fr = new FileReader(arquivoLido);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.split(", "+" "+"--"+"\n "+". "+" --"+"'");
                //String[] palavras = linha.split("[.,\\s'-@]");

                for (int i = 0; i < palavras.length; i++) {
                    if (!lista.buscaLinear(palavras[i]) || lista.vazia()) {
                        lista.insereInicio(palavras[i]);
                    } else {
                        linha = linha.replace(palavras[i], String.valueOf(lista.posicaoNo(palavras[i])));
                    }
                }
                System.out.println(linha);
                
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
            StringBuffer sb = new StringBuffer();
            String linha;

            while ((linha = br.readLine()) != null) {
                  String[] palavras = linha.split(", "+" "+"--"+"\n "+". "+" --"+"'");
                //String[] palavras = linha.split("[.,\\s'-@]");
                int[] numeros = new int[palavras.length];
                int j = 0;
                for (int i = 0; i < palavras.length; i++) {
                    String a = palavras[i];
                    System.out.println(a);
                    if (isNumeric(palavras[i]) && !palavras[i].equals("0")) {
                        //numeros[j] = Integer.parseInt(palavras[i]);
                        //j++;
                        linha = linha.replace(palavras[i], lista.PalaravaPosicaoNo(Integer.parseInt(palavras[i])));
                    }
                }

                System.out.println(linha);
              
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
