package r.afpa.exercices.spring.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan({"r.afpa.exercices.spring"})
public class AppConfiguration {
	
    public DriverManagerDataSource provideSource() {
        DriverManagerDataSource dataSource = new org.springframework.jdbc.datasource.DriverManagerDataSource();
        //this.dataSource = dataSource;
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("testuser");
        dataSource.setPassword("RjqpoN5oLmiD7nEJ");
        dataSource.setUrl("jdbc:mysql://localhost:3308/feedback?useSSL=false&serverTimezone=UTC");
        return dataSource;
    }

    @Bean(name = "applicationJdbcTemplate")
    public JdbcTemplate applicationDataConnection(){
        return new JdbcTemplate(provideSource());
    }

}
