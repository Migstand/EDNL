package avl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;
import revisao.No;

public class Teste{
    public static void main(String[] args){
        long tempoInicial = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        Random ran = new Random();

        int key = sc.nextInt();
        Object ele = so.nextLine();

        Avl avl = new Avl(key, ele);

        System.out.println(avl.root().getkey());

        int a = (avl.root()).getkey();
        int valor;
        No check;
        
        for (int i = 0; i < a; i++){
            //valor = (13+i);
            valor = ran.nextInt(a*2);
            avl.insert(valor, valor);
            //System.out.println(check.getkey() + " Fb: " + check.getfb());
            //avl.mostrar_fb();
            //System.out.println("---------------------------------------");
            //System.out.println(" ");
        }

        // for (int i = 0; i < a; i++){
        //     valor = (13+i);
        //     //valor = ran.nextInt(a*2);
        //     check = avl.insert(valor, valor);
        //     //System.out.println(check.getkey() + " Fb: " + check.getfb());
        //     avl.mostrar();
        // }

        // ArrayList<Object> list = avl.elements();

        // for (int i = 0; i < list.size(); i++){
        //     System.out.println(list.size());
        // }
        //System.out.println(avl.leftchild(avl.root()).getfb());
        System.out.println(avl.rightchild(avl.root()).getfb());
        
        

        System.out.println(avl.height(avl.root()) + " Altura");
        System.out.println(avl.size() + " Tamanho inicial");
        avl.mostrar_fb();

        System.out.println("---------------------------------------");
        System.out.println(" ");

        int s = avl.size();
        for (int i = 0; i < s/2; i++){
            //System.out.println("---------------------------------------");
            //System.out.println(" ");
            avl.remove((avl.root().getkey()));
            
        }
        System.out.println(avl.size() + " Tamanho final");

        System.out.println("---------------------------------------");
        System.out.println(" ");
        avl.mostrar_fb();

        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");
    }
}