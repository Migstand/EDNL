package grafos;

import java.util.Scanner;
import java.util.Random;

public class Main{
    public static void main(String[] args){

        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        Random ran = new Random();

        //grafos.matriz.Grafo g1 = new grafos.matriz.Grafo(key, element)
        //grafos.adjacentelista.Grafo g2 = new grafos.adjacentelista.Grafo(key, element)


        sc.close();
        so.close();
        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }
}