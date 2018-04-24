package com.taobao.fanli.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index");
        super.addViewControllers(registry);
    }


    //302跳转
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/").setViewName("redirect:index.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        super.addViewControllers(registry);
//    }


//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/index");
//        registry.addViewController("/user");
//        registry.addRedirectViewController("/index","/templates/index.html");
//        registry.addRedirectViewController("/","/templates/login.html");
//        registry.addStatusController("/403", HttpStatus.FORBIDDEN);
//        super.addViewControllers(registry);
//    }

}