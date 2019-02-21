package Facebook;

import Social_Net_Exceptions.*;
import dataStructures.*;

public class FacebookClass implements Facebook{

	private static final long serialVersionUID = 1L;
	private static final int EXPECTEDGROUPS = 3000;
	private static final int EXPECTEDCONTACTS = 12000;
	
	private ContactoRW logged;
	private Dictionary<String,ContactoRW> contactos;
	private Dictionary<String,Group> groups;
	
	public FacebookClass(){
		logged = null;
		contactos = new SepChainHashTable<String,ContactoRW>(EXPECTEDCONTACTS);
		groups = new SepChainHashTable<String,Group>(EXPECTEDGROUPS);
	}

	
	public void ins_contacto(String login, String nome, int idade,//metodo para inserir contacto
			String localidade, String job) throws Already_contact_Exception {
		ContactoRW contacto = new ContactoClass(login, nome, localidade, job, idade);
		if (contactos.find(contacto.getLogin())!=null)//excepcao quando o contacto ja existe
			throw new Already_contact_Exception();
		contactos.insert(contacto.getLogin(),contacto);
	}

	
	public ContactoRead login(String login) throws No_contact_Exception {//metodo para fazer login do utilizador
		ContactoRW contacto = new ContactoClass(login);
		ContactoRW c = contactos.find(contacto.getLogin());
		if (c==null)
			throw new No_contact_Exception();//excepcao quando o contacto n existe
		logged = c;
		return logged;
	}

	
	public void ins_amizade(String login1, String login2)//metodo para inserir amizades
			throws No_contact_Exception, Already_friends_Exception
	{
		ContactoRW c1 = contactos.find(login1);
		ContactoRW c2 = contactos.find(login2);
		if ((c1==null)||(c2==null))
			throw new No_contact_Exception();//excepcao qd pelo menos um dos contactos nao existe
		c1.ins_amizade(c2);
		c2.ins_amizade(c1);
	}

	
	public void rem_amizade(String login1, String login2)//metodo de remocao de amizade
			throws Self_hate_Exception,Not_friends_Exception, No_contact_Exception
	{
		ContactoRW c1  = contactos.find(login1);
		ContactoRW c2 =  contactos.find(login2);
		if ((c1==null)||(c2==null))//excepcao qd pelo menos um dos contactos nao existe
			throw new No_contact_Exception();
		c1.rem_amizade(c2);
		c2.rem_amizade(c1);
	}

	
	public void ins_group(String nome, String texto)throws Already_group_Exception {//metodo para adicionar grupo
		if (groups.find(nome)!=null)
			throw new Already_group_Exception();//excepcao quando ja existe grupo com esse nome
		Group group = new GroupClass(nome, texto);
		groups.insert(group.getName(), group);
	}

	
	public Group cons_group(String nome)throws No_group_Exception {//metodo para consultar o grupo
		Group group = groups.find(nome);
		if (group==null)
			throw new No_group_Exception();//excepcao quando o grupo nao existe
		return group;
	}

	
	public void rem_group(String nome) throws No_group_Exception{//metodo para remover o grupo
		Group group = groups.find(nome);
		if (group==null)//excepcao quando o grupo nao existe
			throw new No_group_Exception();
		group.selfDestroy();	
		groups.remove(group.getName());
	}

	
	public void ins_aderentes(String login, String nomeG)//metodo para adicionar aderente ao grupo
			throws No_contact_Exception,No_group_Exception, Already_member_Exception, Max_groups_Exception
	{
		ContactoRW element = contactos.find(login);
		if (element==null)//excepcao qd o contacto nao existe
			throw new No_contact_Exception();
		
		Group group = groups.find(nomeG);
		if (group==null)//excepcao quando o grupo nao existe
			throw new No_group_Exception();
		
		group.addElement(element);
		element.addToGroup(group);
		
	}

	
	public void rem_aderente(String login, String nomeG)//metodo para remover aderente do grupo
			throws No_contact_Exception,No_group_Exception,Empty_group_Exception, Not_member_Exception
	{
		ContactoRW element = contactos.find(login);
		if (element==null)//excepcao qd nao existe o contacto
			throw new No_contact_Exception();
		
		Group group = groups.find(nomeG);
		if (group==null)//excepcao quando nao existe o grupo
			throw new No_group_Exception();
		
		group.remElement(element);
		element.remFromGroup(group);
	}

	
	public void ins_post(String login, String titulo, String texto, String url) throws No_contact_Exception{//metodo para adicionar post a contacto
		ContactoRW element = contactos.find(login);
		if (element==null)//excepcao quando o contacto nao existe
			throw new No_contact_Exception();
		element.ins_post(titulo, texto, url, logged);
	}

	@SuppressWarnings("unchecked")
	
	public Iterator<Entry<String,ContactoRead>> list_amigos(String login) //metodo para listar amigos do contacto
		throws No_contact_Exception, Forever_alone_Exception
	{
		ContactoRW element = contactos.find(login);
		if (element==null)//excepcao qd o contacto nao existe
			throw new No_contact_Exception();
		Iterator<?> it = element.list_amigos();
		it.next();
		if (!it.hasNext())//excepcao qd o contacto nao tem amigos
			throw new Forever_alone_Exception();
		it.rewind();
		return (Iterator<Entry<String, ContactoRead>>)it;
	}

	@SuppressWarnings("unchecked")
	
	public Iterator<Entry<String,ContactoRead>> list_aderentes(String nomeG)//metodo para listar aderentes de um grupo
		throws No_group_Exception, Empty_group_Exception
	{
		Group group = groups.find(nomeG);
		if (group==null)//excepcao qd o grupo nao existe
			throw new No_group_Exception();
		
		Iterator<?> it = group.listAderentes();
		if(!it.hasNext())//excepcao qd o grupo nao tem aderentes
			throw new Empty_group_Exception();

		return (Iterator<Entry<String,ContactoRead>>)it;
	}

	
	public Iterator<Post> list_post_contacto(String login1, String login2)//metodo para listar posts de um contacto
		throws No_contact_Exception,No_permission_Exception,Contact_no_posts_Exception
	{
		ContactoRW element1 = contactos.find(login1);
		ContactoRW element2 = contactos.find(login2);
		if ((element1==null)||(element2==null))
			throw new No_contact_Exception();//excepcao qd pelo menos um dos contactos nao existe
		if (!element1.isFriend(element2))
			throw new No_permission_Exception();//excepcao qd o user actual nao tem permissao
		Iterator<Post> it = element1.list_posts();
		if (!it.hasNext())
			throw new Contact_no_posts_Exception();//excepcao quando o contacto n tem posts
		return it;
	}

	
	public Iterator<Post> list_post_group(String nomeG, String login)//metodo para listar posts de um grupo
		throws No_group_Exception,No_contact_Exception,No_permission_Exception, Group_no_posts_Exception
	{
		Group group = groups.find(nomeG);
		if (group==null)//excepcao qd o grupo nao existe
			throw new No_group_Exception();
		
		ContactoRW element = contactos.find(login);
		if (element==null)//excepcao qd o contacto nao existe
			throw new No_contact_Exception();
		
		if (!group.isAderente(element))//excepcao qd o contacto nao tem permissao
			throw new No_permission_Exception();
		
		Iterator<Post> it = group.listPosts();
		if (!it.hasNext())//excepcao qd o grupo nao tem posts
			throw new Group_no_posts_Exception();
		
		return it;
	}

}
