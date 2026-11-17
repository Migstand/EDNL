import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
import java.util.ArrayList;
import java.util.Iterator;

public class Dijkstra {
    private double mijinion;
    //private ArrayList<Vertice> restantes;
    private ArrayList<Integer> valores;
    private Grafo grafo;

    public Dijkstra(Grafo grafo){
        mijinion = Double.POSITIVE_INFINITY;
        //percorridos = grafo.vertices();
        valores = new ArrayList<>(Collections.nCopies(grafo.size(), 0));
        this.grafo = grafo;
    }

    public ArrayList<Integer> dijkstra(Vertice v){
        ArrayList<Vertice> percorridos = new ArrayList<>();
        percorridos.add(v);

        // PRIMEIRO PASSO: FUNÇÃO L DOS ELEMENTOS
        Iterator it = grafo.vertices();
        while (it.hasNext()) {
            valores.set(i, L(v, it.next()));
        }
        //SEGUNDO PASSO: BUSCA GULOSA
        // while (percorridos.size() != grafo.size()) {
            
        // }

        return valores;
    }

    private int L(Vertice v0, Vertice v){
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