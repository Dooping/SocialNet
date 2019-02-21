package Social_Net_Exceptions;

public class Already_member_Exception extends Social_net_Exception {
	// excepcao qd se tenta adicionar um aderente que ja pertence ao grupo
    static final long serialVersionUID = 0L;

    public Already_member_Exception( ){
        super();
    }

	public String message(){
		return super.ALREADY_MEMBER;
	}
}