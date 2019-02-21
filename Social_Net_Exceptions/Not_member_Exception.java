package Social_Net_Exceptions;

public class Not_member_Exception extends Social_net_Exception {
	// excepcao qd se tenta remover um contacto de um grupo a q nao pertence
    static final long serialVersionUID = 0L;

    public Not_member_Exception( ){
        super();
    }

	public String message(){
		return super.NOT_MEMBER;
	}
}