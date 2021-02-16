package by.zabavskiy;

import by.zabavskiy.config.ApplicationBeans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.zabavskiy")
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ApplicationBeans.class})
public class SpringBootApplicationStarter {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplicationStarter.class, args);
  }
}
