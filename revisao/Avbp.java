import java.util.ArrayList;
import java.util.Iterator;

public class Avbp{
    private No raiz;
    private int size;

    public Avbp(int key, Object element){
        this.raiz = new No(key, element, null);
        this.size = 1;
    }
    public No root(){
        return this.raiz;
    }

    public No leftchild(No no){
        return no.leftchild();
    }

    public No rightchild(No no){
        return no.rightchild();
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
        return (leftchild(no) != null) || (rightchild(no) != null);
    }

    public boolean isExternal(No no){
        return (leftchild(no) == null) && (rightchild(no)) == null;
    }

    private ArrayList<No> children(No no){
        ArrayList<No> childs = new ArrayList<>();
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

    public void replace(No subs, No subed){
        subs.setkey(subed.getkey());
        subs.setelement(subed.getelement());
    }


    public No find(int k, No no){
        if (isExternal(no)){
            return no;
        }
        if (k < no.getkey()){
            return find(k, leftchild(no));
        }
        if (k == no.getkey()){
            return no;
        }else{
            return find(k, rightchild(no));
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
        No no = find(key, this.raiz);
        if(no.getkey()==key){
            System.out.println("Elemento já inserido");
        }
        No new_no = new No(key, ele, no);
        if (key > no.getkey()){
            no.setright(new_no);
        } else{
            no.setleft(new_no);
        }
    }

    public Object remove(No no){
        No removed = find(no.getkey(), this.raiz);
        if(no.getkey()!=removed.getkey()){
            return "Elemento já inserido";
        }
        if (isExternal(no)){
            no = null;
        } else{
            ArrayList <No> quant = children(no);
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
                No copy = rightchild(no);

                while(hasleft(copy)){
                    copy = leftchild(copy);
                }

                No new_no = new No(copy.getkey(), copy.getelement(), copy.getfather());
                (copy.getfather()).setleft(new_no);
                new_no.setright(rightchild(copy));
                replace(no, copy);
                copy = null;
            
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