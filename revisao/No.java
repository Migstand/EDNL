package revisao;

public class No{
    private No father;
    private No left;
    private No right;
    private Object element;
    private int key;
    private int fb;

    public No(int key, Object element, No father){
        this.father = father;
        this.element = element;
        this.key = key;
        this.fb = 0;
    }

    public int getkey(){
        return this.key;
    }

    public Object getelement(){
        return this.element;
    }

    public No getfather(){
        return this.father;
    }

    public No leftchild(){
        return this.left;
    }

    public No rightchild(){
        return this.right;
    }

    public void setkey(int key){
        this.key = key;
    }

    public void setelement(Object o){
        this.element = o;
    }

    public void setright(No right){
        this.right = right;
    }

    public void setleft(No left){
        this.left = left;
    }

    public void setfather(No father){
        this.father = father;
    }

    public void setfb(int fb){
        this.fb = fb;
    }

    public int getfb(){
        return this.fb;
    }
}