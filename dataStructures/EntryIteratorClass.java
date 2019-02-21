package dataStructures;


public class EntryIteratorClass<k,v> implements Iterator<Entry<k, v>>{

	private static final long serialVersionUID = 1L;

	protected Entry<k,v> first;
	
	protected Entry<k,v> last;
	
	protected Entry<k,v> nextToReturn;
	
	EntryIteratorClass(Entry<k,v> first,  Entry<k,v> last){
		this.first = first;
		this.last = last;
		this.rewind();
	}
	
	@Override
	public boolean hasNext() {
		return nextToReturn != null;
	}

	@Override
	public Entry<k,v> next() throws NoSuchElementException {
		if ( !this.hasNext() )
            throw new NoSuchElementException();
		Entry<k,v> entry = nextToReturn;
        nextToReturn = nextToReturn.getNext();
        return entry;
	}

	@Override
	public void rewind() {
		nextToReturn = first;		
	}

}
