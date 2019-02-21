package Social_Net_Exceptions;

public class Self_hate_Exception extends Social_net_Exception{
	// excepcao qd o utilizador deixa de ser amigo de si mesmo T.T
    static final long serialVersionUID = 0L;

    public Self_hate_Exception( ){
        super();
    }

	public String message(){
		return super.SELF_HATE;
	}
}