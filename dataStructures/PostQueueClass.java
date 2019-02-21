package dataStructures;

public class PostQueueClass<E> implements PostQueue<E>{

	private static final long serialVersionUID = 1L;

	protected List<E> list;
	
	public PostQueueClass( )           
    {
        list = new DoublyLinkedList<E>();
    }
	
	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}

	public void enqueue(E element) {
		list.addFirst(element);
	}

	public Iterator<E> listPosts() {
		return list.iterator();
	}

}
