package rbng;

public class Teste{
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        Scanner so = new Scanner(System.in);

        int ini = sc.nextInt();

        Object ini_ele = so.nextLine();


        RN rub = new RN(ini, ini_ele);

        System.out.println(rub.root().getcor());
    }

}