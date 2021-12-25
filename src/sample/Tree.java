package sample;

public class Tree<T> {
    private BstNode<T> root;
    private int noOfElements;

    public int getSize()
    {
        return noOfElements;
    }
    public Tree()
    {
        noOfElements = 0;
    }

    public boolean isEmpty() {

        return (this.root == null);
    }

    public BstNode getRoot() {
        return this.root;
    }

    public void insert(T data) {

        System.out.print("[input: "+data+"]");
        noOfElements++;
        if(root == null) {
            this.root = new BstNode(data);
            System.out.println(" -> inserted: "+data);
            return;
        }

        insertNode(this.root, data);
        System.out.print(" -> inserted: "+data);
        System.out.println();
    }

    private BstNode insertNode(BstNode root, T data) {

        BstNode tmpNode = null;
        System.out.print(" ->"+root.getData());
        if((int)root.getData().hashCode() >= data.hashCode()) {
            System.out.print(" [L]");
            if(root.getLeft() == null) {
                root.setLeft(new BstNode(data));
                return root.getLeft();
            } else {
                tmpNode = root.getLeft();
            }
        } else {
            System.out.print(" [R]");
            if(root.getRight() == null) {
                root.setRight(new BstNode(data));
                return root.getRight();
            } else {
                tmpNode = root.getRight();
            }
        }

        return insertNode(tmpNode, data);
    }

    public void delete(T data) {

        if(deleteNode(this.root, data) != null)
            noOfElements--;
    }

    private BstNode deleteNode(BstNode root, T data) {

        if(root == null) return root;

        if(data.hashCode() < root.getData().hashCode()) {
            root.setLeft(deleteNode(root.getLeft(), data));
        } else if(data.hashCode() > root.getData().hashCode()) {
            root.setRight(deleteNode(root.getRight(), data));
        } else {
            // node with no leaf nodes
            if(root.getLeft() == null && root.getRight() == null) {
                System.out.println("deleting "+data);
                return null;
            } else if(root.getLeft() == null) {
                // node with one node (no left node)
                System.out.println("deleting "+data);
                return root.getRight();
            } else if(root.getRight() == null) {
                // node with one node (no right node)
                System.out.println("deleting "+data);
                return root.getLeft();
            } else {
                // nodes with two nodes
                // search for min number in right sub tree
                T minValue = minValue(root.getRight());
                root.setData(minValue);
                root.setRight(deleteNode(root.getRight(), minValue));
                System.out.println("deleting "+data);
            }
        }

        return root;
    }

    private T minValue(BstNode node) {

        if(node.getLeft() != null) {
            return minValue(node.getLeft());
        }
        return (T) node.getData();
    }

    public void inOrderTraversal() {
        doInOrder(this.root);
    }

    private void doInOrder(BstNode root) {

        if(root == null) return;
        doInOrder(root.getLeft());
        System.out.print(root.getData()+" ");
        doInOrder(root.getRight());
    }

    boolean search(T data)  {
        root = searchData(root, data);
        if (root!= null)
            return true;
        else
            return false;
    }

    public boolean update(T data,T toUpdate)
    {
        if (deleteNode(getRoot(),data) != null) {
            insert(toUpdate);
            return true;
        }
        return false;
    }

    BstNode searchData(BstNode root, T data)// method to search the data
    {

        if (root==null || root.getData().hashCode() == data.hashCode())
        {
            return root;
        }
        if (root.getData().hashCode() > data.hashCode())
        {
            return searchData(root.getLeft(), data); //search data at left
        }
        return searchData(root.getRight(), data); //search data at the right side of root
    }

    public static void main(String a[]) {

        Tree<String> bst = new Tree();
        bst.insert("haris");
        bst.insert("saad");
        bst.insert("afaq");
        bst.insert("zagam");
        bst.delete("haris");
        bst.inOrderTraversal();


    }
}