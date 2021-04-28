package org.springframework.samples.petclinic.aspect;

import org.apache.juli.logging.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {

	Logger logger = LoggerFactory.getLogger(LogAspect.class);

	// LogExecutionTime annotation 아래의 매소드가 호출되기 전 후 혹은 에러 발생 경우에 해당 코드 실행
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object ret = joinPoint.proceed();
		stopWatch.stop();
		logger.info(stopWatch.prettyPrint());

		return ret;
	}

}
