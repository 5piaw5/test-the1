package com.example.TestTheOne.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="spring.demo")
@Configuration
@Getter
@Setter
public class UserAPIProperties {

    private  String url;

}
