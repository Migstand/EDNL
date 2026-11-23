import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

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

    public ArrayList<Antecessor> dijkstra(Vertice v){
        ArrayList<Vertice> n_percorridos = new ArrayList<>();
        ArrayList<Vertice> percorridos = new ArrayList<>();
        percorridos.add(v);
        ArrayList<Antecessor> caminhos = new ArrayList<>(); // Guarda os antecessores e mantém atualizado os seus valores;

        // PRIMEIRO PASSO: FUNÇÃO L DOS ELEMENTOS (FEITA, conceitualmente)
        Iterator it = grafo.vertices();
        while (it.hasNext()) {
            Vertice vet = (Vertice)it.next();
            double va = L(v, vet);
            Antecessor ant = new Antecessor(va, vet);
            caminhos.add(ant);
            if (vet != v){
                n_percorridos.add(vet);
            }
        }

        // Valores == Valores percorridos

        //SEGUNDO PASSO: BUSCA GULOSA
        while (percorridos.size() != grafo.size()) {
            Vertice min_guloso = menor(n_percorridos);

            percorridos.add(min_guloso);
            n_percorridos.remove(min_guloso);
            double percorreu = min_guloso.getelement(); //d[w]

            for (int i = 0; i < n_percorridos.size(); i++){ // CONCEITUAL
                Vertice ind = n_percorridos.get(i); // (CONCEITUAL)
                double valor = caminhos.get(ind.getelement()); // Valor atual do caminho que está sendo verificado; 
                
                caminhos.set(ind, Math.min(valor, percorreu + L(min_guloso, ind))); // Comparar o valor atual do não percorrido com a soma do ponto de partida até o esse novo.
                
                if(percorreu + L(min_guloso, ind)<valor){
                    caminhos.get(ind).setant(min_guloso);
                }
            }
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

    private Vertice menor(ArrayList<Vertice> arr){
        double menor = mijinion;

        for (int i = 0; i < arr.size(); i++){
            menor = Math.min(menor, arr.get(i));
        }

        return min;
    }
}