package com.codeit.springwebbasic.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect // 이 클래스는 AOP 담당자(Aspect)입니다.
@Component  // 빈 등록
public class LoggingAspect {

    // 1. Pointcut (어디서?)
    // execution([수식어] 리턴타입 [클래스경로.]메서드이름(파라미터) [예외]) - []는 생략 가능한 문법
    // @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))")
    // 모든 접근 제한자 허용, 모든 리턴타입허용, Member Controller 안에 있는 모든메서드를 대상(매개값은 모두 파라미터)
    // @Pointcut("execution(* com.codeit.springwebbasic..*.*(..))")
    // ..: 0개 이상의 하위 패키지를 의미 -> 모든 하위 패키지를 전부 지목하고 싶을 때

//    @Pointcut("execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))")
//    private void allControllerMethods(){
//        // 위에서 지정한 (어디에?) 라는 메서드 위치에 사전에 지정해야 할 여러 설정, 사전 작업 등을 명시합니다.
//        // @Pointcut을 생략하고, @Around에 바로 execution을 작성해도 됨
////        System.out.println("allControllerMethods 호출");
//    }
//
//    // 2. Advice (무엇을?): Pointcut에 저장된 곳 주변(advice)에서 이 코드를 실행
//    @Around("allControllerMethods()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        //@ProceedingJointPoint 이 AOP가 적용되는 지점(메서드) 에 대한 정보를 담고 있는 객체
//
//        // 3. 공통 기능(시작)
//        long start = System.currentTimeMillis();
//        String methodName = joinPoint.getSignature().getName(); // 메서드 이름
//        Object[] args = joinPoint.getArgs();// 메서드에 전달된 매개값들
//        Signature signature = joinPoint.getSignature();
//        System.out.println("Signature: " + signature);
//

    /// /        signature.getDeclaringTypeName()  패키지 + 클래스 이름
    /// /        joinPoint.getTarget();    실제 대상 객체(Bean) 가져요기
//
//        System.out.println("메서드 이름: " + methodName);
//        System.out.println("매개값: " + Arrays.toString(args));
//
//        // 4. 해심 기능 실행 (원래의 메서드의 기능을 실행해라)
//        Object result = joinPoint.proceed();
//
//        // 5. 공통 기능(종료 및 로그)
//        long endTime =  System.currentTimeMillis();
//        System.out.println("실행시간 " + "endTime-start" + "ms");
//
//
//        return result;  // 원래 메서드가 반환하는 값을 그래도 반환
//    }


    // @Before: 핵심 기능이 실행되기 직전까지만 실행됨
    @Before("execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // 3. 공통 기능(시작)
        long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName(); // 메서드 이름
        Object[] args = joinPoint.getArgs();// 메서드에 전달된 매개값들
        Signature signature = joinPoint.getSignature();
        System.out.println("Signature: " + signature);
        System.out.println("메서드 이름: " + methodName);
        System.out.println("매개값: " + Arrays.toString(args));

        HttpServletRequest request = getCurrentRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("RequestURI: " + requestURI);
        System.out.println("Method: " + method);


        // 이 메서드가 종료되면 알아서 핵심 로직이 수행됨
    }
    // proceed()를 따로 호출하지 않음

    // @AfterReturning: 메서드 정상 종료 이후 실행할 내용
    @AfterReturning(pointcut = "execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("메서드 이름: " + name);
        System.out.println("반환값: " + result);
    }
    // 저 위의 2개를 한꺼번에 아우룰 수 있는 기능이 @Around

    // 핵심 기능 실행 중 예외가 발생했을 때 실행됨
    // 평소에 사용하는 ControllerAdvice도 AOP 개념을 적용한 기술
    @AfterThrowing(pointcut = "execution(* com.codeit.springwebbasic.member.controller.MemberController.*(..))",
            throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("에러가 발생된 메서드: " + joinPoint.getSignature().getName());
        System.out.println("에러 메시지: " + ex.getMessage());

        // 나중에는 메신저(slack, discord 등)의 알림 전송 로직이 들어갈 수도 있음)
    }

    // 현재 HTTP 요청 가져오기
    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        // 스피링에서 제공하는 요청 관련 정보를 담아놓은 RequestContextHolder에게
        // 요청 객체를 얻어내서 리턴
        return attributes.getRequest();
    }

}
