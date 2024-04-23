package com.clinica.clinicaveterinaria.util;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;


@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer addCustomHibernate5Module() {
    return jacksonObjectMapperBuilder ->
        jacksonObjectMapperBuilder.modules(new Hibernate5Module());
  }
}
