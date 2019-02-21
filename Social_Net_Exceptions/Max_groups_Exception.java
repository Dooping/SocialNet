package Social_Net_Exceptions;

public class Max_groups_Exception extends Social_net_Exception{
	// excepcao qd o utilizador tenta pertencer a mais de 10 grupos. Nao nos disserem o q dizer nesse caso pelo que da esta mensgaem para futuramente substituir
    static final long serialVersionUID = 0L;

    public Max_groups_Exception( ){
        super();
    }

    public String message(){
    	return ("THEY DIDN'T TELL ME WHAT TO DO!!");
    }
}