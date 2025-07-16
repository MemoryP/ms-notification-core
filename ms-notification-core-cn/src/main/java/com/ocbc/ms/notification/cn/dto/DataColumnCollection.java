package com.ocbc.ms.notification.cn.dto;

import com.ocbc.ms.notification.cn.DataTableException;
import com.ocbc.ms.notification.cn.constants.DataType;
import com.ocbc.ms.notification.cn.util.StringUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DataColumnCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    private LinkedHashMap<String, DataColumn> nameMap = new LinkedHashMap();
    private List<DataColumn> columns = new Vector();

    public DataColumnCollection() {
    }

    protected boolean addColumn(DataColumn column) {
        if (!this.nameMap.containsKey(column.getColumnName().toLowerCase())) {
            column.setColumnIndex(this.columns.size());
            if (StringUtil.isEmpty(column.getStoreName())) {
                column.setStoreName(column.getColumnName());
            }

            boolean res = this.columns.add(column);
            this.nameMap.put(column.getColumnName().toLowerCase(), column);
            return res;
        } else {
            return false;
        }
    }

    protected boolean addColumn(int index, DataColumn column) {
        if (this.nameMap.containsKey(column.getColumnName().toLowerCase())) {
            return false;
        } else {
            this.columns.add(index, column);
            column.setColumnIndex(index);
            column.setStoreName(column.getColumnName());
            this.nameMap.put(column.getColumnName().toLowerCase(), column);

            for(int i = index + 1; i < this.columns.size(); ++i) {
                DataColumn dataColumn = (DataColumn)this.columns.get(i);
                dataColumn.setColumnIndex(dataColumn.getColumnIndex() + 1);
                this.columns.set(i, dataColumn);
            }

            return true;
        }
    }

    protected DataColumn addColumn(String columnName, DataType dataType) throws DataTableException {
        if (this.contains(columnName)) {
            throw new DataTableException("指定的列名[" + columnName + "]已存在！");
        } else {
            DataColumn col = new DataColumn(columnName, dataType);
            return this.addColumn(col) ? col : null;
        }
    }

    protected DataColumn addColumn(int index, String columnName, DataType dataType) throws DataTableException {
        if (this.contains(columnName)) {
            throw new DataTableException("指定的列名[" + columnName + "]已存在！");
        } else {
            DataColumn col = new DataColumn(columnName, dataType);
            return this.addColumn(index, col) ? col : null;
        }
    }

    protected boolean remove(DataColumn column) {
        boolean res = false;
        if (this.nameMap.containsKey(column.getColumnName().toLowerCase())) {
            res = this.columns.remove(column);
            this.nameMap.remove(column.getColumnName().toLowerCase());
        }

        return res;
    }

    protected DataColumn remove(int index) {
        DataColumn column = this.get(index);
        if (this.nameMap.containsKey(column.getColumnName().toLowerCase())) {
            this.nameMap.remove(column.getColumnName().toLowerCase());
            return (DataColumn)this.columns.remove(index);
        } else {
            return null;
        }
    }

    protected DataColumn remove(String columnName) {
        int tempIndex = this.getColumnIndex(columnName.toLowerCase());
        return tempIndex > -1 ? this.remove(tempIndex) : null;
    }

    protected void clear() {
        this.columns.clear();
        this.nameMap.clear();
    }

    protected boolean contains(String columnName) {
        return this.nameMap.containsKey(columnName.toLowerCase());
    }

    protected DataColumn get(int index) {
        return (DataColumn)this.columns.get(index);
    }

    protected DataColumn get(String columnName) {
        return this.nameMap.containsKey(columnName.toLowerCase()) ? (DataColumn)this.nameMap.get(columnName.toLowerCase()) : null;
    }

    protected int getColumnIndex(String columnName) {
        if (this.nameMap.containsKey(columnName.toLowerCase().trim())) {
            DataColumn column = (DataColumn)this.nameMap.get(columnName.toLowerCase());
            return column.getColumnIndex();
        } else {
            return -1;
        }
    }

    public List<DataColumn> getColumns() {
        return this.columns;
    }

    public void setColumns(List<DataColumn> columns) {
        this.columns = columns;
        Iterator var2 = columns.iterator();

        while(var2.hasNext()) {
            DataColumn column = (DataColumn)var2.next();
            this.nameMap.put(column.getColumnName().toLowerCase(), column);
        }

    }

    protected int size() {
        return this.columns.size();
    }

    protected Map<String, DataColumn> columnNames() {
        return this.nameMap;
    }

    protected void resetAllColumnName(Map<String, String> resetMapping) {
        Map<String, DataColumn> names = new HashMap();

        DataColumn col;
        for(Iterator var3 = this.columns.iterator(); var3.hasNext(); names.put(col.getColumnName().toLowerCase(), col)) {
            col = (DataColumn)var3.next();
            if (resetMapping.containsKey(col.getColumnName())) {
                col.setColumnName((String)resetMapping.get(col.getColumnName()));
            }
        }

        this.nameMap.clear();
        this.nameMap.putAll(names);
    }
}
