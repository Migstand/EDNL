package grafos.adjacentelista;

import grafos.Aresta;
import java.util.ArrayList;

public class Vertice{
    private int key;
    private Object element;
    private ArrayList<Aresta> arestas;

    public Vertice(int key, Object element){
        this.key = key;
        this.element = element;
        arestas = new ArrayList<>(5);
    }

    public void setelement(Object o){
        this.element = o;
    }

    public Object getelement(){
        return element;
    }

    public int getkey(){
        return key;
    }
    public void setkey(int key){
        this.key = key;
    }

    public ArrayList<Aresta> getarestas(){
        return arestas;
    }

    public void setaresta(Aresta a){
        arestas.add(a);
    }
}