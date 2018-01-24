public class Node{
    public Node left,right;
    Character key;

    public Node(char key)
    {
        this.key = key;
        this.left = null;
        this.right = null;
    }
     public String toString() {
        return (right == null && left == null) ? Character.toString(key) : "(" + left.toString()+ key + right.toString() + ")";
    }
}
