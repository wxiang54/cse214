//rudimentary implementation of Binary Tree
//    * only supports complete Binary Tree
//      (for the purposes of the question)
public class BinaryTree {
    
    private int[] _data;
    private int _capacity;
    private int _lastPos;

    public BinaryTree( int capacity ) {
	_data = new int[capacity];
	_capacity = capacity;
	_lastPos = 0;
    }
    
    public void add( int item ) {
	if (_lastPos >= _capacity) throw new IllegalStateException(); //data array capacity reached
	_data[_lastPos] = item;
	_lastPos++;
    }

    public String inorder() {
	return inorderH(0);
    }

    //helper method for inorder()
    public String inorderH(int pos) {
	String ret = "";
	if (_lastPos == 0) return ret;
	
	int leftPos = pos*2 + 1;
	int rightPos = pos*2 + 2;

	if (leftPos < _lastPos)
	    ret += inorderH(leftPos);
	ret += _data[pos] + " ";
	if (rightPos < _lastPos)
	    ret += inorderH(rightPos);
	return ret;
    }

}
