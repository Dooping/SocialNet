package Social_Net_Exceptions;

public class Already_contact_Exception extends Social_net_Exception{
	// excepcao qd se tenta adicionar um contacto que ja existe
	static final long serialVersionUID = 0L;

	public Already_contact_Exception(){
		super();
	}
	
	public String message(){
		return super.ALREADY_CONTACT;
	}
}