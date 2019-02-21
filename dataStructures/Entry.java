package dataStructures;

import java.io.Serializable;

public interface Entry<K,V> extends Serializable
{

    // Returns the key in the entry.
    K getKey( );

    // Returns the value in the entry.
    V getValue( );
    
    Entry<K,V> getPrevious ();
    
    Entry<K,V> getNext();
    
    void setPrevious(Entry<K,V> newPrevious);
    
    void setNext(Entry<K,V> newNext);
    
    void setValue(V newValue);

}
