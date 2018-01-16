package mvvm.sample.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder builder = DataSourceBuilder.create();
		builder.driverClassName("org.sqlite.JDBC");
		builder.url("jdbc:sqlite:src/main/resources/sqlite/sample.db");
		return builder.build();   
	}
}