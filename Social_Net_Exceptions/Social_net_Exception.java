package Social_Net_Exceptions;

public abstract class Social_net_Exception extends Exception{
	
	private static final long serialVersionUID = 1L;
	protected final String EMPTY_GROUP = "Grupo nao tem aderentes.";	
	protected final String NO_GROUP = "Inexistencia de grupo referido.";	
	protected final String NOT_FRIENDS = "Inexistencia de amizade referida.";
	protected final String SELF_HATE = "Amizade nao pode ser removida.";
	protected final String ALREADY_CONTACT = "Existencia de contacto referido.";
	protected final String NO_CONTACT = "Inexistencia de contacto referido.";
	protected final String ALREADY_FRIENDS = "Existencia de amizade referida.";
	protected final String ALREADY_GROUP = "Existencia de grupo referido.";
	protected final String ALREADY_MEMBER = "Existencia de aderencia referida.";
	protected final String NOT_MEMBER = "Inexistencia de aderencia referida.";
	protected final String FOREVER_ALONE = "Contacto nao tem amigos.";
	protected final String NO_PERMISSION = "Contacto nao tem permissao de leitura de posts.";
	protected final String CONTACT_NO_POSTS = "Contacto nao tem posts.";
	protected final String GROUP_NO_POSTS = "Grupo nao tem posts.";
	//mensagens no caso de cada excepcao
    public Social_net_Exception( )
    {
        super();
    }//contrutor de excepcoes
	public abstract String message();//a ser definido por cada excepcao que extende esta
}
/*cada uma das excepcoes do trabalho extende esta, cada uma dessas pussui so o construtor que e buscado a classe Exception por via
 *  de dois super(), e um metodo que devolve a mensagem que se encontram nesta classe que corresponde a essa excepcao especifica. 
 */