package Facebook;

import Social_Net_Exceptions.*;
import dataStructures.*;

public class GroupClass implements Group{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descricao;
	private OrderedDictionary<String, ContactoRW> aderentes;
	private PostQueue<Post> posts;
	
	public GroupClass(String nome, String descricao){
		this.nome = nome;
		this.descricao = descricao;
		aderentes = new AVLTree<String,ContactoRW>();
		posts = new PostQueueClass<Post>();
	}

	@Override
	public String getName() {//metodo para devolver o nome do grupo
		return nome;
	}

	@Override
	public String getDescricao() {//metodo para devolver a descricao do grupo
		return descricao;
	}

	@Override
	public void addElement(ContactoRW element) throws Already_member_Exception {//metodo para adicionar elemento ao grupo
		if (aderentes.find(element.getLogin())!=null)
			throw new Already_member_Exception();//excepcao qd ocontacto ja e aderente do grupo
		aderentes.insert(element.getLogin(),element);
	}

	@Override
	public void remElement(ContactoRW element) throws Not_member_Exception{//metodo para remover elemento do grupo
		if (aderentes.find(element.getLogin())==null)//excepcao qd o contacto n e aderente do grupo
			throw new Not_member_Exception();
		aderentes.remove(element.getLogin());
	}

	@Override
	public void addPost(Post post) {//metodo para adicionar post ao grupo
		posts.enqueue(post);
	}

	public boolean equals(Object obj){//usado para verificacao se existe grupo ja com esse nome
		if (this == obj) return true;
		if (obj == null) return false;
		GroupClass g2 = (GroupClass)obj;
		return nome.equalsIgnoreCase(g2.getName());
	}

	@Override
	public Iterator<Post> listPosts() {//metodo que devolve um iterador dos posts do grupo
		return posts.listPosts();
	}

	@Override
	public void selfDestroy() {//metodo que elemina o grupo
		Iterator<Entry<String,ContactoRW>> aderentes = this.aderentes.iterator();
		while(aderentes.hasNext())
			try {
				aderentes.next().getValue().remFromGroup(this);//remove cada aderente um a um, neste caso as excepcao nunca ocorrem pelo q sao captadas aqui
			} catch (Not_member_Exception e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}		
	}

	@Override
	public Iterator<Entry<String,ContactoRW>> listAderentes() {//metodo que devolve um iterador dos aderentes do grupo
		return aderentes.iterator();
	}

	@Override
	public boolean isAderente(ContactoRW contacto) {//metodo que verifica se um contacto e aderente do grupo
		return (aderentes.find(contacto.getLogin())!=null);
	}
}
