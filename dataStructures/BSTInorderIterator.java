package dataStructures;

public class BSTInorderIterator<K extends Comparable<K>, V> implements
		Iterator<Entry<K, V>> {
	
	private static final long serialVersionUID = 1L;

	Stack<BSTNode<K,V>> stack;
	
	BSTNode<K,V> root;
	
	
	public BSTInorderIterator(BSTNode<K,V> root){
		this.root = root;
		rewind();
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public Entry<K, V> next() throws NoSuchElementException {
		if(!hasNext())
			throw new NoSuchElementException();
		BSTNode<K, V> next = stack.pop();
		if (next.getRight()!=null)
			diveLeft(next.getRight());	
		return next.getEntry();
	}

	
	public void rewind() {
		stack = new StackInList<BSTNode<K,V>>();
		diveLeft(root);
	}
	
	private void diveLeft(BSTNode<K,V> node){
		while(node !=null){
			stack.push(node);
			node = node.getLeft();
		}
	}

}
