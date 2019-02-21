package Facebook;

import java.io.Serializable;

import dataStructures.Iterator;

public interface ContactoRead extends Serializable{

	public String getLogin();

	public String getName();
	
	public String getLocalizacao();
	
	public String getJob();
	
	public int getIdade();

	public Iterator<Post> list_posts();

	public boolean isFriend(ContactoRead contacto);
}
