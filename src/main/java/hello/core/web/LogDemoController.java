package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;  // 프록시를 사용하면 처음 생성시엔 싱글톤 처럼 사용
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody //  view 없이 문자를 바로 웹에 표현할 수 있게해줌
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject(); // 직접 빈을 찾아서 주입

        // 프록시 설정을 하면 빈관계 형성할 땐 가짜 프록시 객체를 만드어 주입하고
        // 실제로 사용하는 때에 requeset 스코프로 빈을만들어서 부입
        System.out.println("myLogger = " + myLogger.getClass());
        // 확인해보면 내가만든 빈이아니라 CGLIB라는 스프링이 바이트코드를 조작한 빈이 들어간걸 확인할 수 있음
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}

