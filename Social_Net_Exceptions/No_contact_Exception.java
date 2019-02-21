package Social_Net_Exceptions;

public class No_contact_Exception extends Social_net_Exception {
	// excepcao qd nao existe contacto com o login dado
	private static final long serialVersionUID = 1L;

	public  No_contact_Exception(){
		super();
	}
		
	public String message(){
		return super.NO_CONTACT;
	}
}