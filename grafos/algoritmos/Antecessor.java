import java.util.ArrayList;
import grafos.adjacentelista.Vertice;;

public class Antecessor {
    private double valor;
    private ArrayList<Vertice> ants;
    //private Vertice alvo;
    //private Vertice ref;

    public Antecessor(double valor, Vertice v){
        this.valor = valor;
        ants = new ArrayList<>();
        ants.add(v);
    }

    public void addant(Vertice v){
        ants.add(v);
    }

    public void setvalor(double valor){
        this.valor = valor;
    }

    public double getvalor(){
        return valor;
    }

    public ArrayList<Vertice> getants(){
        return ants;
    }
}
