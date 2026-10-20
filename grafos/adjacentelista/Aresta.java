package grafos.adjacentelista;

public class Aresta{
    private Vertice ini;
    private Vertice fim;
    private Object valor;
    private boolean direcionado;

    public Aresta(Vertice ini, Vertice fim, Object valor,boolean direcionado){
        this.ini = ini;
        this.fim = fim;
        this.valor = valor;
        this.direcionado = direcionado;
    } 


    public void setbeg(Vertice beg){
        ini = beg;
    }

    public void setend(Vertice end){
        fim = end;
    }

    public void setvalor(Object o){
        valor = o;
    }

    public Vertice getbeg(){
        return ini;
    }
    
    public Vertice getend(){
        return fim;
    }
    
    public Object getvalor(){
        return valor;
    }
    
    public boolean direcao(){
        return direcionado;
    }
}