package rbng;

public class RN extends Avbp{
    private int num = 0;

    public RN(int key, Object element){
        super(key, element);
        No raiz = root();
        raiz.setcor(0);
    }

    public void mostrar_cores(){
        
        if (size() == 0){
            System.out.println("Não tem nada para ver");
        } else {
            Object matriz[][] = new Object[height(root())+1][size() + 1];
            visuals(matriz, root());
            this.num = 0;

            for (int i = 0; i < height(root()) + 1; i++){
                for (int j = 0; j < size() + 1; j++){
                    if (matriz[i][j] == null){
                        System.out.print("  ");
                    } else {
                        System.out.print(matriz[i][j] + "  ");
                    }
                }
                System.out.println("");
            }    
        }
        
    }

    private void visuals(Object[][] ob, No no){
        if (hasleft(no)){
            visuals(ob, leftchild(no));
        }
        //System.out.println(this.num);
        if (no.getcor() == 0){
            ob[depth(no)][this.num] = no.getkey() + " [Negro] ";    
        } else {
            ob[depth(no)][this.num] = no.getkey() + " [Rubro] ";
        }
        //ob[depth(no)][this.num] = no.getelement() + " [" + no.getfb()+ "]";
        ++this.num;
        if (hasright(no)){
            visuals(ob, rightchild(no));
        }
    }
}