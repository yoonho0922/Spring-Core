package hello.core.singleton;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
        // AppConfig가 아닌 AppConfig@GCLIB... 임
        // 이는 AppConfig를 상속받은 것으로,
        // AppConfig 내부 함수에서도 객체들이 싱글톤이 되도록 보장해줌

        // AppConfig@GCLIB...는 AppConfig의 자식 타입이므로 AppConfig로 꺼내짐
    }
}
