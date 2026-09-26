package rbng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;

public class Teste{
    public static void main(String[] args){
        
        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        Random ran = new Random();

        int ini = sc.nextInt();

        Object ini_ele = so.nextLine();


        RN rub = new RN(ini, ini_ele);

        // if (rub.root().getcor() == 0){
        //     System.out.println("Negro");
        // } else{
        //     System.out.println("Rubro");
        // }

        int a = (rub.root()).getkey();
        int valor;
        //No check;
        
        for (int i = 0; i < a; i++){
            valor = ran.nextInt(a*2);
            //System.out.println(valor);
            //System.out.println(" --------------------------------- ");
            rub.insert(valor, valor);
            // rub.mostrar_cores();
            
            // System.out.println(" --------------------------------- ");
            // System.out.println( " ");
            // System.out.println( " ");

        }

        // INSERÇÕES SÓ PARA DIREITA
        // for (int i = 0; i < a; i++){
        //     valor = (a + i);
        //     rub.insert(valor, valor);
        //     //rub.mostrar_cores();

        //     // System.out.println(" --------------------------------- ");
        //     // System.out.println( " ");
        // }

        // INSERÇÕES SÓ PARA ESQUERDA
        // for (int i = 0; i < a; i++){
        //     valor = (a - i);
        //     rub.insert(valor, valor);
        //     rub.mostrar_cores();
        //     System.out.println(" --------------------------------- ");
        //     System.out.println( " ");
        // }

        //rub.mostrar_cores();
        // rub.desenharArvore(rub.root());
        System.out.println(rub.size() + " Tamanho final");
        
        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }

}