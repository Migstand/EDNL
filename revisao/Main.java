import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);

        int ini = sc.nextInt();

        Object ini_ele = so.nextLine();

        Avbp pesca = new Avbp(ini, ini_ele);

        System.out.println("O início da Árvore tem chave "+ (pesca.root()).getkey()+" que guarda o elemento " + (pesca.root()).getelement());

    }

}