package Config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DBConfig {
	 @Bean 
	   public JdbcTemplate initJdbcTemplate(){
	      DataSource dataSource = new DataSource();
	      dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	      dataSource.setUrl("jdbc:sqlserver://mssql4.gear.host;databaseName=stcommdb;username=stcommdb;password=Mb3oIjf!~jCk;");
	      return new JdbcTemplate(dataSource);
	   }
}
