package com.ocbc.ms.notification.cn.dto;

import com.ocbc.ms.notification.cn.DataTableException;
import com.ocbc.ms.notification.cn.constants.DataType;
import com.ocbc.ms.notification.cn.util.StringUtil;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private DataColumnCollection columns;
    private String tableName;
    private int printDataMaxRowCount;
    private List<DataRow> rows;
    private DataColumnReference reference;

    public DataTable() {
        this.rows = new ArrayList();
        this.columns = new DataColumnCollection();
        this.printDataMaxRowCount = 50;
        this.reference = new DataColumnReference(this.columns);
    }

    public DataTable(String dataTableName) {
        this();
        this.tableName = dataTableName;
    }

    public void resetAllColumnName(Map<String, String> resetMapping) {
        this.columns.resetAllColumnName(resetMapping);
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DataColumn addColumn(String columnName, DataType dataType) throws DataTableException {
        return this.columns.addColumn(columnName, dataType);
    }

    public DataColumn addColumnIndex(int index, String columnName, DataType dataType) throws DataTableException {
        return this.columns.addColumn(index, columnName, dataType);
    }

    public DataRow newRow() {
        DataRow tempRow = new DataRow();
        int lastRowIndex = -1;
        if (!this.rows.isEmpty()) {
            lastRowIndex = ((DataRow)this.rows.get(this.rows.size() - 1)).getRowIndex();
        } else {
            lastRowIndex = 0;
        }

        tempRow.setReferance(this.reference);
        ++lastRowIndex;
        tempRow.setRowIndex(lastRowIndex);
        return tempRow;
    }

    public boolean addRow(DataRow row) {
        if (!this.rows.isEmpty()) {
            row.setRowIndex(((DataRow)this.rows.get(this.rows.size() - 1)).getRowIndex() + 1);
        } else {
            row.setRowIndex(1);
        }

        return this.rows.add(row);
    }

    public int getTotalCount() {
        return this.rows.size();
    }

    public DataRow getRow(int rowIndex) {
        return (DataRow)this.rows.get(rowIndex);
    }

    public List<DataRow> getRows() {
        return this.rows;
    }

    public void setRows(List<DataRow> rows) {
        this.rows = rows;
        Iterator var2 = this.rows.iterator();

        while(var2.hasNext()) {
            DataRow row = (DataRow)var2.next();
            row.setReferance(this.reference);
        }

    }

    public List<DataColumn> getColumns() {
        return this.columns.getColumns();
    }

    public void setColumns(List<DataColumn> columns) {
        Iterator var2 = columns.iterator();

        while(var2.hasNext()) {
            DataColumn column = (DataColumn)var2.next();
            this.columns.addColumn(column);
        }

    }

    public Map<String, DataColumn> columnNames() {
        return this.columns.columnNames();
    }

    public DataTable cloneTable() throws DataTableException {
        DataTable table = new DataTable();
        table.setTableName(this.getTableName());
        Iterator var2 = this.getColumns().iterator();

        while(var2.hasNext()) {
            DataColumn dc = (DataColumn)var2.next();
            table.addColumn(dc.getColumnName(), dc.getDataType());
        }

        return table;
    }

    public void fillDataTable(ResultSet rs) throws DataTableException, SQLException {
        this.fillDataTable(rs, -1, 2147483647);
    }

    public void fillDataTable(ResultSet rs, int count) throws DataTableException, SQLException {
        this.fillDataTable(rs, -1, count);
    }

    public void fillDataTable(ResultSet rs, int startIndex, int count) throws DataTableException, SQLException {
        if (rs != null) {
            this.columns.clear();
            this.rows.clear();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            int[] dataBaseTypes = new int[columnCount];

            int temp;
            for(temp = 1; temp <= columnCount; ++temp) {
                int jdbcType = md.getColumnType(temp);
                String columnName = "";
                if (md.getColumnLabel(temp).isEmpty()) {
                    columnName = md.getColumnName(temp).trim();
                } else {
                    columnName = md.getColumnLabel(temp).trim();
                }

                dataBaseTypes[temp - 1] = jdbcType;
                this.addColumn(columnName.toLowerCase(), this.getDataTypeFromJdbcType(jdbcType));
            }

            temp = 0;
            if (startIndex > 1) {
                if (!rs.absolute(startIndex)) {
                    return;
                }

                if (!rs.next()) {
                    return;
                }
            } else if (startIndex < 0) {
                if (!rs.next()) {
                    return;
                }
            } else if (!rs.first()) {
                return;
            }

            while(temp < count) {
                ++temp;
                DataRow row = this.newRow();

                for(int i = 0; i < columnCount; ++i) {
                    Object obj1 = null;
                    switch(dataBaseTypes[i]) {
                        case 2:
                            BigDecimal bigDecimal = rs.getBigDecimal(i + 1);
                            if (bigDecimal != null) {
                                int scale = rs.getMetaData().getScale(i + 1);
                                int bigScale = rs.getBigDecimal(i + 1).scale();
                                int realscale = scale > bigScale ? scale : bigScale;
                                if (realscale > 0) {
                                    obj1 = rs.getBigDecimal(i + 1).setScale(realscale);
                                } else {
                                    obj1 = rs.getLong(i + 1);
                                }
                            } else {
                                obj1 = bigDecimal;
                            }
                            break;
                        case 3:
                            obj1 = rs.getBigDecimal(i + 1);
                            break;
                        case 6:
                        case 7:
                            obj1 = rs.getFloat(i + 1);
                            break;
                        case 8:
                            obj1 = rs.getDouble(i + 1);
                            break;
                        case 91:
                            obj1 = rs.getDate(i + 1);
                            break;
                        case 92:
                            obj1 = rs.getTime(i + 1);
                            break;
                        case 93:
                            obj1 = rs.getTimestamp(i + 1);
                            break;
                        case 2004:
                            Blob tempBlob = rs.getBlob(i + 1);
                            if (tempBlob != null) {
                                long length = tempBlob.length();
                                obj1 = tempBlob.getBytes(1L, (int)length);
                            }
                            break;
                        case 2005:
                            Clob tempClob = rs.getClob(i + 1);
                            if (tempClob != null) {
                                long length = tempClob.length();
                                obj1 = tempClob.getSubString(1L, (int)length);
                            }
                            break;
                        default:
                            obj1 = rs.getObject(i + 1);
                    }

                    row.setColumnValue(i, obj1);
                }

                this.addRow(row);
                if (!rs.next()) {
                    break;
                }
            }

        }
    }

    public int getPrintDataMaxRowCount() {
        return this.printDataMaxRowCount;
    }

    public void setPrintDataMaxRowCount(int maxRowCount) {
        this.printDataMaxRowCount = maxRowCount;
    }

    public String printDataToString(int printMaxRowCount) {
        return this.printData((DataTable)null, printMaxRowCount, (StringBuilder)null, (File)null);
    }

    public void printDataToFile(File file) {
        this.printData((DataTable)null, 0, (StringBuilder)null, file);
    }

    public void printDataToFile(int printMaxRowCount, File file) {
        this.printData((DataTable)null, printMaxRowCount, (StringBuilder)null, file);
    }

    private String printData(DataTable dataTable, int printMaxRowCount, StringBuilder printSb, File file) {
        DataTable dt = dataTable == null ? this : dataTable;
        StringBuilder sb = printSb == null ? new StringBuilder() : printSb;
        boolean isFile = file != null;
        boolean isChildDataTable = false;
        String childDataTableColName = "DataTable";

        try {
            int[] maxes = new int[dt.getColumns().size()];
            List<DataRow> rowList = dt.getRows();
            Iterator var12 = rowList.iterator();

            while(var12.hasNext()) {
                DataRow dr = (DataRow)var12.next();
                List<DataColumn> cols = dt.getColumns();

                DataColumn col;
                for(Iterator var15 = cols.iterator(); var15.hasNext(); maxes[col.getColumnIndex()] = Math.max(maxes[col.getColumnIndex()], col.getColumnName().length())) {
                    col = (DataColumn)var15.next();
                    String s = null;
                    if (col.getDataType() == DataType.TDataTable) {
                        s = childDataTableColName;
                        isChildDataTable = true;
                    } else {
                        s = (String)dr.getColumnValue(col.getColumnIndex(), String.class);
                    }

                    if (s != null && !s.isEmpty()) {
                        maxes[col.getColumnIndex()] = Math.max(maxes[col.getColumnIndex()], s.getBytes("GBK").length);
                    }
                }
            }

            if (isFile) {
                sb = new StringBuilder();
            }

            sb.append("\n    | ");
            int idx = 0;
            Iterator var21 = dt.getColumns().iterator();

            while(var21.hasNext()) {
                DataColumn col = (DataColumn)var21.next();
                sb.append(' ');
                sb.append(StringUtil.alignLeft(col.getColumnName(), maxes[idx++], ' ', "GBK"));
                sb.append(" |");
            }

            sb.append("\n    |");

            int maxRow;
            for(maxRow = 0; maxRow < dt.getColumns().size(); ++maxRow) {
                sb.append('-');
                sb.append(StringUtil.dup('-', maxes[maxRow]));
                sb.append("-|");
            }

            maxRow = rowList.size();
            if (printMaxRowCount > 0) {
                maxRow = rowList.size() > printMaxRowCount ? printMaxRowCount : rowList.size();
            }

            if (!isFile && maxRow > dt.getPrintDataMaxRowCount()) {
                maxRow = dt.getPrintDataMaxRowCount();
            }

            int row;
            int colIdx;
            for(row = 0; row < maxRow; ++row) {
                sb.append("\n    |");

                for(colIdx = 0; colIdx < dt.getColumns().size(); ++colIdx) {
                    sb.append(' ');
                    if (((DataColumn)dt.getColumns().get(colIdx)).getDataType() == DataType.TDataTable) {
                        sb.append(StringUtil.alignLeft(childDataTableColName, maxes[colIdx], ' ', "GBK"));
                    } else {
                        sb.append(StringUtil.alignLeft(((DataRow)rowList.get(row)).getColumnValue(colIdx, String.class), maxes[colIdx], ' ', "GBK"));
                    }

                    sb.append(" |");
                }

            }

            if (maxRow != rowList.size()) {
                sb.append("\n    .");

                for(row = 0; row < dt.getColumns().size(); ++row) {
                    sb.append(StringUtil.dup('.', maxes[row] + 3));
                }

                sb.append("\n     !!!Too many data . Only display " + maxRow + " lines.");
                sb.append("\n    .");

                for(row = 0; row < dt.getColumns().size(); ++row) {
                    sb.append(StringUtil.dup('.', maxes[row] + 3));
                }

            }

            if (isChildDataTable) {
                for(row = 0; row < maxRow; ++row) {
                    colIdx = 0;

                    for(Iterator var26 = dt.getColumns().iterator(); var26.hasNext(); ++colIdx) {
                        DataColumn col = (DataColumn)var26.next();
                        if (col.getDataType() == DataType.TDataTable) {
                            DataTable childDt = (DataTable)dt.getRow(row).getColumnValue(colIdx, DataTable.class);
                            if (childDt != null) {
                                sb.append(String.format("%n%n第%d行，第%d列：%s的子表数据：%n", row + 1, colIdx + 1, col.getColumnName()));

                                this.printData(childDt, printMaxRowCount, sb, file);
                            } else {
                                sb.append(String.format("%n%n第%d行，第%d列：%s的子表数据：null %n", row + 1, colIdx + 1, col.getColumnName()));
                            }
                        }
                    }
                }
            }

            return isFile ? null : sb.toString();
        } catch (Exception var19) {
            return "DataTable printDataString error : " + var19.getMessage();
        }
    }

    private DataType getDataTypeFromJdbcType(int jdbcType) {
        switch(jdbcType) {
            case -7:
            case 16:
                return DataType.TBoolean;
            case -6:
                return DataType.TString;
            case -5:
                return DataType.TLong;
            case -4:
            case -3:
            case -2:
            case 2004:
                return DataType.TString;
            case -1:
            case 1:
            case 12:
            case 2005:
                return DataType.TString;
            case 0:
            case 70:
            case 1111:
            case 2000:
            case 2001:
            case 2002:
            case 2003:
            case 2006:
                return DataType.TString;
            case 2:
                return DataType.TBigDecimal;
            case 3:
                return DataType.TBigDecimal;
            case 4:
                return DataType.TInteger;
            case 5:
                return DataType.TShort;
            case 6:
            case 7:
                return DataType.TFloat;
            case 8:
                return DataType.TDouble;
            case 91:
                return DataType.TDate;
            case 92:
                return DataType.TString;
            case 93:
                return DataType.TString;
            default:
                return DataType.TString;
        }
    }
}
