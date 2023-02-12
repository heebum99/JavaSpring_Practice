package heeboo.springpractice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //Aop로 사용하기 위해 작성
@Component //컴포넌트 스캔 방식 사용
public class TimeTraceAop {

    @Around("execution(* heeboo.springpractice..*(..))") //공통 관심 사항에 대해서 정해주는 역할
    //패키지 하위 모든 파일에 다 적용하겠다는 의미
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
