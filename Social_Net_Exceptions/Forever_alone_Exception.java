package Social_Net_Exceptions;

public class Forever_alone_Exception extends Social_net_Exception {
	// excepcao qd se tentam listar os amigos, mas o contacto nao tem nenhum 8(
    static final long serialVersionUID = 0L;

    public Forever_alone_Exception( ){
        super();
    }

	public String message(){
		return super.FOREVER_ALONE;
	}
}