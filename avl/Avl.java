package avl;

import revisao.Avbp;
import revisao.No;
import java.util.ArrayList;

public class Avl extends Avbp{
    private Avbp arv;
    private int num;

    public Avl(int key, Object element){
        super(key, element);
        this.num = 0;
    }
    
    @Override
    public No insert(int key, Object ele){
        No no = super.insert(key, ele);
        int fb = 0;
        //System.out.println("Foi inserido o "+ key);
        if (no != null){ //
            if (no.getkey() != key){
                if (no.getkey() > key){
                    fb = 1;
                }
                if (no.getkey() < key){
                    fb = -1;
                }
            } else{
                return no;
            }
            
        }else{
            return no;
        }
        inbalance(no, fb);
        return no;
    }

    @Override
    public No remove(int key){
        No father = super.remove(key);
        //System.out.println("Foi removido o "+ father.getkey());
        int fb = 0;
        
        if (father != null){
            if (father.getkey() != key){
                if (father.getkey() > key){
                    fb = -1;
                }
                if (father.getkey() < key){
                    fb = 1;
                }
            }else{
                return father;
            }
                
        } else{
            return father;
        }

        rebalance(father, fb);
        return father;
    }

    // Goku SSJ 8
    private void inbalance(No no, int fb){ // Existem alguns erros que vão acontecer ao implementar a Avl. Possíveis alterações na Avbp;
       
        // Estrutura lógica do balanceamento da árvore. Apenas para idealização do que será feito
        
        while(no != null){
            //System.out.println(no.getkey() + " Fb: " + no.getfb());
            no.setfb(no.getfb() + fb);
            //System.out.println(no.getkey() + " Fb: " + no.getfb());
            if (no.getfb() > 1 || no.getfb() < -1){
                //System.out.println("Problema 4 aqui em: " + no.getkey());
                chosebalance(no);
            }
            if (no.getfb() == 0 ){//|| no.getfather().getfb() == 0
                //System.out.println("Parei aqui em: " + no.getkey());
                break;
            }

            // O ERRO SE ENCONTRA NESSA PARTE
            // Verificação feita na raiz
            if (no.getfather() != null){
                if (no == leftchild(no.getfather())){
                    fb = 1;
                }else{
                    fb = -1;
                }
            }
            
            no = no.getfather();
        }    
    }

    // Vegeta SSJ 8
    private void rebalance(No no, int fb){ // Existem alguns erros que vão acontecer ao implementar a Avl. Possíveis alterações na Avbp;
       
        // Estrutura lógica do balanceamento da árvore. Apenas para idealização do que será feito
        
        while(no != null){
            no.setfb(no.getfb() + fb);
            if (no.getfb() > 1 || no.getfb() < -1){
                chosebalance(no);
            }
            if (no.getfb() != 0){
                break;
            }

            // Verificação feita na raiz
            if (no.getfather() != null){
                if (no == leftchild(no.getfather())){
                    fb = -1;
                }else{
                    fb = 1;
                }
            }
            
            no = no.getfather();
        }

    }


    private void simplerbalance(No no){
        //System.out.println("Faça as balanças R Simples do " + no.getkey() + " de fb: "+ no.getfb());
        //O Nó podre é o filho direito do seu atual filho esquerdo
        //O novo filho esquerdo do nó podre é o antigo filho direito do nó esquerdo utilizado
        // if (no.getfather() == null){

        // }
        No lefi = leftchild(no);
        if (lefi == null){
            System.out.println("Nó bugado " + no.getkey() + " de fb: "+ no.getfb());
        }
        //System.out.println("Filho esquerdo " + lefi.getkey() + " de fb: "+ lefi.getfb());
        No olrifi = rightchild(lefi);
        //System.out.println("Antigo Filho direito " + olrifi.getkey() + " de fb: "+ olrifi.getfb());
        
        int fatorno = no.getfb();
        int fatorlefi = lefi.getfb();

        //O Nó podre é o filho direito do seu atual filho esquerdo
        lefi.setright(no);
        lefi.setfather(no.getfather());
        if (lefi.getfather() != null){
            if (lefi.getkey() < lefi.getfather().getkey()){
                lefi.getfather().setleft(lefi);
            }else{
                lefi.getfather().setright(lefi);
            }
            
        } else{
            No rt = lefi;
            setRoot(lefi);
            //System.out.println("A raiz: " + root().getkey());
            //System.out.println("A real raiz: " + rt.getkey());
        }
        

        //O novo filho esquerdo do nó podre é o antigo filho direito do nó esquerdo utilizado
        no.setleft(olrifi);
        if (olrifi != null){
            olrifi.setfather(no);
        }
        no.setfather(lefi);

        // Continha cheatada para calcular o fb dos nós alterados;
        int novo_fbno = fatorno - 1 - Math.max(fatorlefi, 0);
        
        //FB_B_novo
        no.setfb(novo_fbno);
        
        // FB_A_novo
        lefi.setfb(fatorlefi - 1 + Math.min(novo_fbno, 0));
        //System.out.println("Pai do balance: " + no.getfather().getkey());
        //if (lefi.getfather() != null){
        //    System.out.println("Pai do filho : " + lefi.getfather().getkey());
        //}
        

        //System.out.println(lefi.getfb() + " Fb de " + lefi.getkey());
    }

