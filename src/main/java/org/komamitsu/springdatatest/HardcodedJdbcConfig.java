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
    /* FIXME
    Caused by: org.springframework.data.jdbc.repository.config.DialectResolver$NoDialectException: Cannot determine a dialect for org.springframework.jdbc.core.JdbcTemplate@3f6bf8aa. Please provide a Dialect.
	        at org.springframework.data.jdbc.repository.config.DialectResolver.lambda$getDialect$2(DialectResolver.java:82) ~[spring-data-jdbc-2.4.3.jar:2.4.3]
    */
    public Dialect jdbcDialect() {
        return new ScalarDbDialect();
    }
}
