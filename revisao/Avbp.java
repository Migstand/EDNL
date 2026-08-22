import java.util.ArrayList;
import java.util.Iterator;

public class Avbp{
    private int num = 0;
    private No raiz;
    private int size;

    public Avbp(int key, Object element){
        this.raiz = new No(key, element, null);
        this.size = 1;
    }

    public int size(){
        return this.size;
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

    public boolean isRoot(No no){
        return no == this.raiz;
    }

    public boolean isInternal(No no){
        return (leftchild(no) != null) || (rightchild(no) != null);
    }

    public boolean isExternal(No no){
        return (leftchild(no) == null) && (rightchild(no)) == null;
    }

    public ArrayList<Object> elements(){
        ArrayList<Object> elementos = new ArrayList<Object>();
        pre_order(this.raiz,elementos);
        
        return elementos;
    }

    public ArrayList<No> nos(){
        ArrayList<No> elementos = new ArrayList<No>();
        pre_orderNo(this.raiz, elementos);

        //Iterator<No> ite = elementos.iterator();
        return elementos;
    }

    public ArrayList<No> children(No no){
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
        if (no == null){
            return no;
        }
        if (isExternal(no)){
            //System.out.println(no.getkey() + " A chave Find Ext");
            return no;
        }
        if (k < no.getkey()){
            //System.out.println(no.getkey() + " A chave Find Left");
            if (leftchild(no) == null) {
                return no; // o pai onde será inserido
            }
            return find(k, leftchild(no));
        }
        if (k == no.getkey()){
            //System.out.println(no.getkey() + " A chave Find Ig");
            return no;
        }else{
            //System.out.println(no.getkey() + " A chave Find Right");
            if (rightchild(no) == null) {
                return no; // o pai onde será inserido
            }
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
        //System.out.println(no.getkey() + " A chave check " + key);
        if(no.getkey() != key){
            No new_no = new No(key, ele, no);
            if (key > no.getkey()){
                no.setright(new_no);
            } else{
                no.setleft(new_no);
            }
            this.size++;
            //System.out.println("O Elemento " + new_no.getelement() + " de chave " + new_no.getkey() + " foi inserido");
        } else{
            //System.out.println("O Elemento " + no.getelement() + " de chave " + no.getkey() + " já inserido");
        }
        
    }

    public void remove(int key){
        No removed = find(key, this.raiz);
        Object ele = removed.getelement();
        if(key!=removed.getkey()){
            System.out.println("Elemento não encontrado");
        } else{
            if (isExternal(removed)){
                if (isRoot(removed)){
                    this.raiz = null;
                } else{
                    if ((removed.getfather()).getkey() < key){
                        (removed.getfather()).setleft(null);
                    }else{
                        (removed.getfather()).setright(null);
                    }
                }
                //removed.setfather(null);
                removed.setfather(null);
            
            } else{
                ArrayList <No> quant = children(removed);
                int si = quant.size();
                //System.out.println(quant.size() + " Tamanho do nó " + removed.getkey());
                if (si == 1){
                    // Implementar catch para o caso 
                    if (isRoot(removed)){
                        quant.get(0).setfather(null);
                        this.raiz = quant.get(0);
                    } //
                    else{
                        if ((removed.getfather()).getkey() < key){
                            (removed.getfather()).setright(quant.get(0));
                            (quant.get(0)).setfather(removed.getfather());
                            // No new_no = new No((quant.get(0)).getkey(), (quant.get(0)).getelement(), removed.getfather());
                            // new_no.setleft(leftchild(quant.get(0)));
                            // new_no.setright(rightchild(quant.get(0)));
                        } else{
                            (removed.getfather()).setleft(quant.get(0));
                            (quant.get(0)).setfather(removed.getfather());
                            // No new_no = new No((quant.get(0)).getkey(), (quant.get(0)).getelement(), removed.getfather());
                            // new_no.setleft(leftchild(quant.get(0)));
                            // new_no.setright(rightchild(quant.get(0)));
                        }
                    }
                    
                } else{
                    // A brincadeira começa aqui >:/
                    No copy = rightchild(removed);
                    No verif = copy;
                    while(hasleft(copy)){
                        copy = leftchild(copy);
                    }

                    replace(removed, copy);
                    if (verif != copy){
                        (copy.getfather()).setleft(rightchild(copy));
                    } else{
                        (copy.getfather()).setright(rightchild(copy));
                    }

                    if (rightchild(copy) != null){
                        rightchild(copy).setfather(copy.getfather());
                    }
            
                    copy = null;
                
                }
                //System.out.println("O Elemento " + ele + " foi removido!");
            }

            removed = null;
            this.size--;
        }
        
    }


    public void mostrar(){
        
        if (size() == 0){
            System.out.println("Não tem nada para ver");
        } else {
            Object matriz[][] = new Object[height(root())+1][size() + 1];
            visuals(matriz, root());
            this.num = 0;

            for (int i = 0; i < height(root()) + 1; i++){
                for (int j = 0; j < size() + 1; j++){
                    if (matriz[i][j] == null){
                        System.out.print("    ");
                    } else {
                        System.out.print(matriz[i][j] + "    ");
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
        // System.out.println(this.num);
        ob[depth(no)][this.num] = no.getelement();
        ++this.num;
        if (hasright(no)){
            visuals(ob, rightchild(no));
        }
    }

    private void pre_order(No v, ArrayList<Object> lista){
        if (v == null){
            return;
        }

        lista.add(v.getelement());

        Iterator<No> meus_fi = children(v).iterator();

        while (meus_fi.hasNext()){
            No prox = meus_fi.next();
            pre_order(prox, lista);
        }
    }

    private void pre_orderNo(No v, ArrayList<No> lista){
        if (v == null){
            return;
        }

        lista.add(v);

        Iterator<No> meus_fi = children(v).iterator();

        while (meus_fi.hasNext()){
            No prox = meus_fi.next();
            pre_orderNo(prox, lista);
        }
    }
}