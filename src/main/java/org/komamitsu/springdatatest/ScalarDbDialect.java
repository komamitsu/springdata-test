package org.komamitsu.springdatatest;

import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.LimitClause;
import org.springframework.data.relational.core.dialect.LockClause;
import org.springframework.data.relational.core.sql.render.SelectRenderContext;

public class ScalarDbDialect implements Dialect {
    @Override
    public LimitClause limit() {
        return null;
    }

    @Override
    public LockClause lock() {
        return null;
    }

    @Override
    public SelectRenderContext getSelectContext() {
        return null;
    }
}