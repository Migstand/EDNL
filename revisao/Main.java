import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;

public class Main{
    public static void main(String[] args){
        
        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        Random ran = new Random();

        int ini = sc.nextInt();

        Object ini_ele = so.nextLine();

        Avbp pesca = new Avbp(ini, ini_ele);

        System.out.println("O início da Árvore tem chave "+ (pesca.root()).getkey()+" que guarda o elemento " + (pesca.root()).getelement());
        
        int a = (pesca.root()).getkey();
        int valor;
        for (int i = 0; i < a; i++){
            System.out.println(i);
            // ini = sc.nextInt();
            // ini_ele = so.nextLine();
            valor = ran.nextInt(a*2);
            pesca.insert(valor, valor);
            // if (i < a/2){
            //     //System.out.println(a/2 + (i + 1) + " Ola" + i);
            //     pesca.insert(a/2 + (i + 1), "Ola" + i); 
            // } else {
            //     //System.out.println((a/2 * i) - i + " Ola" + i);
            //     pesca.insert((a/2 * i) - i, "Ola" + i); //(a + i) /2
            // }
            //pesca.insert(i+1, "Ola" + i);
        }
        System.out.println(pesca.height(pesca.root()) + " Altura");

        pesca.mostrar();

        System.out.println("---------------------------------------");
        System.out.println(" ");

        // Iterator<No> it = (pesca.nos()).iterator();
        // while (it.hasNext()){
        //     No mod = it.next();
        //     No pai = mod.getfather();//pesca.parent(mod);
        //     ArrayList<No> filhos = pesca.children(mod);
        //     if (pai == null){
        //         if (filhos.size() == 0){
        //             System.out.println(mod.getelement() + " O pai: " + "não existe" + ", É externo?: " + pesca.isExternal(mod) + ", É interno?: " + pesca.isInternal(mod) + ", É raiz?: " + pesca.isRoot(mod) + ", É fundo?: " + pesca.depth(mod));
        //         }
        //         if (filhos.size() == 1){
        //             System.out.println(mod.getelement() + " O pai: " + "não existe" + " O filho: " + (filhos.get(0)).getelement() + ", É externo?: " + pesca.isExternal(mod) + ", É interno?: " + pesca.isInternal(mod) + ", É raiz?: " + pesca.isRoot(mod) + ", É fundo?: " + pesca.depth(mod));
        //         } else{
        //             System.out.println(mod.getelement() + " O pai: " + "não existe" + " Os filhos: " + (filhos.get(0)).getelement() + "  " + (filhos.get(1)).getelement() +", É externo?: " + pesca.isExternal(mod) + ", É interno?: " + pesca.isInternal(mod) + ", É raiz?: " + pesca.isRoot(mod) + ", É fundo?: " + pesca.depth(mod));
        //         }
        //     } else{
        //         if (filhos.size() == 0){
        //             System.out.println(mod.getelement() + " O pai: " + pai.getelement() + ", É externo?: " + pesca.isExternal(mod) + ", É interno?: " + pesca.isInternal(mod) + ", É raiz?: " + pesca.isRoot(mod) + ", É fundo?: " + pesca.depth(mod));
        //         }
        //         if (filhos.size() == 1){
        //             System.out.println(mod.getelement() + " O pai: " + pai.getelement() + " O filho: " + (filhos.get(0)).getelement() + ", É externo?: " + pesca.isExternal(mod) + ", É interno?: " + pesca.isInternal(mod) + ", É raiz?: " + pesca.isRoot(mod) + ", É fundo?: " + pesca.depth(mod));
        //         } else{
        //             System.out.println(mod.getelement() + " O pai: " + pai.getelement() + " Os filhos: " + (filhos.get(0)).getelement() + "  " + (filhos.get(1)).getelement() +", É externo?: " + pesca.isExternal(mod) + ", É interno?: " + pesca.isInternal(mod) + ", É raiz?: " + pesca.isRoot(mod) + ", É fundo?: " + pesca.depth(mod));
        //         }
                
        //     }
            
        // }

        System.out.println("---------------------------------------");
        System.out.println(" ");

        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }

}