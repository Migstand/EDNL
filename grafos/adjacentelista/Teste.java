package grafos.adjacentelista;

import java.util.Iterator;
import java.util.Scanner;
// import java.util.Random;

public class Main{
    public static void main(String[] args){

        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        // Random ran = new Random();

        System.out.println("Digite o elemento iniciador");
        Object ob = so.nextLine();

        Grafo grafo = new Grafo(ob);

        
        System.out.println("------------------------");
        System.out.println("Digite um número inteiro");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++){
            System.out.println("Digite o elemento do veritce: " + (i+2));
            ob = so.nextLine();
            grafo.inserirVertice(ob);
        }
        
        Iterator<Vertice> vets = grafo.vertices();

        Vertice vet = vets.next();
        
        int vertices;
        Vertice v1, v2;

        System.out.println("Escolha quantas arestas vai incluir");
        n = sc.nextInt();
        for (int i = 0; i < n; i++){
            Iterator<Vertice> vertis = grafo.vertices();
            
            System.out.println("Não Direcionada = 0 ou direcionada = 1 ?");
            int diret = sc.nextInt();

            System.out.println("Escolha o primeiro vertice");
            vertices = sc.nextInt();
            for (int j = 0; j < vertices; j++){
                vet = vertis.next();
            }
            v1 = vet;

            vertis = grafo.vertices();

            System.out.println("Escolha o segundo vertice");
            vertices = sc.nextInt();
            for (int j = 0; j < vertices; j++){
                vet = vertis.next();
            }
            v2 = vet;
            
            System.out.println("Digite o elemento da aresta");
            ob = so.nextLine();
            if (diret ==  0){
                grafo.inserirAresta(v1, v2, ob);
            } else{
                grafo.inserirArestaDirecionada(v1, v2, ob);
            }
            
        }

        System.out.println("------------------------");

//        grafo.draw();
        grafo.draw_des();

        Iterator<Aresta> arest = grafo.arestas();

        int qtd_aresta = 0;

        while (arest.hasNext()) {
            System.out.println("Nº aresta: " + (qtd_aresta+1)+ " "+ arest.next().getelement());
            qtd_aresta++;
        }

        arest = grafo.arestas();
        Aresta aret = null;

        System.out.println("Quantidade de arestas:" + qtd_aresta);
        System.out.println("Escolha uma aresta");
        n = sc.nextInt();

        // Aresta ar1;
        for (int i = 0; i < n; i++){
            if (arest.hasNext()){
                aret = arest.next();
            }
        
        }
        Vertice[] vertes = grafo.finalVertice(aret);

        for (int i = 0; i < vertes.length; i++){
            System.out.println(vertes[i].getelement());
        }

        sc.close();
        so.close();
        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }
}