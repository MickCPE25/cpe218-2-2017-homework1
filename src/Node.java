public class Node{
    public Node left,right;
    char key;

    public Node(Node left,char key ,Node right)
    {
        this.key = key;
        this.left = null;
        this.right = null;
    }
     public String toString() {
        return (right == null && left == null) ? Character.toString(key) : "(" + left.toString()+ key + right.toString() + ")";
    }
}
