package structures;

import java.util.LinkedList;

public class Node<T> {

    private T data;
    private Node<T> parent;
    private LinkedList<Node<T>> childrenList;
    private boolean hasCorrectData = true;

    public Node() {
        parent = null;
        childrenList = new LinkedList<Node<T>>();
    }

    public Node(T data) {
        childrenList = new LinkedList<Node<T>>();
        this.data = data;
    }
    public Node(Node<T> parent) {
        this();
        this.parent = parent;
    }
    public Node(Node<T> parent, T data) {
        this(parent);
        this.data = data;
    }

    public boolean isLeaf(){
        return childrenList.isEmpty();
    }

    public Node<T> addChild(T data) {
        Node<T> child = new Node<T>(this, data);
        childrenList.add(child);
        return child;
    }

    public Node<T> getChild(int i){
        return childrenList.get(i);
    }

    public boolean isRoot() {
        if (parent != null) {
            return false;
        } else {
            return true;
        }
    }

    public void addNodeChild(Node<T> node) {
        childrenList.add(node);
    }

    public T getData () {
        return data;
    }

    public void setData (T data) {
        this.data = data;
    }

    public Node<T> getParent () {
        return parent;
    }

    public void setParent (Node<T> parent) {
        this.parent = parent;
    }

    public LinkedList<Node<T>> getChildrenList () {
        return childrenList;
    }

    public void setChildrenList (LinkedList<Node<T>> childrenList) {
        this.childrenList = childrenList;
    }

    public boolean isHasCorrectData () {
        return hasCorrectData;
    }

    public void setHasCorrectData (boolean hasCorrectData) {
        this.hasCorrectData = hasCorrectData;
    }
}
