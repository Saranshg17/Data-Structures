class EmptyStackException extends Exception{
    public EmptyStackException(){
        super("Stack is empty");
    }
}
public class MyStack implements StackInterface{
    private Object[] l=new Object[1];
    private int top=-1;
    public void push(Object o){
        if(o==null){
            l=l;
        }else if(top==l.length-1){
            Object[] m=new Object[2*l.length];
            for(int i=0;i<l.length;i++){
                m[i]=l[i];
            }
            m[l.length]=o;
            top++;
            l=m;
        }else{
            top++;
            l[top]=o;
        }
    }
    public Object pop() throws EmptyStackException{
        if(top==-1){
            throw new EmptyStackException();
        }else{
            top--;
            Object s=l[top+1];
            l[top+1]=null;
            return s;
        }
    }
    public Object top() throws EmptyStackException{
        if(top==-1){
            throw new EmptyStackException();
        }else{
            return l[top];
        }
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public int size(){
        return top+1;
    }
    public String toString(){
        String s="[";
        if(top==-1){
            s+="]";
        }else{
            for(int i=top;i>0;i--){
                s+=String.valueOf(l[i]) + ", ";
            }
            s+=String.valueOf(l[0]) +"]";
        }
        return s;
    }
}