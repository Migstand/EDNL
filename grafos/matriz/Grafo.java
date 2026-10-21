package grafos.matriz;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo{
    private Aresta[][] grafo;
    private ArrayList<Vertice> vertices;

    public Grafo(int key, Object o){
        vertices = new ArrayList<>();
        Vertice vet = new Vertice(key, o);
        vertices.add(vet);
        grafo = new Aresta[1][1];
        
    }

    //* OBTER *\\
    public Vertice[] finalVertice(Aresta a){
        Vertice[] finalfall = new Vertice[2];
        finalfall[0] = a.getbeg();
        finalfall[1] = a.getend();
        return finalfall;
    }


    public Vertice oposto(Vertice v, Aresta a){

        if (v == a.getbeg()){
            return a.getend();
        }

        if (v == a.getend()){
            return a.getbeg();
        }
        
        throw new GrafoException("O vértice não pertence a aresta");
    }

    public boolean adjacent(Vertice v, Vertice w){
        return grafo[v.getkey()][w.getkey()] != null;
    }
    // ----------- \\

    //* SUBSTITUIÇÕES *\\
    public void substituirv(Vertice v, Object x){
        v.setelement(x);
    }

    public void substituira(Aresta a, Object x){
        a.setvalor(x);
    }
    // --------------- \\

    //* INSERÇÕES *\
    public void inserirVertice(Vertice v){
        int size = grafo.length;
        Aresta[][] new_graf = new Aresta[size+1][size+1];
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

        grafo[v.getkey()][w.getkey()] = new_aresta;
        grafo[w.getkey()][v.getkey()] = new_aresta;

        return new_aresta;
    }
    //--------------------\\

    //* REMOVES *\\
    public Object removeVertice(Vertice v){
        Object elemento = v.getelement();

        int size = grafo.length;
        int chave = v.getkey();

        Object[][] new_graf = new Object[size-1][size-1]; 
        for (int i = 0; i < size-1; i++){
            if (i == chave){i++;}
            for (int j = 0; j < size; j++){
                if (j == chave) {j++;}
                new_graf[i][j] = grafo[i][j];
            }
        }

        vertices.remove(v);

        return elemento;
    }

    public Object removeAresta(Aresta a){
        Object elemento = a.getvalor();

        int pri = a.getbeg().getkey();
        int sec = a.getend().getkey();

        grafo[pri][sec] = null;
        grafo[sec][pri] = null;
        
        return elemento;
    }
    //---------------------\\

    
    //* ITERATORS *\\

     public Iterator<Aresta> arestasIncidentes(Vertice v){
        ArrayList<Aresta> arestas = new ArrayList<>();
        
        int chave = v.getkey();

        for (int i = 0; i < grafo.length; i++){
            if (grafo[chave][i] != null){
                arestas.add(grafo[chave][i]);
            } 
        }

        for (int i = 0; i < grafo.length; i++){
            if (i == chave){ i++;}
            if (grafo[chave][i] != null){
                arestas.add(grafo[chave][i]);
            }
        }

        Iterator<Aresta> iter = arestas.iterator();
        return iter;
    }

    public Iterator<Vertice> vertices(){
        Iterator<Vertice> iter = vertices.iterator();
        return iter;
    }

    public Iterator<Aresta> arestas(Vertice v){
        ArrayList<Aresta> arestas = new ArrayList<>();

        for (int i = 0; i < grafo.length; i++){
            for (int j = 0; j < grafo.length; j++){
                if (grafo[i][j] != null){
                    arestas.add(grafo[i][j]);
                }
            }
        }
        Iterator<Aresta> iter = arestas.iterator();
        return iter;
    }
    // --------------------------- \\

    //*  *\\
    public boolean hasdirection(Aresta a){
        return a.direcao();
    }

    public Aresta inserirArestaDirecionada(Vertice pri, Vertice fim, Object o){
        Aresta new_aresta = new Aresta(pri, fim, o, true);

        grafo[pri.getkey()][fim.getkey()] = new_aresta;

        return new_aresta;
    }

    public void draw(){
        for (int i = 0; i < grafo.length; i++){
            for (int j = 0; j < grafo.length; j++){
                if (grafo[i][j] != null){
                    System.out.println(" |1");
                } else{
                    System.out.println(" |0" );
                }
            }
        }
    }


}