package org.komamitsu.springdatatest;

import org.springframework.context.ApplicationContext;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.data.jdbc.core.convert.DataAccessStrategy;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.mapping.IdentifierAccessor;
import org.springframework.data.mapping.PersistentPropertyPath;
import org.springframework.data.relational.core.mapping.PersistentPropertyPathExtension;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

public class ScalarDbJdbcAggregateTemplate extends JdbcAggregateTemplate {
    private final DataAccessStrategy dataAccessStrategy;
    private final RelationalMappingContext context;

    public ScalarDbJdbcAggregateTemplate(ApplicationContext publisher, RelationalMappingContext context, JdbcConverter converter, DataAccessStrategy dataAccessStrategy) {
        super(publisher, context, converter, dataAccessStrategy);
        this.context = context;
        this.dataAccessStrategy = dataAccessStrategy;
    }

    @Override
    public <T> T save(T instance) {
        Class<?> type = instance.getClass();

        IdentifierAccessor identifierAccessor = context.getRequiredPersistentEntity(type)
                .getIdentifierAccessor(instance);

        RelationalPersistentEntity<T> persistentEntity = (RelationalPersistentEntity<T>) context.getRequiredPersistentEntity(type);

        context.findPersistentPropertyPaths(type, property -> true) //
//                .filter(PersistentPropertyPathExtension::isWritable) //
                .forEach(x -> {
                    System.out.println(x.getParentPath());
                    System.out.println(x.getBaseProperty());
                    System.out.println(x.getLeafProperty());
                    System.out.println(x.getRequiredLeafProperty());
                });

        return super.save(instance);
    }
}
