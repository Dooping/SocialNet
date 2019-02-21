package dataStructures;

public class StackInArray<E> implements Stack<E>
{

    static final long serialVersionUID = 0L;


    // Default capacity of the stack.
    public static final int DEFAULT_CAPACITY = 1000;

    // Memory of the stack: an array.
    protected E[] array;                                     

    // Index of the element at the top of the stack.
    protected int top;                                       


    @SuppressWarnings("unchecked")
	public StackInArray( int capacity )
    {  
        // Compiler gives a warning.
        array = (E[]) new Object[capacity];
        top = -1;
    }


    public StackInArray( )
    {  
        this(DEFAULT_CAPACITY);
    }


    // Returns true iff the stack contains no elements.
    public boolean isEmpty( )
    {  
        return top == -1;
    }


    // Returns true iff the stack cannot contain more elements.
    public boolean isFull( )
    {  
        return this.size() == array.length; 
    }


    // Returns the number of elements in the stack.
    public int size( )
    {  
        return top + 1;
    }


    // Returns the element at the top of the stack.
    public E top( ) throws EmptyStackException
    {  
        if ( this.isEmpty() )
            throw new EmptyStackException("Stack is empty.");

        return array[top];
    }


    // Inserts the specified element onto the top of the stack.
    public void push( E element ) throws FullStackException
    {  
        if ( this.isFull() )
            throw new FullStackException("Stack is full.");

        top++; 
        array[top] = element;
    }


    // Removes and returns the element at the top of the stack.
    public E pop( ) throws EmptyStackException
    {  
        if ( this.isEmpty() )
            throw new EmptyStackException("Stack is empty.");

        E element = array[top];
        array[top] = null;    // For garbage collection.
        top--;
        return element;
    }                                                 


}























