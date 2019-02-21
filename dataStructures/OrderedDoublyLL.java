package dataStructures;

public class OrderedDoublyLL<k extends Comparable<k>,v> implements OrderedDictionary<k,v>{

	
	private static final long serialVersionUID = 1L;
	
	 // Node at the head of the list.
    protected Entry<k,v> head;

    // Node at the tail of the list.
    protected Entry<k,v> tail;

    // Number of elements in the list.
    protected int currentSize;
    
    public OrderedDoublyLL( )
    {
        head = null;
        tail = null;
        currentSize = 0;
    }

	@Override
	public boolean isEmpty() {
		return head==null;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public v find(k key) {
		Entry<k, v> entry = findEntry(key);
		if (entry!=null)
			if (entry.getKey().equals(key))
				return entry.getValue();
		return null;
	}

	@Override
	public v insert(k key, v value) {
		v oldValue = null;
		if (head==null){
			tail = head = new EntryClass<k, v>(value,key,null,null);
			currentSize++;
		}
		else{
			Entry<k, v> entry = findEntry(key);		
			if (entry == null){
				Entry<k, v> oldTail = tail;
				tail = new EntryClass<k, v>(value,key,tail,null);
				oldTail.setNext(tail);
				currentSize++;
			}
			else if (entry.getKey().equals(key)){
				oldValue = entry.getValue();
				entry.setValue(value);
			}
			else {
				Entry<k, v> nextEntry = entry;
				Entry<k, v> prevEntry = nextEntry.getPrevious();
				entry = new EntryClass<k,v>(value,key,prevEntry,nextEntry);
				nextEntry.setPrevious(entry);
				if (prevEntry != null)
					prevEntry.setNext(entry);
				else
					head = entry;
				currentSize++;
			}
		}
		return oldValue;
	}

	@Override
	public v remove(k key) {
		Entry<k, v> entry = findEntry(key);
		if (entry!=null)
			if (key.equals(entry.getKey())){
				Entry<k,v> next = entry.getNext();
				Entry<k,v> prev = entry.getPrevious();
				if (prev!=null)
					prev.setNext(next);
				else
					head=next;
				if (next!=null)
					next.setPrevious(prev);
				else
					tail=prev;
				currentSize--;
				return entry.getValue();
			}
		return null;
	}

	@Override
	public Iterator<Entry<k, v>> iterator() {
		return new EntryIteratorClass<k, v>(head, tail);
	}

	@Override
	public Entry<k, v> minEntry() throws EmptyDictionaryException {
		if (isEmpty())
			throw new EmptyDictionaryException();
		else return head;
	}

	@Override
	public Entry<k, v> maxEntry() throws EmptyDictionaryException {
		if (isEmpty())
			throw new EmptyDictionaryException();
		else return tail;
	}
	
	private Entry<k, v> findEntry(k key){
		Entry<k, v> node = head;
        while ( node != null && node.getKey().compareTo(key)<0 )
        {
            node = node.getNext();
        }
        return node;
	}

}