    private void simplelbalance(No no){
        //System.out.println("Faça as balanças L Simples do " + no.getkey() + " de fb: "+ no.getfb());
        //O Nó podre é o filho esquerdo do seu atual filho direito
        //O novo filho direito do nó podre é o antigo filho esquerdo do nó direito utilizado

        // if (no.getfather() == null){
        //     if (hasright(no)){
                
        //     }
        // }

        No rifi = rightchild(no);
        //System.out.println("Filho direito " + rifi.getkey() + " de fb: "+ rifi.getfb());
        if (rifi == null){
            System.out.println("Nó bugado " + no.getkey() + " de fb: "+ no.getfb());
        }
        No ollefi = leftchild(rifi);
        //System.out.println("Antigo Filho esquerdo " + ollefi.getkey() + " de fb: "+ ollefi.getfb());

        int fatorno = no.getfb();
        int fatorrifi = rifi.getfb();

        //O Nó podre é o filho esquerdo do seu atual filho direito
        rifi.setleft(no);
        //System.out.println("Novo Filho esquerdo " + leftchild(rifi).getkey() + " de fb: "+ leftchild(rifi).getfb());
        rifi.setfather(no.getfather());
        if (rifi.getfather() != null){
            if (rifi.getkey() < rifi.getfather().getkey()){
                (rifi.getfather()).setleft(rifi);
            }else{
                (rifi.getfather()).setright(rifi);
            }
            
            //System.out.println("O pai: " + rifi.getfather().getkey());
            //System.out.println("O filho: " + rifi.getkey());    
        } else{
            //System.out.println("Pai do filho : " + rifi.getfather().getkey());
            No rt = rifi;
            setRoot(rifi);
            //System.out.println("A raiz: " + root().getkey());
            //System.out.println("A real raiz: " + rt.getkey());
        }
        

        //O novo filho direito do nó podre é o antigo filho esquerdo do nó direito utilizado
        
        no.setright(ollefi);
        if (ollefi != null){
            ollefi.setfather(no);
        }
        //System.out.println("Novo Filho Direito " + rightchild(no));
        no.setfather(rifi);
        //System.out.println("Novo pai " + no.getfather().getkey() + " de fb: "+ no.getfather().getfb());

        // Continha cheatada para calcular o fb dos nós alterados;
        int novo_fbno = fatorno + 1 - Math.min(fatorrifi, 0);
        
        //FB_B_novo
        no.setfb(novo_fbno);
        //System.out.println("Fb do balance: " + no.getfb());
        // FB_A_novo
        rifi.setfb(fatorrifi + 1 + Math.max(novo_fbno, 0));
        //System.out.println("A raiz: " + root().getkey());
        //System.out.println("Fb do filho: " + rifi.getfb());
        //System.out.println("Pai do balance: " + no.getfather().getkey());
        // if (rifi.getfather() != null){
        //     System.out.println("Pai do filho : " + rifi.getfather().getkey());
        // }
        

        //System.out.println(rifi.getfb() + " Fb de " + rifi.getkey());
    }

    private void dublerbalance(No no){
        //System.out.println("O nó podre é: " + no.getkey() + " de fb: " + no.getfb());
        simplelbalance(leftchild(no));
        simplerbalance(no);
    }

    private void dublelbalance(No no){
        //System.out.println("O nó podre é: " + no.getkey() + " de fb: " + no.getfb());
        simplerbalance(rightchild(no));
        simplelbalance(no);
    }

    private void chosebalance(No no){
        //System.out.println(no.getkey());
        if (no.getfb() < -1){
            if (rightchild(no).getfb() == 1){
                dublelbalance(no);
            } else{
                simplelbalance(no);
            }
        }
        if (no.getfb() > 1){
            if (leftchild(no).getfb() == -1){
                dublerbalance(no);
            } else{
                simplerbalance(no);
            }
        }
    }
    public void mostrar_fb(){
        
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
        if (no.getfather() != null){
            ob[depth(no)][this.num] = no.getelement() + " [" + no.getfb()+ "]" + no.getfather().getkey();    
        } else {
            ob[depth(no)][this.num] = no.getelement() + " [" + no.getfb()+ "]";
        }
        //ob[depth(no)][this.num] = no.getelement() + " [" + no.getfb()+ "]";
        ++this.num;
        if (hasright(no)){
            visuals(ob, rightchild(no));
        }
    }
    public void coletarfbs(No no, ArrayList<Object> list){
        if (hasleft(no)){
            coletarfbs(leftchild(no), list);
        }
        list.add(no.getfb());
        if (hasright(no)){
            coletarfbs(rightchild(no), list);
        }
    }
}