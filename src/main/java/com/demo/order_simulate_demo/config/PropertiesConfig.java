package com.demo.order_simulate_demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class PropertiesConfig {

    @Value("${config.templates.excel}")
    private String pathExcel;

}
