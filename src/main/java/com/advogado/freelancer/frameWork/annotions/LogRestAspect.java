package com.advogado.freelancer.frameWork.annotions;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Aspect
@Component
public class LogRestAspect {
    private Logger log = Logger.getLogger(LogRestAspect.class.getName());

    @Around("@annotation(LogRest)")
    public Object logRequisicao(ProceedingJoinPoint joinPoint) throws Throwable {
        Object procedimento = joinPoint.proceed();

        log.warning("Metodo: " + joinPoint.getSignature());
        log.warning("Request: " + (joinPoint.getArgs().length > 0 ? joinPoint.getArgs()[0].toString() : null));
        log.warning("Response: " + procedimento.toString());

        return procedimento;
    }
}
