package grafos.algoritmos;

import java.lang.reflect.Array;
import java.util.ArrayList;
import grafos.adjacentelista.Grafo;
import grafos.adjacentelista.Vertice;
import grafos.adjacentelista.Aresta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class Astar{

    private Grafo grafo;

    public Astar(Grafo g){
        grafo = g;
    }

    public ArrayList<Caminho> astar(Aresta ini, Aresta fim, int parede){
        Caminho partida = new Caminho(ini);
        Caminho chegada = new Caminho(fim);

        int livre = (Integer) ini.getelement();
        int fg = 0;
        
        ArrayList<Caminho> aberta = new ArrayList<>();
        ArrayList<Caminho> fechada = new ArrayList<>();
        ArrayList<Aresta> caminho = new ArrayList<>();
        
        
        boolean achou;
        aberta.add(partida);
        Caminho atual = partida;

        while (aberta.size() != 0 && achou == false) {
            fechada.add(atual);
            aberta.remove(atual);

            for (int i = 0; i < 8; i++){
                Aresta andar = andar(fim, fg, i);
            }

            // Receber o proximo com menor f
        }

        if (achou == false){
            return null;
        }

        return caminho;

    }

    private void calcular_caminho(Caminho ca, ArrayList<Caminho> open, ArrayList<Caminho> close){
        
        for (int i = 0; i < 8; i++){ // RASCUNHO
            if ( "espaco" != null && "espaco" !in(close)){
                if ( "nao andou"){
                    Caminho novo = new Caminho(aresta);
                    novo.setg(ca+valor);
                    novo.setpai(ca);
                } else {
                    if (ca.getg() > camin.getg()){
                        ca.setpai(camin);
                    }
                }

            }
        }
    }

    private Aresta andar(Aresta ar, int x, int y){
        Vertice[] vets = ar.finalVertice();
        Vertice cha1 = vets[0]; // Vertice da vertical 
        Vertice cha2 = vets[1]; // Vertice da horizontal

        
        Aresta aresta;
        aresta = cha1.getarestas().get((cha2.getkey() - 1) + y);
        cha2 = aresta.finalVertice()[1];
        aresta = cha2.getarestas().get((cha1.getkey() - 1) + x);
        
        return aresta;
        // cha2 = aresta.finalVertice()[];
        
    }

}