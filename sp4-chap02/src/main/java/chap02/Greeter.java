package chap02;

public class Greeter {
	
	private String format;
	
	public String greet(String guest)
	{
		return String.format(format, guest);
	}
	
	public void setFormat3(String format)
	{
		this.format = format;
	}
}
