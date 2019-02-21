package dataStructures;

public class ElementAlreadyExistsException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public ElementAlreadyExistsException( )
    {
        super();
    }

    public ElementAlreadyExistsException( String message )
    {
        super(message);
    }

}