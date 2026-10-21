package grafos.matriz;

import java.util.Scanner;
import java.util.Iterator;
//import java.util.Random;

public class Teste{
    public static void main(String[] args){

        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        // Random ran = new Random();

        int ke = sc.nextInt();
        Object ob = so.nextLine();

        Grafo grafo = new Grafo(ke, ob);

        Iterator<Vertice> vets = grafo.vertices();

        Vertice vet;
        while (vets.hasNext()){
            vet = vets.next();
            System.out.println(vet.getelement());
        }
    
        grafo.inserirAresta(vet, vet, "Oi");

        System.out.println("------------------------");

        grafo.draw();

        sc.close();
        so.close();
        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }
}