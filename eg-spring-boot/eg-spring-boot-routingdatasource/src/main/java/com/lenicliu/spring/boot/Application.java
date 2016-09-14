package com.lenicliu.spring.boot;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class Application {
    static final ThreadLocal<DB> SWITCH = new ThreadLocal<>();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/persons")
    public List<Map<String, Object>> persons() {
        SWITCH.set(DB.APP);
        return jdbcTemplate.queryForList("SELECT * FROM TB_PERSON");
    }

    @GetMapping("/logs")
    public List<Map<String, Object>> logs() {
        SWITCH.set(DB.LOG);
        return jdbcTemplate.queryForList("SELECT * FROM TB_LOG");
    }

    @Bean
    @ConfigurationProperties(prefix = "druid.app")
    public DataSource app() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "druid.log")
    public DataSource log() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DataSource primary() {
        return new RoutingDataSource();
    }

    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            SWITCH.set(DB.APP);
            jdbcTemplate.update("CREATE TABLE IF NOT EXISTS TB_PERSON(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20))");
            jdbcTemplate.update("INSERT INTO TB_PERSON(NAME)VALUES('lenicliu')");
            jdbcTemplate.update("INSERT INTO TB_PERSON(NAME)VALUES('richard')");
            SWITCH.set(DB.LOG);
            jdbcTemplate.update("CREATE TABLE IF NOT EXISTS TB_LOG(ID INT PRIMARY KEY AUTO_INCREMENT, CONTENT VARCHAR(20))");
            jdbcTemplate.update("INSERT INTO TB_LOG(CONTENT)VALUES('someone do sth.')");
        };
    }

    @Bean
    public HealthIndicator dbs(DataSource dataSource) {
        if (dataSource instanceof RoutingDataSource) {
            final RoutingDataSource routingDataSource = (RoutingDataSource) dataSource;
            return new AbstractHealthIndicator() {
                @Override
                protected void doHealthCheck(Health.Builder healthBuilder) throws Exception {
                    Field field = ReflectionUtils.findField(RoutingDataSource.class, "resolvedDataSources");
                    field.setAccessible(true);
                    Map<Object, DataSource> dbs = (Map<Object, DataSource>) ReflectionUtils.getField(field, routingDataSource);
                    Health.Builder up = healthBuilder.up();
                    for (Object key : dbs.keySet()) {
                        DruidDataSource druidDataSource = (DruidDataSource) dbs.get(key);
                        up.withDetail(key + " : active", druidDataSource.getActiveCount())
                                .withDetail(key + " : max_active", druidDataSource.getMaxActive())
                                .withDetail(key + " : max_idle", druidDataSource.getMaxIdle())
                                .withDetail(key + " : min_idle", druidDataSource.getMinIdle())
                                .withDetail(key + " : max_wait", druidDataSource.getMaxWait());
                    }
                }
            };
        }
        return null;
    }

    public enum DB {
        APP, LOG;
    }

    class RoutingDataSource extends AbstractRoutingDataSource {
        RoutingDataSource() {
            Map<Object, Object> target = new HashMap<>();
            target.put(DB.APP, app());
            target.put(DB.LOG, log());
            this.setTargetDataSources(target);
            this.setDefaultTargetDataSource(app());
        }

        @Override
        protected Object determineCurrentLookupKey() {
            return SWITCH.get();
        }
    }
}