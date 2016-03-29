package com.lenicliu.spring.boot;

import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;

public class Mybatis2 {
	public static void main(String[] args) throws Exception {

		Class.forName("org.h2.Driver");
		Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false", "sa", "sa");

		ScriptRunner runner = new ScriptRunner(connection);
		runner.runScript(new InputStreamReader(Mybatis2.class.getClassLoader().getResourceAsStream("sql/schema.sql")));
		runner.closeConnection();

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new ClassPathResource("mybatis2.xml").getFile();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
