/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bc5Neptune.cis.gui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author thu.doan
 */
public class RSTableModel extends AbstractTableModel {

    private Vector colHeaders;
    private Vector tbData;

    public RSTableModel(ResultSet rsData) throws SQLException {
        ResultSetMetaData rsMeta = rsData.getMetaData();
        int count = rsMeta.getColumnCount();

        colHeaders = new Vector(count);
        tbData = new Vector();
        for (int i = 1; i <= count; i++) {
            colHeaders.addElement(rsMeta.getColumnName(i));
        }

        while (rsData.next()) {

            Vector dataRow = new Vector(count);

            for (int i = 1; i <= count; i++) {

                dataRow.addElement(rsData.getObject(i));
            }
            tbData.addElement(dataRow);
        }
    }

    public int getColumnCount() {
        return colHeaders.size();
    }

    public int getRowCount() {
        return tbData.size();
    }

    public Object getValueAt(int row, int column) {
        Vector rowData = (Vector) (tbData.elementAt(row));
        return rowData.elementAt(column);
    }

    @Override
    public String getColumnName(int column) {
        return (String) (colHeaders.elementAt(column));
    }
}
