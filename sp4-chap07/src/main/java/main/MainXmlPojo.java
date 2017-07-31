package main;

import org.springframework.context.support.GenericXmlApplicationContext;

import Calculator.Calculator;
import Calculator.ImpeCalculator;

public class MainXmlPojo {
	public static void main(String[] args){
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext("classpath:aopPojo.xml");
		
		//Calculator impeCal = ctx.getBean("impeCal", Calculator.class);
		ImpeCalculator impeCal = ctx.getBean("impeCal", ImpeCalculator.class);
		long fiveFact1 = impeCal.factorial(5);
		System.out.println("impelCal.factorial(5) = " + fiveFact1);
		
		Calculator recCal = ctx.getBean("recCal", Calculator.class);
		long fiveFact2 = recCal.factorial(5);
		System.out.println("recCal.factorial(5) = " + fiveFact2 );
	}
}
