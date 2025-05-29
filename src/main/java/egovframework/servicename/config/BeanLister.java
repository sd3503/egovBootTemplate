package egovframework.servicename.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
 *  어플리케이션 최초 실행시 등록된 Bean 목록 출력
 */
@Slf4j
@Component
public class BeanLister implements CommandLineRunner {
    private final ApplicationContext applicationContext;

    public BeanLister(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] beanNames = applicationContext.getBeanDefinitionNames();

        // Bean 이름 출력
        log.info("beanNames :: " + Arrays.toString(beanNames));
    }
}