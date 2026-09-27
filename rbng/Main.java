package rbng;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        long tempoInicial = System.nanoTime();
        
        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);
        //Random ran = new Random();

        int key = sc.nextInt();
        Object element = so.nextLine();

        RN Rubro_Negra = new RN(key, element);

        int insercoes = key/2;
        System.out.println(" Insira: " + insercoes + " elementos.");
        for (int i = 0; i < insercoes; i++){
            
            System.out.println("Chave: ");
            key = sc.nextInt();
            System.out.println(" ");
            System.out.println("Valor: ");
            element = so.nextLine();
            
            No inserido = Rubro_Negra.find(key, Rubro_Negra.root());
            if (inserido != null){
                if (inserido.getkey() == key){ // Isso aqui é só para ficar bonitinho =/
                    System.out.println( " ");
                    System.out.println("Essa chave já está inserida!!");
                }
            }
            Rubro_Negra.insert(key, element); 
            
        }

        System.out.println(" Sua árvore atual ");
        System.out.println(" ");
        Rubro_Negra.mostrar_cores();
        System.out.println(" Altura inicial: " + Rubro_Negra.height(Rubro_Negra.root()));

        int remocoes = insercoes/2;
        System.out.println(" Romova: " + remocoes + " elementos.");
        for (int i = 0;  i < remocoes; i++){
            key = sc.nextInt();
            Rubro_Negra.remove(key);

            No removido = Rubro_Negra.find(key, Rubro_Negra.root());
            if (removido != null){
                if (removido.getkey() == key){ // Isso aqui é só para ficar bonitinho =/
                    System.out.println( " ");
                    System.out.println("Essa chave não existe mais!!");
                }
            }

        }

        System.out.println(" Sua árvore atual ");
        System.out.println(" ");
        Rubro_Negra.mostrar_cores();
        System.out.println(" Altura inicial: " + Rubro_Negra.height(Rubro_Negra.root()));

        sc.close();
        so.close();

        long tempoFinal = System.nanoTime();
        long tempoTotalNano = tempoFinal - tempoInicial;

        // Converte para milissegundos para facilitar a leitura
        double tempoTotalMili = tempoTotalNano / 1_000_000.0; 

        System.out.println("Tempo de execução: " + tempoTotalMili + " ms");

    }
}
