import grafos.adjacentelista.Vertice;
// Quando utilizar o antecessor, terei acesso para o caminho que ele percorreu;
public class Antecessor {
    private double valor; // Valor mínimo adquirido
    private Antecessor ants;
    private Vertice vet; // Significa o vértice alvo da chega; Ex: A ---> D;

    public Antecessor(double valor, Vertice v){
        this.valor = valor;
        vet = v;
        ants = null;
    }

    public void setant(Antecessor ant){
        ants = ant;
    }

    public void setvalor(double valor){
        this.valor = valor;
    }

    public void setvet(Vertice v){
        vet = v;
    }

    public double getvalor(){
        return valor;
    }

    public Antecessor getants(){
        return ants;
    }

    public Vertice getvet(){
        return vet;
    }
}
