package Social_Net_Exceptions;

public class No_group_Exception extends Social_net_Exception{
	// excepcao qd nao existe grupo com o dado nome
    static final long serialVersionUID = 0L;

    public No_group_Exception( ){
        super();
    }

    public String message(){
    	return super.NO_GROUP;
    }
}