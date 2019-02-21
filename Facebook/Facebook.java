package Facebook;

import java.io.Serializable;

import Social_Net_Exceptions.*;
import dataStructures.*;

public interface Facebook extends Serializable{

	public void ins_contacto(String login, String nome, int idade, String localidade, String job)
			throws Already_contact_Exception; 
	
	public ContactoRead login(String login) throws No_contact_Exception;
	
	public void ins_amizade(String login1, String login2)
			throws No_contact_Exception, Already_friends_Exception;
	
	public void rem_amizade(String login1, String login2)
			throws No_contact_Exception, Self_hate_Exception, Not_friends_Exception;
	
	public void ins_group(String nome, String texto) throws Already_group_Exception;
	
	public Group cons_group(String nome) throws No_group_Exception;
	
	public void rem_group(String nome) throws No_group_Exception;
	
	public void ins_aderentes(String login, String nomeG)
			throws No_contact_Exception,No_group_Exception, Already_member_Exception, Max_groups_Exception;
	
	public void rem_aderente(String login, String nomeG)
			throws No_group_Exception,No_contact_Exception, Empty_group_Exception, Not_member_Exception;
	
	public void ins_post(String login, String titulo, String texto, String url)
			throws No_contact_Exception;
	
	public Iterator<Entry<String,ContactoRead>> list_amigos(String login)
			throws No_contact_Exception, Forever_alone_Exception;
	
	public Iterator<Entry<String,ContactoRead>> list_aderentes(String nomeG)
			throws No_group_Exception, No_contact_Exception, Empty_group_Exception;
	
	public Iterator<Post> list_post_contacto(String login1, String login2)
			throws No_contact_Exception,No_permission_Exception,Contact_no_posts_Exception;
	
	public Iterator<Post> list_post_group(String nomeG, String login)
			throws No_group_Exception, No_contact_Exception, Group_no_posts_Exception, No_permission_Exception;
}
