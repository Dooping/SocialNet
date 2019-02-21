package dataStructures;

public class BSTBreadthFirstIterator<K extends Comparable<K>, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 1L;
	
	Queue<BSTNode<K, V>> queue;
	
	BSTNode<K, V> root;

	public BSTBreadthFirstIterator(BSTNode<K, V> root) {
		this.root = root;
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if (queue.isEmpty())
			throw new NoSuchElementException();
		BSTNode<K, V> node = queue.dequeue();
		if (node.getLeft()!=null)
			queue.enqueue(node.getLeft());
		if (node.getRight()!=null)
			queue.enqueue(node.getRight());
		return node.getEntry();
	}

	@Override
	public void rewind() {
		queue = new QueueInList<BSTNode<K, V>>();
		queue.enqueue(root);
	}

}
