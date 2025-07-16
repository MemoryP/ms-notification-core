package com.ocbc.ms.notification.cn.dto;

import com.ocbc.ms.notification.cn.constants.DataType;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataRow implements Serializable {
    private static final long serialVersionUID = 1L;
    private int rowIndex = -1;
    private DataColumnReference reference;
    private Map<String, Object> rowDatas = new LinkedHashMap();
    private Map<String, DataTable> tableDatas = new LinkedHashMap();

    public DataRow() {
    }

    public void setColumnValue(DataColumn column, Object value) {
        if (column != null) {
            if (column.getDataType() == DataType.TDataTable) {
                this.tableDatas.put(column.getStoreName(), (DataTable)value);
            } else {
                this.rowDatas.put(column.getStoreName(), column.convertTo(value));
            }
        }

    }

    public void setColumnValue(int index, Object value) {
        this.setColumnValue(this.reference.getColumns().get(index), value);
    }

    public void setColumnValue(String columnName, Object value) {
        this.setColumnValue(this.reference.getColumns().get(columnName), value);
    }

    public <T> T getColumnValue(String columnName, Class<T> classOfT) {
        DataColumn column = this.reference.getColumns().get(columnName);
        if (column != null) {
            Object v;
            if (column.getDataType() == DataType.TDataTable) {
                v = this.tableDatas.get(column.getStoreName());
                return v == null ? null : TypeConverter.convert(v, classOfT);
            } else {
                v = this.rowDatas.get(column.getStoreName());
                return v == null ? null : TypeConverter.convert(v, classOfT);
            }
        } else {
            return null;
        }
    }

    public <T> T getColumnValue(int index, Class<T> classOfT) {
        return this.getColumnValue(this.reference.getColumns().get(index).getColumnName(), classOfT);
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setReferance(DataColumnReference reference) {
        this.reference = reference;
    }

    public Map<String, Object> getRowDatas() {
        return this.rowDatas;
    }

    public void setRowDatas(Map<String, Object> rowDatas) {
        this.rowDatas = rowDatas;
    }

    public Map<String, DataTable> getTableDatas() {
        return this.tableDatas;
    }

    public void setTableDatas(Map<String, DataTable> tableDatas) {
        this.tableDatas = tableDatas;
    }

    public List<DataColumn> columns() {
        return this.reference.getColumns().getColumns();
    }
}
