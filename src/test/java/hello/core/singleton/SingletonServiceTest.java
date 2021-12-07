package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonServiceTest {
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService ss1 = SingletonService.getInstance();
        SingletonService ss2 = SingletonService.getInstance();

        System.out.println("ss1 = " + ss1);
        System.out.println("ss2 = " + ss2);

        Assertions.assertThat(ss1).isEqualTo(ss2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService ms1 = ac.getBean("memberService", MemberService.class);
        MemberService ms2 = ac.getBean("memberService", MemberService.class);

        System.out.println("ms1 = " + ms1);
        System.out.println("ms2 = " + ms2);

        Assertions.assertThat(ms1).isEqualTo(ms2);
    }
}
