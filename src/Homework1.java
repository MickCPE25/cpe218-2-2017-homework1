import java.util.Stack;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;


public class Homework1 extends JPanel
    implements TreeSelectionListener {
        public static Stack <Character> newStack = new Stack<Character>();
        public static Node Tree;
        static String post;
        static tree forT;
        JTree tree;
        JEditorPane htmlPane;
        public static DefaultMutableTreeNode checkNode;
        public static DefaultMutableTreeNode Top;

    public Homework1(){
	super(new GridLayout(1,0));

		//Create the nodes.
	Top = new DefaultMutableTreeNode(forT.tRoot.key);
	createNodes(Top,forT.tRoot);

		//Create a tree that allows one selection at a time.
	tree = new JTree(Top);
	tree.getSelectionModel().setSelectionMode	
        (TreeSelectionModel.SINGLE_TREE_SELECTION);

		//Listen for when the selection changes.
	tree.addTreeSelectionListener(this);

	tree.putClientProperty("JTree.lineStyle","None");
	ImageIcon NodeIcon =  createImageIcon("middle.gif");
	DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	renderer.setOpenIcon(NodeIcon);
	renderer.setClosedIcon(NodeIcon);
	tree.setCellRenderer(renderer);
                
		//Create the scroll pane and add the tree to it.
	JScrollPane treeView = new JScrollPane(tree);

		//Create the HTML viewing pane.
	htmlPane = new JEditorPane();

	JScrollPane htmlView = new JScrollPane(htmlPane);

		//Add the scroll panes to a split pane.
	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);	
        splitPane.setTopComponent(treeView);
	splitPane.setBottomComponent(htmlView);
                

	Dimension minimumSize = new Dimension(100, 50);
	htmlView.setMinimumSize(minimumSize);
	treeView.setMinimumSize(minimumSize);
	splitPane.setDividerLocation(100);
	splitPane.setPreferredSize(new Dimension(500, 300));

		//Add the split pane to this panel.
	add(splitPane);
	}
    
public static void main(String[] args) {
                
        post = "251-*32*+";
        if(args.length>0)post=args[0];
        forT = new tree(post);
        forT.treeStack();
        forT.inOrder(forT.tRoot);
        forT.inFix(forT.tRoot);
        forT.calCulate(forT.tRoot);
        System.out.printf( " = " + forT.Sum);
  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           	public void run() {
				createAndShowGUI();
			}

private void createAndShowGUI() {
               //Create and set up the window.
	JFrame frame = new JFrame("Homework1");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
	Homework1 newContentPane = new Homework1();
	newContentPane.setOpaque(true); //content panes must be opaque
	frame.setContentPane(newContentPane);

		//Display the window.
	frame.pack();
	frame.setVisible(true);
        }
    });
               
}

    @Override
    public void valueChanged(TreeSelectionEvent vC) {
            		checkNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		tree.getLastSelectedPathComponent();
		if(checkNode == null){
			return;
		}
		String text = inOrder(checkNode);
		if(!checkNode.isLeaf()) text += "=" + calCulate(checkNode,forT.tRoot);
		htmlPane.setText(text);
    }

    private ImageIcon createImageIcon(String middlegif) {
        java.net.URL imgURL = Homework1.class.getResource(middlegif);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + middlegif);
			return null;
		}
    }

    private void createNodes(DefaultMutableTreeNode top, Node treeroot) {
                if(treeroot.left!=null)
		{
			DefaultMutableTreeNode TopLeft = new DefaultMutableTreeNode(treeroot.left.key);
			top.add(TopLeft);
			createNodes(TopLeft,treeroot.left);
		}
		if(treeroot.right!=null)
		{
			DefaultMutableTreeNode TopRight = new DefaultMutableTreeNode(treeroot.right.key);
			top.add(TopRight);
			createNodes(TopRight,treeroot.right);
		}
    }
        
           
public static class tree {
    
    public String postF;
    public Node tRoot;
    public int Sum = 0;
    public Node atRoot;
    
    tree(String postFix){
        this.postF = postFix;
    }
 
    
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
    
    
    public void treeStack(){
        
        
        final Stack<Node> node = new Stack<Node>();
            for (int i = 0; i < postF.length(); i++){
                char y  = postF.charAt(i);   
                    if (isOperator(y)){
                     Node right = node.pop();
                     Node left = node.pop();
                     atRoot = new Node(y);
                     atRoot.left = left;
                     atRoot.right = right;
                     node.push(atRoot);
                     }
                    else{
                         node.add(new Node(y));
                     }       
            }
        tRoot = node.pop();
    }

    

public String inOrder(DefaultMutableTreeNode node) { 
		if (node == null) return "Root Is Null";
		if(node == checkNode && !node.isLeaf()) { 
			return 	inOrder(node.getNextNode()) + node.toString() + inOrder(node.getNextNode().getNextSibling());
		}else if(forT.isOperator(node.toString().charAt(0)) && node != Top) {
			return "(" + inOrder(node.getNextNode()) + node.toString() + inOrder(node.getNextNode().getNextSibling()) + ")";
		}else {
			return node.toString(); 
		}
	}

public int calCulate(DefaultMutableTreeNode node, Node n) {
       if(node.isLeaf()) return Integer.parseInt(node.toString());
		switch(node.toString()) {
                    case "+" : return calCulate(node.getNextNode(),n.left) + calCulate(node.getNextNode().getNextSibling(),n.left);
                    case "-" : return calCulate(node.getNextNode(),n.left) - calCulate(node.getNextNode().getNextSibling(),n.left);
                    case "*" : return calCulate(node.getNextNode(),n.left) * calCulate(node.getNextNode().getNextSibling(),n.left);
                    case "/" : return calCulate(node.getNextNode(),n.left) / calCulate(node.getNextNode().getNextSibling(),n.left);
		}
        return 0;
	
}
}
}


     