//Sencer Yücel
//16933103152
//Binary Search Tree Class for hw3
public class BST<Key extends Comparable<Key>,Value>{
    private Node root;
    class Node {
        private Key key;
        private Value val;
        private Node left,right;

        //As being wanted, a tree for every movie which includes actors for it.
        BST actors = new BST();

        Node(Key key, Value val)
        {
            this.key = key;
            this.val = val;
        }
    }
    public Value get(Key key) {
        Node x = root;

        while (x != null) {
            //Since all of the values are String, I created a String array here.
            String a = (String)x.val;
            //Well, this "split" method for Strings is something I learnt new. Very useful.
            String[] arr = a.split(",");
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else if (cmp == 0) return (Value)arr;
        }
        return null;
    }
    //Actors' tree's get method.
    public BST getBST(Key key) {
        Node x = root;
        while(x!= null) {
            int cmp = key.compareTo(x.key);
            if(cmp<0) x = x.left;
            else if(cmp>0) x = x.right;
            else return x.actors;
        }
        return null;
    }
    public Queue<Key> Keys() {
        Queue<Key> q = new Queue<>();
        inorderKeys(root, q);
        return q;
    }
    private void inorderKeys(Node x, Queue<Key> q) {
        if (x == null) return;
        inorderKeys(x.left, q);
        q.enqueue(x.key);
        inorderKeys(x.right, q);

    }
    //I am dequeueing the values and keys until they match. When they match, I return to that key.
    public Key getKey(Value val) {
        Queue<Value> q = Values();
        Queue<Key> q2 = Keys();
        while (!q.isEmpty()) {
            String[] arr = (String[]) q.dequeue();
            Key x = q2.dequeue();
            String s = (String)val;
            if(s.contains(arr[0])) {
                return x;
            }

        }
        return null;
    }
    public void put(Key key, Value val) {
        root = put(root,key,val);
    }

    //Simply, putting a node into the tree.
    private Node put(Node n,Key key,Value val) {
        if(n == null) return new Node(key,val);
        int cmp = key.compareTo(n.key);
        if(cmp<0) {
            n.left = put(n.left, key, val);
        }
        else if(cmp>0 ) {
            n.right = put(n.right, key, val);
        }
        else if(cmp ==0) {
            n.val = val;
        }
        return n;
    }
    public Queue<Value> Values() {
        Queue<Value> q = new Queue<Value>();
        inorder(root, q);
        return q;
    }
    //It is used to print out the values with using inorder traversal from smaller to bigger.
    //I used queue here to make it easier (I may also use a random-sized array but it would not seem as pretty as queue, that is why I chose queue structure here.
    private void inorder(Node x, Queue q) {
        if (x == null) return;
        inorder(x.left, q);
        String a = (String)x.val;
        String[] arr = a.split(",");

        q.enqueue(arr);
        inorder(x.right, q);

    }
    public Stack<Value> ReverseValues() {
        Stack<Value> stack = new Stack<Value>();
        ReverseInorder(root, stack);
        return stack;
    }
    //Because of the reason that Stacks have Last In First Out (LIFO) rule, I created this method to print out the values in reversed order.
    private void ReverseInorder(Node x, Stack stack) {
        if (x == null) return;
        ReverseInorder(x.left, stack);
        String a = (String)x.val;
        String[] arr = a.split(",");
        stack.push(arr);
        ReverseInorder(x.right, stack);

    }
    //It returns a boolean value with respect to the key either it contains or not.
    public boolean contains(Key key) {
        return get(key) != null;

    }
    public void deleteMin() { root = deleteMin(root); }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }
    //This delete method has taken from the course slides, simply deletes a node in the logic of binary search trees.
    public void delete(Key key) { root = delete(root, key); }
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }
    //Finding the minimum node in the tree.
    private Node min(Node x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

}
