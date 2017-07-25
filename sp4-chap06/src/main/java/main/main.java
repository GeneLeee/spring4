package main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.Client;

public class main {
	public static void main(String[] args){
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:springconf.xml");
		
		Client clinet = ctx.getBean("client", Client.class);
		clinet.send();
		ctx.close();
	}
}
