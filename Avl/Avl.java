package avl;

import revisao.Avbp;

public class Avl extends Avbp{
    private Avbp arv;

    public Avl(int key, Object element){
        this.arv = new Avbp(key, element);
    }
    
    @Override
    public void insert(int key, Object ele){
        super.insert(key, ele);

        if (isbalance() == false){
            balance
        };
    }

    private No isbalanced(No no){
        if (hasleft(no)){
            
        }
    }

    private void balance(No no){ // Existem alguns erros que vão acontecer ao implementar a Avl. Possíveis alterações na Avbp;
        //int fl = arv.height(arv.leftchild(arv.root()));
        //int fr = arv.height(arv.rightchild(arv.root()));

        // Estrutura lógica do balanceamento da árvore. Apenas para idealização do que será feito
        int fb = fl - fr;

        no.setfb(fb);

        if (no.getfb() > 1 || no.getfb() < -1){
            chosebalance(no)
        }


    }


    private void simplerbalance(No no){

    }

    private void simplelbalance(No no){
        
    }

    private void dublerbalance(){
        
    }

    private void dublelbalance(){
        
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