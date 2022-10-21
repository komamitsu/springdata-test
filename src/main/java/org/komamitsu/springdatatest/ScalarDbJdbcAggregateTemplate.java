package org.komamitsu.springdatatest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

public class ScalarDbJdbcAggregateTemplate extends JdbcAggregateTemplate {
    public ScalarDbJdbcAggregateTemplate(ApplicationContext publisher, RelationalMappingContext context, JdbcConverter converter, DataAccessStrategy dataAccessStrategy) {
        super(publisher, context, converter, dataAccessStrategy);
    }

    public ScalarDbJdbcAggregateTemplate(ApplicationEventPublisher publisher, RelationalMappingContext context, JdbcConverter converter, DataAccessStrategy dataAccessStrategy) {
        super(publisher, context, converter, dataAccessStrategy);
    }

    @Override
    public <T> T save(T instance) {
        // TODO: Throw an exception if `instance` doesn't have ID
        return super.save(instance);
    }

    @Override
    public <T> T insert(T instance) {
        return super.insert(instance);
    }

    @Override
    public <T> T update(T instance) {
        return super.update(instance);
    }

    @Override
    public long count(Class<?> domainType) {
        long result = 0;
        Iterator<?> iterator = super.findAll(domainType).iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        return result;
    }

    @Override
    @Transactional
    public <T> T findById(Object id, Class<T> domainType) {
        return super.findById(id, domainType);
    }

    @Override
    @Transactional
    public <T> boolean existsById(Object id, Class<T> domainType) {
        return super.existsById(id, domainType);
    }

    @Override
    @Transactional
    public <T> Iterable<T> findAll(Class<T> domainType, Sort sort) {
        // TODO: Need to do something?
        return super.findAll(domainType, sort);
    }

    @Override
    @Transactional
    public <T> Page<T> findAll(Class<T> domainType, Pageable pageable) {
        // TODO: Need to do something?
        return super.findAll(domainType, pageable);
    }

    @Override
    @Transactional
    public <T> Iterable<T> findAll(Class<T> domainType) {
        // TODO: Need to do something?
        return super.findAll(domainType);
    }

    @Override
    @Transactional
    public <T> Iterable<T> findAllById(Iterable<?> ids, Class<T> domainType) {
        return super.findAllById(ids, domainType);
    }

    @Override
    public <S> void delete(S aggregateRoot, Class<S> domainType) {
        super.delete(aggregateRoot, domainType);
    }

    @Override
    public <S> void deleteById(Object id, Class<S> domainType) {
        super.deleteById(id, domainType);
    }

    @Override
    public void deleteAll(Class<?> domainType) {
        throw new UnsupportedOperationException("This operation isn't supported in Scalar DB SQL: deleteAll");
    }
}
