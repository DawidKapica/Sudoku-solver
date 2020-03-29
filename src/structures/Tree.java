package structures;

public class Tree<T> {

    Node<T> rootNode;
    public Tree() {
        rootNode = new Node<T>();
    }

    public Tree(Node<T> root) {
        rootNode = root;
    }

    public Node<T> getRootNode () {
        return rootNode;
    }

    public void setRootNode (Node<T> rootNode) {
        this.rootNode = rootNode;
    }
}
