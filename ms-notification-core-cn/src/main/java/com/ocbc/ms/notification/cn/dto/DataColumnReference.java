package com.ocbc.ms.notification.cn.dto;

import java.io.Serializable;

public class DataColumnReference implements Serializable {
    private static final long serialVersionUID = 1L;
    private DataColumnCollection columns;

    public DataColumnReference() {
    }

    public DataColumnReference(DataColumnCollection columns) {
        this.columns = columns;
    }

    public DataColumnCollection getColumns() {
        return this.columns;
    }

    public void setColumns(DataColumnCollection columns) {
        this.columns = columns;
    }
}
