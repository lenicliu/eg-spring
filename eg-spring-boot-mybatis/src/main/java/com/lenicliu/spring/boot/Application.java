package com.lenicliu.spring.boot;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.lenicliu.spring.boot.domain.Message;
import com.lenicliu.spring.boot.mapper.MessageMapper;

@SpringBootApplication
@MapperScan("com.lenicliu.spring.boot.mapper")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.H2);
		builder.continueOnError(false);
		builder.setName("springboot");
		builder.setScriptEncoding("UTF-8");
		builder.setSeparator(";");
		builder.addScript("classpath:/sql/schema.sql");
		builder.addScript("classpath:/sql/data.sql");
		return builder.build();
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.lenicliu.spring.boot.domain");
		return sessionFactory.getObject();
	}

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public void run(String... args) throws Exception {
		Message message = messageMapper.findMessage(new Long(1));
		System.out.println(message);
	}
}