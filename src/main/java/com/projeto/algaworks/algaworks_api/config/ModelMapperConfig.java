package com.projeto.algaworks.algaworks_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    /**
     * Configuro a classe ModelMapper para que o Spring reconheça e consiga usar seus métodos
     */
    public ModelMapper modelMapper () {
        return new ModelMapper() ;
    }

}
