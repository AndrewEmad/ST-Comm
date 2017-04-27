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
	      dataSource.setUrl("jdbc:sqlserver://localhost;databaseName=ST-Comm;integratedSecurity=true;");
	      return new JdbcTemplate(dataSource);
	   }
}
