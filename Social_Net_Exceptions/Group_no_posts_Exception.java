package Social_Net_Exceptions;

public class Group_no_posts_Exception extends Social_net_Exception {
	// excepcao qd se tenta adicionam listar os posts de um grupo que nao possui nenhum
    static final long serialVersionUID = 0L;

    public Group_no_posts_Exception( ){
        super();
    }

	public String message(){
		return super.GROUP_NO_POSTS;
	}
}