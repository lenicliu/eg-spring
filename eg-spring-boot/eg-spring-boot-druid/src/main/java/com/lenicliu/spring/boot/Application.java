package com.lenicliu.spring.boot;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by lenicliu on 11/10/16.
 */
@SpringBootApplication
@ImportResource("classpath:spring-boot-context.xml")
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Autowired
    private DataSource dataSource;

    @Bean(name = "druidStatView")
    public ServletRegistrationBean druidStatView() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        return bean;
    }

    @Bean(name = "druidWebStatFilter")
    public FilterRegistrationBean druidWebStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.addUrlPatterns("/*");
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        bean.addInitParameter("profileEnable", "true");
        bean.addInitParameter("sessionStatEnable", "true");
        return bean;
    }

    @Bean(name = "importDatabase")
    public CommandLineRunner importDatabase() {
        return (args) -> {
            try (Connection connection = dataSource.getConnection()) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/schema.sql"));
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/data.sql"));
            } catch (Exception e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    static class WebController {
        @Autowired
        private JdbcTemplate jdbcTemplate;

        @GetMapping(value = {"/", "/index"})
        public List<Map<String, Object>> index() {
            return jdbcTemplate.queryForList("SELECT * FROM MESSAGE");
        }

    }
}
