package grafos.adjacentelista;

import java.util.Scanner;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);

        System.out.println("=== SISTEMA DE GRAFOS ===");
        System.out.println("Digite o primeiro objeto do grafo");
        Object ob = sc.nextLine();

        Grafo g = new Grafo(ob);

        int opcao = -1;

        while (opcao != 0) {
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

            if (opcao == 0) {
                System.out.println("Encerrando o programa...");
            }

            else if (opcao == 1) {
                System.out.println("Operação: finalVertices(e)");

                Iterator<Aresta> arest = g.arestas();
                int qtd_aresta = 0;

                while (arest.hasNext()) {
                    System.out.println("Nº aresta: " + (qtd_aresta + 1)
                            + " " + arest.next().getelement());
                    qtd_aresta++;
                }

                arest = g.arestas();
                Aresta aret = null;

                System.out.println("Quantidade de arestas: " + qtd_aresta);
                System.out.println("Escolha uma aresta");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (arest.hasNext()) {
                        aret = arest.next();
                    }
                }

                if (aret != null) {
                    Vertice[] vertes = g.finalVertice(aret);

                    for (int i = 0; i < vertes.length; i++) {
                        System.out.println(vertes[i].getelement());
                    }
                }
            }

            else if (opcao == 2) {
                System.out.println("Operação: oposto(v, e)");

                Iterator<Aresta> arest = g.arestas();
                int qtd_aresta = 0;

                while (arest.hasNext()) {
                    System.out.println("Nº aresta: " + (qtd_aresta + 1)
                            + " " + arest.next().getelement());
                    qtd_aresta++;
                }

                arest = g.arestas();
                Aresta aret = null;

                System.out.println("Quantidade de arestas: " + qtd_aresta);
                System.out.println("Escolha uma aresta");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (arest.hasNext()) {
                        aret = arest.next();
                    }
                }

                System.out.println("Escolha o vértice");
                n = sc.nextInt();

                Vertice v = null;
                Iterator<Vertice> verts = g.vertices();

                for (int i = 0; i < n; i++) {
                    if (verts.hasNext()) {
                        v = verts.next();
                    }
                }

                if (v != null && aret != null) {
                    try{
                        System.out.println(g.oposto(v, aret).getelement());
                    } catch (ArithmeticException e){ System.out.println(e.getMessage());}
                    
                }
            }

            else if (opcao == 3) {
                System.out.println("Operação: éAdjacente(v, w)");

                Iterator<Vertice> verts = g.vertices();
                int qtd_vertices = 0;

                System.out.println("-------------------------");
                System.out.println("Vértices: ");

                while (verts.hasNext()) {
                    System.out.println("Nº vértices: " + (qtd_vertices + 1)
                            + " " + verts.next().getelement());
                    qtd_vertices++;
                }

                verts = g.vertices();
                Vertice v1 = null;
                Vertice v2 = null;

                System.out.println("Quantidade de vértices: " + qtd_vertices);
                System.out.println("Escolha um vértice");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (verts.hasNext()) {
                        v1 = verts.next();
                    }
                }

                System.out.println("Escolha outro vértice");
                n = sc.nextInt();

                verts = g.vertices();

                for (int i = 0; i < n; i++) {
                    if (verts.hasNext()) {
                        v2 = verts.next();
                    }
                }

                if (v1 != null && v2 != null) {
                    System.out.println("É adjacente?: " + g.adjacent(v1, v2));
                }
            }

            else if (opcao == 4) {
                System.out.println("Operação: substituir(v, x)");

                Iterator<Vertice> verts = g.vertices();
                int qtd_vertices = 0;

                System.out.println("-------------------------");
                System.out.println("Vértices: ");

                while (verts.hasNext()) {
                    System.out.println("Nº vértices: " + (qtd_vertices + 1)
                            + " " + verts.next().getelement());
                    qtd_vertices++;
                }

                verts = g.vertices();
                Vertice vet = null;

                System.out.println("Quantidade de vértices: " + qtd_vertices);
                System.out.println("Escolha um vértice");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (verts.hasNext()) {
                        vet = verts.next();
                    }
                }

                sc.nextLine();
                ob = sc.nextLine();

                if (vet != null) {
                    g.substituirv(vet, ob);
                    System.out.println("Elemento posterior de vet: " + vet.getelement());
                }
            }

            else if (opcao == 5) {
                System.out.println("Operação: substituir(e, x)");

                Iterator<Aresta> arest = g.arestas();
                int qtd_aresta = 0;

                while (arest.hasNext()) {
                    System.out.println("Nº aresta: " + (qtd_aresta + 1)
                            + " " + arest.next().getelement());
                    qtd_aresta++;
                }

                arest = g.arestas();
                Aresta aret = null;

                System.out.println("Quantidade de arestas: " + qtd_aresta);
                System.out.println("Escolha uma aresta");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (arest.hasNext()) {
                        aret = arest.next();
                    }
                }

                sc.nextLine();
                ob = sc.nextLine();

                if (aret != null) {
                    g.substituira(aret, ob);
                    System.out.println("Elemento posterior de aresta: " + aret.getelement());
                }
            }

            else if (opcao == 6) {
                System.out.println("Operação: inserirVertice(o)");
                sc.nextLine();
                ob = sc.nextLine();
                g.inserirVertice(ob);
            }

            else if (opcao == 7) {
                System.out.println("Operação: inserirAresta(v, w, o)");
                System.out.println("Escolha quantas arestas vai incluir");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    Iterator<Vertice> vertis = g.vertices();
                    Vertice vet = null;
                    Vertice v1 = null;
                    Vertice v2 = null;

                    System.out.println("Escolha o primeiro vértice");
                    int vertices = sc.nextInt();

                    for (int j = 0; j < vertices; j++) {
                        if (vertis.hasNext()) {
                            vet = vertis.next();
                        }
                    }
                    v1 = vet;

                    vertis = g.vertices();

                    System.out.println("Escolha o segundo vértice");
                    vertices = sc.nextInt();

                    for (int j = 0; j < vertices; j++) {
                        if (vertis.hasNext()) {
                            vet = vertis.next();
                        }
                    }
                    v2 = vet;

                    System.out.println("Digite o elemento da aresta");
                    sc.nextLine();
                    ob = sc.nextLine();

                    if (v1 != null && v2 != null) {
                        g.inserirAresta(v1, v2, ob);
                    }
                }

                System.out.println("------------------------");
                g.draw_des();
            }

            else if (opcao == 8) {
                System.out.println("Operação: removeVértice(v)");
                Iterator<Vertice> verts = g.vertices();

                int qtd_vertices = 0;

                while (verts.hasNext()) {
                    Vertice v = verts.next();
                    System.out.println(v.getelement() + " " + "vertice: " +(v.getkey()+1));
                    qtd_vertices++;
                }

                verts = g.vertices();
                Vertice vet = null;
                

                System.out.println("Quantidade de vertices:" + qtd_vertices);
                System.out.println("Escolha um vertice");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++){
                    if (verts.hasNext()){
                        vet = verts.next();
                    }
                
                }

                if (qtd_vertices!=0){
                    System.out.println("O vertice retirado foi: " + g.removeVertice(vet));
                }

                //g.draw();
                System.out.println("------------------");
                g.draw_des();
            }

            else if (opcao == 9) {
                System.out.println("Operação: removeAresta(e)");
                Iterator<Aresta> arest = g.arestas();

                int qtd_aresta = 0;

                while (arest.hasNext()) {
                    System.out.println("Nº aresta: " + (qtd_aresta+1)+ " "+ arest.next().getelement());
                    qtd_aresta++;
                }

                arest = g.arestas();
                Aresta aret = null;

                // INSERÇÕES DE ARESTAS

                System.out.println("Quantidade de arestas:" + qtd_aresta);
                System.out.println("Escolha uma aresta");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++){
                    if (arest.hasNext()){
                        aret = arest.next();
                    }
                
                }

                if (qtd_aresta!=0){
                    System.out.println("O aresta retirada foi: " + g.removeAresta(aret));
                }

                //g.draw();
                System.out.println("------------------");
                g.draw_des();
            }

            else if (opcao == 10) {
                System.out.println("Operação: arestasIncidentes(v)");

                Iterator<Vertice> verts = g.vertices();
                int qtd_vertices = 0;

                System.out.println("-------------------------");
                System.out.println("Vértices: ");

                while (verts.hasNext()) {
                    System.out.println("Nº vértices: " + (qtd_vertices + 1)
                            + " " + verts.next().getelement());
                    qtd_vertices++;
                }

                verts = g.vertices();
                Vertice vet = null;

                System.out.println("Quantidade de vértices: " + qtd_vertices);
                System.out.println("Escolha um vértice");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (verts.hasNext()) {
                        vet = verts.next();
                    }
                }

                if (vet != null) {
                    Iterator<Aresta> arestas = g.arestasIncidentes(vet);

                    while (arestas.hasNext()) {
                        System.out.print(" " + arestas.next().getelement());
                    }
                    System.out.println();
                }
            }

            else if (opcao == 11) {
                System.out.println("Operação: vertices()");

                Iterator<Vertice> vertices = g.vertices();

                while (vertices.hasNext()) {
                    System.out.print(" " + vertices.next().getelement());
                }
                System.out.println();
            }

            else if (opcao == 12) {
                System.out.println("Operação: arestas()");

                Iterator<Aresta> arestas = g.arestas();

                while (arestas.hasNext()) {
                    System.out.print(" " + arestas.next().getelement());
                }
                System.out.println();
            }

            else if (opcao == 13) {
                System.out.println("Operação: éDirecionado(e)");

                Iterator<Aresta> arest = g.arestas();
                int qtd_aresta = 0;

                while (arest.hasNext()) {
                    System.out.println("Nº aresta: " + (qtd_aresta + 1)
                            + " " + arest.next().getelement());
                    qtd_aresta++;
                }

                arest = g.arestas();
                Aresta aret = null;

                System.out.println("Quantidade de arestas: " + qtd_aresta);
                System.out.println("Escolha uma aresta");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    if (arest.hasNext()) {
                        aret = arest.next();
                    }
                }

                if (aret != null) {
                    System.out.println("É direcionada?: " + g.isdircet(aret));
                }
            }

            else if (opcao == 14) {
                System.out.println("Operação: inserirArestaDirecionada(v, w, o)");
                System.out.println("Escolha quantas arestas vai incluir");
                int n = sc.nextInt();

                for (int i = 0; i < n; i++) {
                    Iterator<Vertice> vertis = g.vertices();
                    Vertice vet = null;
                    Vertice v1 = null;
                    Vertice v2 = null;

                    System.out.println("Escolha o primeiro vértice");
                    int vertices = sc.nextInt();

                    for (int j = 0; j < vertices; j++) {
                        if (vertis.hasNext()) {
                            vet = vertis.next();
                        }
                    }
                    v1 = vet;

                    vertis = g.vertices();

                    System.out.println("Escolha o segundo vértice");
                    vertices = sc.nextInt();

                    for (int j = 0; j < vertices; j++) {
                        if (vertis.hasNext()) {
                            vet = vertis.next();
                        }
                    }
                    v2 = vet;

                    System.out.println("Digite o elemento da aresta");
                    sc.nextLine();
                    ob = sc.nextLine();

                    if (v1 != null && v2 != null) {
                        g.inserirArestaDirecionada(v1, v2, ob);
                    }

                    g.draw_des();
                }
            }

            else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();

        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0;

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }
}
