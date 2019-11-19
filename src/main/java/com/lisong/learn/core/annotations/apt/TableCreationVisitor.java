package com.lisong.learn.core.annotations.apt;

import com.lisong.learn.core.annotations.db.*;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.SimpleElementVisitor8;

public class TableCreationVisitor extends SimpleElementVisitor8<Void,StringBuilder> {

    @Override
    public Void visitType(TypeElement e, StringBuilder stringBuilder) {
        DBTable tn = e.getAnnotation(DBTable.class);
        if(tn == null)
            return null;
        String name = tn.name().length() == 0 ? e.getSimpleName().toString().toUpperCase() : tn.name();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS " + name + "(");
        return null;
    }

    @Override
    public Void visitVariable(VariableElement e, StringBuilder stringBuilder) {
        if(e.getAnnotation(SQLString.class) != null) {
            SQLString annotation = e.getAnnotation(SQLString.class);
            stringBuilder.append("\n");
            stringBuilder.append(annotation.name().length() < 1 ?
                    e.getSimpleName().toString().toUpperCase() : annotation.name());
            stringBuilder.append(" VARCHAR(" + annotation.value() + ")");
            stringBuilder.append(getConstraints(annotation.constraints()));
            stringBuilder.append(",");
        }else if(e.getAnnotation(SQLInteger.class) != null) {
            stringBuilder.append("\n");
            SQLInteger annotation = e.getAnnotation(SQLInteger.class);
            stringBuilder.append(annotation.name().length() < 1 ?
                    e.getSimpleName().toString().toUpperCase() : annotation.name());
            stringBuilder.append(" INT");
            stringBuilder.append(getConstraints(annotation.constraints()));
            stringBuilder.append(",");
        }if(e.getAnnotation(SQLDate.class) != null) {
            stringBuilder.append("\n");
            SQLDate annotation = e.getAnnotation(SQLDate.class);
            stringBuilder.append(annotation.name().length() < 1 ?
                    e.getSimpleName().toString().toUpperCase() : annotation.name());
            stringBuilder.append(" DATE");
            stringBuilder.append(getConstraints(annotation.constraints()));
            stringBuilder.append(",");
        }if(e.getAnnotation(SQLTimestamp.class) != null) {
            stringBuilder.append("\n");
            SQLTimestamp annotation = e.getAnnotation(SQLTimestamp.class);
            stringBuilder.append(annotation.name().length() < 1 ?
                    e.getSimpleName().toString().toUpperCase() : annotation.name());
            stringBuilder.append(" TIMESTAMP");
            stringBuilder.append(getConstraints(annotation.constraints()));
            stringBuilder.append(",");
        }if(e.getAnnotation(SQLFloat.class) != null) {
            stringBuilder.append("\n");
            SQLFloat annotation = e.getAnnotation(SQLFloat.class);
            stringBuilder.append(annotation.name().length() < 1 ?
                    e.getSimpleName().toString().toUpperCase() : annotation.name());
            stringBuilder.append(" FLOAT");
            stringBuilder.append(getConstraints(annotation.constraints()));
            stringBuilder.append(",");
        }
        return null;
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