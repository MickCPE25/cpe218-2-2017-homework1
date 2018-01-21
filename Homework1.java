import java.util.Stack;
public class Homework1 {
    
    public static Stack <Character> newStack = new Stack<Character>();
    public static Node Tree;
    public static boolean isOperator(char opeT) {
		if (opeT == '+' || opeT == '-' || opeT == '*' || opeT == '/' ) {
                    return true;
		} else 
                    return false;
	}
    
    public static void  inFix(Node n) {
		if (isOperator(n.key)){
			n.right = new Node(newStack.pop());
			inFix(n.right);
			n.left = new Node(newStack.pop());
			inFix(n.left);
		}
    }
    
    public static int calCulate(Node n){
		if(n.key == '+')
		{
			return calCulate(n.left)+calCulate(n.right);
		}
		else  if(n.key == '-')
		{
			return calCulate(n.left)-calCulate(n.right);
		}
		else  if(n.key == '*')
		{
			return calCulate(n.left)*calCulate(n.right);
		}
		else  if(n.key == '/')
		{
			return  calCulate(n.right)/calCulate(n.left);
		}
		else return Integer.parseInt(n.key.toString());
	}

public static void inOrder(Node n) {
		if(n.key == '+' || n.key == '-' || n.key == '*' || n.key == '/'){
			if(n != Tree)System.out.print("(");
			inOrder(n.left);
			System.out.print(n.key);
			inOrder(n.right);
			if(n != Tree)System.out.print(")");
		}else
		{
			System.out.print(n.key);
		}
	}
    
    public static void main(String[] args) {
        // TODO code application logic here
        String posfix = args[0];
		for(int i=0;i<posfix.length();i++){
			newStack.add(posfix.charAt(i));
		}
                
		Tree = new Node(newStack.pop());
		inFix(Tree);
		inOrder(Tree);
		System.out.print("=" + calCulate(Tree));
    }
    
}
