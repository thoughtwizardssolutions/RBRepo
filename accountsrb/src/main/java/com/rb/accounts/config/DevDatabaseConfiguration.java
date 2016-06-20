package com.rb.accounts.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile(Constants.SPRING_PROFILE_DEVELOPMENT)
@EnableJpaRepositories("com.rb.accounts.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DevDatabaseConfiguration {
    private final Logger log = LoggerFactory.getLogger(DevDatabaseConfiguration.class);

    /*@Bean
    @ConditionalOnExpression("#{environment.acceptsProfiles('" + Constants.SPRING_PROFILE_DEVELOPMENT + "')}")
    public DataSource dataSource() {
        System.out.println("Configuring JDBC datasource for development...");
        log.info("Configuring JDBC datasource for development...");
        //jdbc:hsqldb:mem:testdb        
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
            .setName("accounts")
            .addScript("init.sql")
            .build();
        return db;
    }*/
    
    @Bean
    public HttpMessageConverters customConverters() {
        ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
        return new HttpMessageConverters(arrayHttpMessageConverter);
    }
}
