package dataStructures;
                                              
public class QueueInArray<E> implements Queue<E>
{                                     

    static final long serialVersionUID = 0L;


    // Default capacity of the queue.     
    public static final int DEFAULT_CAPACITY = 1000;     

    // Memory of the queue: a circular array.
    protected E[] array; 
                                              
    // Index of the element at the front of the queue.         
    protected int front;                                       
                                              
    // Index of the element at the rear of the queue.          
    protected int rear;
                                              
    // Number of elements in the queue.
    protected int currentSize;


    @SuppressWarnings("unchecked")
	public QueueInArray( int capacity )
    {
        // Compiler gives a warning.
        array = (E[]) new Object[capacity];
        front = 0;                    
        rear = capacity - 1;      
        currentSize = 0;              
    }


    public QueueInArray( )          
    {
        this(DEFAULT_CAPACITY);
    }


    // Returns true iff the queue contains no elements.
    public boolean isEmpty( ) 
    {    
        return currentSize == 0;
    }

                                             
    // Returns true iff the queue cannot contain more elements.
    public boolean isFull( )  
    {
        return currentSize == array.length;
    }


    // Returns the number of elements in the queue.
    public int size( )        
    {
        return currentSize;
    }

                                             
    // Increments with "wrap around".   
    protected int nextIndex( int index )
    {
        return ( index + 1 ) % array.length;
    }


    // Inserts the specified element at the rear of the queue.
    public void enqueue( E element ) throws FullQueueException    
    {
        if ( this.isFull() )
            throw new FullQueueException("Queue is full.");

        rear = this.nextIndex(rear);       
        array[rear] = element;     
        currentSize++;          
    }


    // Removes and returns the element at the front of the queue.
    public E dequeue( ) throws EmptyQueueException   
    {
        if ( this.isEmpty() )
            throw new EmptyQueueException("Queue is empty.");

        E element = array[front];
        array[front] = null;    // For garbage collection.
        front = this.nextIndex(front);     
        currentSize--;          
        return element;            
    }                                                                


}
