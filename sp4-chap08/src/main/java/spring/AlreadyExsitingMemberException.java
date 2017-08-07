package spring;

public class AlreadyExsitingMemberException extends RuntimeException {
	public AlreadyExsitingMemberException(String message){
		super(message);
	}
}