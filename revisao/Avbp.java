public class Avbp{
    private No raiz;
    private int size;

    public Avbp(int key, Object element){
        this.raiz = new No(key, element, null);
        this.size = 1;
    }

    public No leftchild(No no){
        return no.getleft();
    }

    public No rightchild(No no){
        return no.getright();
    }

    public int size(){
        return this.size;
    }

    public boolean isRoot(No no){
        return no == this.raiz;
    }

    public boolean isInternal(No no){
        return leftchild(no) != null or rightchild(no) != null;
    }

    public boolean isExternal(No no){
        return leftchild(no) == null and rightchild(no) == null;
    }

    public int depth(No no){
        int h = 0;

        No copy = no;
        
        return sumroot(h, copy);

    }

    private int sumroot(int h, No copy){
        if (isRoot(copy)){
            return h;
        } else{
            copy = copy.getfather();
            h += 1;
            return sumroot(h, copy);
        }
    }

    public int height(){
        
    }


    public Object find(No no){
        No compar = this.raiz;

        while (compar != null){
            if (no.getkey() > compar.getkey()){
                compar = compar.rightchild();
            }

            if (no.getkey() < compar.getkey()){
                compar = compar.leftchild();
            }

            if (compar.getkey() == no.getkey()){
                return no.getelement();
            }
        }

        return "Não encontrado";
    }

    // public void mostrar(){
        
    //     Object matriz[][] = new Object[height(root())+1][size()];
    //     visuals(matriz, root());
    //     this.num = 0;

    //     for (int i = 0; i < height(root())+1; i++){
    //         for (int j = 0; j < size(); j++){
    //             if (matriz[i][j] == null){
    //                 System.out.print("    ");
    //             } else {
    //                 System.out.print(matriz[i][j] + "    ");
    //             }
    //         }
    //         System.out.println("");
    //     }
    // }    
}