package Social_Net_Exceptions;

public class Already_friends_Exception extends Social_net_Exception {
	// excepcao qd se tenta adicionar uma amizade que ja existe
    static final long serialVersionUID = 0L;

    public Already_friends_Exception( ){
        super();
    }

	public String message(){
		return super.ALREADY_FRIENDS;
	}
}