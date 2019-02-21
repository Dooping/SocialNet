package Facebook;

import java.io.Serializable;

import Social_Net_Exceptions.Already_member_Exception;
import Social_Net_Exceptions.Not_member_Exception;
import dataStructures.*;

public interface Group extends Serializable{

	public String getName();
	
	public String getDescricao();
	
	public void addElement(ContactoRW element) throws Already_member_Exception;
	
	public void remElement(ContactoRW element) throws Not_member_Exception;
	
	public void addPost(Post post);
	
	public Iterator<Entry<String,ContactoRW>> listAderentes();
	
	public Iterator<Post> listPosts();
	
	public void selfDestroy();
	
	public boolean isAderente(ContactoRW contacto);
}
