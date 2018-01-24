import java.util.Stack;
public class Homework1 {

	public static void main(String[] args) {

		String post = "251-*32*+";
		if(args.length>0)post=args[0];
		tree t = new tree(post);
		t.treeStack();
		t.inOrder(t.tRoot);
		t.inFix(t.tRoot);
		t.calCulate(t.tRoot);
		System.out.print( " = " + t.Sum);
		//  System.out.println(t.sum);


	}




	public static class tree {

		public String postFix;
		public Node tRoot;
		public int Sum = 0;

		tree(String postfix){
			this.postFix = postfix;
		}

		public void inOrder(Node n){
			if (n == null){
                            return ;
			}
			else
                            inOrder(n.left);
                            inOrder(n.right);
		}

		public boolean isOperator(char opeT){
			if(opeT == '+'|| opeT == '-'|| opeT == '*'|| opeT == '/'){
				return true;
			}
			else{
				return false;
			}
		}



		public int calCulate (Node n){

			if((isOperator(n.key))){
				switch (n.key){
					case '+': Sum = calCulate(n.left) + calCulate(n.right) ; break;
					case '-': Sum = calCulate(n.left) - calCulate(n.right) ; break;
					case '*': Sum = calCulate(n.left)  * calCulate(n.right) ; break;
					case '/': Sum = calCulate(n.left)  / calCulate(n.right) ; break;

				}
			}
			else{
				return isNumber(n.key);
			}
			return Sum;
		}


		public void treeStack(){
			final Stack<Node> node = new Stack<Node>();
			for (int i = 0; i < postFix.length(); i++){
				char x  = postFix.charAt(i);

				if (isOperator(x))
				{
					Node rightNode = node.pop();
					Node leftNode = node.pop();
					node.push(new Node(leftNode,x,rightNode));
				}
				else
				{
					node.add(new Node(null, x, null));
				}

			}
			tRoot = node.pop();
		}


		public void inFix(Node n){
			System.out.println(n);
		}



		private int isNumber(char Num)
		{
			return Num - '0';
		}

    }
}