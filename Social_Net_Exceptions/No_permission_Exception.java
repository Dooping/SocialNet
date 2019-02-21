package Social_Net_Exceptions;

public class No_permission_Exception extends Social_net_Exception {
	// excepcao qd o user loggado nao pertence ao grupo da qual tenta listar os posts/aderentes
    static final long serialVersionUID = 0L;

    public No_permission_Exception(){
        super();
    }

	public String message(){
		return super.NO_PERMISSION;
	}
}