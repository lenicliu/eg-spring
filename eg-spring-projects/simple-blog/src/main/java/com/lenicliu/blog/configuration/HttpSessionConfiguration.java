package com.lenicliu.blog.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * Created by lenicliu on 11/10/16.
 */
@Configuration
@EnableJdbcHttpSession
public class HttpSessionConfiguration {
}
