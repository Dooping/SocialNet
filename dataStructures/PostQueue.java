package dataStructures;

import java.io.Serializable;

public interface PostQueue<E> extends Serializable {
	
	// Returns true iff the queue contains no elements.
    boolean isEmpty( );

    // Returns the number of elements in the queue.
    int size( );

    // Inserts the specified element at the rear of the queue.
    void enqueue( E element );
    
    //Lists all the elements in the queue
    Iterator<E> listPosts();

}
