package avl;

import revisao.Avbp;
import revisao.No;

public class Avl extends Avbp{
    private Avbp arv;

    public Avl(int key, Object element){
        super(key, element);
    }
    
    @Override
    public No insert(int key, Object ele){
        No no = super.insert(key, ele);
        int fb = 0;

        if (no != null){
            if (no.getkey() > key){
                fb = 1;
            }
            if (no.getkey() < key){
                fb = -1;
            }
        }
        
        inbalance(no, fb);
        return no;
    }

    @Override
    public No remove(int key){
        No father = super.remove(key);
        
        int fb = 0;
        
        if (father != null){
            if (father.getkey() > key){
                fb = 1;
            }
            if (father.getkey() < key){
                fb = -1;
            }
        }

        rebalance(father, fb);
        return father;
    }

    // Goku SSJ 8
    private void inbalance(No no, int fb){ // Existem alguns erros que vão acontecer ao implementar a Avl. Possíveis alterações na Avbp;
       
        // Estrutura lógica do balanceamento da árvore. Apenas para idealização do que será feito
        
        while(no != null){
            no.setfb(no.getfb() + fb);
            System.out.println(no.getkey() + " Fb: " + no.getfb());
            if (no.getfb() > 1 || no.getfb() < -1){
                chosebalance(no);
            }
            if (no.getfb() == 0 ){//|| no.getfather().getfb() == 0
                break;
            }


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
                    fb = 1;
                }else{
                    fb = -1;
                }
            }
            
            no = no.getfather();
        }

    }


    private void simplerbalance(No no){
        System.out.println("Faça as balanças R Simples");
        //O Nó podre é o filho direito do seu atual filho esquerdo
        //O novo filho esquerdo do nó podre é o antigo filho direito do nó esquerdo utilizado
        // if (no.getfather() == null){

        // }
        No lefi = leftchild(no);
        No olrifi = rightchild(lefi);
        
        int fatorno = no.getfb();
        int fatorlefi = lefi.getfb();

        //O Nó podre é o filho direito do seu atual filho esquerdo
        lefi.setright(no);
        lefi.setfather(no.getfather());

        //O novo filho esquerdo do nó podre é o antigo filho direito do nó esquerdo utilizado
        no.setleft(olrifi);
        no.setfather(lefi);

        // Continha cheatada para calcular o fb dos nós alterados;
        int novo_fbno = fatorno - 1 - Math.min(fatorlefi, 0);
        
        //FB_B_novo
        no.setfb(novo_fbno);
        
        // FB_A_novo
        lefi.setfb(fatorlefi - 1 + Math.max(novo_fbno, 0));
        System.out.println(lefi.getfb() + " Fb de " + lefi.getkey());
    }

    private void simplelbalance(No no){
        System.out.println("Faça as balanças L Simples");
        //O Nó podre é o filho esquerdo do seu atual filho direito
        //O novo filho direito do nó podre é o antigo filho esquerdo do nó direito utilizado

        // if (no.getfather() == null){
        //     if (hasright(no)){
                
        //     }
        // }

        No rifi = rightchild(no);
        No ollefi = leftchild(rifi);

        int fatorno = no.getfb();
        int fatorrifi = rifi.getfb();

        //O Nó podre é o filho esquerdo do seu atual filho direito
        rifi.setleft(no);
        rifi.setfather(no.getfather());

        //O novo filho direito do nó podre é o antigo filho esquerdo do nó direito utilizado
        no.setright(ollefi);
        no.setfather(rifi);

        // Continha cheatada para calcular o fb dos nós alterados;
        int novo_fbno = fatorno + 1 - Math.min(fatorrifi, 0);
        
        //FB_B_novo
        no.setfb(novo_fbno);
        
        // FB_A_novo
        rifi.setfb(fatorrifi + 1 + Math.max(novo_fbno, 0));
        System.out.println(rifi.getfb() + " Fb de " + rifi.getkey());
    }

    private void dublerbalance(No no){
        System.out.println("Faça as balanças R Dupla");
        simplelbalance(leftchild(no));
        simplerbalance(no);
    }

    private void dublelbalance(No no){
        System.out.println("Faça as balanças L dupla");
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
}