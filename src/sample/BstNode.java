package sample;

public class BstNode<T> {

    private BstNode left;
    private BstNode right;
    private T data;

    public BstNode(T data) {
        this.data = data;
    }

    public BstNode getLeft() {
        return left;
    }
    public void setLeft(BstNode left) {
        this.left = left;
    }
    public BstNode getRight() {
        return right;
    }
    public void setRight(BstNode right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}