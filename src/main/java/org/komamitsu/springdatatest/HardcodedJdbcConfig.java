package org.komamitsu.springdatatest;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.relational.core.dialect.Dialect;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class HardcodedJdbcConfig {
    @Bean
    public DataSource scalardbDataSource() throws IOException {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("com.scalar.db.sql.jdbc.SqlJdbcDriver");
        builder.url("jdbc:scalardb:" + new ClassPathResource("scalardb.properties").getURI().getPath());
        return builder.build();
    }

    @Bean
    @Primary
    public Dialect scalardbDialect() {
        return new ScalarDbDialect();
    }

    //    @Bean
    public DataSource postgresqlDataSource() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("org.postgresql.Driver");
        builder.url("jdbc:postgresql://localhost/springdatatest");
        builder.username("springdatauser");
        builder.password("springdatapassword");
        return builder.build();
    }
}
