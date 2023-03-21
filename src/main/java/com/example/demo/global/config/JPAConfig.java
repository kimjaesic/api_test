package com.example.demo.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;

@Configuration
public class JPAConfig {
    //== JPA ==//
    private final EntityManager em;

    //== Query DSL ==//
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public JPAConfig(final EntityManager em) {
        this.em = em;
    }
    //</editor-fold>
}
