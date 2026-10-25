package grafos.matriz;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo{
    private Aresta[][] grafo;
    private ArrayList<Vertice> vertices;
    private int[][] fake_grafo;

    public Grafo(int key, Object o){
        vertices = new ArrayList<>();
        Vertice vet = new Vertice(key, o);
        vertices.add(vet);
        grafo = new Aresta[1][1];
        fake_grafo = new int[1][1];
        
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
        return (grafo[v.getkey()][w.getkey()] != null) || (grafo[w.getkey()][v.getkey()] != null);
    }
    // ----------- \\

    //* SUBSTITUIÇÕES *\\
    public void substituirv(Vertice v, Object x){
        v.setelement(x);
    }

    public void substituira(Aresta a, Object x){
        a.setelement(x);
    }
    // --------------- \\

    //* INSERÇÕES *\
    public void inserirVertice(Object o){
        int size = grafo.length;
        Aresta[][] new_graf = new Aresta[size+1][size+1];
        int [][] new_fake = new int[size+1][size+1];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                new_graf[i][j] = grafo[i][j];

                // Momentaneo \\
                new_fake[i][j] = fake_grafo[i][j];
            }
        }

        grafo = new_graf;
        fake_grafo = new_fake;
        new_graf = null;

        Vertice v = new Vertice(size, o);

        vertices.add(v);
    }

    public Aresta inserirAresta(Vertice v, Vertice w, Object o){
        Aresta new_aresta = new Aresta(v, w, o, false);

        
        grafo[v.getkey()][w.getkey()] = new_aresta;
        if (v.getkey() != w.getkey()){
            grafo[w.getkey()][v.getkey()] = new_aresta;
        }

        // Momentaneo \\

        fake_grafo[v.getkey()][w.getkey()] += 1;
        if (v.getkey() != w.getkey()){
            fake_grafo[w.getkey()][v.getkey()] += 1;    
        }
        

        return new_aresta;
    }
    //--------------------\\

    //* REMOVES *\\
    public Object removeVertice(Vertice v){
        Object elemento = v.getelement();

        int size = grafo.length;
        int chave = v.getkey();

        Aresta[][] new_graf = new Aresta[size-1][size-1]; 
        for (int i = 0; i < size; i++){
            if (i == chave){continue;}
            for (int j = 0; j < size-1; j++){
                if (j == chave) {j++;}
                else{new_graf[i][j] = grafo[i][j];}
            }
        }

        // Momentaneo \\
        int[][] new_fake = new int[size-1][size-1]; 
        for (int i = 0; i < size; i++){
            if (i == chave){continue;}
            for (int j = 0; j < size; j++){
                if (j == chave) {j++;}
                else{new_fake[i][j] = fake_grafo[i][j];}
            }
        }

        grafo = new_graf;
        fake_grafo = new_fake;

        vertices.remove(v);

        return elemento;
    }

    public Object removeAresta(Aresta a){
        Object elemento = a.getelement();

        int pri = a.getbeg().getkey();
        int sec = a.getend().getkey();

        grafo[pri][sec] = null;
        if (!isdircet(a)){
            grafo[sec][pri] = null;
        }
        // Momentanio

        fake_grafo[pri][sec]--;
        if (!isdircet(a)){
            fake_grafo[sec][pri]--;
        }
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
            if (grafo[i][chave] != null){
                arestas.add(grafo[i][chave]);
            }
            if (i == chave){ i++;}
            
        }

        Iterator<Aresta> iter = arestas.iterator();
        return iter;
    }

    public Iterator<Vertice> vertices(){
        Iterator<Vertice> iter = vertices.iterator();
        return iter;
    }

    public Iterator<Aresta> arestas(){
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
    public boolean isdircet(Aresta a){
        return a.direcao();
    }

    public Aresta inserirArestaDirecionada(Vertice pri, Vertice fim, Object o){
        Aresta new_aresta = new Aresta(pri, fim, o, true);

        grafo[pri.getkey()][fim.getkey()] = new_aresta;

        //Momentanio
        fake_grafo[pri.getkey()][fim.getkey()] += 1;

        return new_aresta;
    }

    public void draw(){
        for (int i = 0; i < grafo.length; i++){
            for (int j = 0; j < grafo.length; j++){
                if (grafo[i][j] != null){
                    System.out.print(" |"+ fake_grafo[i][j]);
                } else{
                    System.out.print(" |0" );
                }
            }
            System.err.println("");
        }
    }


}