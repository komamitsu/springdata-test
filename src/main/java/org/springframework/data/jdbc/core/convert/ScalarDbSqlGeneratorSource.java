package org.springframework.data.jdbc.core.convert;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.jdbc.core.convert.SqlGeneratorSource;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Configuration
public class ScalarDbSqlGeneratorSource extends SqlGeneratorSource {
    public ScalarDbSqlGeneratorSource(RelationalMappingContext context, JdbcConverter converter, Dialect dialect) {
        super(context, converter, dialect);
    }

    @Override
    SqlGenerator getSqlGenerator(Class<?> domainType) {
        return super.getSqlGenerator(domainType);
    }
}
