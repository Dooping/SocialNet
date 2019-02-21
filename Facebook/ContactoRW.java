package Facebook;

import Social_Net_Exceptions.Already_friends_Exception;
import Social_Net_Exceptions.Max_groups_Exception;
import Social_Net_Exceptions.Not_friends_Exception;
import Social_Net_Exceptions.Not_member_Exception;
import Social_Net_Exceptions.Self_hate_Exception;
import dataStructures.*;

interface ContactoRW extends ContactoRead{
	
	public void ins_amizade(ContactoRW contacto)throws Already_friends_Exception;
	
	public void rem_amizade(ContactoRW contacto)throws Not_friends_Exception, Self_hate_Exception;
	
	public void ins_post(String titulo, String texto, String url, ContactoRW logged);
	
	public void ins_post(Post post);
	
	public Iterator<Entry<String,ContactoRW>> list_amigos();
	
	public void addToGroup(Group group)throws Max_groups_Exception;
	
	public void remFromGroup(Group group)throws Not_member_Exception;
	
	
}
