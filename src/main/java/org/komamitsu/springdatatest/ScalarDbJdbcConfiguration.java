package org.komamitsu.springdatatest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.mapping.JdbcMappingContext;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactory;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
// TODO: WIP
// @EnableJdbcRepositories(repositoryFactoryBeanClass = ScalarDbJdbcRepositoryFactoryBean.class)
@EnableJdbcRepositories
public class ScalarDbJdbcConfiguration extends AbstractJdbcConfiguration {
    /*
    @Bean
    public DataSource scalardbDataSource() throws IOException {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.driverClassName("com.scalar.db.sql.jdbc.SqlJdbcDriver");
        builder.url("jdbc:scalardb:" + new ClassPathResource("scalardb.properties").getURI().getPath());
        return builder.build();
    }
     */

	@Bean
	@Primary
	public JdbcAggregateTemplate jdbcAggregateTemplate(ApplicationContext applicationContext,
                                                       JdbcMappingContext mappingContext, JdbcConverter converter, DataAccessStrategy dataAccessStrategy) {
		return new ScalarDbJdbcAggregateTemplate(applicationContext, mappingContext, converter, dataAccessStrategy);
	}

	@Bean
	@Primary
	public JdbcRepositoryFactory jdbcRepositoryFactory(DataAccessStrategy dataAccessStrategy, RelationalMappingContext context, JdbcConverter converter, Dialect dialect, ApplicationEventPublisher publisher, NamedParameterJdbcOperations operations) {
		return new ScalarDbJdbcRepositoryFactory(dataAccessStrategy, context, converter, dialect, publisher, operations);
	}
}
