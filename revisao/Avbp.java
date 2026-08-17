import java.util.Arraylist;
import java.util.Iterator;

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

    public boolean hasleft(No no){
        return leftchild(no) != null;
    }

    public boolean hasright(No no){
        return rightchild(no) != null;
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

    private Arraylist<No> children(No no){
        Arraylist<No> childs = new Arraylist<>;
        if (hasleft(no)){
            childs.add(leftchild(no));
        }
        if (hasright(no)){
            childs.add(rightchild(no));
        }
        return childs;
    }

    public int depth(No no){
        if (isRoot(no)){
            return 0;
        }
        return 1 + depth(no.getfather());

    }

    public int height(No no){
        if (isExternal(no)){
            return 0;
        }
        int h = 0;

        Iterator <No> childs = (children(no)).iterator();
        while (childs.hasNext()){
            No next_no = childs.next();
            h = Math.max(h, height(next_no));
        }
        return 1 + h;
    }


    public No find(No no, int k){
        if (isExternal(no)){
            return no;
        }
        if (k < no.getkey()){
            return find(leftchild(no), k);
        }
        if (k == no.getkey()){
            return no;
        }else{
            return find(rightchild(no), k);
        }

        // * WHILE VERSION * \\
        // No compar = this.raiz;
        // while (compar != null){
        //     if (no.getkey() > compar.getkey()){
        //         compar = compar.rightchild();
        //     }

        //     if (no.getkey() < compar.getkey()){
        //         compar = compar.leftchild();
        //     }

        //     if (compar.getkey() == no.getkey()){
        //         return no.getelement();
        //     }
        // }

        // return "Não encontrado";
    }

    public void insert(int key, Object ele){
        No no = find(key);
        No new_no;
        if (key > no.getkey()){
            new_no = new No(key, ele, no);
            no.setright(new_no);
        } else{
            new_no = new No(key, ele, no);
            no.setleft(new_no);
        }
    }

    public Object remove(No no){
        // Object removed = find(no);
        // if (no == null){
        //     return "Não existe o objeto";
        // }
        Object removed = no.getelement();
        if (isExternal(no)){
            no = null;
        } else{
            Arraylist <No> quant = children(no);
            int si = quant.size();
            if (si == 1){
                if ((no.getfather()).getkey() < no.getkey()){
                    (no.getfather()).setright(quant.get(0));
                    No new_no = new No((quant.get(0)).getkey(), (quant.get(0)).getelement(), (quant.get(0)).getfather());
                    new_no.setleft(leftchild(quant.get(0)));
                    new_no.setright(rightchild(quant.get(0)));
                } else{
                    (no.getfather()).setleft(quant.get(0));
                    No new_no = new No((quant.get(0)).getkey(), (quant.get(0)).getelement(), (quant.get(0)).getfather());
                    new_no.setleft(leftchild(quant.get(0)));
                    new_no.setright(rightchild(quant.get(0)));
                }
            } else{
                // A brincadeira começa aqui >:/
                No copy = no.getright();
                while(hasleft(copy)){
                    copy = leftchild(copy);
                }
                No new_no = new No(copy.getkey(), copy.getelement(), copy.getfather());
                (copy.getfather()).setleft(copy);
                new_no = new No(no.getkey(), no.getelement(), no.getfather())
                (no.getfather()).setleft(no);
                no = null;
            }
        }

        no = null;

        return removed;
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