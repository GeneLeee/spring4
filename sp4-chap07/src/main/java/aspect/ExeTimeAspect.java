package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class ExeTimeAspect {
	public Object measure(ProceedingJoinPoint jointPoint) throws Throwable{
		long start = System.nanoTime();
		
		try{
			Object result = jointPoint.proceed();
			return result;
		}finally{
			long finish = System.nanoTime();
			Signature sig = jointPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
					jointPoint.getTarget().getClass().getSimpleName(),
					sig.getName(), Arrays.toString(jointPoint.getArgs()),
					(finish-start));
		}
	}
}
