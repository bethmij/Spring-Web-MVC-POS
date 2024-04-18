package lk.ijse.gdse66.spring.config;

import lk.ijse.gdse66.spring.api.CustomerController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
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
