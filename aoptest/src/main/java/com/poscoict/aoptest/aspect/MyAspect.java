package com.poscoict.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	
	@Before("execution(public com.poscoict.aoptest.vo.ProductVo com.poscoict.aoptest.service.ProductService.find(String))")
	public void beforeAdvice() {
		System.out.println("---Before Advice---");
	}
	
	@After("execution(* *..*.ProductService.find(..))")
	public void afterAdvice() {
		System.out.println("---After Advice---");
	}
	
	@AfterReturning("execution(* *..*.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("---After Returning Advice---");
	}
	
	@AfterThrowing(value="execution(* *..*.*.*(..))", throwing="ex") // global Exception 과 같은것
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("---After Throwing Advice" + ex + "---");
	}

	@Around("execution(* *..*.ProductService.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		/* before */
		System.out.println("---Around(Before) Advice---");
		
		/**
		 *  PointCut method 실행
		 */
		// 파라미터 변경
		Object[] params = {"Camera"};
		Object result = pjp.proceed(params);
		
		/* after */
		System.out.println("---Around(After) Advice---");

		return result;
	}
}