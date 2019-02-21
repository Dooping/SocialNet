package dataStructures;

public class EntryClass<K,V> implements Entry<K,V> {

	private static final long serialVersionUID = 1L;

	// Element stored in the node.
    private V element;
    
    private K key;

    // (Pointer to) the previous node.
    private Entry<K,V> previous;

    // (Pointer to) the next node.
    private Entry<K,V> next;
	
    public EntryClass(V element, K key,Entry<K,V> prev, Entry<K,V> next){
    	this.element = element;
    	this.key = key;
    	this.previous = prev;
    	this.next = next;
    }
    
    public EntryClass(V element, K key){
    	this(element, key, null, null);
    }
    
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return element;
	}

	@Override
	public Entry<K, V> getPrevious() {
		return previous;
	}

	@Override
	public Entry<K, V> getNext() {
		return next;
	}

	@Override
	public void setPrevious(Entry<K, V> newPrevious) {
		previous = newPrevious;
	}

	@Override
	public void setNext(Entry<K, V> newNext) {
		next = newNext;
	}

	@Override
	public void setValue(V newValue) {
		element = newValue;		
	}
	
	public void setKey(K newKey){
		key = newKey;
	}

}
