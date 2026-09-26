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
        
        
        if (no.getkey() != -1){
            //System.out.println(no.getkey());
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
                        else{
                            rotations(no, pai, avo);
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
                //System.out.println(" SRR ");
                simple_right_rot(avo, pai);
                pai.setcor(0);
                avo.setcor(1);
            } 
            
            // Rotação dupla para esquerda
            else{
                double_left_rot(avo, pai, no);
                //System.out.println(" DLR "+ pai.getkey());
                pai.setcor(1);
                avo.setcor(1);
                no.setcor(0);
            }
        } else{

            // Rotação simples para esquerda
            if (rightchild(avo) == pai){
                //System.out.println(" SLR " + pai.getkey());
                simple_left_rot(avo, pai);
                pai.setcor(0);
                avo.setcor(1);
            }

            // Rotação dupla para direita
            else{
                double_right_rot(avo, pai, no);
                //System.out.println(" DRR " + pai.getkey());
                pai.setcor(1);
                avo.setcor(1);
                no.setcor(0);
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
            if (bisavo.getkey() < pai.getkey()){
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
            if (bisavo.getkey() < pai.getkey()){
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

    private void double_right_rot(No avo, No pai, No filho){
        // simple_left_rot(pai, filho);
        // simple_right_rot(avo, pai);
        No antigo_esquerdo = leftchild(filho);
        No antigo_direito = rightchild(filho);
        No bisavo = avo.getfather();
        
        avo.setfather(filho);
        pai.setfather(filho);
        filho.setfather(bisavo);

        // Filho modificações
        filho.setleft(pai);
        filho.setright(avo);

        // Parentes modificações
        pai.setright(antigo_esquerdo);
        if (antigo_esquerdo != null){
            antigo_esquerdo.setfather(pai);
        }

        avo.setleft(antigo_direito);
        if (antigo_direito != null){
            antigo_direito.setfather(avo);
        }

        if (bisavo != null){
            if (bisavo.getkey() < filho.getkey()){
                bisavo.setright(filho);
            }else{
                bisavo.setleft(filho);
            }
        } else{
            setRoot(filho); // EU ACHO QUE É MEIO RUIM FAZER ISSO MAS É O JEITO MAIS SEGURO
        }
        

    }

    private void double_left_rot(No avo, No pai, No filho){
        // simple_right_rot(pai, filho);
        // simple_left_rot(avo, pai);   

        No antigo_esquerdo = leftchild(filho);
        No antigo_direito = rightchild(filho);
        No bisavo = avo.getfather();
        
        avo.setfather(filho);
        pai.setfather(filho);
        filho.setfather(bisavo);

        // Filho modificações
        filho.setright(pai);
        filho.setleft(avo);

        // Parentes modificações
        pai.setleft(antigo_direito);
        if (antigo_direito != null){
            antigo_direito.setfather(pai);
        }
        
        avo.setright(antigo_esquerdo);
        if (antigo_esquerdo != null){
            antigo_esquerdo.setfather(avo);
        }
        
        if (bisavo != null){
            if (bisavo.getkey() < filho.getkey()){
                bisavo.setright(filho);
            }else{
                bisavo.setleft(filho);
            }
        } else{
            setRoot(filho); // EU ACHO QUE É MEIO RUIM FAZER ISSO MAS É O JEITO MAIS SEGURO
        }

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
        // if (hasleft(no)){
        //     ob[depth(no)][this.num] += " Esq " + leftchild(no).getkey();
        // }
        // if (hasright(no)){
        //     ob[depth(no)][this.num] += " Dir " + rightchild(no).getkey();
        // }
        //ob[depth(no)][this.num] = no.getelement() + " [" + no.getfb()+ "]";
        ++this.num;
        if (hasright(no)){
            visuals(ob, rightchild(no));
        }
    }

    // METODO ALTERNA TIVO DE DESENHO
    public void desenharArvore(No no) {
        desenharArvore(no, "", true);
    }

    private void desenharArvore(No no, String prefixo, boolean ehDireita) {
        if (no == null) {
            return;
        }

        // Primeiro desenha o filho direito
        desenharArvore(
            rightchild(no),
            prefixo + (ehDireita ? "│   " : "    "),
            true
        );

        // Desenha o nó atual
        if (no.getcor() == 0){
            System.out.println(
                prefixo + (ehDireita ? "└── " : "┌── ") + no.getkey() + " [Negro] "
            );
        } else{
            System.out.println(
                prefixo + (ehDireita ? "└── " : "┌── ") + no.getkey() + " [Rubro] "
            );
        }
        

        // Depois desenha o filho esquerdo
        desenharArvore(
            leftchild(no),
            prefixo + (ehDireita ? "    " : "│   "),
            false
        );
    }
}