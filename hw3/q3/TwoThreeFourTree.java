import java.util.NoSuchElementException;
import java.util.Arrays;

public class TwoThreeFourTree {
    
    // PRIVATE INNER CLASS TWOTHREEFOURNODE
    private class TwoThreeFourNode {
	private int _numItems;
	private int[] _items;
	private boolean _isLeaf;
	private TwoThreeFourNode[] _children;
	private TwoThreeFourNode _parent; //convenience
	private int _parentInd; //convenience, stores which children index this node is in respect to its parent
	
	public TwoThreeFourNode() {
	    _numItems = 0;
	    _items = new int[4]; //3 items, +1 for insertion overflow handling
	    _isLeaf = true;
	    _children = new TwoThreeFourNode[5]; //4 children, +1 for overflow handling
	    _parent = null;
	    _parentInd = -1;

	}

	public String toString() {
	    return toString(0);
	}

	public String toString(int n) {
	    String s = "[ ";
	    for (int i = 0; i < _numItems; i++)
		s += _items[i] + " ";
	    s += "]";
	    if (!_isLeaf)
		for (int i = 0; i < _numItems+1; i++) {
		    s += "\n" +
			new String(new char[n]).replace("\0", "\t") +
			"Child " + i + ": ";
		    if (_children[i] == null)
			s += "[ ]";
		    else
			s += _children[i].toString(n+1);
		}
	    return s;
	}
    } //end inner class




    
    // TREE INSTANCE VARS
    private TwoThreeFourNode _root;

    // CONSTRUCTOR
    public TwoThreeFourTree() {
	_root = new TwoThreeFourNode();
    }

    @Override
    public String toString() {
	return _root.toString();
    }

    


    
    // ====================== INSERTION ========================= //
    
    public void insert( int k ) {
	insert(k, _root);
    }

    public void insert( int k, TwoThreeFourNode node ) {
	if (node._isLeaf) {
	    //if leaf, insert here, but need to find what index:
	    boolean posFound = false;
	    for (int i = 0; i < node._numItems; i++) {
		if (k < node._items[i]) {
		    //shift everything over
		    for (int j = node._numItems; j > i; j--)
			node._items[j] = node._items[j-1];
		    node._items[i] = k; //insert at pos i
		    posFound = true;
		    break;
		}
	    }
	    
	    if (!posFound)
		//safe bc know there were < 3 items before
		node._items[node._numItems] = k;
	    
	    node._numItems++;

	    //if full --> SPLIT
	    if (node._numItems == 4)
		split(k, node);
	}
	
	else { //not a leaf
	    for (int i = 0; i < node._numItems; i++)
		if (k < node._items[i]) {
		    //recurse thru ith children
		    insert(k, node._children[i]);
		    return;
		}
	    //if not < anything, recurse thru final child
	    insert(k, node._children[node._numItems]); 
	}
    }


    //SPLITTING HELPER FXN FOR INSERTION OVERFLOW
    public void split( int k, TwoThreeFourNode node ) {
	TwoThreeFourNode leftNode = node;
	TwoThreeFourNode rightNode = new TwoThreeFourNode();
	int promoteMe = leftNode._items[1]; //four elems: promote left mid elem

	//transfer elements
	rightNode._items[0] = leftNode._items[2];
	rightNode._items[1] = leftNode._items[3];
	rightNode._numItems = 2;
	leftNode._numItems = 1;

	//transfer children
	for (int x = 0; x < 3; x++) {
	    TwoThreeFourNode transfer_child = leftNode._children[x+2];
	    if (transfer_child != null) {
		rightNode._children[x] = transfer_child;
		transfer_child._parent = rightNode;
		transfer_child._parentInd = x;
	    }
	}

	//if left was internal, then right should be internal
	rightNode._isLeaf = leftNode._children[0] == null;

			   
	//if root (null parent): height increases by 1
	if (node._parent == null) {
	    TwoThreeFourNode newRoot = new TwoThreeFourNode();
	    leftNode._parent = newRoot;
	    leftNode._parentInd = 0;
	    rightNode._parent = newRoot;
	    rightNode._parentInd = 1;
	    newRoot._isLeaf = false;
	    newRoot._children[0] = leftNode;
	    newRoot._children[1] = rightNode;
	    _root = newRoot;
	}

	else { //already had a parent
	    TwoThreeFourNode parent = leftNode._parent;
	    rightNode._parent = parent;
	    int parentInd = leftNode._parentInd;
	    rightNode._parentInd = parentInd + 1;
	    for (int i = parent._numItems+1; i > parentInd+1; i--)
		parent._children[i] = parent._children[i-1];
	    parent._children[ parentInd + 1 ] = rightNode;
	}

	leftNode._parent._isLeaf = true; //to bypass isLeaf check at beginning
	insert( promoteMe, leftNode._parent );
	leftNode._parent._isLeaf = false;
    }
    



