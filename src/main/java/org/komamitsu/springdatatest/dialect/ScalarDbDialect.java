package org.komamitsu.springdatatest.dialect;

import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.render.SelectRenderContext;

public class ScalarDbDialect implements Dialect {
    @Override
    public LimitClause limit() {
        throw new RuntimeException("Not supported");
    }

    @Override
    public LockClause lock() {
        throw new RuntimeException("Not supported");
    }

    @Override
    public SelectRenderContext getSelectContext() {
        throw new RuntimeException("Not supported");
    }
}