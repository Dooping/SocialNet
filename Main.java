import java.io.*;
import java.util.Scanner;
import Social_Net_Exceptions.*;
import dataStructures.*;
import Facebook.*;

/**
 * 
 * @author David Gago 41710
 * @author Andre Dias 41739
 * 
 */
public class Main {

	static Messages_enum messages = new Messages_enum();
	public static final String INPUTFILE = "file.txt";//ficheiro onde se grava o estado do programa
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);//criacao de um objecto scanner
		Facebook facebook = load();
		if (facebook==null) facebook = new FacebookClass();//ciacao de uma classe facebookclass
		String command;
		while (in.hasNext()) {
			command = in.next();//le o commando do scanner
			Interpretador(command, in, facebook);//manda excutar a accao appropriada 
			in.nextLine();
			System.out.println();
		}
		in.close();//fecha o scanner
		store(facebook);//grava o estado para a proxima execucao
	}
	
	private static void Interpretador(String cmd, Scanner in, Facebook facebook) {//associa a cada instrucao ao metodo apropiado
		if (cmd.equalsIgnoreCase("IC"))
			inserir_contacto(in, facebook);
		else if (cmd.equalsIgnoreCase("CC"))
			login(in, facebook);
		else if (cmd.equalsIgnoreCase("IA"))
			inserir_amizade(in, facebook);
		else if (cmd.equalsIgnoreCase("RA"))
			remover_amizade(in, facebook);
		else if (cmd.equalsIgnoreCase("IG"))
			inserir_grupo(in, facebook);
		else if (cmd.equalsIgnoreCase("CG"))
			consultar_grupo(in, facebook);
		else if (cmd.equalsIgnoreCase("RG"))
			remover_grupo(in, facebook);
		else if (cmd.equalsIgnoreCase("ID"))
			inserir_aderente(in, facebook);
		else if (cmd.equalsIgnoreCase("RD"))
			remover_aderente(in, facebook);
		else if (cmd.equalsIgnoreCase("IP"))
			inserir_post(in, facebook);
		else if (cmd.equalsIgnoreCase("LA"))
			listar_amigos(in, facebook);
		else if (cmd.equalsIgnoreCase("LD"))
			listar_aderentes(in, facebook);
		else if (cmd.equalsIgnoreCase("LC"))
			listar_posts_contacto(in, facebook);
		else if (cmd.equalsIgnoreCase("LG"))
			listar_posts_grupo(in, facebook);
		else System.out.println("erro nos comandos");//debugging use only
	}

	private static void listar_posts_grupo(Scanner in, Facebook facebook) {//lista posts de um grupo
		String nomeG = in.next().toUpperCase();
		in.skip(" ");
		String login = in.nextLine().toUpperCase();
		int count = 0;
		
		try{
			Iterator<Post> posts = facebook.list_post_group(nomeG, login);//obtem o iterador dos posts do grupo
			Post p;
			while (posts.hasNext()){
				p=posts.next();
				if (count>0)
					System.out.println();
				count++;
				System.out.println(p.getTitulo());
				System.out.println(p.getTexto());
				System.out.println(p.getURL());
			}
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void listar_posts_contacto(Scanner in, Facebook facebook) {//lista posts de um contacto
		String login1 = in.next().toUpperCase();
		in.skip(" ");
		String login2 = in.nextLine().toUpperCase();
		int count = 0;
		
		try{
			Iterator<Post> posts = facebook.list_post_contacto(login1, login2);//obtem o iterador
			Post p;
			while (posts.hasNext()){
				p=posts.next();
				//if (p.getPoster().getLogin().equalsIgnoreCase(login2)){//selecciona os posts do contacto dois
					if (count>0)
						System.out.println();
					count++;
					System.out.println(p.getTitulo());
					System.out.println(p.getTexto());
					System.out.println(p.getURL());
					
				//}
			}
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void listar_aderentes(Scanner in, Facebook facebook) {//lista aderentes de um grupo
		in.skip(" ");
		String nomeG = in.nextLine().toUpperCase();
		
		try{
			Iterator<Entry<String,ContactoRead>> aderentes = facebook.list_aderentes(nomeG);//obtem o iterador
			ContactoRead c;
			//int count=0;
			while(aderentes.hasNext()){
				c=aderentes.next().getValue();
				//if (count>0)
				//	System.out.println();
				System.out.println(c.getLogin() + " " + c.getName());
				//count++;
			}
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void listar_amigos(Scanner in, Facebook facebook) {//lista amigos de um contacto
		in.skip(" ");
		String login = in.next().toUpperCase();
		
		try{
			Iterator<Entry<String,ContactoRead>> amigos = facebook.list_amigos(login);//obtem o iterador
			ContactoRead c;
			//int count=0;
			while(amigos.hasNext()){
				c=amigos.next().getValue();
				
				if (!login.equals(c.getLogin())){
					//if (count>0)
					//	System.out.println();
					System.out.println(c.getLogin() + " " + c.getName());
					//count++;
				}
				
			}
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}
	private static void inserir_post(Scanner in, Facebook facebook) {//insere post no contacto
		// TODO Auto-generated method stub
		in.skip(" ");
		String login = in.nextLine().toUpperCase();
		String titulo = in.nextLine().toUpperCase();
		String texto = in.nextLine().toUpperCase();
		String url = in.nextLine().toUpperCase();
		
		try{
			facebook.ins_post(login, titulo, texto, url);//insere
			System.out.println(messages.message("POST"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void remover_aderente(Scanner in, Facebook facebook) {//remove aderente de um grupo
		// TODO Auto-generated method stub
		String login = in.next().toUpperCase();
		in.skip(" ");
		String nomeG = in.nextLine().toUpperCase();
		
		try{
			facebook.rem_aderente(login, nomeG);//remove 
			System.out.println(messages.message("NO_LONGER_MEMBER"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void inserir_aderente(Scanner in, Facebook facebook) {//insere aderente no grupo
		// TODO Auto-generated method stub
		String login = in.next().toUpperCase();
		in.skip(" ");
		String nomeG = in.nextLine().toUpperCase();
		
		try{
			facebook.ins_aderentes(login, nomeG);//insere
			System.out.println(messages.message("JOIN_GROUP"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void remover_grupo(Scanner in, Facebook facebook) {//apaga grupo
		// TODO Auto-generated method stub
		in.skip(" ");
		String nomeG = in.nextLine().toUpperCase();
		
		try{
			facebook.rem_group(nomeG);
			System.out.println(messages.message("NO_LONGER_GROUP"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void consultar_grupo(Scanner in, Facebook facebook) {//consulta um grupo
		// TODO Auto-generated method stub
		in.skip(" ");
		String nomeG = in.nextLine().toUpperCase();
		
		try{
			Group g = facebook.cons_group(nomeG);//consulta
			System.out.println(g.getName());
			System.out.println(g.getDescricao());
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void inserir_grupo(Scanner in, Facebook facebook) {//cria grupo novo
		// TODO Auto-generated method stub
		in.skip(" ");
		String nomeG = in.nextLine().toUpperCase();
		String descritivo = in.nextLine().toUpperCase();
		
		try{
			facebook.ins_group(nomeG, descritivo);//insere
			System.out.println(messages.message("GROUP"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void remover_amizade(Scanner in, Facebook facebook) {//remove amizade entre dois contactos
		// TODO Auto-generated method stub
		String login1 = in.next().toUpperCase();
		in.skip(" ");
		String login2 = in.nextLine().toUpperCase();
		
		try{
			facebook.rem_amizade(login1, login2);
			System.out.println(messages.message("NO_LONGER_FRIENDS"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void inserir_amizade(Scanner in, Facebook facebook) {//insere amizade entre dois contactos
		// TODO Auto-generated method stub
		String login1 = in.next().toUpperCase();
		in.skip(" ");
		String login2 = in.nextLine().toUpperCase();
		
		try{
			facebook.ins_amizade(login1, login2);
			System.out.println(messages.message("NOW_FRIENDS"));
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}

	private static void login(Scanner in, Facebook facebook) {//logga um contacto
		// TODO Auto-generated method stub
		in.skip(" ");
		String login = in.nextLine().toUpperCase();
		
		try{
			ContactoRead c = facebook.login(login);//faz o log in
			System.out.println(c.getLogin() + " " +c.getName() + " " + c.getIdade());
			System.out.println(c.getLocalizacao() + " " + c.getJob());
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}
	private static void inserir_contacto(Scanner in, Facebook facebook) {//insere contacto novo
		// TODO Auto-generated method stub
		String login = in.next().toUpperCase();
		in.skip(" ");
		String nome = in.nextLine().toUpperCase();
		int idade = in.nextInt();
		in.skip(" ");
		String localidade = in.nextLine().toUpperCase();
		String profissao = in.nextLine().toUpperCase();
		
		try{
			facebook.ins_contacto(login, nome, idade, localidade, profissao);//insere
			System.out.println(messages.message("CONTACT"));//mensagem de sucesso
		}//tratamento de excepcoes
		catch(Social_net_Exception exception){
			System.out.println(exception.message());
		}
	}
	private static Facebook load(){//faz o load do estado do facebookclass 
		Facebook f = null;
		try
		{
			ObjectInputStream file = new ObjectInputStream( new FileInputStream(INPUTFILE));//abre o stream
			//compiler gives a warning.
			f = (FacebookClass) file.readObject();//le
			file.close();//fecha o stream
		}
		catch (IOException e)
		{
			f = new FacebookClass();//cria nova facebookclass no caso da primeira execucao
			//System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			f = new FacebookClass();//cria nova facebookclass 
		}
		return f;
	}
	
	private static void store(Facebook f){//guarda o estado do programa
		try
		{
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(INPUTFILE));//abre stream
			file.writeObject(f);//escreve no ficheiro
			file.flush();
			file.close();//fecha o stream
		}
		catch (IOException e)
		{
			
		}
	}
}
