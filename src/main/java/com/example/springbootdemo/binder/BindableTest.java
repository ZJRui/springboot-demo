package com.example.springbootdemo.binder;

import com.example.springbootdemo.SpringbootDemoApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class BindableTest {


    public void test(){
        ConfigurableApplicationContext run =null;
        //要为Binder对象提供 PropertySource
        Binder binder = Binder.get(run.getEnvironment());
        CompanyProperties companyProperties = binder.bind("kfit.company", Bindable.of(CompanyProperties.class)).get();
        System.out.println(companyProperties);


        String[] args=null;
        ConfigurableApplicationContext context = new SpringApplicationBuilder(BindableTest.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .properties("user.city.postCode=0731")
                .run(args);
        ConfigurableEnvironment environment = context.getEnvironment();
        // 从 Environment 中获取 ConfigurationPropertySource 集合
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(environment);
        // 构造 Binder 对象，并使用 ConfigurationPropertySource 集合作为配置源 要为Binder对象提供 PropertySource
        Binder binder2 = new Binder(sources);
        // 构造 ConfigurationPropertyName（Spring Boot 2.0 API）
        ConfigurationPropertyName propertyName = ConfigurationPropertyName.of("user.city.post-code");
        // 构造 Bindable 对象，包装 postCode
        Bindable<String> postCodeBindable = Bindable.of(String.class);
        BindResult<String> result = binder2.bind(propertyName, postCodeBindable);
        String postCode = result.get();
        System.out.println("postCode = " + postCode);
        // 关闭上下文
        context.close();
    }
}