    //have not implemented delete yet
    public boolean delete( int k ) {
	if ( ! search(k) ) throw new NoSuchElementException();
	throw new UnsupportedOperationException();
    }




    
    public boolean search( int k ) { return search(k, _root); }
    
    //helper for search method
    public boolean search( int k, TwoThreeFourNode node ) {
	for (int i = 0; i < _root._numItems; i++) {
	    if (node._items[i] == k) return true;
	}
	if (node._isLeaf)
	    return false;
	if (k < node._items[0]) //already know numItems >= 1
	    return search(k, node._children[0]);
	if (node._numItems == 1 || k < node._items[1]) //2-node
	    return search(k, node._children[1]);
	if (node._numItems == 2 || k < node._items[2]) //3-node
	    return search(k, node._children[2]);
	return search(k, node._children[3]); //4-node
    }



    
    
    // ==================== TRAVERSALS ===================== //
    
    public String inorder() {
	return inorder(_root);
    }

    public String inorder(TwoThreeFourNode node) {
	String ret = "";
	int i = 0;
	while (i < node._numItems) {
	    if (!node._isLeaf)
		ret += inorder(node._children[i]);
	    ret += node._items[i] + " ";
	    i++;
	}
	if (!node._isLeaf)
	    ret += inorder( node._children[i] );
		
	return ret;
    }

    public String preorder() {
	return preorder(_root);
    }

    public String preorder(TwoThreeFourNode node) {
	String ret = "";
	for (int i = 0; i < node._numItems; i++)
	    ret += node._items[i] + " ";
	
	if ( !node._isLeaf )
	    for (int i = 0; i < node._numItems+1; i++)
		ret += preorder( node._children[i] );

	return ret;
    }

    public String postorder() {
	return postorder(_root);
    }
    
    public String postorder(TwoThreeFourNode node) {
	String ret = "";
	if ( !node._isLeaf )
	    for (int i = 0; i < node._numItems+1; i++)
		ret += postorder( node._children[i] );

	for (int i = 0; i < node._numItems; i++)
	    ret += node._items[i] + " ";
			
	return ret;
    }


    


    //DEBUGGING PURPOSES ONLY
    public static void main(String[] args) {
	System.out.println("\ntesting 2-3-4 Tree:");
	TwoThreeFourTree tree = new TwoThreeFourTree();
	System.out.println(tree);
	
	int[] toInsert = new int[]{30, 40, 20, 50, 70, 60, 44, 42, 46, 24, 26, 22, 80, 90, 49, 47};
	System.out.println("\ninserting " + Arrays.toString(toInsert));
	for (int i = 0; i < toInsert.length; i++) {
	    System.out.println("\ninserting " + toInsert[i]);
	    tree.insert(toInsert[i]);
	    System.out.println(tree);
	}

	System.out.println(tree);

	System.out.println("\nSearching 100: " + tree.search(0));
	for (int i = 0; i < toInsert.length; i++) {
	    System.out.println("searching " + toInsert[i] + ": " + tree.search(toInsert[i]));
	}
	System.out.println("searching 0: " + tree.search(0));

	System.out.println("\ninorder: " + tree.inorder());
	System.out.println("\npreorder: " + tree.preorder());
	System.out.println("\npostorder: " + tree.postorder());
    }
}
