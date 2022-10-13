package org.komamitsu.springdatatest;

import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

// This project uses `resources/application.properties` instead
// @Configuration
// @EnableJdbcRepositories
public class JdbcConfig extends AbstractJdbcConfiguration {
    /*
    @Bean
    public DataSource scalardbDataSource() throws IOException {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("com.scalar.db.sql.jdbc.SqlJdbcDriver");
        builder.url("jdbc:scalardb:" + new ClassPathResource("scalardb.properties").getURI().getPath());
        return builder.build();
    }
     */
}
