package Social_Net_Exceptions;

public class Not_friends_Exception extends Social_net_Exception{
	// excepcao qd os users nao sao amigos
    static final long serialVersionUID = 0L;

    public Not_friends_Exception(){
        super();
    }

    public String message(){
    	return super.NOT_FRIENDS;
    }
}