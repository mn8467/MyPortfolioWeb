package Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseMybatisConfig {
    @Bean
    public DataSource batisDataSource() {
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


    @Bean(name= "batisSqlSessionFactory")
    public SqlSessionFactory batisSqlSessionFactory(@Qualifier("batisDataSource") DataSource batisDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
        sqlSession.setDataSource(batisDataSource);
        sqlSession.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml")); //mybatis-config.xml의 경로
        sqlSession.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*mapper.xml")); //쿼리문을 관리하는 mapper파일의 경로

        return sqlSession.getObject();

    }

    @Bean(name = "batisSqlSessionTemplate")
    public SqlSessionTemplate batisSqlSessionTemplate(SqlSessionFactory batisSqlSessionFactory) throws Exception {

        return new SqlSessionTemplate(batisSqlSessionFactory);
    }

}
