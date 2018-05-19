package com.lenicliu.spring.boot;

import org.aopalliance.intercept.MethodInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@EnableTransactionManagement
@MapperScan("com.lenicliu.spring.boot.mybatis.mapper")
@EnableJpaRepositories(repositoryFactoryBeanClass = RoutingConfiguration.RoutingRepositoryFactoryBean.class)
public class RoutingConfiguration {
    @Bean
    DataSource dataSource() {
        return new AbstractRoutingDataSource() {
            @Autowired
            private DataSourceRouter router;

            @Override
            public void afterPropertiesSet() {
                Map<Object, Object> targetDataSources = new LinkedHashMap<>();
                targetDataSources.put("app", new EmbeddedDatabaseBuilder()
                    .setName("app").setType(H2).addScript("classpath:sql/app.sql").build()
                );
                targetDataSources.put("log", new EmbeddedDatabaseBuilder()
                    .setName("log").setType(H2).addScript("classpath:sql/log.sql").build()
                );
                this.setDefaultTargetDataSource(new EmbeddedDatabaseBuilder().setName("def").setType(H2).build());
                this.setTargetDataSources(targetDataSources);
                super.afterPropertiesSet();
            }

            @Override
            protected Object determineCurrentLookupKey() {
                return router.getCurrentKey();
            }
        };
    }

    static class RoutingRepositoryFactoryBean extends JpaRepositoryFactoryBean {
        public RoutingRepositoryFactoryBean(Class repositoryInterface) {
            super(repositoryInterface);
        }

        @Autowired
        DataSourceRouter router;

        @Override
        protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
            RepositoryFactorySupport repositoryFactory = super.createRepositoryFactory(entityManager);
            repositoryFactory.addRepositoryProxyPostProcessor(
                (factory, information) -> factory.addAdvice((MethodInterceptor)invocation -> {
                    try {
                        router.set(DataSourceRouter.routing(information.getRepositoryInterface()));
                        return invocation.proceed();
                    } finally {
                        router.remove();
                    }
                })
            );
            return repositoryFactory;
        }
    }
}
