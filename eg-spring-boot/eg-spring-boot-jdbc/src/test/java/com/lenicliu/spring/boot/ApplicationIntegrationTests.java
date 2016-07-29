package com.lenicliu.spring.boot;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationIntegrationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate() {
        String sql = "SELECT * FROM MESSAGE";
        RowMapper<Message> rowMapper = new BeanPropertyRowMapper<Message>(Message.class);
        List<Message> messages = jdbcTemplate.query(sql, rowMapper);
        Assert.assertTrue(messages.size() == 2);
    }
}
