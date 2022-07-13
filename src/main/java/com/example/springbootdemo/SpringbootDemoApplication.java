package com.example.springbootdemo;

import com.example.springbootdemo.binder.CompanyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootDemoApplication.class, args);
        Binder binder = Binder.get(run.getEnvironment());
        CompanyProperties companyProperties = binder.bind("kfit.company", Bindable.of(CompanyProperties.class)).get();
        System.out.println(companyProperties);
    }

}
