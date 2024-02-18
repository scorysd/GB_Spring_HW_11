package gb.hw.timerstarter;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@gb.hw.timerstarter.Timer *)")
    public void Within(){
    }

    @Pointcut("@annotation(gb.hw.timerstarter.Timer)")
    public void Annotation(){
    }

    @Around("Within() || Annotation()")
    public Object loggableAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            Object returnValue = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            log.info("target = {}, method = {} - # {}", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), String.valueOf(timeElapsed));
            log.info("result = {}", returnValue);
            return returnValue;
        } catch (Throwable e) {
            log.info("exception = [{}, {}]", e.getClass(), e.getMessage());
            throw e;
        }
    }
}
