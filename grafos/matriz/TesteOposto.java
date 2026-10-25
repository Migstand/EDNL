package grafos.matriz;

import java.util.Scanner;
import java.util.Iterator;

public class TesteOposto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        // Random ran = new Random();

        //int ke = sc.nextInt();
        System.out.println("Digite o elemento iniciador");
        Object ob = so.nextLine();

        Grafo grafo = new Grafo(0, ob);

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

        // INSERÇÕES DE ARESTAS

        System.out.println("Escolha quantas arestas vai incluir");
        n = sc.nextInt();
        for (int i = 0; i < n; i++){
            
            System.out.println("Direcionada = 0 ou Não direcionada = 1 ?");
            int diret = sc.nextInt();

            Iterator<Vertice> vertis = grafo.vertices();

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

        grafo.draw();

        Iterator<Aresta> arest = grafo.arestas();

        int qtd_aresta = 0;

        while (arest.hasNext()) {
            arest.next();
            qtd_aresta++;
        }

        arest = grafo.arestas();
        Aresta aret = null;

        // INSERÇÕES DE ARESTAS

        System.out.println("Quantidade de arestas:" + qtd_aresta);
        System.out.println("Escolha uma aresta");
        n = sc.nextInt();

        // Aresta ar1;
        for (int i = 0; i < n; i++){
            if (arest.hasNext()){
                aret = arest.next();
            }
        
        }

        Iterator<Vertice> verts = grafo.vertices();

        int qtd_vertices = 0;

        while (verts.hasNext()) {
            verts.next();
            qtd_vertices++;
        }

        verts = grafo.vertices();
        vet = null;
        v1 = null;
        v2 = null;
        

        System.out.println("Quantidade de vertices:" + qtd_vertices);
        System.out.println("Escolha um vertice");
        n = sc.nextInt();

        // Aresta ar1;
        // for (int i = 0; i < n; i++){
        //     if (verts.hasNext()){
        //         vet = verts.next();
        //     }
        
        // }

        for (int i = 0; i < n; i++){
            if (verts.hasNext()){
                v1 = verts.next();
            }
        
        }

        Iterator<Aresta> incidents = grafo.arestasIncidentes(v1);

        //Aresta inci;
        while (incidents.hasNext()) {
            // incidents.next();
            System.out.print(incidents.next().getelement() + " ");
        }

        System.out.println("");

        System.out.println("Escolha outro vertice");
        n = sc.nextInt();

        verts = grafo.vertices();
        for (int i = 0; i < n; i++){
            if (verts.hasNext()){
                v2 = verts.next();
            }
        
        }

        incidents = grafo.arestasIncidentes(v2);

        while (incidents.hasNext()) {
            // incidents.next();
            System.out.print(incidents.next().getelement());
        }

        // Vertice oposto = grafo.oposto(vet, aret);
        //System.out.println("É adjacente?: " + grafo.adjacent(v1, v2));
        //System.out.println("Elemento anterior de v1:" + v1.getelement());
        System.out.println("Elemento anterior de a1:" + aret.getelement());
        System.out.println("É direcionada?: " + grafo.isdircet(aret));
        System.out.println("Digite um elemento");
        ob = so.nextLine();
        //grafo.substituirv(v1, ob);
        grafo.substituira(aret, ob);
        // System.out.println("Elemento posterior de v1:" + v1.getelement());
        System.out.println("Elemento posterior de a1:" + aret.getelement());
       // System.out.println(oposto.getelement());
        

        sc.close();
        so.close();
    }
}
