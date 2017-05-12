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
	      dataSource.setUrl("jdbc:sqlserver://mssql5.gear.host;databaseName=stcomm1;username=stcomm1;password=Ts9j?c0StAj-;");
	      return new JdbcTemplate(dataSource);
	   }
}
