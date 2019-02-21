package dataStructures;

public class SepChainHashTableIterator<k,v> implements Iterator<Entry<k, v>> {

	private static final long serialVersionUID = 1L;

	Dictionary<k,v>[] table;
	
	int current;
	
	Iterator<Entry<k, v>> currentIt;
	
	Entry<k,v> nextToReturn;
	
	public SepChainHashTableIterator(Dictionary<k,v>[] table){
		this.table = table;
		nextToReturn = null;
		this.rewind();
	}
	
	@Override
	public boolean hasNext() {
		return nextToReturn!=null;
	}

	@Override
	public Entry<k, v> next() throws NoSuchElementException {
		if (!hasNext())
			throw new NoSuchElementException();
		Entry<k,v> result = nextToReturn;
		if (currentIt.hasNext())
			nextToReturn = currentIt.next();
		else
			do{
				current++;
				currentIt = table[current].iterator();
				if (currentIt.hasNext())
					nextToReturn = currentIt.next();
				
			}while(table.length>current&&currentIt.hasNext());
		return result;
	}

	@Override
	public void rewind() {
		current = 0;
		do{
			currentIt = table[current].iterator();
			if (!currentIt.hasNext())
				current++;
			else
				nextToReturn = currentIt.next();
			
		}while(table.length>current&&currentIt.hasNext());
		
	}

}
