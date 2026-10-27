package grafos.matriz;

public class Vertice{
    private int key;
    private Object element;

    public Vertice(int key, Object element){
        this.key = key;
        this.element = element;
    }

    public void setelement(Object o){
        this.element = o;
    }

    public Object getelement(){
        return element;
    }

    public int getkey(){
        return key;
    }
    public void setkey(int key){
        this.key = key;
    }
}