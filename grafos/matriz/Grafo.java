package grafos.matriz;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo{
    private Object[][] grafo;
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;

    public Grafo(Vertice v){
        vertices = new ArrayList<>();
        grafo = new grafo[1][1];
        grafo[1][1] = 0;
        
    }

    public Vertice[] finalVertice(Aresta a){
        Vertice[] finalfall = new Vertice[2];
        finalfall[0] = a.getbeg();
        finalfall[1] = a.getend();
        return finalfall;
    }


    public Vertice oposto(Vertice v, Aresta a){
        if (v != a.getbeg() && v != a.getend()){
            throw new GrafoException("O vértice não pertence a aresta");
        }

        if (v == a.getbeg()){
            return a.getend();
        }

        if (v == a.getend()){
            return a.getbeg();
        }
    }

    public boolean adjacent(Vertice v, Vertice w){
        return grafo[v.getkey()][w.getkey()] != 0;
    }

    public void substituirv(Vertice v, Object x){
        v.setelement(x);
    }

    public void substituira(Aresta a, Object x){
        a.setelement(x);
    }

    public void inserirVertice(Vertice v){
        int size = grafo.length;
        Object[][] new_graf = new Object[size+1][size+1];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                new_graf[i][j] = grafo[i][j];
            }
        }

        grafo = new_graf;

        new_graf = null;

        vertices.add(v);
    }

    public Aresta inserirAresta(Vertice v, Vertice w, Object o){
        Aresta new_aresta = new Aresta(v, w, o, false);

        grafo[v.getkey()][w.getkey()] = o;
        grafo[w.getkey()][v.getkey()] = o;

        return new_aresta;
    }

    public Object removeVertice(Vertice v){
        Object elemento = v.getelement();
        
        int size =  arestas.size();
        for (int i = 0; i < size; i++){
            if (v == arestas.get(i).getbeg() || v == arestas.get(i).getbend()){
                arestas.remove(arestas.get(i)); //Eu vou colocar o iterator depois (0)V(0) ...
            }
        }

        size = grafo.length;
        Object[][] new_graf = new Object[size-1][size-1]; 
        for (int i = 0; i < size-1; i++){
            for (int j = 0; j < size; j++){
                if (i == v.getkey()){
                    new_graf[i][j] = grafo[i+1][j];
                }
            }
        }

        vertices.remove(v);

        return elemento;
    }

    public Object removeAresta(Aresta a){
        Object elemento = a.getelement();

        arestas.remove(a);
        
        return elemento;
    }

    public Iterator<Aresta> arestasIncidentes(Vertice v){
        Iterator<Aresta> iter = new Iterator<>();

        return iter;
    }
    
    public Iterator<Vertice> (){
        Iterator<Vertice> iter = new Iterator<>();

        return iter;
    }
    
    public Iterator<Aresta> (){
        Iterator<Aresta> iter = new Iterator<>();

        return iter;
    }

    public boolean direction(Aresta a){
        return a.getdirecao();
    }

    public void inserirArestaDirecionada(v, w, o){
        Aresta new_aresta = new Aresta(v, w, o, true);

        grafo[v.getkey()][w.getkey()] = o;

        return new_aresta;
    }


}