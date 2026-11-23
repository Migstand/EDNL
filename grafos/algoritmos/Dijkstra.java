import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {
    private double mijinion;
    //private Antecessor caminhos;
    private PriorityQueue<Double> valores;
    private Grafo grafo;

    public Dijkstra(Grafo grafo){
        mijinion = Double.POSITIVE_INFINITY;
        //percorridos = grafo.vertices();
        valores = new PriorityQueue<>();
        this.grafo = grafo;
    }

    public ArrayList<Antecessor> dijkstra(Vertice v){
        ArrayList<Vertice> percorridos = new ArrayList<>();
        percorridos.add(v);
        ArrayList<Antecessor> caminhos = new ArrayList<>();

        // PRIMEIRO PASSO: FUNÇÃO L DOS ELEMENTOS
        Iterator it = grafo.vertices();
        while (it.hasNext()) {
            Vertice vet = (Vertice)it.next();
            double va = L(v, vet);
            valores.add(va);
            Antecessor ant = new Antecessor(va, vet);
            caminhos.add(ant);
        }

        // Valores == Valores percorridos

        //SEGUNDO PASSO: BUSCA GULOSA
        while (percorridos.size() != grafo.size()) {
            
        }

        return caminhos;
    }

    private double L(Vertice v0, Vertice v){
        if (v0 == v){
            return 0;
        }

        if (grafo.adjacent(v0, v)){
            return (Integer)aresta(v0, v).getelement();
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
}