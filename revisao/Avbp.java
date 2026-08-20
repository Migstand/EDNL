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
            return no;
        }
        if (k < no.getkey()){
            if (leftchild(no) == null) {
                return no; // o pai onde será inserido
            }
            return find(k, leftchild(no));
        }
        if (k == no.getkey()){
            return no;
        }else{
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
        if(no.getkey() != key){
            No new_no = new No(key, ele, no);
            if (key > no.getkey()){
                no.setright(new_no);
            } else{
                no.setleft(new_no);
            }
            this.size++;
        } else{
            System.out.println("Elemento já inserido");
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
        
    //     Object matriz[][] = new Object[height(root()) + 1][size()];
    //     visuals(matriz, root());
    //     this.num = 0;

    //     for (int i = 0; i < height(root()) + 1; i++){
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

    // private void visuals(Object[][] ob, No no){
    //     if (hasleft(no)){
    //         visuals(ob, leftchild(no));
    //     }
    //     // System.out.println(this.num);
    //     ob[depth(no)][this.num] = no.getelement();
    //     ++this.num;
    //     if (hasright(no)){
    //         visuals(ob, rightchild(no));
    //     }
    // }

    // public void mostrar() {
    //     mostrarRecursivo(root(), 0);
    // }

    // private void mostrarRecursivo(No no, int nivel) {
    //     if (no == null) return;

    //     // 1. Visita o lado direito primeiro (para o topo da árvore ficar na esquerda da tela)
    //     if (hasright(no)) {
    //         mostrarRecursivo(rightchild(no), nivel + 1);
    //     }

    //     // 2. Imprime o nó atual com recuo baseado no nível (profundidade)
    //     for (int i = 0; i < nivel; i++) {
    //         System.out.print("    "); // 4 espaços por nível de profundidade
    //     }
    //     if (no == root()){
    //         System.out.println(no.getelement() + " (Chave: " + no.getkey() + ")");
    //     } else{
    //         System.out.println(no.getelement() + " (Chave: " + no.getkey() + ")" + " (Chave: " + (no.getfather()).getkey() + ")");
    //     }
        

    //     // 3. Visita o lado esquerdo
    //     if (hasleft(no)) {
    //         mostrarRecursivo(leftchild(no), nivel + 1);
    //     }
    // }

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