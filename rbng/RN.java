package rbng;

public class RN extends Avbp{
    private int num = 0;

    public RN(int key, Object element){
        super(key, element);
        No raiz = root();
        raiz.setcor(0);
    }

    @Override
    public No insert(int key, Object ele){
        No no = super.insert(key, ele);
        
        if (no.getkey() == key){
            insert_verif(no);   
        }

        return no;
    }

    private void insert_verif(No no){
        if (isRoot(no)){
            no.setcor(0);
        }
        else{
            No pai = no.getfather();
        
            // Pai Rubro, Filho rubro
            if (pai.getcor() == 1){
                No avo = pai.getfather();
                
                // Caso 2: Tio Rubro
                if (avo != null){
                    No tio = getbro(pai);
                    if (tio != null){
                        if (tio.getcor() == 1){
                            tio.setcor(0);
                            pai.setcor(0);
                            avo.setcor(1);

                            insert_verif(avo);
                        }
                    } 
                    // CASO 3: Tio Negro
                    else{
                        rotations(no, pai, avo);
                    }
                    
                }
                
            }
        }
    
        
    }

    private No getbro(No no){
        No painho = no.getfather();
        No bro;
        if (leftchild(painho) == no){
            bro = rightchild(painho);
        } else{
            bro = leftchild(painho);
        }

        return bro;
    }

    private void rotations(No no, No pai, No avo){
        if (leftchild(pai) == no){
            
            // Rotação simples para direita
            if (leftchild(avo) == pai){
                simple_right_rot(avo, pai);
            } 
            
            // Rotação dupla para esquerda
            else{
                double_left_rot(avo, pai);
            }
        } else{

            // Rotação simples para esquerda
            if (rightchild(avo) == pai){
                simple_left_rot(avo, pai);
            }

            // Rotação dupla para direita
            else{
                double_right_rot(avo, pai);
            }
        }
    }

    private void simple_right_rot(No avo, No pai){

        No antigo_direito = rightchild(pai);
        No bisavo = avo.getfather();

        pai.setfather(bisavo);
        pai.setright(avo);

        // Caso exita um nó
        if (bisavo != null){
            if (bisavo.getkey() > pai.getkey()){
                bisavo.setright(pai);
            }else{
                bisavo.setleft(pai);
            }
        } else{
            setRoot(pai); // EU ACHO QUE É MEIO RUIM FAZER ISSO MAS É O JEITO MAIS SEGURO
        }

        avo.setfather(pai);
        avo.setleft(antigo_direito);

        if (antigo_direito != null){
            antigo_direito.setfather(avo);
        }
    }

    private void simple_left_rot(No avo, No pai){
        No antigo_esquerdo = leftchild(pai);
        No bisavo = avo.getfather();

        pai.setfather(bisavo);
        pai.setleft(avo);

        // Caso exita um nó
        if (bisavo != null){
            if (bisavo.getkey() > pai.getkey()){
                bisavo.setright(pai);
            }else{
                bisavo.setleft(pai);
            }
        } else{
            setRoot(pai); // EU ACHO QUE É MEIO RUIM FAZER ISSO MAS É O JEITO MAIS SEGURO
        }

        avo.setfather(pai);
        avo.setright(antigo_esquerdo);

        if (antigo_esquerdo != null){
            antigo_esquerdo.setfather(avo);
        }
    }

    private void double_right_rot(No avo, No pai){
        
    }

    private void double_left_rot(No avo, No pai){
        
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