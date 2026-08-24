package avl;

import revisao.Avbp;
import revisao.No;

public class Avl extends Avbp{
    private Avbp arv;

    public Avl(int key, Object element){
        this.arv = new Avbp(key, element);
    }
    
    @Override
    public void insert(int key, Object ele){
        No no = find(key, this.raiz);
        int fb = -1;
        if(no.getkey() != key){
            No new_no = new No(key, ele, no);
            if (key > no.getkey()){
                no.setright(new_no);
                fb = -1;
            } else{
                no.setleft(new_no);
                fb = 1;
            }
            this.size++;
            
        } else{
            //System.out.println("O Elemento " + no.getelement() + " de chave " + no.getkey() + " já inserido");
        }

        inbalance(no, fb);
    }

    @Override
    public void remove(int key){
        No removed = find(key, this.raiz);
        Object ele = removed.getelement();
        int fb;
        No father;
        if(key!=removed.getkey()){
            System.out.println("Elemento não encontrado");
        } else{
            if (isExternal(removed)){
                if (isRoot(removed)){
                    this.raiz = null;
                } else{
                    father = removed.getfather();
                    if ((removed.getfather()).getkey() < key){
                        (removed.getfather()).setright(null); // setleft
                        fb = 1;
                    }else{
                        (removed.getfather()).setleft(null); // setright
                        fb = -1;
                    }
                }
                removed.setfather(null);
            
            } else{
                ArrayList <No> quant = children(removed);
                int si = quant.size();
                if (si == 1){
                    // Implementar catch para o caso 
                    if (isRoot(removed)){
                        quant.get(0).setfather(null);
                        this.raiz = quant.get(0);
                    } //
                    else{
                        father = removed.getfather();
                        if ((removed.getfather()).getkey() < key){
                            (removed.getfather()).setright(quant.get(0));
                            (quant.get(0)).setfather(removed.getfather());
                            fb = 1;
                        } else{
                            (removed.getfather()).setleft(quant.get(0));
                            (quant.get(0)).setfather(removed.getfather());
                            fb = -1;
                        }
                    }
                    
                } else{
                    // A brincadeira começa aqui >:/
                    fb = -1;
                    No copy = rightchild(removed);
                    No verif = copy;
                    while(hasleft(copy)){
                        copy = leftchild(copy);
                    }

                    replace(removed, copy);
                    father = copy.getfather();
                    if (verif != copy){
                        (father).setleft(rightchild(copy));
                    } else{
                        (father).setright(rightchild(copy));
                    }

                    if (rightchild(copy) != null){
                        rightchild(copy).setfather(father);
                    }
            
                    copy = null;
                
                }
                //System.out.println("O Elemento " + ele + " foi removido!");
            }

            removed = null;
            this.size--;

            rebalance(father, fb);
        }
        
    }


    private void inbalance(No no, int fb){ // Existem alguns erros que vão acontecer ao implementar a Avl. Possíveis alterações na Avbp;
       
        // Estrutura lógica do balanceamento da árvore. Apenas para idealização do que será feito
        
        while(no != null){
            no.setfb(no.getfb() + fb);
            if (no.getfb() > 1 || no.getfb() < -1){
                chosebalance(no);
            }
            if (no.getfb() == 0){
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
        //O Nó podre é o filho direito do seu atual filho esquerdo
        //O novo filho esquerdo do nó podre é o antigo filho direito do nó esquerdo utilizado
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
        int novo_fbno = fatorno + 1 - Math.min(fatorlefi, 0);
        
        //FB_B_novo
        no.setfb(novo_fbno);
        
        // FB_A_novo
        lefi.setfb(fatorlefi + 1 + Math.max(novo_fbno, 0));
    }

    private void simplelbalance(No no){
        //O Nó podre é o filho esquerdo do seu atual filho direito
        //O novo filho direito do nó podre é o antigo filho esquerdo do nó direito utilizado
        No rifi = rightchild(no);
        No ollefi = leftchild(lefi);

        int fatorno = no.getfb();
        int fatorrifi = rifi.getfb();

        //O Nó podre é o filho esquerdo do seu atual filho direito
        rifi.setleft(no);
        rifi.setfather(no.getfather());

        //O novo filho direito do nó podre é o antigo filho esquerdo do nó direito utilizado
        no.setright(ollefi);
        no.setfather(rifi);

        // Continha cheatada para calcular o fb dos nós alterados;
        int novo_fbno = fatorno - 1 - Math.min(fatorrifi, 0);
        
        //FB_B_novo
        no.setfb(novo_fbno);
        
        // FB_A_novo
        lefi.setfb(fatorrifi - 1 + Math.max(novo_fbno, 0));

    }

    private void dublerbalance(No no){
        simplelbalance(rightchild(no));
        simplerbalance(no);
    }

    private void dublelbalance(){
        simplerbalance(leftchild(no));
        simplelbalance(no);
    }

    private void chosebalance(No no){
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