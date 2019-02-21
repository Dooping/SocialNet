package dataStructures;  

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 

    static final long serialVersionUID = 0L;


    // The array of dictionaries.
    protected Dictionary<K,V>[] table;


    @SuppressWarnings("unchecked")
	public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
             table[i] = new OrderedDoublyLL<K,V>();
            //table[i] = null;
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                


    // Returns the hash value of the specified key.
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // returns its value; otherwise, returns null.
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // replaces its value by the specified value and returns the old value;
    // otherwise, inserts the entry (key, value) and returns null.
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            //TODO: Original comentado para nao dar erro de compilacao.
             this.rehash();
        V elem = table[ this.hash(key) ].insert(key, value);
        if (elem == null)
        	currentSize++;
        return elem;
    }


	@SuppressWarnings("unchecked")
	private void rehash() {
    	maxSize *=2;
    	Iterator<Entry<K,V>> it = this.iterator();
    	int arraySize = HashTable.nextPrime((int) (1.1 * maxSize));
    	Dictionary<K,V>[] tableAux = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
        	tableAux[i] = new OrderedDoublyLL<K,V>();
        while (it.hasNext()){
        	Entry<K,V> e = it.next();
        	this.insert(e.getKey(), e.getValue());
        }
        table = tableAux;
	}


	// If there is an entry in the dictionary whose key is the specified key,
    // removes it from the dictionary and returns its value;
    // otherwise, returns null.
    public V remove( K key )
    {
    	V elem = table[ this.hash(key) ].remove(key);
    	if (elem != null)
    		currentSize--;
        return elem;
    }


    // Returns an iterator of the entries in the dictionary.
    public Iterator<Entry<K,V>> iterator( )
    {
        return new SepChainHashTableIterator<K, V>(table);
    } 


}
































