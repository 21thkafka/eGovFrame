package egov.lib.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class ExampleAop {

	private static final Logger logger = LoggerFactory.getLogger(ExampleAop.class);
	
	@Pointcut("execution(* egov.**.web.*Controller.*(..))")
	public void aspectMethod() {
	    
	}
	    
	@Before("aspectMethod()")
	public void beforeMethod(JoinPoint joinPoint) throws Exception
	{
		logger.info("사용자 요청"+joinPoint.getTarget());
		System.out.println("사용자 요청"+joinPoint.getTarget());
	}
	
	@AfterThrowing(pointcut = "aspectMethod()", throwing = "exception")
	public void afterExceptionMethod(JoinPoint joinPoint,Exception exception)  throws Exception{
		//이외에도 예외처리에는 많은 방법이 존재합니다. ErrorController로 제어등..
		logger.error("ST에러발생=================");
		logger.error(""+exception.getMessage());
		logger.error("ED에러발생=================");
		throw exception;
	}
	
	@After("aspectMethod()")
	public void afterMethod(JoinPoint joinPoint)  throws Exception{
		
	}
	
	 @Around("aspectMethod()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
	  long st = System.currentTimeMillis();
	  //핵심기능 수행
	  Object rtn = joinPoint.proceed();
	  long ed = System.currentTimeMillis();
	  System.out.println("성능측정 걸린시간:"+(ed-st));
	  //currentTimeMillis:1/1000 초
	  return rtn;
	}

}
