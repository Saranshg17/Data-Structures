class InvalidPostfixException extends Exception{
    public InvalidPostfixException(){
        super("Invalid Postfix expression");
    }
}
class InvalidExprException extends Exception{
    public InvalidExprException(){
        super("Invalid Arithmetic expression");
    }
}
public class Calculator{
    public int evaluatePostFix(String s) throws InvalidPostfixException{
        String[] l=s.split(" ");
        MyStack p=new MyStack();
        for(int i=0;i<l.length;i++){
            String k=l[i].replace(" ","");
            if(k.equals("+")){
                try{
                    int a=(int)p.pop();
                    int c=(int)p.pop();
                    p.push(a+c);
                }
                catch(EmptyStackException e){
                    throw new InvalidPostfixException();
                }
            }else if(k.equals("-")){
                try{
                    int x=(int)p.pop();
                    int y=(int)p.pop();
                    p.push(y-x);
                }
                catch(EmptyStackException e){
                    throw new InvalidPostfixException();
                }
            }else if(k.equals("*")){
                try{
                    int a1=(int)p.pop();
                    int c1=(int)p.pop();
                    p.push(a1*c1);
                }
                catch(EmptyStackException e){
                    throw new InvalidPostfixException();
                }
            }else{
                try{
                    int t=Integer.parseInt(k);
                    if(t>=0){
                        p.push(Integer.parseInt(k));
                    }else{
                        throw new InvalidPostfixException();
                    }
                }catch(NumberFormatException e){
                    throw new InvalidPostfixException();
                }
            }
        }
        try{
            int j=(int)p.pop();
            return j;
        }
        catch(EmptyStackException e){
            throw new InvalidPostfixException();
        }
        catch(NumberFormatException e1){
            throw new InvalidPostfixException();
        }
    }
    public class Main{
        static void runPostfixTestCases(){
            Calculator calc = new Calculator();
            Scanner inps = new Scanner(new FileInputStream("calcTestCases.txt"));
            int t = inps.nextInt();
            for(int ti=1;ti<=t;++ti)
            {
                String inp = "";
                while(inp.length()==0)
                    inp = inps.nextLine();
                try{
                    int ans = calc.evaluatePostFix(inp);
                    System.out.println(ans);
                }catch(InvalidPostfixException e){
                    System.out.println("\tGot: InvalidPostfixException");
                }
            }
            
        }
        public static void main(String[] args){
            runPostfixTestCases();
        }
    }
    public String convertExpression(String s) throws InvalidExprException{
        MyStack p=new MyStack();
        String res="";
        int count=0;
        s=s.replace(" ","");
        for(int i=0;i<s.length();i++){
            String k=s.substring(i,i+1);
            if(k.equals("-")||k.equals("*")||k.equals("+")){
                try{
                    String u=s.substring(i-1,i);
                    String x=(String)p.top();
                    if(x.equals("(")||u.equals("-")||u.equals("*")||u.equals("+")){
                        throw new InvalidExprException();
                    }
                }catch(EmptyStackException e){
                    continue;
                }catch(StringIndexOutOfBoundsException e){
                    throw new InvalidExprException();
                }if(i==s.length()-1){
                    throw new InvalidExprException();
                }
            }
            if(k.equals("+")){
                res="+ "+res;
            }else if(k.equals("-")){
                res="- "+res;
            }else if(k.equals("*")){
                res="* "+res;
            }else if(k.equals(" ")){
                continue;
            }else if(k.equals("(")){
                count+=1;
                p.push(k);
            }else if(k.equals(")")){
                count-=1;
                try{
                    String d=(String)p.pop();
                    while(!d.equals("(")){
                        res=d+" "+res;
                        d=(String)p.pop();
                    }
                }catch(EmptyStackException e){
                    throw new InvalidExprException();
                }
            }else{
                if(i!=0){
                    String l=s.substring(i-1,i);
                    if(!l.equals("-")&&!l.equals("*")&&!l.equals("+")&&!l.equals("(")&&!l.equals(")")){
                        try{
                            String y=(String)p.pop();
                            k=y+k;
                        }catch(EmptyStackException e){
                            continue;
                        }
                    }
                }
                p.push(k);
            }
        }
        if(count!=0){
            throw new InvalidExprException();
        }else{
            while(!p.isEmpty()){
                try{
                    String t=(String)p.pop();
                    res=t+" "+res;
                }catch(EmptyStackException e){
                    continue;
                }
            }
            res=res.trim();
            return res;
        }
    }
}