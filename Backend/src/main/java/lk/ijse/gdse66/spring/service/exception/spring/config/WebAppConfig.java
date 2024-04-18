package lk.ijse.gdse66.spring.service.exception.spring.config;

import lk.ijse.gdse66.spring.service.exception.spring.api.CustomerController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = CustomerController.class)
public class WebAppConfig {
    public WebAppConfig() {
        System.out.println("Web App Config()");
    }
}
