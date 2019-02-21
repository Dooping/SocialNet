package Facebook;

import Social_Net_Exceptions.*;
import dataStructures.*;

public class ContactoClass implements ContactoRW{

	private static final int MAXGROUPS = 10;//numero maximo de grupos a que um contacto pode pertencer
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String nome;
	private String localizacao;
	private String job;
	private int idade;
	private OrderedDictionary<String,ContactoRW> friends;//dicionatio contendo os amigos do contact
	private PostQueue<Post> posts;//post queue contendo os posts do contacto
	private List<Group> groups;//lista que contem os grupos a que o contacto pertence
	
	public ContactoClass(String login){
		this.login = login;
	}
	
	public ContactoClass(String login, String nome, String localizacao, String job, int idade){
		this.login = login;
		this.nome = nome;
		this.localizacao = localizacao;
		this.job = job;
		this.idade = idade;
		friends = new AVLTree<String,ContactoRW>();
		posts = new PostQueueClass<Post>();
		groups = new DoublyLinkedList<Group>();
		friends.insert(login, this);
	}
	
	public String getLogin(){
		return login;//devolve o login associado ao contacto
	}
	
	public String getName() {
		return nome;//devolve o nome associado ao contacto
	}

	public String getLocalizacao() {
		return localizacao;//devolve a localizacao associado ao contacto
	}

	public String getJob() {
		return job;//devolve o trabalho associado ao contacto
	}

	public int getIdade() {
		return idade;//devolve a idade associada ao contacto
	}

	public void ins_amizade(ContactoRW contacto) throws Already_friends_Exception {//metodo de insercao de amizade
		if (isFriend(contacto))//devolve exception se ja forem amigos
			throw new Already_friends_Exception();
		friends.insert(contacto.getLogin(),contacto);
	}

	public void rem_amizade(ContactoRW contacto) throws Not_friends_Exception, Self_hate_Exception {//remove amizade entre dois contactos
		if (contacto.equals(this))
			throw new Self_hate_Exception();//excepcao se tentar remover-se a si mesmo
		if (friends.remove(contacto.getLogin())==null)
			throw new Not_friends_Exception();//excepcao se nao forem amigos
	}

	public void ins_post(String titulo, String texto, String url, ContactoRW logged) {//metodo de inserir post no mural do contacto
		Post post = new PostClass(titulo, texto, url, logged);
		Iterator<Group> groups = this.groups.iterator();
		while (groups.hasNext())//insercao do post em todos os grupos do contacto
			groups.next().addPost(post);
		
		Iterator<Entry<String,ContactoRW>> friends = this.friends.iterator();
		while (friends.hasNext())//e em todos os amigos deste
			friends.next().getValue().ins_post(post);
	}

	public void ins_post(Post post) {
		posts.enqueue(post);//insercao de post de vindo de um amigo
	}

	public Iterator<Entry<String,ContactoRW>> list_amigos() {
		return friends.iterator();//devolve um iterador dos amigos do contacto para listagem
	}

	public void addToGroup(Group group) throws Max_groups_Exception{//metodo de adicionar o contacto a um grupo
		if (groups.size()<=MAXGROUPS)//excepcao quando se tenta pertencer a mais de 10 grupos
			groups.addLast(group);
		else
			throw new Max_groups_Exception();
	}

	public void remFromGroup(Group group) throws Not_member_Exception{//metodo de remover de um grupo
		if (!groups.remove(group))//excepcao se nao for membro deste
			throw new Not_member_Exception();
	}
	
	public boolean equals(Object obj){//usado para verificacao se existe contacto com esse login
		if (this == obj) return true;
		if (obj == null) return false;
		ContactoClass c2 = (ContactoClass)obj;
		return login.equalsIgnoreCase(c2.getLogin());
	}

	public boolean isFriend(ContactoRead contacto) {//verifica se existe amizade entre this e outro contacto
		return (friends.find(contacto.getLogin())!=null);
	}

	public Iterator<Post> list_posts() {//devolve iterador dos posts no mural do utilizador
		return posts.listPosts();
	}

}
