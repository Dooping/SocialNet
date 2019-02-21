package Social_Net_Exceptions;

public class Contact_no_posts_Exception extends Social_net_Exception {
	// excepcao qd se tentam listar os posts, mas o contacto nao tem nenhum
    static final long serialVersionUID = 0L;

    public Contact_no_posts_Exception( ){
        super();
    }

	public String message(){
		return super.CONTACT_NO_POSTS;
	}
}