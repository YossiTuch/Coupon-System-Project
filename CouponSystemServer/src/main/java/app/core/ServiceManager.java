package app.core;

import app.core.filters.LoginFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

@Configuration
@ComponentScan
public class ServiceManager {

    @Bean
    public AdminService getAdminService() {
        return new AdminService();
    }
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CompanyService getCompanyService() {
        return new CompanyService();
    }
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CustomerService getCustomerService() {
        return new CustomerService();
    }


//    @Bean
//    public FilterRegistrationBean<LoginFilter> filterRegistrationBean(LoginFilter loginFilter){
//        FilterRegistrationBean<LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(loginFilter);
//        filterRegistrationBean.addUrlPatterns("/api/*");
//        return filterRegistrationBean;
//    }

}