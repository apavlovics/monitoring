package lv.continuum.monitoring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoringConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
