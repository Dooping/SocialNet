package Facebook;

import java.io.Serializable;

public interface Post extends Serializable{

	public String getTitulo();
	
	public String getTexto();
	
	public String getURL();
	
	public ContactoRead getPoster();
}
