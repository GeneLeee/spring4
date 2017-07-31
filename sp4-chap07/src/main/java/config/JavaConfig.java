package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import Calculator.Calculator;
import Calculator.RecCalculator;
import aspect.ExeTimeAspect2;

@Configuration
@EnableAspectJAutoProxy
public class JavaConfig {
	@Bean
	public ExeTimeAspect2 exeTimeAspect(){
		return new ExeTimeAspect2();
	}
	
	@Bean
	public Calculator recCal(){
		return new RecCalculator();
	}
}
