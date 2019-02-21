package Facebook;

public class Messages_enum{//classe que contem as mensagens de sucesso
	

	public String message(String msg){
		
		 if (msg.equals("CONTACT")) 
			 return "Insercao de contacto com sucesso.";
		 else if (msg.equals("NOW_FRIENDS")) 
			 return "Insercao de amizade com sucesso.";
		 else if (msg.equals("GROUP"))
			 return "Insercao de grupo com sucesso.";
		 else if (msg.equals("JOIN_GROUP"))
			 return "Insercao de aderencia a grupo com sucesso.";
		 else if (msg.equals("NO_LONGER_GROUP"))
			 return "Remocao de grupo com sucesso.";
		 else if (msg.equals("NO_LONGER_FRIENDS"))
			 return "Remocao de amizade com sucesso.";
		 else if (msg.equals("NO_LONGER_MEMBER"))
			 return "Remocao de aderencia com sucesso.";
		 else if (msg.equals("POST"))
			 return "Insercao de post com sucesso.";
		 else
			 return "ERROR";
	}
}