package grafos;

import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;
// import grafos.matriz.Grafo;
// import grafos.matriz.Vertice;
// import grafos.matriz.Aresta;
import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
public class Main{
    public static void main(String[] args){

        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        Random ran = new Random();

        //grafos.matriz.Grafo g1 = new grafos.matriz.Grafo(element);
        //grafos.adjacentelista.Grafo g2 = new grafos.adjacentelista.Grafo(key, element)

        System.out.println("=== SISTEMA DE GRAFOS ===");
        // System.out.println("Escolha a representação:");
        // System.out.println("0 - Utilizar matriz de adjacência");
        // System.out.println("1 - Utilizar lista de adjacência");
        // System.out.print("Opção: ");

        //int representacao = sc.nextInt();
        
        System.out.println("Digete o primeiro objeto do grafo");
        Object ob = so.nextLine();

        Grafo g = new Grafo(ob);

        int opcao;

        do {
            System.out.println("\n=== MENU DE OPERAÇÕES ===");
            System.out.println("0  - Encerrar programa");
            System.out.println("1  - finalVertices(e)");
            System.out.println("2  - oposto(v, e)");
            System.out.println("3  - éAdjacente(v, w)");
            System.out.println("4  - substituir(v, x)");
            System.out.println("5  - substituir(e, x)");
            System.out.println("6  - inserirVertice(o)");
            System.out.println("7  - inserirAresta(v, w, o)");
            System.out.println("8  - removeVértice(v)");
            System.out.println("9  - removeAresta(e)");
            System.out.println("10 - arestasIncidentes(v)");
            System.out.println("11 - vertices()");
            System.out.println("12 - arestas()");
            System.out.println("13 - éDirecionado(e)");
            System.out.println("14 - inserirArestaDirecionada(v, w, o)");
            System.out.print("Escolha uma operação: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                case 1:
                    System.out.println("Operação: finalVertices(e)");
                    // Solicitar a aresta e chamar o método.
                    break;

                case 2:
                    System.out.println("Operação: oposto(v, e)");
                    // Solicitar o vértice v e a aresta e.
                    break;

                case 3:
                    System.out.println("Operação: éAdjacente(v, w)");
                    // Solicitar os vértices v e w.
                    break;

                case 4:
                    System.out.println("Operação: substituir(v, x)");
                    // Solicitar o vértice v e o novo elemento x.
                    break;

                case 5:
                    System.out.println("Operação: substituir(e, x)");
                    // Solicitar a aresta e e o novo elemento x.
                    break;

                case 6:
                    System.out.println("Operação: inserirVertice(o)");
                    ob = so.nextLine();
                    g.inserirVertice(ob);
                    break;

                case 7:
                    System.out.println("Operação: inserirAresta(v, w, o)");
                    // Solicitar v, w e o.
                    break;

                case 8:
                    System.out.println("Operação: removeVértice(v)");
                    // Solicitar o vértice v.
                    break;

                case 9:
                    System.out.println("Operação: removeAresta(e)");
                    // Solicitar a aresta e.
                    break;

                case 10:
                    System.out.println("Operação: arestasIncidentes(v)");
                    // Solicitar o vértice v.
                    break;

                case 11:
                    System.out.println("Operação: vertices()");
                    Iterator<Vertice> vertices = g.vertices();

                    while(vertices.hasNext()){
                        System.out.print(" " + vertices.next().getelement());
                    }
                    System.out.println(" ");
                    break;

                case 12:
                    System.out.println("Operação: arestas()");
                    // Exibir todas as arestas.
                    break;

                case 13:
                    System.out.println("Operação: éDirecionado(e)");
                    // Solicitar a aresta e.
                    break;

                case 14:
                    System.out.println("Operação: inserirArestaDirecionada(v, w, o)");
                    // Solicitar v, w e o.
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        sc.close();
        so.close();
        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }
}