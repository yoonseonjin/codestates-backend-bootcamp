package start.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class Aspect6 {  // 어드바이스 종류 : @Around, @Before, @AfterRunning, @AfterThrowing, @After
    @Around("start.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            // @Before
            log.info("[around] 트랜잭션 시작 -> {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[around] 트랜잭션 커밋 -> {}", joinPoint.getSignature());
            return result;
        }
        catch (Exception e) {
            // @AfterThrowing
            log.info("[around] 트랜잭션 롤백 -> {}", joinPoint.getSignature());
            throw e;
        }
        finally {
            // @After
            log.info("[around] 리소스 릴리즈 -> {}", joinPoint.getSignature());
        }
    }

    @Before("start.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "start.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return = {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "start.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[throwing] {} message = {}", joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "start.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
