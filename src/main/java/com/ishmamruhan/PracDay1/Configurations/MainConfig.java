package com.ishmamruhan.PracDay1.Configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "School System Api", description = "Student & Teacher & Course Api Available"))
public class MainConfig {
}
