package com.ocbc.ms.notification.cn.dto;

import com.ocbc.ms.notification.cn.constants.DataType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DataColumn implements Serializable {
    private static final long serialVersionUID = 1L;
    private String columnName;
    private String storeName;
    private int columnIndex;
    private DataType dataType;

    public DataColumn() {
    }

    public DataColumn(String columnName) {
        this(columnName, DataType.TString);
    }

    public DataColumn(String columnName, DataType dataType) {
        this.setDataType(dataType);
        this.columnName = columnName;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public int getColumnIndex() {
        return this.columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Object convertTo(Object value) {
        Object objValue = null;
        switch(this.dataType) {
            case TString:
                objValue = TypeConverter.convert(value, String.class);
                break;
            case TBoolean:
                objValue = TypeConverter.convert(value, Boolean.class);
                break;
            case TShort:
                objValue = TypeConverter.convert(value, Short.class);
                break;
            case TInteger:
                objValue = TypeConverter.convert(value, Integer.class);
                break;
            case TLong:
                objValue = TypeConverter.convert(value, Long.class);
                break;
            case TFloat:
                objValue = TypeConverter.convert(value, Float.class);
                break;
            case TDouble:
                objValue = TypeConverter.convert(value, Double.class);
                break;
            case TDate:
                objValue = TypeConverter.convert(value, Date.class);
                break;
            case TBigDecimal:
                objValue = TypeConverter.convert(value, BigDecimal.class);
                break;
            case TDataTable:
                objValue = TypeConverter.convert(value, DataTable.class);
        }

        return objValue;
    }
}
