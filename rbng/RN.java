package rbng;

//import java.util.ArrayList;
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

    @Override
    public No remove(int key){
        No no = super.remove(key);

        if (no.getkey() != -1){
            remove_verif(no);
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

    private void remove_verif(No no){
        if (no.getcor() == 0 && (isRoot(no) == false)){ //

            // Caso 3:
            No pai = no.getfather();
            No bro = getbro(no);
            // System.out.println(bro.getkey());
            if (bro != null){
                
                // Caso 3.1:
                if (bro.getcor() == 1){
                    
                    // Lado que está torto;
                    if (pai.getkey() > no.getkey()){
                        simple_left_rot(pai, bro);
                    }else{
                        simple_right_rot(pai, bro);
                    }

                    pai.setcor(1);
                    bro.setcor(0);
                    remove_verif(no);
                    
                }
                // IRMÃO NEGRO
                else{
                    
                    No[] subrinhos = subrinhos(no.getkey(), bro);
                    
                    int prox = 0; 
                    int dist = 0;
                    
                    // VERIFICAÇÕES DE SUBRINHOS FACILITADAS
                    if (subrinhos[0] != null){
                        if (subrinhos[0].getcor() == 1){
                            prox = 1;
                        }   
                    }

                    if (subrinhos[1] != null){
                        if (subrinhos[1].getcor() == 1){
                            dist = 1;
                        }   
                    }
                    //System.out.println( "Próximo: " + prox + ", Distante: " + dist);
                    //System.out.println("Nó de análise: " + no.getkey());

                    // Caso 3.2:
                    if (prox == 0 && dist == 0){
                        
                        // Caso 3.2a:
                        if (pai.getcor() == 0){
                            bro.setcor(1);
                            remove_verif(pai);
                        }
                        
                        // Caso 3.2b:
                        else{
                            bro.setcor(1);
                            pai.setcor(0);

                            // FIM DUPLO NEGRO
                            if (isInternal(bro)){
                                if (hasleft(bro)){
                                    if (leftchild(bro).getcor() == 1){
                                        insert_verif(leftchild(bro));
                                    }
                                } else{
                                    if (rightchild(bro).getcor() == 1)
                                        insert_verif(rightchild(bro));            
                                    }
                            }
                            
                        }
                    }
                    
                    // Caso 3.3:
                    if (prox == 1 && dist == 0){

                        if (bro.getkey() > no.getkey()){ // Um possível local para erros
                            simple_right_rot(bro, subrinhos[0]);
                        } else{
                            simple_left_rot(bro, subrinhos[0]);
                        }

                        bro.setcor(prox);
                        subrinhos[0].setcor(0);

                        remove_verif(no);
                    }

                    // 3.4
                    if (dist == 1){
                        if (bro.getkey() > no.getkey()){ // Um possível local para erros
                            simple_left_rot(pai, rightchild(pai));
                        } else{
                            simple_right_rot(pai, leftchild(pai));
                        }

                        bro.setcor(pai.getcor());
                        pai.setcor(0);
                        subrinhos[1].setcor(0);

                        // FIM DO DUPLO NEGRO

                    }
                    
                }
            }
            else{
                // Caso 3.2a:
                if (pai.getcor() == 0){
                    remove_verif(pai);
                }
                // Caso 3.2b:
                else{
                    pai.setcor(0);
                    // FIM DUPLO NEGRO
                    insert_verif(bro);
                }
        }
        }

    }

    private No getbro(No no){
        No painho = no.getfather();
        No bro;
        if (painho.getkey() > no.getkey()){
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

    private No[] subrinhos(int key, No bro){
        No[] subrinhos = new No[2];
        // 0: Subrinho proximo
        // 1: Subrinho distante
        
        if (key > bro.getkey()){
            subrinhos[0] = rightchild(bro);
            subrinhos[1] = leftchild(bro);
        } else{
            subrinhos[0] = leftchild(bro);
            subrinhos[1] = rightchild(bro);
        }

        return subrinhos;
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