package grafos.algoritmos;

import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class DijkstraMain{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        
        System.out.println("Digite o elemento do vertice: "+ 1);
        Object ob = so.nextLine();
        Grafo g = new Grafo(ob);
        
        for (int i = 0; i < 4; i++){
            System.out.println("Digite o elemento do vertice: "+ (i+2));
            ob = so.nextLine();
            g.inserirVertice(ob);
        }

        // Inserir as arestas
        Iterator its = g.vertices();
        ArrayList<Vertice> vertices = new ArrayList<>();
        while (its.hasNext()){
            vertices.add((Vertice)its.next());
        }

        g.inserirAresta(vertices.get(0), vertices.get(2), 3);
        g.inserirAresta(vertices.get(0), vertices.get(1), 4);
        g.inserirAresta(vertices.get(1), vertices.get(2), 5);
        g.inserirAresta(vertices.get(2), vertices.get(3), 1);
        g.inserirAresta(vertices.get(2), vertices.get(4), 3);
        g.inserirAresta(vertices.get(1), vertices.get(3), 2);
        g.inserirAresta(vertices.get(3), vertices.get(4), 4);

        Dijkstra d = new Dijkstra(g);

        its = g.vertices();
        Vertice v = null;

        System.out.println("Digite o nº vertice:");
        int ver = sc.nextInt();

        for (int i = 0; i < ver-1; i++){
            its.next();
        }
        v = (Vertice)its.next();

        HashMap<Integer, Antecessor> dijkstra = d.dijkstra(v);
        for (int i = 0; i < g.size(); i++){
            Antecessor ant = dijkstra.get(i);
            System.out.println("De: " + v.getelement() + " até " + ant.getvet().getelement() + " = " +  ant.getvalor());
            System.out.print("Caminho: ");
            Antecessor cami = ant;
            while(cami.getants() != null){
                System.out.print( cami.getvet().getelement()+ " <-- ");
                cami = cami.getants();
            }
            System.out.println(cami.getvet().getelement());
            System.out.println("-----------------------------");

        }

    }
}