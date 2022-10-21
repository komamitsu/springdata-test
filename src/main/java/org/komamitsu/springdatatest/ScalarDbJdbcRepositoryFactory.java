package org.komamitsu.springdatatest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactory;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

public class ScalarDbJdbcRepositoryFactory extends JdbcRepositoryFactory {
    private final ApplicationEventPublisher publisher;
    private final RelationalMappingContext context;
    private final JdbcConverter converter;
    private final DataAccessStrategy accessStrategy;

    /**
     * Creates a new {@link JdbcRepositoryFactory} for the given {@link DataAccessStrategy},
     * {@link RelationalMappingContext} and {@link ApplicationEventPublisher}.
     *
     * @param dataAccessStrategy must not be {@literal null}.
     * @param context            must not be {@literal null}.
     * @param converter          must not be {@literal null}.
     * @param dialect            must not be {@literal null}.
     * @param publisher          must not be {@literal null}.
     * @param operations         must not be {@literal null}.
     */
    public ScalarDbJdbcRepositoryFactory(DataAccessStrategy dataAccessStrategy, RelationalMappingContext context, JdbcConverter converter, Dialect dialect, ApplicationEventPublisher publisher, NamedParameterJdbcOperations operations) {
        super(dataAccessStrategy, context, converter, dialect, publisher, operations);
        this.publisher = publisher;
        this.context = context;
        this.converter = converter;
        this.accessStrategy = dataAccessStrategy;
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation repositoryInformation) {

        JdbcAggregateTemplate template = new ScalarDbJdbcAggregateTemplate(publisher, context, converter, accessStrategy);

        /* FIXME
        if (entityCallbacks != null) {
            template.setEntityCallbacks(entityCallbacks);
        }
         */

        RelationalPersistentEntity<?> persistentEntity = context
                .getRequiredPersistentEntity(repositoryInformation.getDomainType());

        return getTargetRepositoryViaReflection(repositoryInformation, template, persistentEntity,
                converter);
    }
}
