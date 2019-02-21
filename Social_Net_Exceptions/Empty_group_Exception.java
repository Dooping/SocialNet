package Social_Net_Exceptions;

public class Empty_group_Exception extends Social_net_Exception{
	// excepcao qd se tenta listar os aderentes de um grupo vazio
    static final long serialVersionUID = 0L;

    public Empty_group_Exception( ){
        super();
    }

    public String message(){
    	return super.EMPTY_GROUP;
    }
}