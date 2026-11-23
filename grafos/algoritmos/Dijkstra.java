package grafos.algoritmos;

import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.HashMap;
//import java.util.PriorityQueue;

public class Dijkstra {
    private double mijinion; // Infinito
    //private Antecessor caminhos;
    // private PriorityQueue<Double> valores;
    private Grafo grafo;

    public Dijkstra(Grafo grafo){
        mijinion = Double.POSITIVE_INFINITY;
        //percorridos = grafo.vertices();
        // valores = new PriorityQueue<>();
        this.grafo = grafo;
    }

    public HashMap<Integer, Antecessor> dijkstra(Vertice v){
        ArrayList<Antecessor> n_percorridos = new ArrayList<>();
        ArrayList<Vertice> percorridos = new ArrayList<>();
        percorridos.add(v);
        HashMap<Integer, Antecessor> caminhos = new HashMap<>(); // Guarda os antecessores e mantém atualizado os seus valores;

        // PRIMEIRO PASSO: FUNÇÃO L DOS ELEMENTOS (FEITA, conceitualmente)
        Iterator it = grafo.vertices();
        while (it.hasNext()) {
            Vertice vet = (Vertice)it.next();
            double va = L(v, vet);
            Antecessor ant = new Antecessor(va, vet);
            //System.out.println(vet.getkey());
            caminhos.put(vet.getkey(), ant);
            if (vet != v){
                n_percorridos.add(ant);
            }
        }

        // Valores == Valores percorridos

        //SEGUNDO PASSO: BUSCA GULOSA
        while (percorridos.size() != grafo.size()) {
            Vertice min_guloso = menor(n_percorridos);

            percorridos.add(min_guloso);
            n_percorridos.remove(min_guloso);
            double percorreu = caminhos.get(min_guloso.getkey()).getvalor(); //d[w] valor atual do elemento percorrido

            for (int i = 0; i < n_percorridos.size(); i++){ // CONCEITUAL
                Vertice n_percorre = n_percorridos.get(i).getvet();
                int ind = n_percorre.getkey(); // (CONCEITUAL)
                double valor = caminhos.get(ind).getvalor(); //get(ind.getelement()); // Valor atual do caminho que está sendo verificado;
                double l = L(min_guloso, n_percorre);
                
                caminhos.get(ind).setvalor(Math.min(valor, percorreu + l)); // Comparar o valor atual do não percorrido com a soma do ponto de partida até o esse novo.
                
                if(percorreu + l <valor){
                    caminhos.get(ind).setant(caminhos.get(min_guloso.getkey()));
                }
            }
        }

        return caminhos;
    }

    private double L(Vertice v0, Vertice v){
        if (v0 == v){
            return 0;
        }

        //System.out.println(" ");
        //System.out.println(v0.getkey() + " " + v.getkey());
        if (grafo.adjacent(v0, v)){
            return ((Integer) aresta(v0, v).getelement()).doubleValue();
        }

        return mijinion;

    }

    private Aresta aresta(Vertice i, Vertice f){
        Aresta aresta = null;
        ArrayList<Aresta> arestas = i.getarestas();
        for (int v = 0; v < arestas.size(); v++){
            if (grafo.oposto(i, arestas.get(v)) == f){
                aresta = arestas.get(v);
            }
        }
        return aresta;
    }

    private Vertice menor(ArrayList<Antecessor> arr){
        double menor = mijinion;
        Vertice min = null;
        for (int i = 0; i < arr.size(); i++){
            if (menor > arr.get(i).getvalor()){
                min = arr.get(i).getvet();
            }
            //menor = Math.min(menor, arr.get(i).getvalor());
        }

        return min;
    }
}