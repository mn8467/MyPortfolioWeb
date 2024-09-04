package Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
//configuration annotation
@EnableJpaRepositories(
        basePackages="com.example.toyproject1_wst",  //repository를 관리할 패키지 명시
        entityManagerFactoryRef = "entityManagerFactory", //EntityManagerFactory
        transactionManagerRef = "transactionManager") // transactionManager
public class DatabaseConfig {
    private static final String DEFAULT_NAMING_STRATEGY
            = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";

    @Bean
    @Primary //해당 Bean을 우선적으로 선택하도록 하는 annotation
    public DataSource defaultDataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setJdbcUrl("jdbc:mysql://13.125.104.251:3306/projectWST");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("gimpo123");
        dataSourceConfig.setMaximumPoolSize(500);
        dataSourceConfig.setMinimumIdle(5);
        dataSourceConfig.setMaxLifetime(1800000);
        dataSourceConfig.setConnectionTimeout(5000	);
        dataSourceConfig.setIdleTimeout(600000);

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, String> propertiesHashMap = new HashMap<>();
        propertiesHashMap.put("hibernate.physical_naming_strategy",DEFAULT_NAMING_STRATEGY);

        LocalContainerEntityManagerFactoryBean rep =
                builder.dataSource(defaultDataSource())
                        .packages("com.example.toyproject1_wst.Model")
                        //domain을 관리할 패키지 경로 명시 (domain = DO 파일)
                        .properties(propertiesHashMap)
                        .build();

        return rep;
    }

    @Primary
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

}
