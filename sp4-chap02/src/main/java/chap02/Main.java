package chap02;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
	public static void main(String[] args)
	{
		GenericXmlApplicationContext ctx 
				= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		System.out.println("이름을 입력해주십시오 : ");
		String myName;
		Scanner sc = new Scanner(System.in);
		myName = sc.nextLine();		
		
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet(myName);
		System.out.println(msg);
		ctx.close();
	}
}
