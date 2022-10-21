package org.komamitsu.springdatatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.repository.support.JdbcRepositoryFactoryBean;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.util.Assert;

import java.io.Serializable;

class ScalarDbJdbcRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends JdbcRepositoryFactoryBean {
    private JdbcConverter converter;
    private DataAccessStrategy dataAccessStrategy;
    private RelationalMappingContext context;
    private Dialect dialect;
    private ApplicationEventPublisher publisher;
    private NamedParameterJdbcOperations operations;

    /**
     * Creates a new {@link JdbcRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public ScalarDbJdbcRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        return new ScalarDbJdbcRepositoryFactory(dataAccessStrategy, context, converter, dialect, publisher, operations);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        super.setApplicationEventPublisher(publisher);
        this.publisher = publisher;
    }

    @Autowired
    public void setMappingContext(RelationalMappingContext mappingContext) {
        Assert.notNull(mappingContext, "MappingContext must not be null");
        super.setMappingContext(mappingContext);
        this.context = mappingContext;
    }

    @Autowired
    public void setDialect(Dialect dialect) {
        Assert.notNull(dialect, "Dialect must not be null");
        this.dialect = dialect;
    }

    /**
     * @param dataAccessStrategy can be {@literal null}.
     */
    public void setDataAccessStrategy(DataAccessStrategy dataAccessStrategy) {
        Assert.notNull(dataAccessStrategy, "DataAccessStrategy must not be null");
        this.dataAccessStrategy = dataAccessStrategy;
    }

    public void setJdbcOperations(NamedParameterJdbcOperations operations) {
        Assert.notNull(operations, "NamedParameterJdbcOperations must not be null");
        this.operations = operations;
    }

    @Autowired
    public void setConverter(JdbcConverter converter) {
        Assert.notNull(converter, "JdbcConverter must not be null");
        this.converter = converter;
    }
}
