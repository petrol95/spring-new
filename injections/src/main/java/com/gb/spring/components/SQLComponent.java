package com.gb.spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SQLComponent {
    private SQLHelper sqlHelper;

    public SQLHelper getSqlHelper() {
        return sqlHelper;
    }

    @Autowired
    @Qualifier("mySQLHelper")
    public void setSqlHelper(SQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }
}
