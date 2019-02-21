package Facebook;

public class PostClass implements Post{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String texto;
	private String url;
	private ContactoRW poster;
	//dados que cada post contem
	public PostClass(String titulo, String texto, String url, ContactoRW poster){//construtor de um post
		this.titulo = titulo;
		this.texto = texto;
		this.url = url;
		this.poster = poster;
	}
	
	public String getTitulo() {//devolve o titulo do post
		return titulo;
	}

	public String getTexto() {//devolve o texto do post
		return texto;
	}

	public String getURL() {//devolve o URL contido no post
		return url;
	}

	public ContactoRead getPoster() {//devolve quem fez o post
		return poster;
	}

}
