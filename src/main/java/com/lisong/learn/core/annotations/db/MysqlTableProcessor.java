package com.lisong.learn.core.annotations.db;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.lisong.learn.core.util.Print.print;

public class MysqlTableProcessor extends AbstractTableProcessor {

    @Override
    protected void execute(String sql) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/learn", "lisong", "lisong123")) {
            con.setAutoCommit(false);
            PreparedStatement preStmt = con.prepareStatement(sql);
            preStmt.executeUpdate();
            print("sql completed successfully!");
        }catch(SQLException e) {
            print("sql failed!");
            e.printStackTrace();
        }
    }

    @Override
    public String buildField(Field field) {
        String result = "";
        Annotation[] annotations = field.getDeclaredAnnotations();
        if(annotations.length < 1)
            return result;
        Annotation annotation = annotations[0];
        if(annotation instanceof SQLString) {
            result += ((SQLString) annotation).name().length() < 1 ?
                    field.getName().toUpperCase() : ((SQLString) annotation).name();
            result += " VARCHAR(" + ((SQLString) annotation).value() + ")";
            result += getConstraints(((SQLString) annotation).constraints());
        }else if(annotation instanceof SQLInteger) {
            result += ((SQLInteger) annotation).name().length() < 1 ?
                    field.getName().toUpperCase() : ((SQLInteger) annotation).name();
            result += " INT";
            result += getConstraints(((SQLInteger) annotation).constraints());
        }else if(annotation instanceof SQLDate) {
            result += ((SQLDate) annotation).name().length() < 1 ?
                    field.getName().toUpperCase() : ((SQLDate) annotation).name();
            result += " DATE";
            result += getConstraints(((SQLDate) annotation).constraints());
        }else if(annotation instanceof SQLTimestamp) {
            result += ((SQLTimestamp) annotation).name().length() < 1 ?
                    field.getName().toUpperCase() : ((SQLTimestamp) annotation).name();
            result += " TIMESTAMP";
            result += getConstraints(((SQLTimestamp) annotation).constraints());
        }else if(annotation instanceof SQLFloat) {
            result += ((SQLFloat) annotation).name().length() < 1 ?
                    field.getName().toUpperCase() : ((SQLFloat) annotation).name();
            result += " FLOAT";
            result += getConstraints(((SQLFloat) annotation).constraints());
        }else {
            throw new UnsupportedSqlTypeException();
        }
        return result;
    }

    private String getConstraints(Constraints constraints) {
        String result = "";
        if(!constraints.allowNull())
            result += " NOT NULL";
        if(constraints.unique())
            result += " UNIQUE";
        if(constraints.primaryKey())
            result += " PRIMARY KEY";
        return result;
    }
}