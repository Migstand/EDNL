package grafos.adjacentelista;

import java.util.ArrayList;
import java.util.Iterator;

public class Grafo{
    private ArrayList<Vertice> grafo;
    private ArrayList<Aresta> arestas;
    private int[][] fake_grafo;

    public Grafo(Object o){
        grafo = new ArrayList<>();
        arestas =  new ArrayList<>();
        Vertice vet = new Vertice(0, o);
        grafo.add(vet);
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
        int pri = v.getkey();
        int sec = w.getkey();
        for (int i = 0; i < grafo.size(); i++){
            if (grafo.get(pri).getarestas().get(i))
        }
        return false;
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
        int size = grafo.size();
        Vertice v = new Vertice(size, o);
        grafo.add(v);
    }

    public Aresta inserirAresta(Vertice v, Vertice w, Object o){
        Aresta new_aresta = new Aresta(v, w, o, false);
        arestas.add(new_aresta);

        grafo.get(v.getkey()).setaresta(new_aresta);
        
        if (v.getkey() != w.getkey()){
            grafo.get(w.getkey()).setaresta(new_aresta);
        }

        // Momentaneo \\

        fake_grafo[v.getkey()][w.getkey()] += 1;
        if (v.getkey() != w.getkey()){
            fake_grafo[w.getkey()][v.getkey()] += 1;    
        }
        

        return new_aresta;
    }
    //--------------------\\

    public Object removeVertice(Vertice v){
        Object elemento = v.getelement();

        int size = grafo.size();
        
        Aresta as = null;

        Iterator<Aresta> restas = arestasIncidentes(v);
        while(restas.hasNext()){
            as = restas.next();
            removeAresta(as);
        }
        for (int i = v.getkey(); i < size-1; i++){
            vertices.get(i+1).setkey(i);
            vertices.set(i, vertices.get(i+1));
        }
        vertices.remove(size-1);
        

        // Momentaneo \\
        int[][] new_fake = new int[size-1][size-1]; 
        for (int i = 0; i < size-1; i++){
            if (i == chave){continue;}
            for (int j = 0; j < size-1; j++){
                if (j == chave) {continue;}
                else{new_fake[i][j] = fake_grafo[i][j];}
            }
        }

        return elemento;
    }

    public Object removeAresta(Aresta a){
        Object elemento = a.getelement();

        int pri = a.getbeg().getkey();
        int sec = a.getend().getkey();

        arestas.remove(a);
        grafo.get(pri).getarestas().remove(a);
        grafo.get(sec).getarestas().remove(a);
        
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
        ArrayList<Aresta> arests = v.getarestas();

        Iterator<Aresta> iter = arests.iterator();
        return iter;
    }

    public Iterator<Vertice> vertices(){
        Iterator<Vertice> iter = vertices.iterator();
        return iter;
    }

    public Iterator<Aresta> arestas(){
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

        grafo.get(v.getkey()).setaresta(new_aresta);

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

    public void draw_des(){
        for (int i = 0; i < grafo.size(); i++){
            System.out.print(grafo.get(i).getelement() + ": ");
            ArrayList<Aresta> arests = grafo.get(i).getarestas;
            if (arests.isEmpty()){
                System.out.print(" |null");
            }
            for (int j = 0; j < arests.size(); j++){
                System.out.print(" |"+ (arests.get(j)).getelement());
            }
            System.out.println("");
        }
    }


}